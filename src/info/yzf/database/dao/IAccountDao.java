package info.yzf.database.dao;

import info.yzf.database.model.Account;

import java.util.Vector;

public interface IAccountDao {
	/**
	 * 生成账号
	 * @return
	 */
	String generateUsername();
	/**
	 * 通过id获取帐户
	 * @param id
	 * @return
	 */
	Account get(int id);
	/**
	 * 通过账号获取帐户
	 * @param username
	 * @return
	 */
	Account get(String username);
	/**
	 * 更新余额
	 * @param id
	 * @param balance
	 * @return
	 */
	Account updateBalance(int id, double balance);
	/**
	 * 添加帐户
	 * @param account
	 * @return
	 */
	Account add(Account account);
	/**
	 * 删除帐户
	 * @param account
	 */
	void delete(Account account);
	/**
	 * 获取所有帐户
	 * @return
	 */
	Vector<Account> get();
	/**
	 * 冻结账户
	 * @return
	 */
	Account changeStatus(int id, boolean isFreezed);
}
