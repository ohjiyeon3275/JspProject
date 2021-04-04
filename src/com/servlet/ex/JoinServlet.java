package com.servlet.ex;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ex.MemberDAO;

@WebServlet("/join.se")
public class JoinServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String hobby = request.getParameter("hobby");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int result = dao.join(id, password, birthday, email, hobby);
		
		if(result == 1) {
			request.setAttribute("message", "회원가입 성공");
		}else if(result == -1) {
			request.setAttribute("message", "이미 존재하는 아이디 입니다.");
		}else if(result == 0 ) {
			request.setAttribute("message", "회원가입 실패");
		}
		
		String url = "joinAction.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}
