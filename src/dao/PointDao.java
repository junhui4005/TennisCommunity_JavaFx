package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controller.login.Login;
import dto.Point;


public class PointDao {
	
	private Connection con ;
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public static PointDao pointDao = new PointDao();
	
	public PointDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?serverTimezone=Asia/Seoul","root","1234");
		}
		catch(Exception e) {
			System.out.println("PointDao DB연동" + e);
		}
	}

	// 메소드 시작
	
	// 1. 포인트충전 메소드
	public boolean pointadd(Point point) {
		String sql = "insert into 포인트지급(포인트지급회원번호, 지급날짜, 지급포인트) values(?,?,?)";
		System.out.println(point.get지급포인트());
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, point.get포인트지급회원번호());
			ps.setString(2, point.get지급날짜());
			ps.setInt(3, point.get지급포인트());
			ps.executeUpdate();
				String sql2 = "update 회원 set 회원포인트=? where 회원번호=? ";
				int mpoint = Login.member.get회원포인트();
				ps = con.prepareStatement(sql2);
				ps.setInt(1, mpoint+point.get지급포인트());
				ps.setInt(2, Login.member.get회원번호());
				ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[SQL 오류-포인트충전]"+e);}
		return false;
	} // 포인트충전 end
	
	//1위 상금 포인트지급
	public boolean rewardpointadd1(Point point) {
		String sql = "insert into 포인트지급(포인트지급회원번호, 지급날짜, 지급포인트) values(?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, point.get포인트지급회원번호());
			ps.setString(2, point.get지급날짜());
			ps.setInt(3, point.get지급포인트());
			ps.executeUpdate();
				String sql2 = "update 회원 set 회원포인트=? where 회원번호=? ";
				int mpoint = Login.member.get회원포인트();
				ps = con.prepareStatement(sql2);
				ps.setInt(1, mpoint+point.get지급포인트());
				ps.setInt(2, point.get포인트지급회원번호());
				ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[SQL 오류-포인트충전]"+e);}
		return false;
	}
	
	public boolean rewardpointadd2(Point point) {
		String sql = "insert into 포인트지급(포인트지급회원번호, 지급날짜, 지급포인트) values(?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, point.get포인트지급회원번호());
			ps.setString(2, point.get지급날짜());
			ps.setInt(3, point.get지급포인트());
			ps.executeUpdate();
				String sql2 = "update 회원 set 회원포인트=? where 회원번호=? ";
				int mpoint = Login.member.get회원포인트();
				ps = con.prepareStatement(sql2);
				ps.setInt(1, mpoint+point.get지급포인트());
				ps.setInt(2, point.get포인트지급회원번호());
				ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[SQL 오류-포인트충전]"+e);}
		return false;
	}
	
	public boolean rewardpointadd3(Point point) {
		String sql = "insert into 포인트지급(포인트지급회원번호, 지급날짜, 지급포인트) values(?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, point.get포인트지급회원번호());
			ps.setString(2, point.get지급날짜());
			ps.setInt(3, point.get지급포인트());
			ps.executeUpdate();
				String sql2 = "update 회원 set 회원포인트=? where 회원번호=? ";
				int mpoint = Login.member.get회원포인트();
				ps = con.prepareStatement(sql2);
				ps.setInt(1, mpoint+point.get지급포인트());
				ps.setInt(2, point.get포인트지급회원번호());
				ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[SQL 오류-포인트충전]"+e);}
		return false;
	}

}