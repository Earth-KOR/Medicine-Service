package com.service.db;

import java.sql.Timestamp;

public class serviceBean {

	private int service_num;
	private String service_id;
	private String service_pw;
	private String service_subject;
	private String service_content;
	private String service_file;
	private int service_re_ref;
	private int service_re_lev;
	private int service_re_seq;
	private int service_readcount;
	private Timestamp service_date;
	
	
	
	public int getService_num() {
		return service_num;
	}
	public void setService_num(int service_num) {
		this.service_num = service_num;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public String getService_pw() {
		return service_pw;
	}
	public void setService_pw(String service_pw) {
		this.service_pw = service_pw;
	}
	public String getService_subject() {
		return service_subject;
	}
	public void setService_subject(String service_subject) {
		this.service_subject = service_subject;
	}
	public String getService_content() {
		return service_content;
	}
	public void setService_content(String service_content) {
		this.service_content = service_content;
	}
	public String getService_file() {
		return service_file;
	}
	public void setService_file(String service_file) {
		this.service_file = service_file;
	}
	public int getService_re_ref() {
		return service_re_ref;
	}
	public void setService_re_ref(int service_re_ref) {
		this.service_re_ref = service_re_ref;
	}
	public int getService_re_lev() {
		return service_re_lev;
	}
	public void setService_re_lev(int service_re_lev) {
		this.service_re_lev = service_re_lev;
	}
	public int getService_re_seq() {
		return service_re_seq;
	}
	public void setService_re_seq(int service_re_seq) {
		this.service_re_seq = service_re_seq;
	}
	public int getService_readcount() {
		return service_readcount;
	}
	public void setService_readcount(int service_readcount) {
		this.service_readcount = service_readcount;
	}
	public Timestamp getService_date() {
		return service_date;
	}
	public void setService_date(Timestamp service_date) {
		this.service_date = service_date;
	}
	
	
	
}
