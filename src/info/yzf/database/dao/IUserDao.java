package info.yzf.database.dao;

import info.yzf.database.model.User;

public interface IUserDao {
	/**
	 * 通过id获取顾客
	 * @param id
	 * @return
	 */
	User get(int id);
	/**
	 * 通过身份证获取顾客
	 * @param identity
	 * @return
	 */
	User get(String identity);
	/**
	 * 添加顾客
	 * @param customer
	 * @return
	 */
	User add(User user);
	/**
	 * 删除顾客
	 * @param customer
	 */
	void delete(User user);
}
