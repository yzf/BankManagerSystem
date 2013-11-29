package info.yzf.database.daoImpl;

import info.yzf.database.SerialDatabase;
import info.yzf.database.dao.IDepartmentDao;
import info.yzf.database.model.BaseModel;
import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;

import java.util.Vector;

public class DepartmentDaoSerial implements IDepartmentDao {

	@Override
	public Department get(int id) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(Department.class)) {
			Department d = (Department) bm;
			if (d.getId() == id) {
				return d;
			}
		}
		return null;
	}

	@Override
	public Department get(String name) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(Department.class)) {
			Department d = (Department) bm;
			if (d.getName().equals(name)) {
				return d;
			}
		}
		return null;
	}

	@Override
	public Vector<Department> get() {
		// TODO Auto-generated method stub
		Vector<Department> ret = new Vector<Department>();
		for (BaseModel bm : SerialDatabase.getInstance().get(Department.class)) {
			ret.add((Department)bm);
		}
		return ret;
	}

	@Override
	public boolean hasManager(int depId) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(Employee.class)) {
			Employee e = (Employee) bm;
			Department d = e.getDepartment();
			if (d != null && d.getId() == depId && e.isManager()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Employee getManager(Department d) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(Employee.class)) {
			Employee e = (Employee) bm;
			if (e.isManager() && e.getDepartment().equals(d)) {
				return e;
			}
		}
		return null;
	}

}
