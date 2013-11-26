package info.yzf.database.dao;

import info.yzf.database.model.UserAccount;

/**
 * 定义关于用户账户的操作
 * @author yzf
 *
 */
public interface IUserAccountDao {
	/**
	 * 通过id获取帐户关系
	 * @param id
	 * @return
	 */
	UserAccount get(int id);
	/**
	 * 只对非企业用户有效
	 * @param username
	 * @return
	 */
	UserAccount get(String username);
	/**
	 * 通过账号密码获取帐户关系
	 * @param username
	 * @param password
	 * @return
	 */
	UserAccount get(String username, String password);
	/**
	 * 通过身份证，账号和密码获取帐户关系
	 * @param identity
	 * @param username
	 * @param password
	 * @return
	 */
	UserAccount get(String identity, String username, String password, boolean usePassword);
	/**
	 * 添加帐户关系
	 * @param customerAccount
	 * @return
	 */
	UserAccount add(UserAccount userAccount);
	/**
	 * 删除帐户关系
	 * @param customerAccount
	 */
	void delete(UserAccount userAccount);
	/**
	 * 修改密码
	 * @param id
	 * @param password
	 * @return
	 */
	UserAccount updatePassword(int id, String password);
	/**
	 * 获取超级操作人的人数
	 * @param username
	 * @return
	 */
	int getSuperNumber(String username);
}
