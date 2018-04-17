package com.example.sahilgoyal.apnishuttle.serverrequesthandler.models;

public class GetStopsModel{
	private String route_id;
	private String updatedAt;
	private String name;
	private String createdAt;
	private int id;
	private Object deletedAt;
	private String order;

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	private String longitude;
	private String latitude;
	public void setRoute_id(String route_id){
		this.route_id = route_id;
	}

	public String getRoute_id(){
		return route_id;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDeletedAt(Object deletedAt){
		this.deletedAt = deletedAt;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public void setOrder(String order){
		this.order = order;
	}

	public String getOrder(){
		return order;
	}

	@Override
 	public String toString(){
		return 
			"GetStopsModel{" + 
			"route_id = '" + route_id + '\'' +
			",updated_at = '" + updatedAt + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			",order = '" + order + '\'' + 
			"}";
		}
}
