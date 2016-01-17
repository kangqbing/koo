package org.happy.wx.model;

import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.foxinmy.weixin4j.mp.model.User;
@Table("wx_user")
public class Wx_User extends User{
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
