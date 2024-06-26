package com.goodsshop.controller.action.admin;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.ReviewDAO;
import com.goodsshop.util.Paging;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminReviewListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ReviewDAO dao = ReviewDAO.getInstance();
		
		int total = dao.getTotalReview();
		int currentPage = 1;
		int amount = 10;
		
		if(session.getAttribute("currentPage") != null) {
			currentPage = (Integer)session.getAttribute("currentPage");
		}
		if(session.getAttribute("amount") != null) {
			amount = (Integer)session.getAttribute("amount");
		}
		
		Paging paging = new Paging(currentPage, amount, total);
		
		request.setAttribute("reviewList", dao.getReviewList(paging.getAmount(), paging.getCurrentPage()));
		request.setAttribute("paging", paging);
		request.getRequestDispatcher("/WEB-INF/jsp/admin/reviewList.jsp").forward(request, response);
	}
}