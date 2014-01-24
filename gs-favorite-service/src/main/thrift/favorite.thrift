namespace java com.ctrip.gs.favorite.thrift

exception ExceptionBase {
1: i32 what,
2: string why
}

struct Favorite {
1: i64 uid,
2: i32 favoriteType,
3: i32 resourceId,
4: optional i32 districtId
}

struct Favorites {
1: i32 total,
2: list<Favorite> items
}

service FavoriteService{

        /**
          add a favorite,failed exception raise
        */
        void AddFavorite(1:Favorite favorite) throws (1:ExceptionBase eb)

        /**
          del a favorite,failed exception raise
        */
        void DeleteFavorite(1:i64 uid, 2:i32 favoriteType, 3:i32 resourceId) throws (1:ExceptionBase eb)

        /**
          is favorited by uid,failed exception raise
        */
        bool IsFavorite(1:i64 uid, 2:i32 favoriteType, 3:i32 resourceId) throws (1:ExceptionBase eb)

        /**
          get favorites of uid by favoriteType,failed exception raise
        */
        Favorites GetFavorites(1:i64 uid, 2:i32 favoriteType, 3:i32 start, 4:i32 count) throws (1:ExceptionBase eb)

        /**
          get latest favorites of uid, failed exception raise
        */
        list<Favorite> GetLatestFavorites(1:i64 uid, 2:i32 count) throws (1:ExceptionBase eb)


        /**
          get favorites count of uid, failed exception raise
        */
        i32 GetFavoritesCount(1:i64 uid, 2:i32 favoriteType) throws (1:ExceptionBase eb)
}
