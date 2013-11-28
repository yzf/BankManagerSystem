package info.yzf.database.daoImpl;

import info.yzf.database.SerialDatabase;
import info.yzf.database.dao.IEmployeeDao;
import info.yzf.database.model.BaseModel;
import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;

import java.util.HashMap;
import java.util.Map;
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
	public Vector<Map<Department, Vector<Employee>>> getSubordinates(Employee employee) {
		// TODO Auto-generated method stub
		Vector<Map<Department, Vector<Employee>>> ret = new Vector<Map<Department, Vector<Employee>>>();
		if (employee.getType() == Employee.Manager) {
			Map<Department, Vector<Employee>> depEmp = new HashMap<Department, Vector<Employee>>();
			Department d = employee.getDepartment();
			Vector<Employee> employees = new Vector<Employee>();
			for (BaseModel bm : SerialDatabase.getInstance().get(Employee.class)) {
				Employee e = (Employee) bm;
				if (e.getDepartment().equals(d)) {
					employees.add(e);
				}
			}
			depEmp.put(d, employees);
			ret.add(depEmp);
		}
		if (employee.getType() == Employee.Conductor) {
			for (BaseModel bm : SerialDatabase.getInstance().get(Department.class)) {
				Department d = (Department) bm;
				Map<Department, Vector<Employee>> depEmp = new HashMap<Department, Vector<Employee>>();
				Vector<Employee> employees = new Vector<Employee>();
				for (BaseModel bm2 : SerialDatabase.getInstance().get(Employee.class)) {
					Employee e = (Employee) bm2;
					if (e.getDepartment().equals(d)) {
						employees.add(e);
					}
				}
				depEmp.put(d, employees);
				ret.add(depEmp);
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
}
