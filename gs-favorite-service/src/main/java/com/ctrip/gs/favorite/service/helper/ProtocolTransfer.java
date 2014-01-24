package com.ctrip.gs.favorite.service.helper;

import java.util.ArrayList;
import java.util.List;

import com.ctrip.gs.favorite.bean.FavoriteItem;
import com.ctrip.gs.favorite.thrift.Favorite;
import com.twitter.scrooge.Option;

public class ProtocolTransfer {
	public  static FavoriteItem transToPOJO(Favorite favorite) {
		FavoriteItem item = new FavoriteItem();
		item.setCollectionType(favorite.getFavoriteType());
		item.setDistrictId(favorite.getDistrictId());
		item.setFlag(0);
		item.setRegionId(0);
		item.setResourceId(favorite.getResourceId());
		item.setUserId(favorite.getUid());
		return item;
	}
	
	public static Favorite transFromPOJO(FavoriteItem item) {
		return new Favorite(item.getUserId(), item.getCollectionType(),
							item.getResourceId(), Option.make(true, item.getDistrictId()));
	}
	
	public static List<Favorite> transFromPOJOs(List<FavoriteItem> items) {
		ArrayList<Favorite> favorites = new ArrayList<Favorite>();
		favorites.ensureCapacity(items.size());
		for(FavoriteItem item: items) {
			favorites.add(transFromPOJO(item));
		}
		return favorites;
	}
}