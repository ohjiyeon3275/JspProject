package com.servlet.ex;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ex.MemberDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.se")
public class LoginServlet extends HttpServlet {
       
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int result = dao.login(id, password);
		
		HttpSession session = request.getSession();
		
		if(result==1) {
			request.setAttribute("message", "로그인 성공");
			session.setAttribute("id", id);
			
		}else if(result==0) {
			request.setAttribute("message", "로그인 실패");
		}
		
		String url = "main.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}
