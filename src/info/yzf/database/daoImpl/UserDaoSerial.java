package info.yzf.database.daoImpl;

import info.yzf.database.SerialDatabase;
import info.yzf.database.dao.IUserDao;
import info.yzf.database.model.BaseModel;
import info.yzf.database.model.User;

public class UserDaoSerial implements IUserDao {

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(User.class)) {
			User c = (User) bm;
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	@Override
	public User get(String identity) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(User.class)) {
			User c = (User) bm;
			if (c.getIdentity().equals(identity)) {
				return c;
			}
		}
		return null;
	}

	@Override
	public User add(User user) {
		// TODO Auto-generated method stub
		return (User) SerialDatabase.getInstance().add(user);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		SerialDatabase.getInstance().remove(user);
	}
}
