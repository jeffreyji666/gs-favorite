package com.ctrip.gs.favorite.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ctrip.gs.favorite.bean.FavoriteItem;
import com.ctrip.gs.favorite.dal.dao.FavoriteDao;

@Service
@Scope("singleton")
public class FavoriteManager {
	@Autowired
	private FavoriteDao favoriteDao;

	public int addFavorite(FavoriteItem favorite) {
		return favoriteDao.insertFavoriteItem(favorite);
	}

	public FavoriteItem getFavorite(long uid, int favoriteType, int resourceId) {
		return favoriteDao.getFavoriteItem(uid, favoriteType, resourceId);
	}

	public int updateFavorite(FavoriteItem item) {
		return favoriteDao.updateFavoriteItem(item);
	}

	public int deleteFavorite(long uid, int favoriteType, int resourceId) {
		return favoriteDao.deleteFavoriteItem(uid, favoriteType, resourceId);
	}

	public Boolean isFavorite(long uid, int favoriteType,
			int resourceId) {
		return favoriteDao.getFavoriteItem(uid, favoriteType, resourceId) != null;
	}

	public List<FavoriteItem> getFavorites(long uid, int favoriteType,
			int start, int count) {
		return favoriteDao.getFavorites(uid, favoriteType, start, count);
	}

	public List<FavoriteItem> getLatestFavorites(long uid, int count) {
		return favoriteDao.getLatestFavorites(uid, count);
	}

	public Integer getFavoritesCount(long uid, int favoriteType) {
		return favoriteDao.getFavoritesCount(uid, favoriteType);
	}
}