package info.yzf.database.daoImpl;

import info.yzf.database.SerialDatabase;
import info.yzf.database.dao.IUserAccountDao;
import info.yzf.database.model.BaseModel;
import info.yzf.database.model.UserAccount;

public class UserAccountDaoSerial implements IUserAccountDao {
	
	@Override
	public UserAccount get(int id) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(UserAccount.class)) {
			UserAccount ca = (UserAccount) bm;
			if (ca.getId() == id) {
				return ca;
			}
		}
		return null;
	}

	@Override
	public UserAccount get(String username) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(UserAccount.class)) {
			UserAccount ca = (UserAccount) bm;
			if (ca.getAccount().getUsername().equals(username)) {
				return ca;
			}
		}
		return null;
	}
	
	@Override
	public UserAccount get(String username, String password) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(UserAccount.class)) {
			UserAccount ca = (UserAccount) bm;
			if (ca.getAccount().getUsername().equals(username) &&
					ca.getPassword().equals(password)) {
				return ca;
			}
		}
		return null;
	}
	
	@Override
	public UserAccount get(String identity, String username, String password, boolean usePassword) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(UserAccount.class)) {
			UserAccount ca = (UserAccount) bm;
			if (ca.getUser().getIdentity().equals(identity) &&
					ca.getAccount().getUsername().equals(username)) {
				if (usePassword) {
					if (ca.getPassword().equals(password)) {
						return ca;
					}
				} 
				else {
					return ca;
				}
			}
		}
		return null;
	}
	
	@Override
	public UserAccount add(UserAccount userAccount) {
		// TODO Auto-generated method stub
		return (UserAccount) SerialDatabase.getInstance().add(userAccount);
	}
	
	@Override
	public void delete(UserAccount userAccount) {
		// TODO Auto-generated method stub
		SerialDatabase.getInstance().remove(userAccount);
	}
	
	@Override
	public UserAccount updatePassword(int id, String password) {
		// TODO Auto-generated method stub
		UserAccount ca = get(id);
		ca.setPassword(password);
		return ca;
	}

	@Override
	public int getSuperNumber(String username) {
		// TODO Auto-generated method stub
		int ret = 0;
		for (BaseModel bm : SerialDatabase.getInstance().get(UserAccount.class)) {
			UserAccount ca = (UserAccount) bm;
			if (ca.getAccount().getUsername().equals(username)
					&& ca.getRole() == UserAccount.Super) {
				++ ret;
			}
		}
		return ret;
	}
}
