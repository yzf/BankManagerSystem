package info.yzf.util;

import info.yzf.database.model.Employee;
import info.yzf.database.model.Log;
import info.yzf.database.model.Summary;

import java.util.Map;
import java.util.Vector;

public class Report {
	public static int One = 0;
	public static int Dep = 1;
	public static int All = 3;
	private int type;
	private Employee employee;
	private Vector<Employee> subordinate;
	private Map<String, Integer> statistics;
	private Vector<Summary> summary;
	private Vector<Log> log;
	
	public Report(int type, Employee employee, Vector<Employee> subordinate,
			Map<String, Integer> statistics, Vector<Summary> summary,
			Vector<Log> log) {
		super();
		this.type = type;
		this.employee = employee;
		this.subordinate = subordinate;
		this.statistics = statistics;
		this.summary = summary;
		this.log = log;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Vector<Employee> getSubordinate() {
		return subordinate;
	}

	public void setSubordinate(Vector<Employee> subordinate) {
		this.subordinate = subordinate;
	}

	public Map<String, Integer> getStatistics() {
		return statistics;
	}

	public void setStatistics(Map<String, Integer> statistics) {
		this.statistics = statistics;
	}

	public Vector<Summary> getSummary() {
		return summary;
	}

	public void setSummary(Vector<Summary> summary) {
		this.summary = summary;
	}

	public Vector<Log> getLog() {
		return log;
	}

	public void setLog(Vector<Log> log) {
		this.log = log;
	}
}
