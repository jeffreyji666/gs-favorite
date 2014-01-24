package com.ctrip.gs.favorite.dal.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ctrip.gs.favorite.bean.FavoriteItem;
import com.ctrip.gs.favorite.dal.AbstractSpringTest;

public class FavoriteDaoTest extends AbstractSpringTest {
	@Resource
	private FavoriteDao favoriteDao;

	@Before
	public void setUp() throws SQLException {
	}

	@After
	public void tearDown() throws SQLException {
	}

	@Test
	public void testFavorite() {
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

		int rowId = favoriteDao.insertFavoriteItem(item);
		item = favoriteDao.getFavoriteItem(uid, collectionType, resourceId);
		Assert.assertEquals(rowId, item.getRowId().intValue());
		Assert.assertEquals(uid, item.getUserId().longValue());
		Assert.assertEquals(collectionType, item.getCollectionType().intValue());
		Assert.assertEquals(resourceId, item.getResourceId().intValue());
		Assert.assertEquals(districtId, item.getDistrictId().intValue());
		Assert.assertEquals(regionId, item.getRegionId().intValue());
		Assert.assertEquals(flag, item.getFlag().intValue());

		int favoritesCount = favoriteDao.getFavoritesCount(uid, collectionType);
		Assert.assertEquals(1, favoritesCount);

		int start = 0;
		int count = 2;
		List<FavoriteItem> items = favoriteDao.getFavorites(uid, collectionType, start, count);
		Assert.assertEquals(1, items.size());

		List<FavoriteItem> lastestItems = favoriteDao.getLatestFavorites(uid, count);
		Assert.assertEquals(1, lastestItems.size());

		collectionType = 100;
		item.setCollectionType(collectionType);
		favoriteDao.updateFavoriteItem(item);
		item = favoriteDao.getFavoriteItem(uid, collectionType, resourceId);
		Assert.assertEquals(rowId, item.getRowId().intValue());
		Assert.assertEquals(collectionType, item.getCollectionType().intValue());

		favoriteDao.deleteFavoriteItem(uid, collectionType, resourceId);
		item = favoriteDao.getFavoriteItem(uid, collectionType, resourceId);
		Assert.assertNull(item);
	}
}
