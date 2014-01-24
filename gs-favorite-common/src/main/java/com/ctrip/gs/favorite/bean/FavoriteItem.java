package com.ctrip.gs.favorite.bean;

import java.io.Serializable;
import java.util.Date;

public class FavoriteItem implements Serializable {
	private static final long serialVersionUID = 11L;
	private Integer rowId;
	private Long userId;
	private Integer collectionType;
	private Integer resourceId;
	private Integer districtId;
	private Integer regionId;
	private Integer flag;
	private Date dataChange_LastTime;
	private Date dataChange_CreateTime;

	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(Integer collectionType) {
		this.collectionType = collectionType;
	}
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Date getDataChange_LastTime() {
		return dataChange_LastTime;
	}
	public void setDataChange_LastTime(Date dataChange_LastTime) {
		this.dataChange_LastTime = dataChange_LastTime;
	}
	public Date getDataChange_CreateTime() {
		return dataChange_CreateTime;
	}
	public void setDataChange_CreateTime(Date dataChange_CreateTime) {
		this.dataChange_CreateTime = dataChange_CreateTime;
	}
}
