package info.yzf.database.model;

import info.yzf.util.Message;

import java.io.Serializable;

/**
 * 账户类
 * @author yzf
 *
 */
public class Account extends BaseModel implements Serializable, Cloneable {
	
	private static final long serialVersionUID = -7538577403509380089L;
	
	public static final int TimeDeposit = 0;//活期
	public static final int FixedDeposit = 1;//定期
	public static final int Normal = 0;//普通帐户
	public static final int VIP = 1;//VIP帐户
	public static final int Enterprise = 2;//企业账户
	
	private String username;
	private int aType;//账户类型  0活期 1定期
	private int uType;//用户类型  0普通 1VIP 2企业
	private double balance;//金额
	private boolean freezed;//是否被冻结

	@Override
	public String toString() {
		StringBuilder sb = new  StringBuilder("(");
		sb.append("账号:" + username + ", ");
		sb.append("余额:" + balance + ", ");
		sb.append((isFixedDeposit() ? "定期" : "活期") + ", ");
		sb.append((isNormal() ? "普通帐户, " : (isVIP() ? "VIP帐户, " : "企业帐户, ")));
		sb.append(isFreezed() ? "被冻结账户" : "正常帐户");
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj) || username.equals(((Account)obj).username);
	}

	@Override
	public Account clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Account)super.clone();
	}

	public boolean isTimeDeposit() {
		return aType == TimeDeposit ? true : false;
	}
	
	public boolean isFixedDeposit() {
		return aType == FixedDeposit ? true : false;
	}
	
	public boolean isNormal() {
		return uType == Normal ? true : false;
	}
	
	public boolean isVIP() {
		return uType == VIP ? true : false;
	}

	public boolean isEnterprise() {
		return uType == Enterprise ? true : false;
	}
	
	public Account() {
		super();
	}

	public Account(String username, int aType, int uType, double balance) throws Exception {
		super();
		if (aType != 0 && aType != 1) throw new Exception(Message.UnknownType);
		if (uType != 0 && uType != 1 && uType != 2) throw new Exception(Message.UnknownType);
		this.username = username;
		this.aType = aType;
		this.uType = uType;
		this.balance = balance;
		this.freezed = false;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public int getaType() {
		return aType;
	}

	public void setaType(int aType) {
		this.aType = aType;
	}

	public int getuType() {
		return uType;
	}

	public void setuType(int uType) {
		this.uType = uType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isFreezed() {
		return freezed;
	}

	public void setFreezed(boolean freezed) {
		this.freezed = freezed;
	}

}
