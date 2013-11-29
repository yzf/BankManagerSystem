package info.yzf.database.model;

import info.yzf.util.Util;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 日志类
 * @author yzf
 *
 */
public class Log extends BaseModel implements Serializable, Comparable<Log> {

	private static final long serialVersionUID = -1438126000696131556L;

	public static String CreateAccount = "开户";
	public static String Deposit = "存款";
	public static String Withdrawal = "取款";
	public static String Query = "查询";
	public static String Transfer = "转账";
	public static String Password = "修改密码";
	public static String CloseAccount = "销户";
	public static String Other = "其他";
	
	private Timestamp time;//时间
	private Employee employee;//雇员
	private UserAccount top;//攻方
	private UserAccount bottom;//受方
	private String operation;//详细操作
	private String type;
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("时间:" + getFormatTime() + "\t");
		sb.append("员工:" + employee + "\t");
		sb.append("客户:" + top.getUser() + "\t");
		sb.append("帐户:" + top.getAccount() + "\t");
		sb.append(bottom == null ? "" : "客户:" + bottom.getUser() + "\t");
		sb.append(bottom == null ? "" : "帐户:" + bottom.getAccount() + "\t");
		sb.append("操作:" + operation);
		return sb.toString();
	}
	
	@Override
	public int compareTo(Log o) {
		// TODO Auto-generated method stub
		return time.compareTo(o.time);
	}
	
	public String getFormatTime() {
		return Util.formatTime(time);
	}
	
	public Log() {
		super();
	}

	public Log(Timestamp time, Employee employee, UserAccount top,
			UserAccount bottom, String operation, String type) throws Exception {
		super();
		this.time = time;
		this.employee = employee.clone();
		this.top = top.clone();
		this.bottom = (bottom != null ? bottom.clone() : null);
		this.operation = operation;
		this.type = type;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public UserAccount getTop() {
		return top;
	}

	public void setTop(UserAccount top) {
		this.top = top;
	}

	public UserAccount getBottom() {
		return bottom;
	}

	public void setBottom(UserAccount bottom) {
		this.bottom = bottom;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
