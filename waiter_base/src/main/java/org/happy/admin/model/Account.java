package org.happy.admin.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("m_account")
public class Account extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public int accountid;

	public int getAccountid() {
		return accountid;
	}

	public int getManagerid() {
		return managerid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column
	public int managerid;

	@Column
	public String openid;

}
