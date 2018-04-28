package com.market.maicheng.model;

public class City {

	private int Id;
	
	private String sid;
	
	public String getSid() {
		return Id+"";
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	private String Name;
	
	private int Pid;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getPid() {
		return Pid;
	}

	public void setPid(int pid) {
		Pid = pid;
	}
}
