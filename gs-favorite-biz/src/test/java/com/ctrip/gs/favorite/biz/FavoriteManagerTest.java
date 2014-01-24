package com.ctrip.gs.favorite.biz;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ctrip.gs.favorite.bean.FavoriteItem;

public class FavoriteManagerTest extends AbstractSpringTest {
	@Resource
	@Autowired
	private FavoriteManager favoriteManager;

	@Before
	public void setUp() throws SQLException {
	}
	
	@After
	public void tearDown() throws SQLException {
	}

	@Test
	public void testFavoriteManager() {
		long uid = 10001L;
		int collectionType = 0;
		int resourceId = 1;
		int districtId = 2;
		int regionId = 3;
		int flag = 4;
		FavoriteItem item = new FavoriteItem();
		item.setUserId(uid);
		item.setCollectionType(collectionType);
		item.setResourceId(resourceId);
		item.setDistrictId(districtId);
		item.setRegionId(regionId);
		item.setFlag(flag);
		item.setDataChange_CreateTime(new Date());
		
		int rowId = favoriteManager.addFavorite(item);
		item = favoriteManager.getFavorite(uid, collectionType, resourceId);
		Assert.assertEquals(rowId, item.getRowId().intValue());
		Assert.assertEquals(uid, item.getUserId().longValue());
		Assert.assertEquals(collectionType, item.getCollectionType().intValue());
		Assert.assertEquals(resourceId, item.getResourceId().intValue());
		Assert.assertEquals(districtId, item.getDistrictId().intValue());
		Assert.assertEquals(regionId, item.getRegionId().intValue());
		Assert.assertEquals(flag, item.getFlag().intValue());
		
		int favoritesCount = favoriteManager.getFavoritesCount(uid, collectionType);
		Assert.assertEquals(1, favoritesCount);
		
		int start = 0;
		int count = 2;
		List<FavoriteItem> items = favoriteManager.getFavorites(uid, collectionType, start, count);
		Assert.assertEquals(1, items.size());
		
		List<FavoriteItem> lastestItems = favoriteManager.getLatestFavorites(uid, count);
		Assert.assertEquals(1, lastestItems.size());
		
		collectionType = 100;
		item.setCollectionType(collectionType);
		favoriteManager.updateFavorite(item);
		item = favoriteManager.getFavorite(uid, collectionType, resourceId);
		Assert.assertEquals(rowId, item.getRowId().intValue());
		Assert.assertEquals(collectionType, item.getCollectionType().intValue());
		
		favoriteManager.deleteFavorite(uid, collectionType, regionId);
		item = favoriteManager.getFavorite(uid, collectionType, regionId);
		Assert.assertNull(item);
	}
}