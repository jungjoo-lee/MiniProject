package com.goodsshop.controller;

import com.goodsshop.controller.action.Action;
import com.goodsshop.controller.action.IndexAction;
import com.goodsshop.controller.action.member.IDCheckAction;
import com.goodsshop.controller.action.member.JoinAction;
import com.goodsshop.controller.action.member.JoinPageAction;
import com.goodsshop.controller.action.mypage.DeleteMemberAction;
import com.goodsshop.controller.member.login.FindIdAction;
import com.goodsshop.controller.member.login.FindIdFormAction;
import com.goodsshop.controller.member.login.FindZipnumAction;
import com.goodsshop.controller.member.login.LoginAction;
import com.goodsshop.controller.member.login.LoginFormAction;
import com.goodsshop.controller.member.login.LogoutAction;
import com.goodsshop.controller.member.update.UpdateMemberAction;
import com.goodsshop.controller.member.update.UpdateMemberFormAction;
import com.goodsshop.controller.action.goods.GoodsCategoryAction;


public class ActionFactory {
	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if( command.equals("index") ) ac = new IndexAction();	
		else if (command.equals("goodsCategory")) ac = new GoodsCategoryAction();
		else if (command.equals("adminLoginForm")) ac = new AdminLoginFormAction();
		else if (command.equals("adminLogin")) ac = new AdminLoginAction();
		else if (command.equals("adminIndex")) ac = new AdminIndexAction();
		else if (command.equals("qna")) ac = new QnaAction();
		else if (command.equals("qnaView")) ac = new QnaViewAction();
		else if (command.equals("qnaReplyWrite")) ac = new QnaReplyWriteAction();
		else if (command.equals("qnaReplyUpdate")) ac = new QnaReplyUpdateAction();
		else if (command.equals("qnaReplyDelete")) ac = new QnaReplyDeleteAction();
		
		//멤버
		else if (command.equals("loginForm")) ac = new LoginFormAction();
		else if (command.equals("login")) ac = new LoginAction();
		else if (command.equals("logout")) ac = new LogoutAction();
		else if (command.equals("findIdForm")) ac = new FindIdFormAction();
		else if (command.equals("findId")) ac = new FindIdAction();
		
		// 멤버 페이지
		else if (command.equals("updateMemberForm")) ac = new UpdateMemberFormAction();
		else if (command.equals("updateMember")) ac = new UpdateMemberAction();
		else if (command.equals("findZipnum")) ac = new FindZipnumAction();
		//member
		if( command.equals("join") ) 									ac = new JoinAction();
		if( command.equals("joinPage") ) 							ac = new JoinPageAction();
		if( command.equals("IDCheck") ) 							ac = new IDCheckAction();
		
		//mypage
		if( command.equals("deleteMember") ) 					ac = new DeleteMemberAction();

		
		
		return ac;
	}
}
