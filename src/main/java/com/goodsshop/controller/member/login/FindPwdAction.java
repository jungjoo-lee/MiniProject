package com.goodsshop.controller.member.login;

import java.io.IOException;

import com.goodsshop.controller.action.Action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.MemberDAO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.properties.Env;


import jakarta.servlet.http.HttpSession;

public class FindPwdAction implements Action {

	private static final String SENDER_EMAIL  =  Env.getSendEmail(); // 보내는 사람 이메일 주소
	private static final String SENDER_PASSWORD =  Env.getEmailPwd(); // 보내는 사람 이메일 비밀번호
	private static final long CODE_EXPIRATION_DURATION = 3 * 60 * 1000;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 저장된 userid 과 email 불러오기
		String userid = request.getParameter("userid");
		String email = request.getParameter("email");
		
		// dao 불러오기
		MemberDAO mdao = MemberDAO.getInstance();
		MemberVO mvo = mdao.checkMembers(userid, email);
		String url="/WEB-INF/jsp/email/PwdEmail.jsp";
		 try {
			 	if(mvo== null){ 
			 		request.setAttribute("message", "등록된 회원이 아닙니다");
			 		 request.getRequestDispatcher("/WEB-INF/jsp/member/findPwdForm.jsp").forward(request, response);
			 	}	else if (mvo.getEmail() == null || mvo.getUserid() == null || !mvo.getEmail().equals(email) || !mvo.getUserid().equals(userid)) {

		            request.setAttribute("message", "등록된 회원이 아닙니다");
		            request.getRequestDispatcher("/WEB-INF/jsp/member/findPwdForm.jsp").forward(request, response);
		        } else if (mvo.getEmail().equals(email) && mvo.getUserid().equals(userid)) {
		        	request.setAttribute("message", "인증번호가 전송되었습니다");
		            // 랜덤한 인증 코드 생성
		            String verificationCode = generateVerificationCode();

		            
		            // 이메일 전송
		            sendEmail(email, verificationCode);

		            // 이메일 전송 후 작업 수행
		            // 예: 세션에 인증 코드 저장 등
		            HttpSession session = request.getSession();
	                session.setAttribute("verificationCode", verificationCode);
	                session.setAttribute("verificationCodeExpiration", System.currentTimeMillis() + CODE_EXPIRATION_DURATION);
	                session.setAttribute("userid", userid);
	                session.setAttribute("email", email);


		            // 이메일 인증 페이지로 이동
		            request.getRequestDispatcher(url).forward(request, response);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	
	
	// 랜덤한 6자리 숫자로 인증 코드 생성
	private static String generateVerificationCode() {
		Random random = new Random();
		int code = 100000 + random.nextInt(900000);
		return String.valueOf(code);
	}
	
	// 이메일 전송
	private static void sendEmail(String email, String verificationCode) {
		Properties properties = new Properties();
		  	properties.put("mail.smtp.auth", "true");
		    properties.put("mail.smtp.starttls.enable", "true");
		    properties.put("mail.smtp.ssl.enable", "true"); // SSL 활성화
		    properties.put("mail.smtp.host", "smtp.gmail.com"); // SMTP 호스트
		    properties.put("mail.smtp.port", "465"); // SMTP 포트 (SSL 사용 시)

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SENDER_EMAIL));
			// 이메일 주소가 null 이 아닌 경우에만 이메일 주소를 설정합니다.
	        if (email != null && !email.isEmpty()) {
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
	            // 이메일 제목 설정
                message.setSubject("이메일 인증 코드");

                // 이메일 본문 설정
                String emailContent = "안녕하세요, 이메일 인증 코드는 다음과 같습니다: " + verificationCode;
                message.setText(emailContent);

                // 이메일 전송
                Transport.send(message);
                
        		System.out.println("이메일이 성공적으로 전송되었습니다.");
	        } else if(email == null || email.isEmpty()){
	            // 이메일 주소가 null 이거나 비어있는 경우에 대한 처리를 수행합니다.
	            System.out.println("이메일 주소가 올바르지 않습니다.");
	           
	            return; // 메서드 종료
	        }
		} catch (MessagingException e) {e.printStackTrace();}
		
	}
}