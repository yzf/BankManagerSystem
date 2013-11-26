package info.yzf.database.model;

import java.io.Serializable;
/**
 * 雇员类
 * @author yzf
 *
 */
public class Employee extends BaseModel implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 8323801475942797325L;
	
	public static final int Operator = 0;//前台操作人
	public static final int Manager = 1;//银行经理
	public static final int Conductor = 2;//银行业务总管
	public static final int Admin = 3;//系统管理员

	private String name;//员工名称
	private String username;//账号
	private String password;//密码
	private int type; //雇员类型
	private Department department;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		sb.append("id:" + id + ", ");
		sb.append("雇员名称:" + name + ", ");
		sb.append("帐户:" + username + ", ");
		sb.append(isOperator() ? "前台操作员" : (isManager() ? "银行经理" : (isConductor() ? "银行业务总管" : "系统管理员")));
		sb.append(")");
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		// 通过雇员账号来区分不同雇员
		return super.equals(obj) || username.equals(((Employee)obj).username);
	}
	
	@Override
	public Employee clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Employee)super.clone();
	}

	public boolean isOperator() {
		return type == Operator ? true : false;
	}
	
	public boolean isManager() {
		return type == Manager ? true : false;
	}
	
	public boolean isConductor() {
		return type == Conductor ? true : false;
	}
	
	public boolean isAdmin() {
		return type == Admin ? true : false;
	}
	
	public Employee() {
		super();
	}

	public Employee(String name, String username,
			String password, int type, Department department) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.type = type;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
