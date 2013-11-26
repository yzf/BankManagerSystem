package info.yzf.service.manager;

import info.yzf.database.dao.IDepartmentDao;
import info.yzf.database.dao.ILogDao;
import info.yzf.database.dao.IUserAccountDao;
import info.yzf.database.daoImpl.DepartmentDaoSerial;
import info.yzf.database.daoImpl.LogDaoSerial;
import info.yzf.database.daoImpl.UserAccountDaoSerial;
import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;
import info.yzf.database.model.Log;
import info.yzf.database.model.UserAccount;
import info.yzf.util.Message;
import info.yzf.util.Pair;
import info.yzf.util.Util;

import java.sql.Timestamp;
import java.util.Vector;

/**
 * 负责写日志的管理器，单例模式
 * @author yzf
 *
 */
public class LogManager {
	
	private ILogDao logDao;
	private IUserAccountDao customerAccountDao;
	private IDepartmentDao departentDao;
	
	private LogManager() {
		logDao = new LogDaoSerial();
		customerAccountDao = new UserAccountDaoSerial();
		departentDao = new DepartmentDaoSerial();
	}
	
	private static class InstanceHolder {
		private static LogManager instance = new LogManager();
	}
	
	public static LogManager getInstance() {
		return InstanceHolder.instance;
	}	
	/**
	 * 记录日志
	 * @param employee
	 * @param customer
	 * @param account
	 * @param time
	 * @param operation
	 * @return
	 * @throws Exception 
	 */
	public Log recordOperation(Employee employee, UserAccount top,
			UserAccount bottom, String operation) throws Exception {
		Log log = new Log(new Timestamp(System.currentTimeMillis()), 
						employee, top, bottom, operation);
		try {
			log = logDao.add(log);
		} catch (Exception e) {
			throw new Exception(Message.LogFail);
		}
		return log;
	}
	/**
	 * 查询某帐户一段时间的所有操作
	 * @param account
	 * @param begin
	 * @param end
	 * @return
	 */
	public Vector<Log> query(String identity, String username, String password, String begin, String end) throws Exception {
		begin += " 00:00:00";
		end += " 23:59:59";
		Timestamp beginTime;
		Timestamp endTime;
		try {
			beginTime = Util.convertToTimestamp(begin);
			endTime = Util.convertToTimestamp(end);
		} catch (Exception e) {
			throw new Exception(Message.TimeFormat);
		}
		UserAccount ca = customerAccountDao.get(identity, username, password, true);
		if (ca == null) {
			throw new Exception(Message.Mismatching);
		}
		return logDao.get(ca.getAccount(), beginTime, endTime);
	}
	/**
	 * 雇员查看日志
	 * @param level
	 * @param depId
	 * @param type
	 * @param time
	 * @return
	 * @throws Exception 
	 */
	public Pair getJournal(Employee employee, int level, int depId, int type, String time) throws Exception {
		Department d = departentDao.get(depId);
		if (d == null) {
			throw new Exception(Message.DepNotExist);
		}
		String begin, end;
		Timestamp beginTime;
		Timestamp endTime;
		Vector<Log> logs;
		if (type == 0) {//日报
			begin = time + " 00:00:00";
			end = time + " 23:59:59";
		}
		else if (type == 1) {//月报
			int nMonth = Integer.parseInt(time.split("-")[1]);
			int nYear = Integer.parseInt(time.split("-")[0]);
			begin = time.split("-")[0] + "-";
			if (nMonth < 10) {
				begin += "0";
			}
			begin += String.valueOf(nMonth) + "-01 00:00:00";
			if (nMonth == 12) {
				end = String.valueOf(nYear) + "-01-01 00:00:00";
			}
			else {
				end = time.split("-")[0] + "-";
				if (nMonth < 9) {
					end += "0";
				}
				end += String.valueOf(nMonth + 1) + "-01 00:00:00";
			}
		}
		else if (type == 2) {//季度
			int jidu = Integer.parseInt(time.split(" ")[1]);
			if (jidu > 4 || jidu < 1) {
				throw new Exception(Message.DateFormat);
			}
			if (jidu == 1) {
				begin = time.split(" ")[0] + "-01-01 00:00:00";
				end = time.split(" ")[0] + "-04-01 00:00:00";
			}
			else if (jidu == 2) {
				begin = time.split(" ")[0] + "-04-01 00:00:00";
				end = time.split(" ")[0] + "-07-01 00:00:00";
			}
			else if (jidu == 3) {
				begin = time.split(" ")[0] + "-07-01 00:00:00";
				end = time.split(" ")[0] + "-10-01 00:00:00";
			}
			else {
				begin = time.split(" ")[0] + "-10-01 00:00:00";
				end = time.split(" ")[0] + "-12-31 23:59:59";
			}
		}
		else {//年报
			begin = time + "-01-01 00:00:00";
			end = String.valueOf(Integer.parseInt(time) + 1) + "-01-01 00:00:00";
		}

		try {
			beginTime = Util.convertToTimestamp(begin);
			endTime = Util.convertToTimestamp(end);
		} catch (Exception e) {
			throw new Exception(Message.TimeFormat);
		}
		if (level <= 0) {//个人
			logs = logDao.get(employee, beginTime, endTime);
		}
		else if (level <= 1) {
			logs = logDao.get(d, beginTime, endTime);
		}
		else {
			logs = logDao.get(beginTime, endTime);
		}
		
		return new Pair(null, logs);
	}
}
