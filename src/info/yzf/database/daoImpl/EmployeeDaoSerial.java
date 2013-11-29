package info.yzf.database.daoImpl;

import info.yzf.database.SerialDatabase;
import info.yzf.database.dao.IEmployeeDao;
import info.yzf.database.model.BaseModel;
import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;

import java.util.Vector;

public class EmployeeDaoSerial implements IEmployeeDao {
	
	@Override
	public Employee get(int id) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(Employee.class)) {
			Employee e = (Employee) bm;
			if (e.getId() == id) {
				return e;
			}
		}
		return null;
	}
	
	@Override
	public Employee get(String username) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(Employee.class)) {
			Employee e = (Employee) bm;
			if (e.getUsername().equals(username)) {
				return e;
			}
		}
		return null;
	}
	
	@Override
	public Employee get(String username, String password) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(Employee.class)) {
			Employee e = (Employee) bm;
			if (e.getUsername().equals(username) &&
					e.getPassword().equals(password)) {
				return e;
			}
		}
		return null;
	}
	
	@Override
	public Vector<Employee> getSubordinates(Department department) {
		// TODO Auto-generated method stub
		Vector<Employee> ret = new Vector<Employee>();
		for (BaseModel bm : SerialDatabase.getInstance().get(Employee.class)) {
			Employee e = (Employee) bm;
			if (e.getDepartment() == null) {
				continue;
			}
			if (department.equals(e.getDepartment())) {
				ret.add(e);
			}
		}
		return ret;
	}

	@Override
	public Employee add(Employee employee) {
		// TODO Auto-generated method stub
		return (Employee) SerialDatabase.getInstance().add(employee);
	}

	@Override
	public boolean delete(String name, String username) {
		// TODO Auto-generated method stub
		Employee e = get(username);
		if (e != null && e.getName().equals(name)) {
			SerialDatabase.getInstance().remove(e);
			return true;
		}
		return false;
	}

	@Override
	public Employee updatePassword(String username, String password) {
		// TODO Auto-generated method stub
		Employee e = get(username);
		if (e != null) {
			e.setPassword(password);
		}
		return e;
	}

	@Override
	public Employee updateInfo(String name, String username, int type,
			Department d) {
		// TODO Auto-generated method stub
		Employee e = get(username);
		if (e == null || ! e.getName().equals(name)) {
			return null;
		}
		e.setType(type);
		e.setDepartment(d);
		return e;
	}

	@Override
	public Vector<Employee> gets() {
		// TODO Auto-generated method stub
		Vector<Employee> ret = new Vector<Employee>();
		for (BaseModel bm : SerialDatabase.getInstance().get(Employee.class)) {
			if (((Employee)bm).getType() == Employee.Admin) {
				continue;
			}
			ret.add((Employee) bm);
		}
		return ret;
	}
}
