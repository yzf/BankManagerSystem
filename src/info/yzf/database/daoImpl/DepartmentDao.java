package info.yzf.database.daoImpl;

import info.yzf.database.MysqlDatabase;
import info.yzf.database.dao.IDepartmentDao;
import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DepartmentDao implements IDepartmentDao {
	
	private static final String GetById = "select * from `department` where `id`=? limit 1";
	private static final String GetByName = "select * from `department` where `name`=? limit 1";
	private static final String GetAll = "select * from `department`";
	private static final String HasManager = "select count(*) from `department` d, `employee` e where d.id=e.dep_id and e.type=1 and d.id=?";
	private static final String GetManager = "select e.id as id from `department` d, `employee` e where d.id=e.dep_id and e.type=1 and d.id=? limit 1";
	
	private Department generate(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("id"));
		dep.setName(rs.getString("name"));
		return dep;
	}
	
	@Override
	public Department get(int id) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Department dep = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetById);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				dep = generate(rs);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dep;
	}

	@Override
	public Department get(String name) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Department dep = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetByName);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				dep = generate(rs);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dep;
	}

	@Override
	public Vector<Department> get() {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Vector<Department> ret = new Vector<Department>();
		try {
			PreparedStatement ps = con.prepareStatement(GetAll);
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
	public boolean hasManager(int depId) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		int cnt = 0;
		try {
			PreparedStatement ps = con.prepareStatement(HasManager);
			ps.setInt(1, depId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt > 0;
	}

	@Override
	public Employee getManager(Department d) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Employee emp = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetManager);
			ps.setInt(1, d.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				emp = new EmployeeDao().get(rs.getInt("id"));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emp;
	}

}
