package com.studcloud;

public class ConnectionOne {

	String url,unm,pwd,classnm;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUnm() {
		return unm;
	}
	public void setUnm(String unm) {
		this.unm = unm;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getClassnm() {
		return classnm;
	}
	public void setClassnm(String classnm) {
		this.classnm = classnm;
	}
	public ConnectionOne()
	{
		url="jdbc:mysql://localhost:3306/studcloud";
		unm="root";
		pwd="sql1";
		classnm="com.mysql.jdbc.Driver";
		
	}
	
}
