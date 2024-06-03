package com.goodsshop.controller.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.controller.member.MemberVO;
import com.goodsshop.dao.CartDAO;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dto.CartVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewWishAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();	
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		CartDAO cdao = new CartDAO();

		if (loginUser == null) {
			PrintWriter out = response.getWriter();
			
			
			request.getRequestDispatcher("gshop.do?command=index").forward(request, response);
		} else {
			
			List<CartVO> wishlist = null;
			String userid = loginUser.getUserid();
			
			wishlist = cdao.getWishList(userid);
			
			for( CartVO cvo : wishlist) {
				GoodsDAO gdao = new GoodsDAO();
				String thum = gdao.getThumbnail(cvo.getGseq());
				cvo.setThum(thum);
			}
			
			
			request.setAttribute("wishlist", wishlist);
			request.getRequestDispatcher("jsp/goods/wishlistView.jsp").forward(request, response);
						
		}

	}

}