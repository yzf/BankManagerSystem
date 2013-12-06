package info.yzf.database;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * mysql实现的数据库
 * @author yzf
 *
 */
public class MysqlDatabase {

	private MysqlDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static class InstanceHolder {
		private static MysqlDatabase instance = new MysqlDatabase();
	} 
	
	public static MysqlDatabase getInstance() {
		return InstanceHolder.instance;
	}
	
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bms?characterEncoding=utf8", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
