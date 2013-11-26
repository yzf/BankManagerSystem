package info.yzf.database;

import info.yzf.database.model.Account;
import info.yzf.database.model.BaseModel;
import info.yzf.database.model.Department;
import info.yzf.database.model.Employee;
import info.yzf.database.model.EnterpriseAccount;
import info.yzf.database.model.Log;
import info.yzf.database.model.User;
import info.yzf.database.model.UserAccount;
import info.yzf.util.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
/**
 * 串行化 实例 实现的数据库
 * @author yzf
 *
 */
public class SerialDatabase {
	
	private static final String DB_FILE = "database.data";
	private static final int USERNAME_LEN = 19;
	
	private Map<Class<?>, Vector<BaseModel>> map;
	
	@SuppressWarnings("unchecked")
	private SerialDatabase() {
		super();
		File dbFile = new File(DB_FILE);
		if (dbFile.exists()) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DB_FILE));
				map = (Map<Class<?>, Vector<BaseModel>>)ois.readObject();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			reset();
			save();
		}
	}
	
	private static class InstanceHolder {
		private static SerialDatabase instance = new SerialDatabase();
	}
	
	public static SerialDatabase getInstance() {
		return InstanceHolder.instance;
	}
	/**
	 * 序列化所有数据到文件
	 */
	public void save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DB_FILE));
			oos.writeObject(map);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 还原所有数据
	 */
	public void reset() {
		map = new HashMap<Class<?>, Vector<BaseModel>>();
		map.put(Account.class, new Vector<BaseModel>());
		map.put(User.class, new Vector<BaseModel>());
		map.put(UserAccount.class, new Vector<BaseModel>());
		map.put(EnterpriseAccount.class, new Vector<BaseModel>());
		map.put(Employee.class, new Vector<BaseModel>());
		map.put(Log.class, new Vector<BaseModel>());
		map.put(Department.class, new Vector<BaseModel>());
		//添加初始账号
		Department d = new Department("业务部门");
		Department dd = new Department("财政部门");
		Employee admin = new Employee("系统管理员", "admin", "admin", Employee.Admin, null);	
		Employee e3 = new Employee("女王大人", "zhou", "1", Employee.Conductor, null);
		Employee e2 = new Employee("袁同学", "yuan", "1", Employee.Manager, d);	
		Employee e1 = new Employee("霍同学", "huo", "1", Employee.Operator, d);
		
		add(admin);
		add(e3);
		add(e2);
		add(e1);
		add(d);
		add(dd);
	}
	public void dump() {
		for (Map.Entry<Class<?>, Vector<BaseModel>> entry : map.entrySet()) {
			String key = entry.getKey().toString();
			Vector<BaseModel> list = entry.getValue();
			System.out.println(key);
			for (BaseModel bm : list) {
				System.out.println(bm);
			}
			System.out.println();
		}
	}
	/**
	 * 获取数据
	 * @param c
	 * @return
	 */
	public Vector<BaseModel> get(Class<?> c) {
		return map.get(c);
	}
	/**
	 * 生成一个新的id
	 * @param c
	 * @return
	 */
	public int generateId(Class<?> c) {
		Vector<BaseModel> list = map.get(c);
		int id = 0;
		for (BaseModel bm : list) {
			if (bm.getId() > id) {
				id = bm.getId();
			}
		}
		++ id;
		return id;
	}
	/**
	 * 添加条目
	 * @param c 类型
	 * @param obj 条目
	 * @return 元素已经存在则返回该元素
	 */
	public BaseModel add(BaseModel obj) {
		obj.setId(generateId(obj.getClass()));
		Vector<BaseModel> list = map.get(obj.getClass());
		for (BaseModel bm : list) {
			if (obj.equals(bm)) {
				return bm;
			}
		}
		list.add(obj);
		return obj;
	}
	/**
	 * 删除条目
	 * @param c 类型
	 * @param obj 条目
	 */
	public void remove(BaseModel obj) {
		Vector<BaseModel> list = map.get(obj.getClass());
		for (int i = 0; i < list.size(); ++ i) {
			if (obj.equals(list.get(i))) {
				list.remove(i);
				break;
			}
		}
	}
	
	private boolean isUsed(Class<?> c, String username) {
		for (BaseModel bm : get(c)) {
			if (Employee.class.equals(c)) {
				Employee e = (Employee) bm;
				if (e.getUsername().equals(username)) {
					return true;
				}
			}
			if (Account.class.equals(c)) {
				Account ca = (Account) bm;
				if (ca.getUsername().equals(username)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public String generateUsername(Class<?> c) {
		String username;
		do {
			username = Util.generateString(USERNAME_LEN);
		} while (isUsed(c, username));
		return username;
	}
}
