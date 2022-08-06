package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Calendar;
import java.util.TreeMap;


public class SalesDao {
	
	private Connection con ;
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public static SalesDao salesDao = new SalesDao();
	
	public SalesDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?serverTimezone=Asia/Seoul","root","1234");
		}
		catch(Exception e) {
			System.out.println("SalesDao DB연동" + e);
		}
	}

	// 메소드 시작
	public void salesbooking (String day, int amount,int bookingnum) {
		try {
			String sql = "insert into 매출(매출날짜,매출액,매출예약번호) values (?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, day);
			ps.setInt(2, amount);
			ps.setInt(3, bookingnum);
			ps.executeUpdate();
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	public int callbnum (String bookingday, String bookingtime) { // 매출예약번호확인용 메소드
		try {
			String sql = "select 예약번호 from 예약 where 예약날짜=? and 예약시간=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, bookingday);
			ps.setString(2, bookingtime);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		}
		catch(Exception e) {			
		}
		return 0;		
	}
	
	// 예약취소(삭제)메소드
	public void deletesales(int bnum) {
		try {
			String sql = "delete from 매출 where 매출예약번호=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bnum);
			ps.executeUpdate();
		}
		catch(Exception e) {
			
		}
	}
	
	// 예약변경메소드
	public void changesales(int bnum, int cash) {
		try {
			String sql = "update 매출 set 매출액=매출액+? where 매출예약번호=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, cash);
			ps.setInt(2, bnum);
			ps.executeUpdate();
		}
		catch(Exception e) {
			
			System.out.println("매출메소드에러" + e);
		}
	}
	
	// 포인트충전시 매출저장 메소드
	public void pointaddsales(String pdate, int pointcash, int pnum) {
		String sql = "insert into 매출(매출날짜, 매출액, 매출포인트번호) values (?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, pdate);
			ps.setInt(2, pointcash);
			ps.setInt(3, pnum);
			ps.executeUpdate();
		}catch(Exception e) {System.out.println("[SQL 오류-포인트충전시 매출저장]"+e);}
	} // 포인트충전시 매출저장 end
	
	public int callpnum(int bnum, String pointadddate) {
		String sql = "select 포인트번호 from 포인트지급 where 포인트지급회원번호 =? and 지급날짜=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, bnum);
			ps.setString(2, pointadddate);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			} // if end
		}catch(Exception e) {System.out.println("[SQL 오류-포인트번호 호출]"+e);}
		return 0;
	} // 포인트번호호출 end
	
	public TreeMap<String, Integer> salestotal(int year, int month, int day){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1); 
		int eday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		String 월 ;
		if(month<=9) {
			월 = "0"+month;
		}
		else {
			월=""+month;
		}
		TreeMap<String, Integer> map = new TreeMap<String, Integer>(); 
			try {
			String sql = "select substring_index(매출날짜,'-',-1), sum(매출액) from 매출 where 매출날짜 like '%"+year+"-"+월+"%' group by 매출날짜 order by 매출날짜 asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			for(int i = 1 ; i <= eday ; i++) {
				if(i<=9) {
					map.put("0"+i, 0);
				}
				else{
					map.put(""+i, 0);
				}
			}	
			while(rs.next()) {
				if (map.containsKey(rs.getString(1))) {
					map.put(rs.getString(1), rs.getInt(2));	
				}
				
			}
			return map;
		}
		catch(Exception e) {
			
		}
		return null;
	}
	

	public int yearsales(String year) { // 연도별 매출액
		try {
			String sql = "select sum(매출액) from 매출 where substring_index(매출날짜,'-',1)=?" ;
			ps = con.prepareStatement(sql);
			ps.setString(1, year);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		}
		catch(Exception e) {
		}
		return 0;
	}
	
	
	
	
	
}