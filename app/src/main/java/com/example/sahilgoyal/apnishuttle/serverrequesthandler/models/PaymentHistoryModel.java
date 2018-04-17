package com.example.sahilgoyal.apnishuttle.serverrequesthandler.models;

public class PaymentHistoryModel{
	private String firstname;
	private String amount;
	private String payableType;
	private String phone;
	private String mihpayid;
	private String discount;
	private int id;
	private String netAmountDebit;
	private String payableId;
	private String email;
	private String txnid;
	private String status;
	private String mode;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPayableType() {
		return payableType;
	}

	public void setPayableType(String payableType) {
		this.payableType = payableType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMihpayid() {
		return mihpayid;
	}

	public void setMihpayid(String mihpayid) {
		this.mihpayid = mihpayid;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNetAmountDebit() {
		return netAmountDebit;
	}

	public void setNetAmountDebit(String netAmountDebit) {
		this.netAmountDebit = netAmountDebit;
	}

	public String getPayableId() {
		return payableId;
	}

	public void setPayableId(String payableId) {
		this.payableId = payableId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTxnid() {
		return txnid;
	}

	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getBank_ref_num() {
		return bank_ref_num;
	}

	public void setBank_ref_num(String bank_ref_num) {
		this.bank_ref_num = bank_ref_num;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	private String bank_ref_num;
	private String lastname;

}
