package org.happy.admin.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("m_attr")
public class Attri extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	public int id;
	
	@Column
	public String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
