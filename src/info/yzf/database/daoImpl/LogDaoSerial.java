package info.yzf.database.daoImpl;

import info.yzf.database.SerialDatabase;
import info.yzf.database.dao.ILogDao;
import info.yzf.database.model.Account;
import info.yzf.database.model.BaseModel;
import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;
import info.yzf.database.model.Log;

import java.sql.Timestamp;
import java.util.Vector;

public class LogDaoSerial implements ILogDao {

	@Override
	public Log add(Log log) {
		// TODO Auto-generated method stub
		return (Log) SerialDatabase.getInstance().add(log);
	}

	@Override
	public Vector<Log> get(Account account, Timestamp begin, Timestamp end) {
		// TODO Auto-generated method stub
		Vector<Log> logList = new Vector<Log>();
		for (BaseModel bm : SerialDatabase.getInstance().get(Log.class)) {
			Log log = (Log) bm;
			if (log.getTop().getAccount().equals(account) && 
					log.getTime().compareTo(begin) >= 0 && 
					log.getTime().compareTo(end) <= 0) {
				logList.add(log);
			}
		}
		return logList;
	}

	@Override
	public Vector<Log> get(Employee employee, Timestamp begin, Timestamp end) {
		// TODO Auto-generated method stub
		Vector<Log> logList = new Vector<Log>();
		for (BaseModel bm : SerialDatabase.getInstance().get(Log.class)) {
			Log log = (Log) bm;
			if (log.getEmployee().equals(employee) && 
					log.getTime().compareTo(begin) >= 0 && 
					log.getTime().compareTo(end) <= 0) {
				logList.add(log);
			}
		}
		return logList;
	}

	@Override
	public Vector<Log> get(Department department, Timestamp begin, Timestamp end) {
		// TODO Auto-generated method stub
		Vector<Log> logList = new Vector<Log>();
		for (BaseModel bm : SerialDatabase.getInstance().get(Log.class)) {
			Log log = (Log) bm;
			if (log.getEmployee().getDepartment().equals(department) && 
					log.getTime().compareTo(begin) >= 0 && 
					log.getTime().compareTo(end) <= 0) {
				logList.add(log);
			}
		}
		return logList;
	}

	@Override
	public Vector<Log> get(Timestamp begin, Timestamp end) {
		// TODO Auto-generated method stub
		Vector<Log> logList = new Vector<Log>();
		for (BaseModel bm : SerialDatabase.getInstance().get(Log.class)) {
			Log log = (Log) bm;
			if (log.getTime().compareTo(begin) >= 0 && 
					log.getTime().compareTo(end) <= 0) {
				logList.add(log);
			}
		}
		return logList;
	}

}
