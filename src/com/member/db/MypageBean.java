package com.member.db;

import java.sql.Timestamp;

public class MypageBean {

	private String UserID;
	private String ITEM_SEQ;
	private String ITME_NAME;
	private String ENTP_NAME;
	private String MATERIAL_NAME;
	private String VALID_TERM;
	private String Side_Effect;
	private Timestamp date;
	private String cycle;
	private int graph_y;
	
	public int getGraph_y() {
		return graph_y;
	}
	public void setGraph_y(int graph_y) {
		this.graph_y = graph_y;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getITEM_SEQ() {
		return ITEM_SEQ;
	}
	public void setITEM_SEQ(String iTEM_SEQ) {
		ITEM_SEQ = iTEM_SEQ;
	}
	public String getITME_NAME() {
		return ITME_NAME;
	}
	public void setITME_NAME(String iTME_NAME) {
		ITME_NAME = iTME_NAME;
	}
	public String getENTP_NAME() {
		return ENTP_NAME;
	}
	public void setENTP_NAME(String eNTP_NAME) {
		ENTP_NAME = eNTP_NAME;
	}
	public String getMATERIAL_NAME() {
		return MATERIAL_NAME;
	}
	public void setMATERIAL_NAME(String mATERIAL_NAME) {
		MATERIAL_NAME = mATERIAL_NAME;
	}
	public String getVALID_TERM() {
		return VALID_TERM;
	}
	public void setVALID_TERM(String vALID_TERM) {
		VALID_TERM = vALID_TERM;
	}
	public String getSide_Effect() {
		return Side_Effect;
	}
	public void setSide_Effect(String side_Effect) {
		Side_Effect = side_Effect;
	}
	
	
}
