package info.yzf.database.dao;

import info.yzf.database.model.EnterpriseAccount;

public interface IEnterpriseAccountDao {
	/**
	 * 根据id获取企业帐户关系
	 * @param id
	 * @return
	 */
	EnterpriseAccount get(int id);
	/**
	 * 根据企业账户名获取企业关系
	 * @param username
	 * @return
	 */
	EnterpriseAccount get(String username);
	/**
	 * 添加企业账户关系
	 * @param ea
	 * @return
	 */
	EnterpriseAccount add(EnterpriseAccount ea);
	/**
	 * 删除企业账户关系
	 * @param ea
	 */
	void delete(EnterpriseAccount ea);
}
