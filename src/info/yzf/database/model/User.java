package info.yzf.database.model;

import java.io.Serializable;
/**
 * 用户
 * @author yzf
 *
 */
public class User extends BaseModel implements Serializable, Cloneable {

	private static final long serialVersionUID = -4960122028291619179L;
	
	protected String identity;	//凭证
	protected String name;		//用户名称
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		sb.append("id:" + id + ", ");
		sb.append("凭证:" + identity + ", ");
		sb.append("用户名称:" + name + ")");
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj) || 
				identity.equals(((User)obj).identity);
	}
	
	@Override
	public User clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (User)super.clone();
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String identity, String name) {
		super();
		this.identity = identity;
		this.name = name;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
