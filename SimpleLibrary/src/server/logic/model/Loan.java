package server.logic.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Loan {
	int userid;
	String isbn;
	String copynumber;
	Date date;
	String renewstate;
	
	public Loan(int userid,String isbn,String copynumber,Date date,String renewstate){
		this.userid=userid;
		this.isbn=isbn;
		this.copynumber=copynumber;
		this.date=date;
		this.renewstate=renewstate;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public String toString(){
		return "["+this.userid+","+this.isbn+","+this.copynumber+","+format1.format(this.date)+","+this.renewstate+"]";
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getCopynumber() {
		return copynumber;
	}
	public void setCopynumber(String copynumber) {
		this.copynumber = copynumber;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRenewstate() {
		return renewstate;
	}
	public void setRenewstate(String renewstate) {
		this.renewstate = renewstate;
	}

}
