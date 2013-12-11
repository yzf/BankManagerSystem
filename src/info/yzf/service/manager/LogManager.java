package info.yzf.service.manager;

import info.yzf.database.dao.IDepartmentDao;
import info.yzf.database.dao.IEmployeeDao;
import info.yzf.database.dao.ILogDao;
import info.yzf.database.dao.ISummaryDao;
import info.yzf.database.dao.IUserAccountDao;
import info.yzf.database.daoImpl.DepartmentDao;
import info.yzf.database.daoImpl.EmployeeDao;
import info.yzf.database.daoImpl.LogDao;
import info.yzf.database.daoImpl.SummaryDao;
import info.yzf.database.daoImpl.UserAccountDao;
import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;
import info.yzf.database.model.Log;
import info.yzf.database.model.Summary;
import info.yzf.database.model.UserAccount;
import info.yzf.util.Message;
import info.yzf.util.Report;
import info.yzf.util.Util;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Vector;

/**
 * 负责写日志的管理器，单例模式
 * @author yzf
 *
 */
public class LogManager {
	
	private ILogDao logDao;
	private IUserAccountDao customerAccountDao;
	private IDepartmentDao departentDao;
	private ISummaryDao summaryDao;
	private IEmployeeDao employeeDao;
	
	private LogManager() {
		logDao = new LogDao();
		customerAccountDao = new UserAccountDao();
		departentDao = new DepartmentDao();
		summaryDao = new SummaryDao();
		employeeDao = new EmployeeDao();
	}
	
	private static class InstanceHolder {
		private static LogManager instance = new LogManager();
	}
	
	public static LogManager getInstance() {
		return InstanceHolder.instance;
	}	
	/**
	 * 记录日志
	 */
	public Log recordOperation(int empId, String empName, String topName, String topUsername, 
			String bottomName, String bottomUsername, String operation, String type) throws Exception {
		Log log = new Log(new Timestamp(System.currentTimeMillis()), empId, empName, topName, 
						topUsername, bottomName, bottomUsername, operation, type);
		log = logDao.add(log);
		return log;
	}
	/**
	 * 查询某帐户一段时间的所有操作
	 * @param account
	 * @param begin
	 * @param end
	 * @return
	 */
	public Vector<Log> query(String identity, String username, String password, String begin, String end) throws Exception {
		Timestamp beginTime = Util.getToday(begin);
		Timestamp endTime = Util.getDayAfterDays(end, 1);
		UserAccount ca = customerAccountDao.get(identity, username, password, true);
		if (ca == null) {
			throw new Exception(Message.Mismatching);
		}
		return logDao.get(ca.getAccount(), beginTime, endTime);
	}
	
	public Report getPersonalReport(String username, Timestamp begin, Timestamp end) {
		Employee e = employeeDao.get(username);
		Vector<Summary> summary = summaryDao.get(e, begin, end);
		Vector<Log> log = logDao.get(e, begin, end);
		Map<String, Integer> statistics = Util.countLogType(log);
		return new Report(Report.One, e, null, statistics, summary, log);
	}
	
	public Report getDepReport(Employee e, int depId, Timestamp begin, Timestamp end) throws Exception {
		Department d = departentDao.get(depId);
		if (d == null) {
			throw new Exception(Message.DepNotExist);
		}
		Employee manager = departentDao.getManager(d);
		Vector<Employee> subordinate = employeeDao.getSubordinates(d);
		Vector<Summary> summary = summaryDao.get(d, begin, end);
		Vector<Log> log = logDao.get(d, begin, end);
		Map<String, Integer> statistics = Util.countLogType(log);
		return new Report(Report.Dep, manager, subordinate, statistics, summary, log);
	}
	
	public Report getBankReport(Employee e, Timestamp begin, Timestamp end) throws Exception {
		if (e.getType() < Employee.Conductor) {
			throw new Exception(Message.Unauthorized);
		}
		Vector<Employee> subordinate = employeeDao.gets();
		Vector<Summary> summary = summaryDao.get(begin, end);
		Vector<Log> log = logDao.get(begin, end);
		Map<String, Integer> statistics = Util.countLogType(log);
		return new Report(Report.All, e, subordinate, statistics, summary, log);
	}
}
