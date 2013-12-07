package info.yzf.database.daoImpl;

import info.yzf.database.MysqlDatabase;
import info.yzf.database.dao.IUserDao;
import info.yzf.database.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements IUserDao {

	private static final String GetById = "select * from `user` where `id`=? limit 1";
	private static final String GetByIdentity = "select * from `user` where `identity`=? limit 1";
	private static final String Add = "insert into `user` (`identity`, `name`) values (?, ?)";
	private static final String Delete = "delete from `user` where `identity`=? limit 1";
	
	private User generate(ResultSet rs) throws SQLException {
		User u = new User();
		u.setId(rs.getInt("id"));
		u.setIdentity(rs.getString("identity"));
		u.setName(rs.getString("name"));
		return u;
	}
	
	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		User u = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetById);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				u = generate(rs);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public User get(String identity) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		User u = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetByIdentity);
			ps.setString(1, identity);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				u = generate(rs);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public User add(User user) {
		// TODO Auto-generated method stub
		User u = get(user.getIdentity());
		if (u != null) {
			return u;
		}
		Connection con = MysqlDatabase.getInstance().getConnection();
		u = null;
		try {
			PreparedStatement ps = con.prepareStatement(Add);
			ps.setString(1, user.getIdentity());
			ps.setString(2, user.getName());
			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				u = get(user.getIdentity());
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(Delete);
			ps.setString(1, user.getIdentity());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
