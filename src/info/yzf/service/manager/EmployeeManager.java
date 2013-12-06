package info.yzf.service.manager;

import info.yzf.database.dao.IDepartmentDao;
import info.yzf.database.dao.IEmployeeDao;
import info.yzf.database.dao.ISummaryDao;
import info.yzf.database.daoImpl.DepartmentDao;
import info.yzf.database.daoImpl.EmployeeDao;
import info.yzf.database.daoImpl.SummaryDao;
import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;
import info.yzf.database.model.Summary;
import info.yzf.util.Message;
import info.yzf.util.Pair;

import java.sql.Timestamp;
import java.util.Vector;

/**
 * 负责处理雇员的管理器，单例模式
 * @author yzf
 *
 */
public class EmployeeManager {
	
	private IEmployeeDao employeeDao;
	private IDepartmentDao departmentDao;
	private ISummaryDao summaryDao;
	
	private EmployeeManager() {
//		employeeDao = new EmployeeDaoSerial();
//		departmentDao = new DepartmentDaoSerial();
//		summaryDao = new SummaryDaoSerial();
		employeeDao = new EmployeeDao();
		departmentDao = new DepartmentDao();
		summaryDao = new SummaryDao();
	}

	private static class InstanceHolder {
		private static EmployeeManager instance = new EmployeeManager();
	}
	
	public static EmployeeManager getInstance() {
		return InstanceHolder.instance;
	}
	/**
	 * 登陆验证
	 * @param username
	 * @param password
	 * @return
	 */
	public Employee get(String username, String password) {
		return employeeDao.get(username, password);
	}
	/**
	 * 添加员工
	 * @param boss
	 * @param name
	 * @param username
	 * @param password
	 * @param type
	 * @return
	 */
	public Pair add(String name, String username, String password, int type, int depId) throws Exception {
		if (employeeDao.get(username) != null) {
			throw new Exception(Message.UsernameExist);//账号已经存在
		}
		if (departmentDao.get(depId) == null) {
			throw new Exception(Message.DepNotExist);//部门不存在
		}
		if (type != Employee.Conductor) {
			if (type == Employee.Manager && departmentDao.hasManager(depId)) {
				throw new Exception(Message.ManagerExist);//部门已经有经理
			}
			Employee e = new Employee(name, username, password, type, departmentDao.get(depId));
			employeeDao.add(e);
		}
		else {
			Employee e = new Employee(name, username, password, type, null);
			employeeDao.add(e);
		}
		return new Pair(null, null);
	}
	public Vector<Department> getDepartments() {
		return departmentDao.get();
	}
	
	public void delete(String name, String username) throws Exception {
		if (! employeeDao.delete(name, username)) {
			throw new Exception(Message.Mismatching);
		}
	}
	
	public void updatePassword(String username, String password) throws Exception {
		Employee e = employeeDao.updatePassword(username, password);
		if (e == null) {
			throw new Exception(Message.EmployeeNotExist);
		}
	}
	
	public void updateInfo(String name, String username, int type, int depId) throws Exception {
		Department d = departmentDao.get(depId);
		if (d == null) {
			throw new Exception(Message.DepNotExist);
		}
		Employee e = employeeDao.get(username);
		if (e == null) {
			throw new Exception(Message.EmployeeNotExist);
		}
		if (type == Employee.Manager && departmentDao.hasManager(depId)) {
			throw new Exception(Message.ManagerExist);//部门已经有经理
		}
		if (type == Employee.Conductor) {
			d = null;
		}
		e = employeeDao.updateInfo(name, username, type, d);
		if (e == null) {
			throw new Exception(Message.Fail);
		}
	}
	
	public void submitSummary(Employee employee, String content) throws Exception {
		Summary s = new Summary(new Timestamp(System.currentTimeMillis()),
					employee, content);
		summaryDao.add(s);
	}
}
