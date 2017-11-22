package com.tfs.irp.leaveapply.entity;


public class IrpLeaveapplystatus implements Comparable<IrpLeaveapplystatus>{
private String username;
private int worktoataltime;	
private int orderby;
public int getOrderby() {
	return orderby;
}
public void setOrderby(int orderby) {
	this.orderby = orderby;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
private int normal;
private	int important;
private	int urgent;
public int getNormal() {
	return normal;
}
public void setNormal(int normal) {
	this.normal = normal;
}
public int getImportant() {
	return important;
}
public void setImportant(int important) {
	this.important = important;
}
public int getUrgent() {
	return urgent;
}
public void setUrgent(int urgent) {
	this.urgent = urgent;
}
@Override
public int compareTo(IrpLeaveapplystatus o) {
	// 10正常   30紧急  20重要
	int res =-1;
	if(this.getOrderby()==10){
		if(this.getNormal()>o.getNormal()){
			res=-1;
		}else{
			res=1;
		}
	}else if(this.getOrderby()==30){
		if(this.getUrgent()>o.getUrgent()){
			res= -1;
		}else{
			res= 1;
		}
	}else if(this.getOrderby()==20){
		if(this.getImportant()>o.getImportant()){
			res= -1;
		}else{
			res= 1;
		}
	}else if(this.getOrderby()==40){
		if(this.getWorktoataltime()>o.getWorktoataltime()){
			res= -1;
		}else{
			res= 1;
		}
		
	}
	return res;		
}
public int getWorktoataltime() {
	return worktoataltime;
}
public void setWorktoataltime(int worktoataltime) {
	this.worktoataltime = worktoataltime;
}

}
