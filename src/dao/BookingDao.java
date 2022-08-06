package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import controller.booking.Bookinglist;
import controller.login.Login;
import dto.Booking;


public class BookingDao {
	
	private Connection con ;
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public static BookingDao bookingDao = new BookingDao();
	
	public BookingDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?serverTimezone=Asia/Seoul","root","1234");
		}
		catch(Exception e) {
			System.out.println("BookingDao DB연동" + e);
		}
	}
	
	// 메소드 시작
	
	// 예약하기 메소드
	public boolean booking (String bookingday, String bookingtime, int cash) { 
		try {
			String sql = "insert into 예약(예약회원번호,예약날짜,예약시간,예약금액) values (?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, Login.member.get회원번호());
			ps.setString(2, bookingday);
			ps.setString(3, bookingtime);
			ps.setInt(4, cash);
			ps.executeUpdate();
			return true;		
		}
		catch(Exception e) {
			System.out.println("예약하기 메소드" + e);
		}
		return false;			
	}
	
	// 예약정보 불러오기 메소드 (예약신청 화면시 예약중복체크 방지용)
	public TreeMap<String, String> bookingcheck (int year, int month, int day) {
		TreeMap<String, String> map = new TreeMap<>();
		String 월 = null ;
		if(month<=9) {
			월 = "0"+month;
		}
		else {
			월 = month+"";
		}
		try {
			String sql = "select trim(leading '0' from substring_index(예약날짜, '-',-1)), 예약시간, 예약번호 from 예약 where 예약날짜 like '%"+year+"-"+월+"%' order by 예약날짜 asc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(Bookinglist.selectedbooking!=null) {
				while(rs.next()) {
					if(rs.getInt(3)!=Bookinglist.selectedbooking.get예약번호()) {
						if(map.containsKey(rs.getString(1))){
							map.replace(rs.getString(1), (map.get(rs.getString(1))+rs.getString(2)));
						}	
						else{
							map.put(rs.getString(1), rs.getString(2));
						}
					}
					else {}
				}
				
			}
			else {
				while(rs.next()) {
					if(map.containsKey(rs.getString(1))){
						map.replace(rs.getString(1), (map.get(rs.getString(1))+rs.getString(2)));
					}	
					else{
						map.put(rs.getString(1), rs.getString(2));
					}				
				}
			}
			return map;
		}
		catch(Exception e) {
			System.out.println("예약정보불러오기 메소드" + e);
		}
		return null;
	}
	
	// 예약정보 불러오기 메소드 (예약변경 화면시 회원이 예약한 내역 불러오기용)
	public ArrayList<Booking> bookinglist(int mnum) {
		try {
			ArrayList<Booking> list = new ArrayList<>();
			String sql = "select * from 예약 where 예약회원번호=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			rs = ps.executeQuery();
			while(rs.next()) {
				Booking book = new Booking(mnum, rs.getInt(1), rs.getString(3), rs.getString(4) , rs.getInt(5));
				list.add(book);
			}
			return list;
		}
		catch(Exception e) {
			System.out.println("예약정보불러오기(예약변경)" + e);
		}	
		return null;
	}
	
	// 예약취소(삭제)메소드
	public void deletebooking(int bnum) {
		try {
			String sql = "delete from 예약 where 예약번호=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bnum);
			ps.executeUpdate();
		}
		catch(Exception e) {
			
		}
	}
	
	// 예약 변경 메소드
	public void changebooking(int bnum, String bookingday, String bookingtime, int cash) {
		try {
			String sql = "update 예약 set 예약날짜=?, 예약시간=?, 예약금액=예약금액+? where 예약번호=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, bookingday);
			ps.setString(2, bookingtime);
			ps.setInt(3, cash);
			ps.setInt(4, bnum);
			ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("예약메소드에러" + e);
		}
	}
	

}
