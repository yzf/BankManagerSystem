package info.yzf.database.model;

import java.io.Serializable;

public class BaseModel implements Serializable, Cloneable {

	private static final long serialVersionUID = 3282070218167928193L;
	
	protected int id;

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return id == ((BaseModel)obj).id;
	}
	
	@Override
	public BaseModel clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (BaseModel)super.clone();
	}

	public BaseModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BaseModel(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
