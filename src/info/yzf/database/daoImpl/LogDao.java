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
	private static final String Add = "insert into `log` (`time`, `e_id`, `top_ua_id`, `bottom_ua_id`, `operation`, `type`) values (?, ?, ?, ?, ?, ?)";
	private static final String GetA = "select l.id as id, l.time as time, l.e_id as e_id, l.top_ua_id as top_ua_id,"
			+ " l.bottom_ua_id as bottom_ua_id, l.operation as operation, l.type as type"
			+ " from `log` l, `user_account` ua where l.top_ua_id=ua.id and ua.a_id=? and l.time between ? and ?";
	private static final String GetE = "select * from `log` where `e_id`=? and `time` between ? and ?";
	private static final String GetD = "select l.id as id, l.time as time, l.e_id as e_id, l.top_ua_id as top_ua_id,"
			+ " l.bottom_ua_id as bottom_ua_id, l.operation as operation, l.type as type"
			+ " from `log` l, `employee` e where l.e_id=e.id and e.dep_id=? and `time` between ? and ?";
	private static final String GetAll = "select * from `log` l where `time` between ? and ?";
	
	private Log generate(ResultSet rs) throws SQLException {
		Log log = new Log();
		log.setId(rs.getInt("id"));
		log.setTime(rs.getTimestamp("time"));
		log.setEmployee(new EmployeeDao().get(rs.getInt("e_id")));
		log.setTop(new UserAccountDao().get(rs.getInt("top_ua_id")));
		log.setBottom(new UserAccountDao().get(rs.getInt("bottom_ua_id")));
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
			ps.setInt(2, log.getEmployee().getId());
			ps.setInt(3, log.getTop().getId());
			if (log.getBottom() != null) {
				ps.setInt(4, log.getBottom().getId());
			}
			else {
				ps.setInt(4, 0);
			}
			ps.setString(5, log.getOperation());
			ps.setString(6, log.getType());
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
			ps.setInt(1, account.getId());
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
