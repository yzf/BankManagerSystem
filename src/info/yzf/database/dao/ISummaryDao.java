package info.yzf.database.dao;

import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;
import info.yzf.database.model.Summary;

import java.sql.Timestamp;
import java.util.Vector;

public interface ISummaryDao {
	/**
	 * 通过id获取报告
	 * @param id
	 * @return
	 */
	Summary get(int id);
	/**
	 * 添加报告
	 * @param summary
	 * @return
	 */
	Summary add(Summary summary);
	/**
	 * 删除报告
	 * @param summary
	 */
	void delete(Summary summary);
	/**
	 * 员工的所有报告
	 * @param e
	 * @param begin
	 * @param end
	 * @return
	 */
	Vector<Summary> get(Employee e, Timestamp begin, Timestamp end);
	/**
	 * 部门的所有报告
	 * @param d
	 * @param begin
	 * @param end
	 * @return
	 */
	Vector<Summary> get(Department d, Timestamp begin, Timestamp end);
	/**
	 * 银行的所有报告
	 * @param begin
	 * @param end
	 * @return
	 */
	Vector<Summary> get(Timestamp begin, Timestamp end);
}
