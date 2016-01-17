package org.happy.admin.model;

import java.io.Serializable;

import org.nutz.json.Json;

public class BaseObject implements Serializable{

	private static final long serialVersionUID = 1L;
	@Override
	public String toString() {
		return Json.toJson(this);
	}

}