create database commdb /*!40100 DEFAULT CHARACTER SET utf8 */;

use commdb;

CREATE TABLE IF NOT EXISTS CollectionInfo (
      RowId int(11) NOT NULL auto_increment,
      UserId bigint(20) NOT NULL default '0',
      CollectionType int(11) NOT NULL default '0',
      ResourceId int(11) NOT NULL default '0',
      DistrictId int(11) NOT NULL default '0',
      RegionId int(11) NOT NULL default '0',
      Flag int(11) NOT NULL default '0',
      DataChange_LastTime timestamp NOT NULL default CURRENT_TIMESTAMP,
      DataChange_CreateTime timestamp NOT NULL default '0000-00-00 00:00:00',
      PRIMARY KEY  (RowId),
      KEY idx_UserId (UserId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
