package com.goodsshop.controller.action.cart;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dto.OrderVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetPaymentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numberOfGoods = Integer.parseInt(request.getParameter("numberOfGoods"));
		int totalPrice = Integer.parseInt(request.getParameter("orderTotalPrice"));
		
		request.getRequestDispatcher("/WEB-INF/jsp/goods/paymentPage.jsp").forward(request, response);
	}
}
