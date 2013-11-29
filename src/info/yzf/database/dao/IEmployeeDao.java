package info.yzf.database.dao;

import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;

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
	 * 获取所有部门成员
	 * @param employee
	 * @return
	 */
	Vector<Employee> getSubordinates(Department department);
	/**
	 * 添加雇员
	 * @param boss 雇员上司
	 * @param username 雇员账号
	 * @param password 密码
	 * @return
	 */
	Employee add(Employee employee);
	/**
	 * 删除雇员
	 * @param employee
	 */
	boolean delete(String name, String username);
	/**
	 * 修改密码
	 * @param username
	 * @param password
	 */
	Employee updatePassword(String username, String password);
	/**
	 * 修改雇员信息
	 * @param name
	 * @param username
	 * @param type
	 * @param d
	 * @return
	 */
	Employee updateInfo(String name, String username, int type, Department d);
	/**
	 * 获取所有员工
	 * @return
	 */
	Vector<Employee> gets();
}
