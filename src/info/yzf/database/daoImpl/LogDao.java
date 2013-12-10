package info.yzf.database.daoImpl;

import info.yzf.database.MysqlDatabase;
import info.yzf.database.dao.ILogDao;
import info.yzf.database.model.Account;
import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;
import info.yzf.database.model.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

public class LogDao implements ILogDao {
	
	private static final String GetById = "select * from `log` where `id`=? limit 1";
	private static final String Add = "insert into `log` (`time`, `emp_id`, `emp_n`, `top_u_n`, `top_a_un`, `bottom_u_n`, `bottom_a_un`, `operation`, `type`) values (?,?,?,?,?,?,?,?,?)";
	private static final String GetA = "select l.id as id, l.time as time, l.emp_id as emp_id, l.emp_n as emp_n, l.top_u_n as top_u_n, l.top_a_un as top_a_un,"
			+ " l.bottom_u_n as bottom_u_n, l.bottom_a_un as bottom_a_un, l.operation as operation, l.type as type"
			+ " from `log` l, `account` a where l.top_a_un=a.username and a.username=? and time between ? and ?";
	private static final String GetE = "select * from `log` where `emp_id`=? and `time` between ? and ?";
	private static final String GetD = "select l.id as id, l.time as time, l.emp_n as emp_n, l.top_u_n as top_u_n, l.top_a_un as top_a_un,"
			+ " l.bottom_u_n as bottom_u_n, l.bottom_a_un as bottom_a_un, l.operation as operation, l.type as type"
			+ " from `log` l, `employee` e where l.emp_id=e.id and e.dep_id=? and `time` between ? and ?";
	private static final String GetAll = "select * from `log` l where `time` between ? and ?";
	
	private Log generate(ResultSet rs) throws SQLException {
		Log log = new Log();
		log.setId(rs.getInt("id"));
		log.setTime(rs.getTimestamp("time"));
		log.setEmpId(rs.getInt("emp_id"));
		log.setEmpName(rs.getString("emp_n"));
		log.setTopName(rs.getString("top_u_n"));
		log.setTopUsername(rs.getString("top_a_un"));
		log.setBottomName(rs.getString("bottom_u_n"));
		log.setBottomUsername(rs.getString("bottom_a_un"));
		log.setOperation(rs.getString("operation"));
		log.setType(rs.getString("type"));
		return log;
	}
	@Override
	public Log get(int id) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Log log = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetById);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				log = generate(rs);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return log;
	}

	@Override
	public Log add(Log log) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Log l = null;
		try {
			PreparedStatement ps = con.prepareStatement(Add, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setTimestamp(1, log.getTime());
			ps.setInt(2, log.getEmpId());
			ps.setString(3, log.getEmpName());
			ps.setString(4, log.getTopName());
			ps.setString(5, log.getTopUsername());
			ps.setString(6, log.getBottomName());
			ps.setString(7, log.getBottomUsername());
			ps.setString(8, log.getOperation());
			ps.setString(9, log.getType());
			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					l = get(rs.getInt(1));
				}
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public Vector<Log> get(Account account, Timestamp begin, Timestamp end) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Vector<Log> ret = new Vector<Log>();
		try {
			PreparedStatement ps = con.prepareStatement(GetA);
			ps.setString(1, account.getUsername());
			ps.setTimestamp(2, begin);
			ps.setTimestamp(3, end);
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
	public Vector<Log> get(Employee employee, Timestamp begin, Timestamp end) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Vector<Log> ret = new Vector<Log>();
		try {
			PreparedStatement ps = con.prepareStatement(GetE);
			ps.setInt(1, employee.getId());
			ps.setTimestamp(2, begin);
			ps.setTimestamp(3, end);
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
	public Vector<Log> get(Department department, Timestamp begin, Timestamp end) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Vector<Log> ret = new Vector<Log>();
		try {
			PreparedStatement ps = con.prepareStatement(GetD);
			ps.setInt(1, department.getId());
			ps.setTimestamp(2, begin);
			ps.setTimestamp(3, end);
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
	public Vector<Log> get(Timestamp begin, Timestamp end) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Vector<Log> ret = new Vector<Log>();
		try {
			PreparedStatement ps = con.prepareStatement(GetAll);
			ps.setTimestamp(1, begin);
			ps.setTimestamp(2, end);
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
