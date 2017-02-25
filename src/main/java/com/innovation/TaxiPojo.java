package com.innovation;

public class TaxiPojo {

	String latitude;
	String longitude;
	String id;
	String status;
	Boolean isOnline;
	TaxiPojo(String id,boolean isOnline){
		this.id=id;
		this.isOnline=isOnline;
		
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public void setLatitudeLongi(String latitude,String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}
	@Override
	public String toString() {
		return "TaxiPojo [latitude=" + latitude + ", longitude=" + longitude + ", id=" + id + ", status=" + status
				+ ", isOnline=" + isOnline + "]";
	}
	
}
