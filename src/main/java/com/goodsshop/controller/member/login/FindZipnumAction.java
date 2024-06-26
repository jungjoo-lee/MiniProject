package com.goodsshop.controller.member.login;

import java.io.IOException;
import java.util.ArrayList;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.MemberDAO;
import com.goodsshop.dto.AddressVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FindZipnumAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dong = request.getParameter("dong");
		if(dong != null) {
			if(dong.equals("") == false) {
				MemberDAO mdao = MemberDAO.getInstance();
				ArrayList<AddressVO> list = mdao.selectAddressByDong(dong);
				request.setAttribute("addressList", list);
			}
		}
		request.getRequestDispatcher("/WEB-INF/jsp/member/findZipNum.jsp").forward(request, response);

	}

}
