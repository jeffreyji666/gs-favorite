package com.ctrip.gs.favorite.service;

import java.util.List;
import java.util.concurrent.Executors;

import com.ctrip.gs.favorite.thrift.Favorite;
import com.ctrip.gs.favorite.thrift.FavoriteService.FutureIface;
import com.ctrip.gs.favorite.thrift.Favorites;
import com.twitter.util.ExecutorServiceFuturePool;
import com.twitter.util.Function0;
import com.twitter.util.Future;
import com.twitter.util.FuturePool;

public class FavoriteServiceImpl implements FutureIface {
	FuturePool futurePool = new ExecutorServiceFuturePool(Executors.newFixedThreadPool(32));

	@Override
	public Future<Void> addFavorite(Favorite favorite) {
        Function0<Void> addFavoriteHandler = new AddFavoriteHandler(favorite);
        return futurePool.apply(addFavoriteHandler);
	}

	@Override
	public Future<Void> deleteFavorite(long uid, int favoriteType,
			int resourceId) {
        Function0<Void> deleteFavoriteHandler = new DeleteFavoriteHandler(uid, favoriteType, resourceId);
        return futurePool.apply(deleteFavoriteHandler);
	}

	@Override
	public Future<Boolean> isFavorite(long uid, int favoriteType, int resourceId) {
        Function0<Boolean> isFavoriteHandler = new IsFavoriteHandler(uid, favoriteType, resourceId);
        return futurePool.apply(isFavoriteHandler);
	}

	@Override
	public Future<Favorites> getFavorites(long uid, int favoriteType,
			int start, int count) {
        Function0<Favorites> getFavoritesHandler = new GetFavoritesHandler(uid, favoriteType, start, count);
        return futurePool.apply(getFavoritesHandler);
	}

	@Override
	public Future<List<Favorite>> getLatestFavorites(long uid, int count) {
        Function0<List<Favorite>> getLatestFavoritesHandler = new GetLatestFavoritesHandler(uid, count);
        return futurePool.apply(getLatestFavoritesHandler);
	}

	@Override
	public Future<Integer> getFavoritesCount(long uid, int favoriteType) {
        Function0<Integer> getFavoritesCountHandler = new GetFavoritesCountHandler(uid, favoriteType);
        return futurePool.apply(getFavoritesCountHandler);
	}
}
