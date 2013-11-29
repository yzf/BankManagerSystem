package info.yzf.database.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Summary extends BaseModel implements Serializable {

	private static final long serialVersionUID = -1352309054286426391L;
	
	private Timestamp time;
	private Employee employee;
	private String content;
	
	public Summary(Timestamp time, Employee employee, String content) throws CloneNotSupportedException {
		super();
		this.time = time;
		this.employee = employee.clone();
		this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
