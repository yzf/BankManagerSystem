package info.yzf.database.model;

import java.io.Serializable;
/**
 * 企业账户关系类
 * @author yzf
 *
 */
public class EnterpriseAccount extends BaseModel implements Serializable {

	private static final long serialVersionUID = -580259808355671780L;
	
	private User enterprise;//企业
	private Account account;//帐户
	private String username;//账号
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		sb.append("id:" + id + ", ");
		sb.append("企业:" + enterprise + ", ");
		sb.append("帐户:" + account + ", ");
		sb.append("账号:" + username + ")");
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		EnterpriseAccount e = (EnterpriseAccount) obj;
		return (enterprise.equals(e.enterprise) && account.equals(e.account));
	}

	public EnterpriseAccount() {
		super();
	}

	public EnterpriseAccount(User enterprise, Account account, String username) {
		super();
		this.enterprise = enterprise;
		this.account = account;
		this.username = username;
	}

	public User getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(User enterprise) {
		this.enterprise = enterprise;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
