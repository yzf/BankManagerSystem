package info.yzf.database.daoImpl;

import info.yzf.database.MysqlDatabase;
import info.yzf.database.dao.IEnterpriseAccountDao;
import info.yzf.database.model.EnterpriseAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnterpriseAccountDao implements IEnterpriseAccountDao {

	private static final String GetById = "select * from `enterprise_account` where `id`=? limit 1";
	private static final String GetByUsername = "select ea.id as id from `enterprise_account` ea, `account` a where ea.a_id=a.id and a.username=? limit 1";
	private static final String Add = "insert into `enterprise_account` (`e_id`, `a_id`) values (?, ?)";
	private static final String Delete = "delete from `enterprise_account` where `e_id`=? and `a_id`=? limit 1";
	
	private EnterpriseAccount generate(ResultSet rs) throws SQLException {
		EnterpriseAccount ea = new EnterpriseAccount();
		ea.setId(rs.getInt("id"));
		ea.setEnterprise(new UserDao().get(rs.getInt("e_id")));
		ea.setAccount(new AccountDao().get(rs.getInt("a_id")));
		ea.setUsername(ea.getAccount().getUsername());
		return ea;
	}
	
	@Override
	public EnterpriseAccount get(int id) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		EnterpriseAccount ea = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetById);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ea = generate(rs);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ea;
	}

	@Override
	public EnterpriseAccount get(String username) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		EnterpriseAccount ea = null;
		try {
			PreparedStatement ps = con.prepareStatement(GetByUsername);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ea = get(rs.getInt("id"));
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ea;
	}

	@Override
	public EnterpriseAccount add(EnterpriseAccount ea) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		EnterpriseAccount ret = null;
		try {
			PreparedStatement ps = con.prepareStatement(Add, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, ea.getEnterprise().getId());
			ps.setInt(2, ea.getAccount().getId());
			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					ret = get(rs.getInt(1));
				}
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public void delete(EnterpriseAccount ea) {
		// TODO Auto-generated method stub
		Connection con = MysqlDatabase.getInstance().getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(Delete);
			ps.setInt(1, ea.getEnterprise().getId());
			ps.setInt(2, ea.getAccount().getId());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
