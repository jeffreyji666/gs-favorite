package com.ctrip.gs.favorite.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ctrip.gs.favorite.bean.FavoriteItem;

public interface FavoriteItemMapper {
	public FavoriteItem getFavoriteItem(@Param("uid") long uid,
										@Param("collectionType") int collectionType,
										@Param("resourceId") int resourceId);

	public int insertFavoriteItem(FavoriteItem item);

	public int updateFavoriteItem(FavoriteItem item);

	public int deleteFavoriteItem(	@Param("uid") long uid,
									@Param("collectionType") int collectionType,
									@Param("resourceId") int resourceId);
	
	public int getFavoritesCount(	@Param("uid") long uid,
									@Param("collectionType") int collectionType);
	
	public List<FavoriteItem> getFavorites(	@Param("uid") long uid,
											@Param("collectionType") int collectionType,
											@Param("start") int start,
											@Param("count") int count);

	public List<FavoriteItem> getLatestFavorites(	@Param("uid") long uid,
													@Param("count") int count);
}
