package info.yzf.database.model;

import info.yzf.util.Message;

import java.io.Serializable;
/**
 * 用户账户关系类
 * @author yzf
 *
 */
public class UserAccount extends BaseModel implements Serializable, Cloneable {
	
	private static final long serialVersionUID = -6062906866528967803L;
	
	public static final int Normal = 0;//普通操作人
	public static final int Super = 1;//超级操作人

	private User user;//用户
	private Account account;//账户
	private String password;//密码
	private int role;//操作人类型 0普通 1超级
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		sb.append("id:" + id + ", ");
		sb.append("用户:" + user + ", ");
		sb.append("帐户:" + account + ", ");
		sb.append("密码:" + password + ", ");
		sb.append(isSuper() ? "超级操作人" : "普通操作人");
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	public UserAccount clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (UserAccount)super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		UserAccount ca = (UserAccount)obj;
		return super.equals(obj) || 
				(user.equals(ca.user) && account.equals(ca.account));
	}
	
	public boolean isNormal() {
		return role == Normal ? true : false;
	}
	
	public boolean isSuper() {
		return role == Super ? true : false;
	}
	
	public UserAccount() {
		super();
	}
	
	public UserAccount(User user, Account account) throws Exception {
		super();
		this.user = user;
		this.account = account;
	}

	public UserAccount(User user, Account account,
			String password, int role) throws Exception {
		super();
		if (role != 0 && role != 1) throw new Exception(Message.UnknownType);
		this.user = user;
		this.account = account;
		this.password = password;
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}	
}
