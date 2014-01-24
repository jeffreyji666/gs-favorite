package com.ctrip.gs.favorite.service.helper;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ctrip.gs.favorite.biz.FavoriteManager;

public class FavoriteManagerFactory {

	private static FavoriteManager favoriteManager = null;
	private static boolean inited = false;
	private static AbstractApplicationContext ac;

	public static void init() {
		if (!inited) {
			ac = new ClassPathXmlApplicationContext("classpath*:applicationContext-service.xml");
			favoriteManager = (FavoriteManager) ac.getBean("favoriteManager");
			inited = true;
		}
	}
	public static FavoriteManager get() {
		return favoriteManager;
	}

	public static void close() {
		ac.close();
	}
}
