package info.yzf.database.model;

import java.io.Serializable;

public class Department extends BaseModel implements Serializable {

	private static final long serialVersionUID = -861451890195680972L;

	private String name;//部门名称
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj) ||
				name.equals(((Department)obj).getName());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("(");
		sb.append("id:" + id + ", ");
		sb.append("部门名:" + name + ")");
		return sb.toString();
	}

	public Department() {
		super();
	}
	
	public Department(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
