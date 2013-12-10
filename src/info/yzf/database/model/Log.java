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
	private int empId;//员工id
	private String empName;//员工名
	private String topName;//攻方的名称
	private String topUsername;//攻方的帐号
	private String bottomName;//受方的名称
	private String bottomUsername;//受方的账号
	private String operation;//详细操作
	private String type;//操作类型
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("时间:" + getFormatTime() + "\t");
		sb.append("员工:" + empName + "\t");
		sb.append("客户:" + topName + "\t");
		sb.append("帐户:" + topUsername + "\t");
		sb.append(bottomName == "" ? "" : "客户:" + bottomName + "\t");
		sb.append(bottomUsername == "" ? "" : "帐户:" + bottomUsername + "\t");
		sb.append("操作:" + operation + "\t");
		sb.append("操作类型:" + type);
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

	public Log(Timestamp time, int empId, String empName, String topName,
			String topUsername, String bottomName, String bottomUsername,
			String operation, String type) {
		super();
		this.time = time;
		this.empId = empId;
		this.empName = empName;
		this.topName = topName;
		this.topUsername = topUsername;
		this.bottomName = bottomName;
		this.bottomUsername = bottomUsername;
		this.operation = operation;
		this.type = type;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getTopName() {
		return topName;
	}

	public void setTopName(String topName) {
		this.topName = topName;
	}

	public String getTopUsername() {
		return topUsername;
	}

	public void setTopUsername(String topUsername) {
		this.topUsername = topUsername;
	}

	public String getBottomName() {
		return bottomName;
	}

	public void setBottomName(String bottomName) {
		this.bottomName = bottomName;
	}

	public String getBottomUsername() {
		return bottomUsername;
	}

	public void setBottomUsername(String bottomUsername) {
		this.bottomUsername = bottomUsername;
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
