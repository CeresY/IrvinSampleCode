package corejava.model;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String pwd;
	private Date date;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Account() {
		super();
	}
	public Account(String username, String pwd) {
		super();
		this.username = username;
		this.pwd = pwd;
	}
	
	public Account(String username, String pwd, Date date) {
		super();
		this.username = username;
		this.pwd = pwd;
		this.date = date;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	@Override
	public String toString() {
		return "Account:{" +
                "username=" + username +
                ", pwd=" + pwd +
                ", date="+date+"}";
	}
}
