package com.ctrip.gs.favorite.service;

import java.util.List;

import com.ctrip.gs.favorite.bean.FavoriteItem;
import com.ctrip.gs.favorite.service.helper.FavoriteManagerFactory;
import com.ctrip.gs.favorite.service.helper.ProtocolTransfer;
import com.ctrip.gs.favorite.thrift.Favorite;
import com.ctrip.gs.favorite.thrift.Favorites;

class AddFavoriteHandler extends com.twitter.util.Function0<Void> {

	private Favorite favorite;

	public AddFavoriteHandler(Favorite favorite) {
		this.favorite = favorite;
	}

	@Override
	public Void apply() {
		FavoriteManagerFactory.get().addFavorite(ProtocolTransfer.transToPOJO(this.favorite));
		return null;
	}
}

class DeleteFavoriteHandler extends com.twitter.util.Function0<Void> {
	private long uid;
	private int favoriteType;
	private int resourceId;

	public DeleteFavoriteHandler(long uid, int favoriteType, int resourceId) {
		this.uid = uid;
		this.favoriteType = favoriteType;
		this.resourceId = resourceId;
	}

	@Override
	public Void apply() {
		FavoriteManagerFactory.get().deleteFavorite(this.uid, this.favoriteType, this.resourceId);
		return null;
	}
}

class IsFavoriteHandler extends com.twitter.util.Function0<Boolean> {
	private long uid;
	private int favoriteType;
	private int resourceId;

	public IsFavoriteHandler(long uid, int favoriteType, int resourceId) {
		this.uid = uid;
		this.favoriteType = favoriteType;
		this.resourceId = resourceId;
	}

	@Override
	public Boolean apply() {
		return FavoriteManagerFactory.get().isFavorite(this.uid, this.favoriteType, this.resourceId);
	}
}

class GetFavoritesHandler extends com.twitter.util.Function0<Favorites> {
	private long uid;
	private int favoriteType;
	private int start;
	private int count;

	public GetFavoritesHandler(long uid, int favoriteType, int start, int count) {
		this.uid = uid;
		this.favoriteType = favoriteType;
		this.start = start;
		this.count = count;
	}

	@Override
	public Favorites apply() {
		List<FavoriteItem> items = FavoriteManagerFactory.get().getFavorites(this.uid, this.favoriteType, this.start, this.count);
		List<Favorite> favorites = ProtocolTransfer.transFromPOJOs(items);
		int count = FavoriteManagerFactory.get().getFavoritesCount(this.uid, this.favoriteType);
		return new Favorites(count, favorites);
	}
}

class GetLatestFavoritesHandler extends com.twitter.util.Function0<List<Favorite>> {
	private long uid;
	private int count;

	public GetLatestFavoritesHandler(long uid, int count) {
		this.uid = uid;
		this.count = count;
	}

	@Override
	public List<Favorite> apply() {
		List<FavoriteItem> items = FavoriteManagerFactory.get().getLatestFavorites(this.uid, this.count);
		return ProtocolTransfer.transFromPOJOs(items);
	}
}

class GetFavoritesCountHandler extends com.twitter.util.Function0<Integer> {
	private long uid;
	private int favoriteType;

	public GetFavoritesCountHandler(long uid, int favoriteType) {
		this.uid = uid;
		this.favoriteType = favoriteType;
	}

	@Override
	public Integer apply() {
		return FavoriteManagerFactory.get().getFavoritesCount(this.uid, this.favoriteType);
	}
}