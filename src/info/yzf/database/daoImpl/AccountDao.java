package info.yzf.database.daoImpl;

import info.yzf.database.MysqlDatabase;
import info.yzf.database.dao.IAccountDao;
import info.yzf.database.model.Account;
import info.yzf.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao implements IAccountDao {
	
	private static final String GetById = "select * from `account` where `id`=? limit 1";
	private static final String GetByUsername = "select * from `account` where `username`=? limit 1";
	private static final String UpdateBalance = "update `account` set `balance`=? where `id`=? limit 1";
	private static final String Add = "insert into `account` (`username`, `a_type`, `u_type`, `balance`, `freezed`) values (?, ?, ?, ?, ?)";
	private static final String Delete = "delete from `account` where `username`=? limit 1";

	private Account generate(ResultSet rs) throws SQLException {
		Account account = new Account();
		account.setId(rs.getInt("id"));
		account.setUsername(rs.getString("username"));
		account.setaType(rs.getInt("a_type"));
		account.setuType(rs.getInt("u_type"));
		account.setBalance(rs.getDouble("balance"));
		account.setFreezed(rs.getBoolean("freezed"));
		return account;
	}
	
	@Override
	public String generateUsername() {
		// TODO Auto-generated method stub
		return Util.generateString(12);
	}

	@Override
	public Account get(int id) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Account ant = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetById);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ant = generate(rs);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ant;
	}

	@Override
	public Account get(String username) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Account ant = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetByUsername);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ant = generate(rs);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ant;
	}

	@Override
	public Account updateBalance(int id, double balance) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Account ant = null;
		try {
			PreparedStatement ps = con.prepareStatement(UpdateBalance);
			ps.setDouble(1, balance);
			ps.setInt(2, id);
			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				ant = get(id);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ant;
	}

	@Override
	public Account add(Account account) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		Account ant = null;
		try {
			PreparedStatement ps = con.prepareStatement(Add, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, account.getUsername());
			ps.setInt(2, account.getaType());
			ps.setInt(3, account.getuType());
			ps.setDouble(4, account.getBalance());
			ps.setBoolean(5, account.isFreezed());
			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					ant = get(rs.getInt(1));
				}
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ant;
	}

	@Override
	public void delete(Account account) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(Delete);
			ps.setString(1, account.getUsername());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
