package com.utils;

import java.util.Date;

public class tiezi {
	
	public tiezi(String name, String url, Date time, String readNum,
				 String huifuNum) {
		super();
		this.name = name;
		this.url = url;
		this.time = time;
		this.readNum = readNum;
		this.huifuNum = huifuNum;
	}

	private String name;
	
	private String url;
	
	private Date time;
	
	private String readNum;
	
	private String huifuNum;
	
	public String toString()
	{
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getReadNum() {
		return readNum;
	}

	public void setReadNum(String readNum) {
		this.readNum = readNum;
	}

	public String getHuifuNum() {
		return huifuNum;
	}

	public void setHuifuNum(String huifuNum) {
		this.huifuNum = huifuNum;
	}

}
