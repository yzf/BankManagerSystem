package info.yzf.database.daoImpl;

import info.yzf.database.MysqlDatabase;
import info.yzf.database.dao.IUserAccountDao;
import info.yzf.database.model.UserAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountDao implements IUserAccountDao {
	
	private static final String GetById = "select * from `user_account` where `id`=? limit 1";
	private static final String GetByUsername = "select ua.id as id from `user_account` ua, `account` a where ua.a_id=a.id and a.username=? limit 1";
	private static final String GetByUsernamePwd = "select ua.id as id from `user_account` ua, `account` a where ua.a_id=a.id and a.username=? and ua.password=? limit 1";
	private static final String GetByIdtyUsernamePwd = "select ua.id as id from `user_account` ua, `user` u, `account` a"
			+ " where ua.u_id=u.id and ua.a_id=a.id and u.identity=? and a.username=? and ua.password=? limit 1";
	private static final String GetByIdtyUsernameNoPwd = "select ua.id as id from `user_account` ua, `user` u, `account` a"
			+ " where ua.u_id=u.id and ua.a_id=a.id and u.identity=? and a.username=? limit 1";
	private static final String Add = "insert into `user_account` (`u_id`, `a_id`, `password`, `role`) values (?, ?, ?, ?)";
	private static final String Delete = "delete from `user_account` where `id`=? limit 1";
	private static final String UpdatePassword = "update `user_account` set `password`=? where `id`=? limit 1";
	private static final String GetSuperNumber = "select count(*) from `user_account` ua, `account` a"
			+ " where ua.a_id=a.id and a.username=?";
	
	private UserAccount generate(ResultSet rs) throws SQLException {
		UserAccount ua = new UserAccount();
		ua.setId(rs.getInt("id"));
		ua.setUser(new UserDao().get(rs.getInt("u_id")));
		ua.setAccount(new AccountDao().get(rs.getInt("a_id")));
		ua.setPassword(rs.getString("password"));
		ua.setRole(rs.getInt("role"));
		return ua;
	}
	
	@Override
	public UserAccount get(int id) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		UserAccount ua = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetById);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ua = generate(rs);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ua;
	}

	@Override
	public UserAccount get(String username) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		UserAccount ua = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetByUsername);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ua = get(rs.getInt("id"));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ua;
	}

	@Override
	public UserAccount get(String username, String password) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		UserAccount ua = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetByUsernamePwd);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ua = get(rs.getInt("id"));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ua;
	}

	@Override
	public UserAccount get(String identity, String username, String password,
			boolean usePassword) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		PreparedStatement ps = null;
		UserAccount ua = null;
		try {
			if (usePassword) {
				ps = con.prepareStatement(GetByIdtyUsernamePwd);
			}
			else {
				ps = con.prepareStatement(GetByIdtyUsernameNoPwd);
			}
			ps.setString(1, identity);
			ps.setString(2, username);
			if (usePassword) {
				ps.setString(3, password);
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ua = get(rs.getInt("id"));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ua;
	}

	@Override
	public UserAccount add(UserAccount userAccount) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		UserAccount ua = null;
		try {
			PreparedStatement ps = con.prepareStatement(Add, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, userAccount.getUser().getId());
			ps.setInt(2, userAccount.getAccount().getId());
			ps.setString(3, userAccount.getPassword());
			ps.setInt(4, userAccount.getRole());
			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					ua = get(rs.getInt(1));
				}
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ua;
	}

	@Override
	public void delete(UserAccount userAccount) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(Delete);
			ps.setInt(1, userAccount.getUser().getId());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public UserAccount updatePassword(int id, String password) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		UserAccount ua = null;
		try {
			PreparedStatement ps = con.prepareStatement(UpdatePassword);
			ps.setString(1, password);
			ps.setInt(2, id);
			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				ua = get(id);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ua;
	}

	@Override
	public int getSuperNumber(String username) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		int ret = 0;
		try {
			PreparedStatement ps = con.prepareStatement(GetSuperNumber);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ret = rs.getInt(1);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

}
