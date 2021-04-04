package com.dao.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.dto.ex.BoardDTO;

public class BoardDAO {

	private BoardDAO() {}
	
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	Connection conn = null;
	PreparedStatement ps = null;
	Statement st = null;
	ResultSet rs = null;
	
	public Connection getConnection() {
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	public void hit(String bid) {
		
		String sql = "update webboard set bhit=bhit+1 where bid=?";
	
		try {
			conn = getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, bid);
			rs=ps.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public ArrayList<BoardDTO> list() {
		ArrayList<BoardDTO> dtos = new ArrayList<BoardDTO>();
		String sql = "select * from webboard order by bgroup, bindent, bstep desc";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBid(rs.getInt("bid"));
				dto.setBname(rs.getString("bname"));
				dto.setBtitle(rs.getString("btitle"));
				dto.setBcontent(rs.getString("bcontent"));
				dto.setBdate(rs.getString("bdate"));
				dto.setBhit(rs.getInt("bhit"));
				dto.setBgroup(rs.getInt("bgroup"));
				dto.setBstep(rs.getInt("bstep"));
				dto.setBindent(rs.getInt("bindent"));
				dtos.add(dto);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	
	public ArrayList<BoardDTO> boardView(String bid) {	
		BoardDAO.getInstance().hit(bid);
		ArrayList<BoardDTO> dtos = new ArrayList<BoardDTO>();
		String sql = "select * from webboard where bid=?";
		
		try {
			conn = getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, bid);
			rs=ps.executeQuery();
			while(rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				dto.setBid(rs.getInt("bid"));
				dto.setBname(rs.getString("bname"));
				dto.setBtitle(rs.getString("btitle"));
				dto.setBcontent(rs.getString("bcontent"));
				dto.setBdate(rs.getString("bdate"));
				dto.setBhit(rs.getInt("bhit"));
				dto.setBgroup(rs.getInt("bgroup"));
				dto.setBstep(rs.getInt("bstep"));
				dto.setBindent(rs.getInt("bindent"));
				dtos.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
			return dtos;
	}

	//결과값이 하나만 나와도 되기때문에 arraylist로 안해도됨

	public void write(String btitle, String bcontent, String user) {
		String sql = "insert into webboard(bid, bname, btitle, bcontent, bhit, bgroup, bstep, bindent) "
								+ "values(bseq.nextVal, ?, ?, ?, 0, bseq.currVal, 0, 0)";
		
		try {
			conn = getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, btitle);
			ps.setString(3, bcontent);
			rs=ps.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	
	public void delete(String bid) {
		String sql= "delete from webboard where bid=?";
		
		try {
			conn = getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, bid);
			rs=ps.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	
	public void modify(String boardTitle, String boardContent, String boardNum) {
		
		String sql = "update webboard set boardTitle=?, boardContent=? where boardNum=?";
		
		try {
			conn = getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, boardTitle);
			ps.setString(2, boardContent);
			ps.setString(3, boardNum);
			rs=ps.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<BoardDTO> replyView(String bid) {	
		ArrayList<BoardDTO> dtos = new ArrayList<BoardDTO>();
		String sql = "select * from webboard where bid=?";
		
		try {
			conn = getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, bid);
			rs=ps.executeQuery();
			
			if(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBid(rs.getInt("bid"));
				dto.setBname(rs.getString("bname"));
				dto.setBtitle(rs.getString("btitle"));
				dto.setBcontent(rs.getString("bcontent"));
				dto.setBdate(rs.getString("bdate"));
				dto.setBhit(rs.getInt("bhit"));
				dto.setBgroup(rs.getInt("bgroup"));
				dto.setBstep(rs.getInt("bstep"));
				dto.setBindent(rs.getInt("bindent"));
				dtos.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
			return dtos;
	}

	public void replyStep(int bgroup, int bstep) {
		
		String sql = "update webboard set bstep=bstep+1 where bgroup=? and bstep>?";
		
		try {
			conn = getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1, bgroup);
			ps.setInt(2, bstep);
			rs=ps.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}	

	}
	
	public void reply(String rtitle, String rcontent, int bgroup, int bstep, int bindent, String user, String bid) {	
		
		
		replyStep(bgroup, bstep);
		
		ArrayList<BoardDTO> list = BoardDAO.getInstance().replyView(bid);
		
		String sql = "insert into webboard(bid, bname, btitle, bcontent, bhit, bgroup, bstep, bindent) values(bseq.nextVal, ?,?,?,0,?,?,?)";
		
		try {
			conn = getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, rtitle);
			ps.setString(3, rcontent);
			ps.setInt(4, list.get(0).getBgroup());
			ps.setInt(5, list.get(0).getBstep()+1);
			ps.setInt(6, list.get(0).getBindent()+1);
		
			rs=ps.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
