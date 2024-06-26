package com.goodsshop.controller.action.admin;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.QnaDAO;
import com.goodsshop.util.Paging;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminQnaListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		QnaDAO dao = QnaDAO.getInstance();
		
		int total = dao.getTotalQna();
		int currentPage = 1;
		int amount = 10;
		
		if(session.getAttribute("currentPage") != null) {
			currentPage = (Integer)session.getAttribute("currentPage");
		}
		if(session.getAttribute("amount") != null) {
			amount = (Integer)session.getAttribute("amount");
		}
		
		Paging paging = new Paging(currentPage, amount, total);
		
		request.setAttribute("qnaList", dao.getQnaList(paging.getAmount(), paging.getCurrentPage()));
		request.setAttribute("paging", paging);
		request.getRequestDispatcher("/WEB-INF/jsp/admin/qnaList.jsp").forward(request, response);
	}
}