package com.example.sahilgoyal.apnishuttle.serverrequesthandler.models;

public class BookOrderModel{
	private int amount;
	private String firstname;
	private String phone;
	private String productinfo;
	private String email;
	private String txnid;

	public void setAmount(int amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}

	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public String getFirstname(){
		return firstname;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setProductinfo(String productinfo){
		this.productinfo = productinfo;
	}

	public String getProductinfo(){
		return productinfo;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setTxnid(String txnid){
		this.txnid = txnid;
	}

	public String getTxnid(){
		return txnid;
	}

	@Override
 	public String toString(){
		return 
			"BookOrderModel{" + 
			"amount = '" + amount + '\'' + 
			",firstname = '" + firstname + '\'' + 
			",phone = '" + phone + '\'' + 
			",productinfo = '" + productinfo + '\'' + 
			",email = '" + email + '\'' + 
			",txnid = '" + txnid + '\'' + 
			"}";
		}
}
