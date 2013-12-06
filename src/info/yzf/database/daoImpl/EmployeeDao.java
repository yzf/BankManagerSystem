package info.yzf.database.daoImpl;

import info.yzf.database.MysqlDatabase;
import info.yzf.database.dao.IEmployeeDao;
import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class EmployeeDao implements IEmployeeDao {
	
	private static final String GetById = "select * from `employee` where `id`=? limit 1";
	private static final String GetByUsername = "select * from `employee` where `username`=? limit 1";
	private static final String GetByUsernamePwd = "select * from `employee` where `username`=? and `password`=? limit 1";
	private static final String GetSubordinates = "select * from `employee` where dep_id=?";
	private static final String Add = "insert into `employee` (`name`, `username`, `password`, `type`, `dep_id`) values (?, ?, ?, ?, ?)";
	private static final String Delete = "delete from `employee` where `name`=? and `username`=? limit 1";
	private static final String UpdatePassword = "update `employee` set `password`=? where `username`=? limit 1";
	private static final String UpdateInfo = "update `employee` set `type`=?, `dep_id`=? where `name`=? and `username`=? limit 1";
	private static final String GetAll = "select * from `employee` where `type` != ?";
	
	private Employee generate(ResultSet rs) throws SQLException {
		Employee emp = new Employee();
		emp.setId(rs.getInt("id"));
		emp.setName(rs.getString("name"));
		emp.setUsername(rs.getString("username"));
		emp.setPassword(rs.getString("password"));
		emp.setType(rs.getInt("type"));
		emp.setDepartment(new DepartmentDao().get(rs.getInt("dep_id")));
		return emp;
	}
	
	@Override
	public Employee get(int id) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Employee emp = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetById);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				emp = generate(rs);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public Employee get(String username) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Employee emp = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetByUsername);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				emp = generate(rs);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public Employee get(String username, String password) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Employee emp = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetByUsernamePwd);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				emp = generate(rs);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public Vector<Employee> getSubordinates(Department department) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Vector<Employee> ret = new Vector<Employee>();
		try {
			PreparedStatement ps = con.prepareStatement(GetSubordinates);
			ps.setInt(1, department.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ret.add(generate(rs));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public Employee add(Employee employee) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Employee emp = null;
		try {
			PreparedStatement ps = con.prepareStatement(Add, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, employee.getName());
			ps.setString(2, employee.getUsername());
			ps.setString(3, employee.getPassword());
			ps.setInt(4, employee.getType());
			if (employee.getDepartment() != null) {
				ps.setInt(5, employee.getDepartment().getId());
			}
			else {
				ps.setInt(5, 0);
			}
			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					emp = get(rs.getInt(1));
				}
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public boolean delete(String name, String username) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		boolean ret = false;
		try {
			PreparedStatement ps = con.prepareStatement(Delete);
			ps.setString(1, name);
			ps.setString(2, username);
			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				ret = true;
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public Employee updatePassword(String username, String password) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Employee emp = null;
		try {
			PreparedStatement ps = con.prepareStatement(UpdatePassword);
			ps.setString(1, password);
			ps.setString(2, username);
			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				emp = get(username, password);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public Employee updateInfo(String name, String username, int type,
			Department d) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Employee emp = null;
		try {
			PreparedStatement ps = con.prepareStatement(UpdateInfo);
			ps.setInt(1, type);
			if (d != null) {
				ps.setInt(2, d.getId());
			}
			else {
				ps.setInt(2, 0);
			}
			ps.setString(3, name);
			ps.setString(4, username);
			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				emp = get(username);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public Vector<Employee> gets() {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Vector<Employee> ret = new Vector<Employee>();
		try {
			PreparedStatement ps = con.prepareStatement(GetAll);
			ps.setInt(1, Employee.Admin);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ret.add(generate(rs));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

}
