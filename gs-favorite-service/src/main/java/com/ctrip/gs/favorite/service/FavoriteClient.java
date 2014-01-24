package com.ctrip.gs.favorite.service;

import java.net.InetSocketAddress;
import java.util.List;

import org.apache.thrift.protocol.TBinaryProtocol;

import com.ctrip.gs.favorite.thrift.Favorite;
import com.ctrip.gs.favorite.thrift.FavoriteService;
import com.ctrip.gs.favorite.thrift.Favorites;
import com.ctrip.gs.favorite.util.Config;
import com.twitter.finagle.Service;
import com.twitter.finagle.builder.ClientBuilder;
import com.twitter.finagle.thrift.ClientId;
import com.twitter.finagle.thrift.ThriftClientFramedCodecFactory;
import com.twitter.finagle.thrift.ThriftClientRequest;
import com.twitter.scrooge.Option;
import com.twitter.util.Await;
import com.twitter.util.Duration;
import com.twitter.util.FutureEventListener;

public class FavoriteClient {
	private static final Integer port = Config.getInt("favorite.server.port");

	// IMPORTANT: this determines how many rpc's are sent in at once.
	// If set to 1, you get no parallelism on for this client.
	static Service<ThriftClientRequest, byte[]> client = ClientBuilder.safeBuild(ClientBuilder.get().failFast(false)
			.hosts(new InetSocketAddress(port)).codec(new ThriftClientFramedCodecFactory(new ClientId("favoriteClient")))
			.hostConnectionLimit(100).retries(3).requestTimeout(Duration.fromMilliseconds(50000L)));
	static FavoriteService.FinagledClient favoriteClient = new FavoriteService.FinagledClient(client,
			new TBinaryProtocol.Factory(), "FavoriteService");

	private static void testAddFavoriteAsync(Favorite favorite) {
		// add a favorite
		favoriteClient.addFavorite(favorite).addEventListener(new FutureEventListener<Void>() {
			@Override
			public void onFailure(Throwable cause) {
				System.out.println("addFavorite. Failure: " + cause);
			}

			@Override
			public void onSuccess(Void value) {
				System.out.println("addFavorite. Success: ");
			}
		});
	}

	private static void testGetFavoritesAsync(Favorite favorite, int start, int count) {
		// get a favorite
		favoriteClient.getFavorites(favorite.getUid(), favorite.getFavoriteType(), start, count).addEventListener(
				new FutureEventListener<Favorites>() {
					@Override
					public void onFailure(Throwable cause) {
						System.out.println("getFavorites. Failure: " + cause);
					}

					@Override
					public void onSuccess(Favorites value) {
						System.out.println("getFavorites. Success, Total:" + value.getTotal());
					}
				});
	}

	private static void testDeleteFavoriteAsync(Favorite favorite) {
		// get a favorite
		favoriteClient.deleteFavorite(favorite.getUid(), favorite.getFavoriteType(), favorite.getResourceId()).addEventListener(
				new FutureEventListener<Void>() {
					@Override
					public void onFailure(Throwable cause) {
						System.out.println("deleteFavorite. Failure: " + cause);
					}

					@Override
					public void onSuccess(Void value) {
						System.out.println("deleteFavorite. Success.");
					}
				});
	}

	private static void testIsFavoriteAsync(Favorite favorite) {
		favoriteClient.isFavorite(favorite.getUid(), favorite.getFavoriteType(), favorite.getResourceId()).addEventListener(
				new FutureEventListener<Boolean>() {
					@Override
					public void onFailure(Throwable cause) {
						System.out.println("isFavorite. Failure: " + cause);
					}

					@Override
					public void onSuccess(Boolean value) {
						System.out.println("isFavorite. Success: " + value);
					}
				});
	}

	private static void testGetFavoritesCountAsync(Favorite favorite) {
		favoriteClient.getFavoritesCount(favorite.getUid(), favorite.getFavoriteType()).addEventListener(
				new FutureEventListener<Integer>() {
					@Override
					public void onFailure(Throwable cause) {
						System.out.println("getFavoritesCount. Failure: " + cause);
					}

					@Override
					public void onSuccess(Integer value) {
						System.out.println("getFavoritesCount. Success, count:" + value);
					}
				});
	}

	private static void testGetLatestFavoritesAsync(Favorite favorite, int count) {
		favoriteClient.getLatestFavorites(favorite.getUid(), count).addEventListener(new FutureEventListener<List<Favorite>>() {
			@Override
			public void onFailure(Throwable cause) {
				System.out.println("getLatestFavorites. Failure: " + cause);
			}

			@Override
			public void onSuccess(List<Favorite> value) {
				System.out.println("getLatestFavorites. Success, size:" + value.size());
			}
		});
	}

	// sync methods
	private static void testAddFavoriteSync(Favorite favorite) throws Exception {
		// add a favorite
		Await.result(favoriteClient.addFavorite(favorite));
		System.out.println("addFavoriete success.");
	}

	private static void testGetFavoritesSync(Favorite favorite, int start, int count) throws Exception {
		// get favorites
		Favorites favorites = Await.result(favoriteClient.getFavorites(favorite.getUid(), favorite.getFavoriteType(), start,
				count));
		System.out.println("getFavorites success, total:" + favorites.getTotal());
	}

	private static void testDeleteFavoriteSync(Favorite favorite) throws Exception {
		// delete a favorite
		Await.result(favoriteClient.deleteFavorite(favorite.getUid(), favorite.getFavoriteType(), favorite.getResourceId()));
		System.out.println("deleteFavorite success");
	}

	private static void testIsFavoriteSync(Favorite favorite) throws Exception {
		Boolean isFavorited = Await.result(favoriteClient.isFavorite(favorite.getUid(), favorite.getFavoriteType(),
				favorite.getResourceId()));
		System.out.println("isFavorite success,result:" + isFavorited);
	}

	private static void testGetFavoritesCountSync(Favorite favorite) throws Exception {
		Integer count = Await.result(favoriteClient.getFavoritesCount(favorite.getUid(), favorite.getFavoriteType()));
		System.out.println("GetFavoritesCount success,count:" + count.intValue());
	}

	private static void testGetLatestFavoritesSync(Favorite favorite, int count) throws Exception {
		List<Favorite> favorites = Await.result(favoriteClient.getLatestFavorites(favorite.getUid(), count));
		System.out.println("GetLatestFavorites success,size:" + favorites.size());
	}

	public static void main(String[] args) {
		long uid = 10001L;
		int favoriteType = 0;
		int resourceId = 1;
		int districtId = 2;
		int start = 0;
		int count = 10;

		Favorite favorite = new Favorite(uid, favoriteType, resourceId, Option.make(true, districtId));
		// Async test
		// testDeleteFavoriteAsync(favorite);
		// testAddFavoriteAsync(favorite);
		// testIsFavoriteAsync(favorite);
		// testGetFavoritesAsync(favorite, start, count);
		// testGetFavoritesCountAsync(favorite);
		// testGetLatestFavoritesAsync(favorite, count);

		// Sysnc test
		try {
			testDeleteFavoriteSync(favorite);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			testIsFavoriteSync(favorite);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			testAddFavoriteSync(favorite);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			testIsFavoriteSync(favorite);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			testGetFavoritesSync(favorite, start, count);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			testGetFavoritesCountSync(favorite);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			testGetLatestFavoritesSync(favorite, count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			testDeleteFavoriteSync(favorite);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
