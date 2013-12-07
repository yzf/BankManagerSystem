package info.yzf.service;

import info.yzf.database.SerialDatabase;
import info.yzf.database.dao.IAccountDao;
import info.yzf.database.daoImpl.AccountDao;
import info.yzf.database.daoImpl.AccountDaoSerial;
import info.yzf.database.model.Account;
import info.yzf.database.model.BaseModel;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class BankTask {
	private final double HunThousand = 100000.00;
	private final Timer timer = new Timer();
	private final long task1Delay = 0;
	private final long task2Delay = 5000;
	private final long day = 3600 * 24 * 1000;
//	private IAccountDao accountDao = new AccountDaoSerial();
	private IAccountDao accountDao = new AccountDao();
	
	private BankTask() {}
	
	private static class InstanceHolder {
		private static BankTask instance = new BankTask();
	}
	
	public static BankTask getInstance() {
		return InstanceHolder.instance;
	}
	
	public void runTasks() {
		//VIP管理费 连续两个月日平均余额低于10万，需要收取每月1000元的管理费
		timer.scheduleAtFixedRate(new TimerTask() {
			
			private Map<Integer, Double> balanceSum = new HashMap<Integer, Double>();
			private Map<Integer, Integer> dayCnt = new HashMap<Integer, Integer>();
			private Map<Integer, Boolean> preStatus = new HashMap<Integer, Boolean>();
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("Run vip management cost check......");
				for (Account account : accountDao.get()) {
					if (account.isVIP() && ! account.isFreezed()) {
						int id = account.getId();
						if (balanceSum.get(id) == null) {
							balanceSum.put(id, 0.0);
							dayCnt.put(id, 0);
							preStatus.put(id, false);
						}
						Calendar.getInstance().setTime(new Date());
						if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1) {
							double avg = balanceSum.get(id) / dayCnt.get(id);
							if (avg < HunThousand) {
								if (preStatus.get(id).equals(true)) {
									//扣除管理费
									System.out.println(account);
									account = accountDao.updateBalance(account.getId(), account.getBalance() - 1000);
								}
								preStatus.put(id, true);
							}
							else {
								preStatus.put(id, false);
							}
							balanceSum.put(id, 0.0);
							dayCnt.put(id, 0);
						}
						balanceSum.put(id, balanceSum.get(id) + account.getBalance());
						dayCnt.put(id, dayCnt.get(id) + 1);
					}
				}
			}
			
		}, task1Delay, day);
		//冻结 透支超过30天，账户将被冻结
		timer.scheduleAtFixedRate(new TimerTask() {
			
			private Map<Integer, Integer> dayCnt = new HashMap<Integer, Integer>();

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("Run account freezed check......");
				
				for (Account account : accountDao.get()) {
					if (account.isVIP()) {
						if (dayCnt.get(account.getId()) == null) {
							dayCnt.put(account.getId(), 0);
						}
						if (account.getBalance() >= 0) {//解冻帐户
							dayCnt.put(account.getId(), 0);
							account = accountDao.changeStatus(account.getId(), false);
						}
						else {
							int cnt = dayCnt.get(account.getId());
							dayCnt.put(account.getId(), cnt + 1);
						}
						if (dayCnt.get(account.getId()) > 30) {//冻结账户
							account = accountDao.changeStatus(account.getId(), true);
						}
					}
				}
			}
		}, task2Delay, day);
	}
}
