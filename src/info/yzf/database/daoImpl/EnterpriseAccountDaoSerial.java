package info.yzf.database.daoImpl;

import info.yzf.database.SerialDatabase;
import info.yzf.database.dao.IEnterpriseAccountDao;
import info.yzf.database.model.BaseModel;
import info.yzf.database.model.EnterpriseAccount;

public class EnterpriseAccountDaoSerial implements IEnterpriseAccountDao {

	@Override
	public EnterpriseAccount get(int id) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(EnterpriseAccount.class)) {
			EnterpriseAccount e = (EnterpriseAccount) bm;
			if (e.getId() == id) {
				return e;
			}
		}
		return null;
	}

	@Override
	public EnterpriseAccount get(String username) {
		// TODO Auto-generated method stub
		for (BaseModel bm : SerialDatabase.getInstance().get(EnterpriseAccount.class)) {
			EnterpriseAccount e = (EnterpriseAccount) bm;
			if (e.getUsername().equals(username)) {
				return e;
			}
		}
		return null;
	}

	@Override
	public EnterpriseAccount add(EnterpriseAccount ea) {
		// TODO Auto-generated method stub
		return (EnterpriseAccount) SerialDatabase.getInstance().add(ea);
	}

	@Override
	public void delete(EnterpriseAccount ea) {
		// TODO Auto-generated method stub
		SerialDatabase.getInstance().remove(ea);
	}

	

}
