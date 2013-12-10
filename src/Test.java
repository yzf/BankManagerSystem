import info.yzf.database.daoImpl.AccountDao;
import info.yzf.database.daoImpl.EmployeeDao;
import info.yzf.database.daoImpl.LogDao;
import info.yzf.database.model.Account;
import info.yzf.database.model.Employee;
import info.yzf.util.Util;

import java.io.FileNotFoundException;
import java.io.IOException;




public class Test {

	/**
	 * @param args
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Account account = new AccountDao().get("123456");
		Employee emp = new EmployeeDao().get("yuan");
		System.out.println(new LogDao().get(emp, Util.getToday("2013-12-08 12:11:11"), Util.getToday("2013-12-10 12:11:11")));
	}
}
