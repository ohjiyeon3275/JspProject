package com.servlet.ex;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ex.BoardDAO;
import com.dto.ex.BoardDTO;

@WebServlet("*.do")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String viewPage=null;
		
		String uri=request.getRequestURI();
		String conPath=request.getContextPath();
		String com=uri.substring(conPath.length());
		
		HttpSession session = request.getSession();
		
		
		if(com.equals("/board.do")) {
			viewPage="board.jsp";
			
			BoardDAO dao = BoardDAO.getInstance();
			ArrayList<BoardDTO> list  = dao.list();
			
			request.setAttribute("list", list);
			
			
		}else if(com.equals("/boardView.do")) {
			viewPage="boardView.jsp";
			BoardDAO dao = BoardDAO.getInstance();
			String bid=(String)request.getParameter("bid");
			ArrayList<BoardDTO> list  = dao.boardView(bid);
			
			request.setAttribute("bname", list.get(0).getBname());
			
			request.setAttribute("list", list);
			
			
		}else if(com.equals("/write.do")){
			viewPage="board.do";
			String btitle= request.getParameter("btitle");
			String bcontent = request.getParameter("bcontent");
			String id = (String)session.getAttribute("id");
			BoardDAO.getInstance().write(btitle, bcontent, id);
			
			
		}else if(com.equals("/delete.do")) {
			viewPage="board.do";
			String bid=(String)request.getParameter("bid");
			BoardDAO.getInstance().delete(bid);
			
			
		}else if(com.equals("/modify.do")) {
			viewPage="board.do";
			String btitle=request.getParameter("btitle");
			String bcontent=request.getParameter("bcontent");
			String bid=request.getParameter("bid");
			BoardDAO.getInstance().modify(btitle, bcontent, bid);
			
			ArrayList<BoardDTO> list=BoardDAO.getInstance().boardView(bid);
			request.setAttribute("list", list);
			
			
		}else if(com.equals("/reply.do")) {
			viewPage="board.do";
			String bid = (String)request.getParameter("bid");
			ArrayList<BoardDTO> list  = BoardDAO.getInstance().boardView(bid);
			
			String rtitle=request.getParameter("rtitle");
			String rcontent=request.getParameter("rcontent");
			
			int bgroup=list.get(0).getBgroup();
			int bstep=list.get(0).getBstep();
			int bindent=list.get(0).getBindent();
			
			

			String id = (String)session.getAttribute("id");
			BoardDAO.getInstance().reply(rtitle, rcontent, bgroup, bstep, bindent, id, bid);
			
		}
		
		//request.setAttribute("id", id);
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
