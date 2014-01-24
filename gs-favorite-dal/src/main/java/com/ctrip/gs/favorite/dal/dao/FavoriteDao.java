package com.ctrip.gs.favorite.dal.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.ctrip.gs.favorite.bean.FavoriteItem;

@Repository("favoriteDao")
@Scope("singleton")
public class FavoriteDao {
	@Autowired
	private FavoriteItemMapper favoriteItemMapper;

	public FavoriteItem getFavoriteItem(long uid, int favoriteType, int resourceId) {
		return favoriteItemMapper.getFavoriteItem(uid, favoriteType, resourceId);
	}

	public int insertFavoriteItem(FavoriteItem item) {
		favoriteItemMapper.insertFavoriteItem(item);
		return item.getRowId();
	}

	public int updateFavoriteItem(FavoriteItem item) {
		return favoriteItemMapper.updateFavoriteItem(item);
	}

	public int deleteFavoriteItem(long uid, int favoriteType, int resourceId) {
		return favoriteItemMapper.deleteFavoriteItem(uid, favoriteType, resourceId);
	}
	
	
	public int getFavoritesCount(long uid, int favoriteType) {
		return favoriteItemMapper.getFavoritesCount(uid, favoriteType);
	}
	
	public List<FavoriteItem> getFavorites(long uid, int favoriteType,
			int start, int count){
		return favoriteItemMapper.getFavorites(uid, favoriteType, start, count);
	}
	
	
	public List<FavoriteItem> getLatestFavorites(long uid, int count) {
		return favoriteItemMapper.getLatestFavorites(uid, count);
	}
}