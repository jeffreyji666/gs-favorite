package com.ctrip.gs.favorite.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ctrip.gs.favorite.thrift.Favorite;
import com.ctrip.gs.favorite.thrift.FavoriteService;
import com.ctrip.gs.favorite.thrift.Favorites;
import com.twitter.finagle.Service;
import com.twitter.finagle.builder.ClientBuilder;
import com.twitter.finagle.thrift.ClientId;
import com.twitter.finagle.thrift.ThriftClientFramedCodecFactory;
import com.twitter.finagle.thrift.ThriftClientRequest;
import com.twitter.scrooge.Option;
import com.twitter.util.Await;

public class FavoriteServiceTest extends AbstractSpringTest {
	private static final int port = 33123;
	static FavoriteService.FinagledClient favoriteClient = null;
	static Process favoriteServerProcess = null;

	private FavoriteService.FinagledClient createClient(int port) {
		Service<ThriftClientRequest, byte[]> client = ClientBuilder.safeBuild(ClientBuilder.get()
				.hosts(new InetSocketAddress(port))
				.codec(new ThriftClientFramedCodecFactory(new ClientId("favoriteClient"))).hostConnectionLimit(100));
		return new FavoriteService.FinagledClient(client,
				new TBinaryProtocol.Factory(), "FavoriteService");
	}
	
	private void destroyClient(FavoriteService.FinagledClient c) {
		c = null;
	}

	private void createServer(String[] cmd) throws IOException {
		Runtime.getRuntime().exec(cmd);
	}
	
	private void destroyServer(String[] cmd) throws InterruptedException, IOException {
		Runtime.getRuntime().exec(cmd);
	}


	@Before
	public void setUp() throws SQLException, InterruptedException, IOException {
		//start server
		String[] cmd = { "/bin/sh", "-c", "cd /Users/yancl/work/java/gs-favorite/gs-favorite-service/target/gs-favorite-service; sh startup.sh start" };
		createServer(cmd);
		Thread.sleep(3 * 1000);

		favoriteClient = createClient(port);
	}

	@After
	public void tearDown() throws SQLException, InterruptedException, IOException {
		destroyClient(favoriteClient);

		//destroy server
		String[] cmd = { "/bin/sh", "-c", "cd /Users/yancl/work/java/gs-favorite/gs-favorite-service/target/gs-favorite-service; sh startup.sh stop" };
		destroyServer(cmd);
	}

	@Test
	public void testFavoriteService() throws Exception{
		long uid = 10001L;
        int favoriteType = 0;
        int resourceId = 1;
        int districtId = 2;
        int start = 0;
        int count = 10;

		Favorite favorite = new Favorite(uid, favoriteType, resourceId, Option.make(true, districtId));

		//add a favorite
		Await.result(favoriteClient.addFavorite(favorite));

		//test favorites collection
		Favorites favorites = Await.result(favoriteClient.getFavorites(uid, favoriteType, start, count));
		Assert.assertEquals(1, favorites.getTotal());
		Assert.assertEquals(1, favorites.getItems().size());
		Assert.assertEquals(uid, favorites.getItems().get(0).getUid());

		//test favorites count
		Integer favoriteCount = Await.result(favoriteClient.getFavoritesCount(uid, favoriteType));
		Assert.assertEquals(1, favoriteCount.intValue());

		//test favorite
		Boolean isFavorite = Await.result(favoriteClient.isFavorite(uid, favoriteType, resourceId));
		Assert.assertEquals(isFavorite.booleanValue(), true);

		//test delete a favorite
		Await.result(favoriteClient.deleteFavorite(uid, favoriteType, resourceId));

		//test favorite
		isFavorite = Await.result(favoriteClient.isFavorite(uid, favoriteType, resourceId));
		Assert.assertEquals(isFavorite.booleanValue(), false);
    }
}