package com.example.sahilgoyal.apnishuttle.serverrequesthandler.models;

public class RoutesModel{
	private String fare;
	private String updatedAt;
	private String name;
	private String createdAt;
	private int id;
	private Object deletedAt;
	private String order;

	public double getDrop_point_distance() {
		return drop_point_distance;
	}

	public void setDrop_point_distance(double drop_point_distance) {
		this.drop_point_distance = drop_point_distance;
	}

	private double drop_point_distance;

	public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	private String route_id;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	private String latitude;
	private String longitude;

	public void setFare(String fare){
		this.fare = fare;
	}

	public String getFare(){
		return fare;
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
			"RoutesModel{" + 
			"fare = '" + fare + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",deleted_at = '" + deletedAt + '\'' + 
			",order = '" + order + '\'' + 
			"}";
		}
}
