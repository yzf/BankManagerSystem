package info.yzf.database.daoImpl;

import info.yzf.database.SerialDatabase;
import info.yzf.database.dao.IAccountDao;
import info.yzf.database.model.Account;
import info.yzf.database.model.BaseModel;

import java.util.Vector;

public class AccountDaoSerial implements IAccountDao {
	
	@Override
	public String generateUsername() {
		// TODO Auto-generated method stub
		return SerialDatabase.getInstance().generateUsername(Account.class);
	}

	@Override
	public Account get(int id) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(Account.class)) {
			Account a = (Account) bm;
			if (a.getId() == id) {
				return a;
			}
		}
		return null;
	}
	
	@Override
	public Account get(String username) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(Account.class)) {
			Account a = (Account) bm;
			if (a.getUsername().equals(username)) {
				return a;
			}
		}
		return null;
	}

	@Override
	public Account updateBalance(int id, double balance) {
		// TODO Auto-generated method stub
		Account a = get(id);
		a.setBalance(balance);
		return a;
	}

	@Override
	public void delete(Account account) {
		// TODO Auto-generated method stub
		SerialDatabase.getInstance().remove(account);
	}

	@Override
	public Account add(Account account) {
		// TODO Auto-generated method stub
		return (Account) SerialDatabase.getInstance().add(account);
	}

	@Override
	public Vector<Account> get() {
		// TODO Auto-generated method stub
		Vector<Account> ret = new Vector<Account>();
		for (BaseModel bm : SerialDatabase.getInstance().get(Account.class)) {
			Account a = (Account) bm;
			ret.add(a);
		}
		return ret;
	}

	@Override
	public Account changeStatus(int id, boolean isFreezed) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(Account.class)) {
			Account a = (Account) bm;
			if (a.getId() == id) {
				a.setFreezed(isFreezed);
				return a;
			}
		}
		return null;
	}

	
}
