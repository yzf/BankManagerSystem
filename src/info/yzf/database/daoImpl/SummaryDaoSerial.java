package info.yzf.database.daoImpl;

import info.yzf.database.SerialDatabase;
import info.yzf.database.dao.ISummaryDao;
import info.yzf.database.model.BaseModel;
import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;
import info.yzf.database.model.Summary;

import java.sql.Timestamp;
import java.util.Vector;

public class SummaryDaoSerial implements ISummaryDao {

	@Override
	public Summary add(Summary summary) {
		// TODO Auto-generated method stub
		return (Summary) SerialDatabase.getInstance().add(summary);
	}

	@Override
	public void delete(Summary summary) {
		// TODO Auto-generated method stub
		SerialDatabase.getInstance().remove(summary);
	}

	@Override
	public Vector<Summary> get(Employee e, Timestamp begin, Timestamp end) {
		// TODO Auto-generated method stub
		Vector<Summary> ret = new Vector<Summary>();
		for (BaseModel bm : SerialDatabase.getInstance().get(Summary.class)) {
			Summary s = (Summary) bm;
			if (s.getEmployee().equals(e) &&
					s.getTime().compareTo(begin) >= 0 && 
					s.getTime().compareTo(end) <= 0) {
				ret.add(s);
			}
		}
		return ret;
	}

	@Override
	public Vector<Summary> get(Department d, Timestamp begin, Timestamp end) {
		// TODO Auto-generated method stub
		Vector<Summary> ret = new Vector<Summary>();
		for (BaseModel bm : SerialDatabase.getInstance().get(Summary.class)) {
			Summary s = (Summary) bm;
			if (d != null && d.equals(s.getEmployee().getDepartment()) &&
					s.getTime().compareTo(begin) >= 0 && 
					s.getTime().compareTo(end) <= 0) {
				ret.add(s);
			}
		}
		return ret;
	}

	@Override
	public Vector<Summary> get(Timestamp begin, Timestamp end) {
		// TODO Auto-generated method stub
		Vector<Summary> ret = new Vector<Summary>();
		for (BaseModel bm : SerialDatabase.getInstance().get(Summary.class)) {
			Summary s = (Summary) bm;
			if (s.getTime().compareTo(begin) >= 0 && 
					s.getTime().compareTo(end) <= 0) {
				ret.add(s);
			}
		}
		return ret;
	}

}
