package info.yzf.database.daoImpl;

import info.yzf.database.MysqlDatabase;
import info.yzf.database.dao.ISummaryDao;
import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;
import info.yzf.database.model.Summary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

public class SummaryDao implements ISummaryDao {
	
	private static final String GetById = "select * from `summary` where `id`=? limit 1";
	private static final String Add = "insert into `summary` (`time`, `e_id`, `content`) values (?, ?, ?)";
	private static final String Delete = "delete from `summary` where `id`=? limit 1";
	private static final String GetE = "select * from `summary` where `e_id`=? and `time` between ? and ?";
	private static final String GetD = "select s.id as id, s.time as time, s.content as content, s.e_id as e_id from `summary` s, `employee` e where s.e_id=e.id and e.dep_id=? and `time` between ? and ?";
	private static final String GetAll = "select * from `summary` where `time` between ? and ?";
	
	private Summary generate(ResultSet rs) throws SQLException {
		Summary s = new Summary();
		s.setId(rs.getInt("id"));
		s.setTime(rs.getTimestamp("time"));
		s.setEmployee(new EmployeeDao().get(rs.getInt("e_id")));
		s.setContent(rs.getString("content"));
		return s;
	}
	
	@Override
	public Summary get(int id) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Summary s = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetById);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				s = generate(rs);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	@Override
	public Summary add(Summary summary) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Summary s = null;
		try {
			PreparedStatement ps = con.prepareStatement(Add, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setTimestamp(1, summary.getTime());
			ps.setInt(2, summary.getEmployee().getId());
			ps.setString(3, summary.getContent());
			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					s = get(rs.getInt(1));
				}
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public void delete(Summary summary) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(Delete);
			ps.setInt(1, summary.getId());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Vector<Summary> get(Employee emp, Timestamp begin, Timestamp end) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Vector<Summary> ret = new Vector<Summary>();
		try {
			PreparedStatement ps = con.prepareStatement(GetE);
			ps.setInt(1, emp.getId());
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
	public Vector<Summary> get(Department d, Timestamp begin, Timestamp end) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Vector<Summary> ret = new Vector<Summary>();
		try {
			PreparedStatement ps = con.prepareStatement(GetD);
			ps.setInt(1, d.getId());
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
	public Vector<Summary> get(Timestamp begin, Timestamp end) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Vector<Summary> ret = new Vector<Summary>();
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
