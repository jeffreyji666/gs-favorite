package com.ctrip.gs.favorite.service;

import java.net.InetSocketAddress;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctrip.gs.favorite.service.helper.FavoriteManagerFactory;
import com.ctrip.gs.favorite.thrift.FavoriteService;
import com.ctrip.gs.favorite.util.Config;
import com.twitter.finagle.builder.ServerBuilder;
import com.twitter.finagle.thrift.ThriftServerFramedCodec;

public class FavoriteServer {
	public static final Logger logger = LoggerFactory.getLogger(FavoriteServer.class);

	private static final Integer port = Config.getInt("favorite.server.port");

	public static void main(String[] args) {
		try {
			FavoriteManagerFactory.init();
			FavoriteService.FutureIface processor = new FavoriteServiceImpl();
			ServerBuilder.safeBuild(
					new FavoriteService.FinagledService(processor, new TBinaryProtocol.Factory()),
					ServerBuilder.get().name("FavoriteService").codec(ThriftServerFramedCodec.get())
							.bindTo(new InetSocketAddress(port)));
			logger.info("Start favorite service on port:" + port);

			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					FavoriteManagerFactory.close();
					logger.info("shutdown favorite service,port:" + port);
				}
			});
		} catch (Exception ex) {
			logger.error("Could not initialize service", ex);
		}
	}
}
