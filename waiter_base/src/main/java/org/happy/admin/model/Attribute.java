package org.happy.admin.model;

import java.util.List;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Table;

@Table("m_attribute")
public class Attribute extends BaseObject {

	public List<Attri> getTastelist() {
		return tastelist;
	}

	public void setTastelist(List<Attri> tastelist) {
		this.tastelist = tastelist;
	}

	public List<Attri> getSizelist() {
		return sizelist;
	}

	public void setSizelist(List<Attri> sizelist) {
		this.sizelist = sizelist;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public String getStrTaste() {
		return strTaste;
	}

	public void setStrTaste(String strTaste) {
		this.strTaste = strTaste;
	}

	public String getStrSize() {
		return strSize;
	}

	public void setStrSize(String strSize) {
		this.strSize = strSize;
	}

	public int accountid;

	public String strTaste;

	public String strSize;

	@ManyMany(target = Attri.class, relation = "m_attr_taste", from = "attribute_id", to = "attr_id")
	public List<Attri> tastelist;

	@ManyMany(target = Attri.class, relation = "m_attr_size", from = "attribute_id", to = "attr_id")
	public List<Attri> sizelist;

}
