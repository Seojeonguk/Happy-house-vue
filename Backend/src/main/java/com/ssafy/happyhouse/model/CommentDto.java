package com.ssafy.happyhouse.model;

public class CommentDto {
	private String userid;
	private String date;
	private String comments;
	private int aptno;
	private int grade;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getAptno() {
		return aptno;
	}
	public void setAptno(int aptno) {
		this.aptno = aptno;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
}
