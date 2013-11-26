package info.yzf.database.dao;

import info.yzf.database.model.Department;

import java.util.Vector;

public interface IDepartmentDao {
	/**
	 * 根据id获取部门
	 * @param id
	 * @return
	 */
	Department get(int id);
	/**
	 * 根据部门名获取部门
	 * @param name
	 * @return
	 */
	Department get(String name);
	/**
	 * 获取所有部门
	 * @return
	 */
	Vector<Department> get();
	/**
	 * 看部门是否已经有经理
	 * @param depId
	 * @return
	 */
	boolean hasManager(int depId);
}
