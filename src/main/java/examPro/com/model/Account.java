package examPro.com.model;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Account implements Serializable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int accountId; // primary key
	  private double balance;  // not null
	  private Date created_on;
	  private int statusId ;
	  private int typeId;
	public Account () {
		super();
	}
	
	public Account(int accountId, double balance, Date date,int statusId, int typeId){
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.statusId = statusId;
		this.typeId = typeId;
		/*
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY:MM:dd");
        String date = sdf.format(cal.getTime());
        //System.out.println( date);
         * 
         */
		this.created_on = date; // this might be the USA time where the server location is 
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + statusId;
		result = prime * result + typeId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (statusId != other.statusId)
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", created_on=" + created_on + ", statusId="
				+ statusId + ", typeId=" + typeId + "]";
	}
	
	  
	  
	
}
