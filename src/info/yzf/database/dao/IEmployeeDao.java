package info.yzf.database.dao;

import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;

import java.util.Map;
import java.util.Vector;

/**
 * 定义关于雇员的操作
 * @author yzf
 *
 */
public interface IEmployeeDao {
	/**
	 * 获取雇员
	 * @param id
	 * @return
	 */
	Employee get(int id);
	/**
	 * 获取雇员
	 * @param username
	 * @return
	 */
	Employee get(String username);
	/**
	 * 判断账号密码获取雇员
	 * @param username
	 * @param password
	 * @return
	 */
	Employee get(String username, String password);
	/**
	 * 获取雇员的所有下属
	 * @param employee
	 * @return
	 */
	Vector<Map<Department, Vector<Employee>>> getSubordinates(Employee employee);
	/**
	 * 添加雇员
	 * @param boss 雇员上司
	 * @param username 雇员账号
	 * @param password 密码
	 * @return
	 */
	Employee add(Employee employee);
}
