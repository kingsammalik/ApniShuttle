package com.example.sahilgoyal.apnishuttle.serverrequesthandler.models;

public class MyRidesModel{
	private String drop;
	private String driverName;
	private String driverNumber;
	private String fare;
	private String pickupDate;
	private Object busSheduleId;
	private String userId;
	private String routeName;
	private String pickup;
	private int id;
	private String pickupTime;
	private String busNumber;

	public PaymentHistoryModel getPayments() {
		return payments;
	}

	public void setPayments(PaymentHistoryModel payments) {
		this.payments = payments;
	}

	private PaymentHistoryModel payments;


	public void setDrop(String drop){
		this.drop = drop;
	}

	public String getDrop(){
		return drop;
	}

	public void setDriverName(String driverName){
		this.driverName = driverName;
	}

	public String getDriverName(){
		return driverName;
	}

	public void setDriverNumber(String driverNumber){
		this.driverNumber = driverNumber;
	}

	public String getDriverNumber(){
		return driverNumber;
	}

	public void setFare(String fare){
		this.fare = fare;
	}

	public String getFare(){
		return fare;
	}

	public void setPickupDate(String pickupDate){
		this.pickupDate = pickupDate;
	}

	public String getPickupDate(){
		return pickupDate;
	}

	public void setBusSheduleId(Object busSheduleId){
		this.busSheduleId = busSheduleId;
	}

	public Object getBusSheduleId(){
		return busSheduleId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setRouteName(String routeName){
		this.routeName = routeName;
	}

	public String getRouteName(){
		return routeName;
	}

	public void setPickup(String pickup){
		this.pickup = pickup;
	}

	public String getPickup(){
		return pickup;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPickupTime(String pickupTime){
		this.pickupTime = pickupTime;
	}

	public String getPickupTime(){
		return pickupTime;
	}

	public void setBusNumber(String busNumber){
		this.busNumber = busNumber;
	}

	public String getBusNumber(){
		return busNumber;
	}

	@Override
 	public String toString(){
		return 
			"MyRidesModel{" + 
			"drop = '" + drop + '\'' + 
			",driver_name = '" + driverName + '\'' + 
			",driver_number = '" + driverNumber + '\'' + 
			",fare = '" + fare + '\'' + 
			",pickup_date = '" + pickupDate + '\'' + 
			",bus_shedule_id = '" + busSheduleId + '\'' + 
			",user_id = '" + userId + '\'' + 
			",route_name = '" + routeName + '\'' + 
			",pickup = '" + pickup + '\'' + 
			",id = '" + id + '\'' + 
			",pickup_time = '" + pickupTime + '\'' + 
			",bus_number = '" + busNumber + '\'' + 
			"}";
		}
}
