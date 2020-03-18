package po;

import java.io.Serializable;

public class User implements Serializable {
	
	private String name;
	private String password;
	private Integer balance;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", balance=" + balance + "]";
	}





}
