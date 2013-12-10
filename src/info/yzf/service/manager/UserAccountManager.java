package info.yzf.service.manager;

import info.yzf.database.dao.IAccountDao;
import info.yzf.database.dao.IEnterpriseAccountDao;
import info.yzf.database.dao.IUserAccountDao;
import info.yzf.database.dao.IUserDao;
import info.yzf.database.daoImpl.AccountDao;
import info.yzf.database.daoImpl.EnterpriseAccountDao;
import info.yzf.database.daoImpl.UserAccountDao;
import info.yzf.database.daoImpl.UserDao;
import info.yzf.database.model.Account;
import info.yzf.database.model.Employee;
import info.yzf.database.model.EnterpriseAccount;
import info.yzf.database.model.Log;
import info.yzf.database.model.User;
import info.yzf.database.model.UserAccount;
import info.yzf.util.Message;
import info.yzf.util.Pair;

/**
 * 负责处理用户账户的管理器，单例模式
 * @author yzf
 *
 */
public class UserAccountManager {
	
	private static final double Zero = 0.00;//零
	private static final double TenThousand = 10000.00;//一万
	private static final double HunThousand = 100000.00;//十万
	private static final double Million = 1000000.00;//百万
	private static final int MaxSuper = 2;
	
	private IUserDao userDao;
	private IAccountDao accountDao;
	private IUserAccountDao userAccountDao;
	private IEnterpriseAccountDao enterpriseAccountDao;
	
	private UserAccountManager() {
//		userDao = new UserDaoSerial();
//		accountDao = new AccountDaoSerial();
//		userAccountDao = new UserAccountDaoSerial();
//		enterpriseAccountDao = new EnterpriseAccountDaoSerial();
		userDao = new UserDao();
		accountDao = new AccountDao();
		userAccountDao = new UserAccountDao();
		enterpriseAccountDao = new EnterpriseAccountDao();
	}
	
	private static class InstanceHolder {
		private static UserAccountManager instance = new UserAccountManager();
	}
	
	public static UserAccountManager getInstance() {
		return InstanceHolder.instance;
	}

