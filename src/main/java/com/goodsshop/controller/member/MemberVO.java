package com.goodsshop.controller.member;

import java.sql.Timestamp;

import lombok.Data;
@Data
public class MemberVO {

	private String userid;
	private int gseq;
	private String pwd;
	private String name;
	private String email;
	private String phone;
	private String zip_code;
	private String address;
	private String d_address;
	private Timestamp indate;
	private Timestamp last_login_time;
	private int is_login;

}