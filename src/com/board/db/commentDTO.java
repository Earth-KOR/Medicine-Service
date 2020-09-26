package com.board.db;

import java.sql.Timestamp;

public class commentDTO {

	private int board_num;
	private int comment_num;
	private String id;
	private String com_con;
	private Timestamp date;
	
	
	//get,set
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCom_con() {
		return com_con;
	}
	public void setCom_con(String com_con) {
		this.com_con = com_con;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	




	
	
	
}