	//开户
	public Pair createAccount(Employee employee, String enterpriseIdentity, String enterpriseName,
			String identity, String name, int uType, int aType, double balance, 
			String password, String passwordAgain) throws Exception {
		if (! password.equals(passwordAgain)) {//两次密码不同
			throw new Exception(Message.PasswordDiffer);
		}
		if (balance < 0) {
			throw new Exception(Message.NumberFormat);
		}
		String username = accountDao.generateUsername();
		User user = new User(identity, name);
		Account account = new Account(username, aType, uType, balance);
		UserAccount userAccount;
		String operation;
		
		if (uType != Account.Enterprise) {//个人账户
			if (aType == Account.VIP && balance < Million) {
				throw new Exception(Message.VIP);//没有达到VIP资格（所有账户总存款达到一百万）
			}
			//添加
			user = userDao.add(user);
			account = accountDao.add(account);
			userAccount = new UserAccount(user, account, password, UserAccount.Normal);
			userAccount = userAccountDao.add(userAccount);
			operation = "创建" + (account.isVIP() ? "VIP" : "普通") + (account.isFixedDeposit() ? "定期" : "活期") + "账户";
		}
		else {//企业账户
			if (balance < TenThousand) {//企业用户账面存款余额总数不能少于1万
				throw new Exception(Message.Enterprise);
			}
			User enterprise = new User(enterpriseIdentity, enterpriseName);
			enterprise = userDao.add(enterprise);
			account = accountDao.add(account);
			EnterpriseAccount ea = new EnterpriseAccount(enterprise, account, username);
			user = userDao.add(user);
			userAccount = new UserAccount(user, account, password, UserAccount.Super);//超级用户
			userAccount = userAccountDao.add(userAccount);
			ea = enterpriseAccountDao.add(ea);
			operation = "创建企业账户";
		}
		Log log = LogManager.getInstance().recordOperation(employee.getId(), employee.getName(), userAccount.getUser().getName(),
						userAccount.getAccount().getUsername(), "", "", operation, Log.CreateAccount);
		return new Pair(username, log);
	}
	//存款
	public Pair deposit(Employee employee, String username, String password, double money) throws Exception {
		if (money < Zero) {//存入金额有问题
			throw new Exception(Message.NumberFormat);
		}
 		UserAccount userAccount = userAccountDao.get(username, password);
		if (userAccount == null) {//账号密码不匹配
			throw new Exception(Message.Mismatching);
		}
		Account account = userAccount.getAccount();
		if (account.isFreezed()) {//帐户被冻结
			throw new Exception(Message.AccountFreezed);
		}
		//存款操作
		account = accountDao.updateBalance(account.getId(), account.getBalance() + money);
		//日志记录
		String operation = "存入：" + money + "元";
		Log log = LogManager.getInstance().recordOperation(employee.getId(), employee.getName(), userAccount.getUser().getName(), 
					userAccount.getAccount().getUsername(), "", "", operation, Log.Deposit);
		return new Pair(account.getBalance(), log);
	}
	//取款 
	public Pair withdrawal(Employee employee, String username, String password, double money) throws Exception {
		if (money < Zero) {//数据格式错误
			throw new Exception(Message.NumberFormat);
		}
		UserAccount userAccount = userAccountDao.get(username, password);
		if (userAccount == null) {//账号或密码错误
			throw new Exception(Message.Mismatching);
		}
		Account account = userAccount.getAccount();
		if (account.isFreezed()) {//账户被冻结
			throw new Exception(Message.AccountFreezed);
		}
		
		if (account.isEnterprise()) {//企业账户
			if (account.getBalance() - money < TenThousand) {
				throw new Exception(Message.Enterprise);
			}
		}
		else {//个人账户
			if (account.getBalance() < money) {
				if (account.isNormal()) {//余额不足
					throw new Exception(Message.LackBalance);
				}
				if (account.getBalance() - money < -HunThousand) {//透支过度
					throw new Exception(Message.Overdraft);
				}
			}
		}
		account = accountDao.updateBalance(account.getId(), account.getBalance() - money);
		String operation = "取出：" + money + "元" + (account.getBalance() < 0 ? " 账户处于透支状态" : "");
		Log log = LogManager.getInstance().recordOperation(employee.getId(), employee.getName(), userAccount.getUser().getName(), 
				userAccount.getAccount().getUsername(), "", "", operation, Log.Withdrawal);
		return new Pair(account.getBalance(), log);
	}
	//查询
	public Pair query(Employee employee, String identity, String username, String password) throws Exception {
		UserAccount ua = userAccountDao.get(identity, username, password, true);
		if (ua == null) {
			throw new Exception(Message.Mismatching);
		}
		Log log = LogManager.getInstance().recordOperation(employee.getId(), employee.getName(), ua.getUser().getName(), 
					ua.getAccount().getUsername(), "", "", "查询余额", Log.Query);
		return new Pair(ua.getAccount().getBalance(), log);
	}
	//转账
	public Pair transfer(Employee employee, String fromIdentity, String fromName, 
			String fromUsername, String fromPassword, String toName, String toUsername, 
			double money) throws Exception {
		if (money < Zero) {
			throw new Exception(Message.NumberFormat);
		}
		UserAccount fromUa = userAccountDao.get(fromIdentity, fromUsername, fromPassword, true);
		UserAccount toUa = userAccountDao.get(toUsername);
		User fromUser;
		User toUser;
		Account fromAccount;
		Account toAccount;
		if (fromUa == null || toUa == null) {
			throw new Exception(Message.Mismatching);
		}
		if (fromUa.getAccount().isEnterprise()) {//企业账户转账
			EnterpriseAccount fromEa = enterpriseAccountDao.get(fromUsername);
			EnterpriseAccount toEa = enterpriseAccountDao.get(toUsername);
			if (toEa == null) {//企业账户转账给个人账户
				throw new Exception(Message.Unauthorized);
			}
			if (! fromUa.getUser().getName().equals(fromName) ||
					! toEa.getEnterprise().getName().equals(toName)) {
				throw new Exception(Message.Mismatching);//用户信息有误
			}
			fromUser = fromUa.getUser();
			toUser = toEa.getEnterprise();
			fromAccount = fromEa.getAccount();
			toAccount = toEa.getAccount();
			if (fromAccount.getBalance() - money < TenThousand) {
				throw new Exception(Message.Enterprise);//企业余额要大于一万元
			}
		}
		else {//个人用户转账
			if (toUa == null) {
				throw new Exception(Message.Mismatching);//用户信息有误
			}
			if (toUa.getAccount().isEnterprise()) {
				throw new Exception(Message.Unauthorized);//个人用户不能转给企业用户
			}
			if (! fromUa.getUser().getName().equals(fromName) ||
					! toUa.getUser().getName().equals(toName)) {
				throw new Exception(Message.Mismatching);//用户信息有误
			}
			fromUser = fromUa.getUser();
			toUser = toUa.getUser();
			fromAccount = fromUa.getAccount();
			toAccount = toUa.getAccount();
			if (fromAccount.isNormal()) {//普通账户
				if (fromAccount.getBalance() < money) {
					throw new Exception(Message.LackBalance);//余额不足
				}
				if (! fromUa.getUser().equals(toUa.getUser())) {//普通账户不能转账给其他人的账户
					throw new Exception(Message.Unauthorized);
				}
			}
			//VIP账户
			if (fromAccount.isVIP() && fromAccount.getBalance() - money < -HunThousand) {
				throw new Exception(Message.Overdraft);
			}
		}
		if (fromAccount.isFreezed() || toAccount.isFreezed()) {
			throw new Exception(Message.AccountFreezed);//其中一方被冻结
		}
		fromAccount = accountDao.updateBalance(fromAccount.getId(), fromAccount.getBalance() - money);
		toAccount = accountDao.updateBalance(toAccount.getId(), toAccount.getBalance() + money);
		Log log = LogManager.getInstance().recordOperation(employee.getId(), employee.getName(), fromUser.getName(), fromAccount.getUsername(), 
				toUa.getUser().getName(), toUa.getAccount().getUsername(), "转账：" + money + "元" + (fromAccount.getBalance() < 0 ? " 账户处于透支状态" : ""), Log.Transfer);
		return new Pair(fromAccount.getBalance(), log);
	}
	//修改密码
	public Pair changePassword(Employee employee, String identity, String username, 
			String oldPassword, String newPassword) throws Exception {
		UserAccount userAccount = userAccountDao.get(identity, username, oldPassword, true);
		if (userAccount == null) {
			throw new Exception(Message.Mismatching);
		}
		if (userAccount.getAccount().isEnterprise()) {
			throw new Exception(Message.Unauthorized);
		}
		userAccount = userAccountDao.updatePassword(userAccount.getId(), newPassword);
		Log log = LogManager.getInstance().recordOperation(employee.getId(), employee.getName(), userAccount.getUser().getName(), 
					userAccount.getAccount().getUsername(), "", "", "修改密码", Log.Password);
		return new Pair(null, log);
	}
	//销户
	public Pair closeAccount(Employee employee, String identity, String username, String password) throws Exception {
		UserAccount userAccount = userAccountDao.get(identity, username, password, true);
		if (userAccount == null) {
			throw new Exception(Message.Mismatching);
		}
		if (userAccount.getAccount().getBalance() < 0) {//透支帐户
			throw new Exception(Message.AccountOverdraft);
		}
		if (userAccount.getAccount().isFreezed()) {
			throw new Exception(Message.AccountFreezed);
		}
		accountDao.delete(userAccount.getAccount());
		userAccountDao.delete(userAccount);
		if (userAccount.getAccount().isEnterprise()) {
			EnterpriseAccount ea = enterpriseAccountDao.get(username);
			enterpriseAccountDao.delete(ea);
		}
		Log log = LogManager.getInstance().recordOperation(employee.getId(), employee.getName(), userAccount.getUser().getName(), 
				userAccount.getAccount().getUsername(), "", "", "销户", Log.CloseAccount);
		return new Pair(userAccount.getAccount().getBalance(), log);
	}
	//增加企业账户操作人
	public Pair addOperator(Employee employee, String enIdentity, String enName,
			String identity, String username,  String password, String newIdentity, 
			String newName, String newPassword, String newPasswordAgain, int role) throws Exception {
		UserAccount ua = userAccountDao.get(identity, username, password, true);
		if (ua == null) {
			throw new Exception(Message.Mismatching);//用户信息有误
		}
		EnterpriseAccount ea = enterpriseAccountDao.get(username);
		if (! ea.getEnterprise().getIdentity().equals(enIdentity) ||
				! ea.getEnterprise().getName().equals(enName)) {
			throw new Exception(Message.Mismatching);//企业信息有误
		}
		if (! newPassword.equals(newPasswordAgain)) {
			throw new Exception(Message.PasswordDiffer);//两次密码输入不同
		}
		if (userAccountDao.get(username, newPassword) != null) {
			throw new Exception(Message.PasswordSame);//已经有操作人占用这个密码
		}
		if (userAccountDao.getSuperNumber(username) >= MaxSuper) {
			throw new Exception(Message.SuperOperatorLimit);//超级操作人个数超过限制
		}
		if (userAccountDao.get(newIdentity, username, newPassword, false) != null) {//该用户本来就是操作员
			throw new Exception(Message.OperatorExist);
		}
		User user = new User(newIdentity, newName);
		user = userDao.add(user);
		UserAccount newUa = new UserAccount(user, ua.getAccount(), newPassword, role);
		newUa = userAccountDao.add(newUa);
		Log log = LogManager.getInstance().recordOperation(employee.getId(), employee.getName(), ua.getUser().getName(), 
					ua.getAccount().getUsername(), newUa.getUser().getName(), newUa.getAccount().getUsername(), 
						"创建" + (newUa.isSuper() ? "超级" : "普通") + "操作人", Log.Other);
		return new Pair(null, log);
	}
	//删除企业账户操作人
	public Pair deleteOperator(Employee employee, String enIdentity, String enName,
			String identity, String username,  String password, String delIdentity, 
			String delName) throws Exception {
		UserAccount uaSuper = userAccountDao.get(identity, username, password, true);
		if (uaSuper == null) {
			throw new Exception(Message.Mismatching);//用户信息有误
		}
		EnterpriseAccount ea = enterpriseAccountDao.get(username);
		if (! ea.getEnterprise().getIdentity().equals(enIdentity) ||
				! ea.getEnterprise().getName().equals(enName)) {
			throw new Exception(Message.Mismatching);//企业信息有误
		}
		UserAccount ua = userAccountDao.get(delIdentity, username, password, false);
		if (ua == null) {
			throw new Exception(Message.Mismatching);//要删除的操作人信息有误
		}
		if (ua.getRole() == UserAccount.Super) {
			throw new Exception(Message.Unauthorized);//不允许删除超级操作人
		}
		userAccountDao.delete(ua);
		Log log = LogManager.getInstance().recordOperation(employee.getId(), employee.getName(), uaSuper.getUser().getName(), 
				uaSuper.getAccount().getUsername(), ua.getUser().getName(), ua.getAccount().getUsername(), "删除普通操作人", Log.Other);
		return new Pair(null, log);
	}
}
