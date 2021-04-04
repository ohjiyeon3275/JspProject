package com.dao.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	private MemberDAO() {}
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}

	Connection conn = null;
	PreparedStatement ps = null;
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
	
	public int idcheck(String id) {
		
		String sql = "select * from member where id = ?";
		
		try {
			conn = getConnection();
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			
			if(rs.next()) {
				return -1;	
			}
			
		} catch (Exception e) {
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
		return 1;
	}
	public int join(String id, String password, String birthday, String email, String hobby) {
		
		int idcheck = idcheck(id);
		if(idcheck<0) {
			return -1;
		}
		String sql = "insert into member values(?,?,?,?,?)";
		
		try {
			conn = getConnection();

			ps = conn.prepareStatement(sql);
			ps.setString(1,id);
			ps.setString(2,password);
			ps.setString(3,birthday);
			ps.setString(4,email);
			ps.setString(5,hobby);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				return 1;
			}
			
		} catch (Exception e) {
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
		return 0;
	}
	
	public int login(String id, String password) {
		
		String sql = "select * from member where id=? and password=?";
		
		try {
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1,id);
			ps.setString(2,password);
			rs=ps.executeQuery();
			if(rs.next()) {
				return 1;
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

		return 0;
	}
	
	
	
	
	
	
}
