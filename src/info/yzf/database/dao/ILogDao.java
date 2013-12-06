package info.yzf.database.dao;

import info.yzf.database.model.Account;
import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;
import info.yzf.database.model.Log;

import java.sql.Timestamp;
import java.util.Vector;

/**
 * 定义关于日志的操作
 * @author yzf
 *
 */
public interface ILogDao {
	/**
	 * 通过id获取日志
	 * @param id
	 * @return
	 */
	Log get(int id);
	/**
	 * 添加日志条目
	 * @param log
	 * @return
	 */
	Log add(Log log);
	/**
	 * 获取某帐户一段时间的日志
	 * @param begin
	 * @param end
	 * @return
	 */
	Vector<Log> get(Account account, Timestamp begin, Timestamp end);
	/**
	 * 获取某雇员的一段时间的操作日志
	 * @param employee
	 * @param begin
	 * @param end
	 * @return
	 */
	Vector<Log> get(Employee employee, Timestamp begin, Timestamp end);
	/**
	 * 获取部门某段时间的操作
	 * @param department
	 * @param begin
	 * @param end
	 * @return
	 */
	Vector<Log> get(Department department, Timestamp begin, Timestamp end);
	/**
	 * 获取所有雇员一段时间的操作
	 * @param begin
	 * @param end
	 * @return
	 */
	Vector<Log> get(Timestamp begin, Timestamp end);
}
