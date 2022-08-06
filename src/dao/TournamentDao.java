package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TournamentDao {
	
	private Connection con ;
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public static TournamentDao tournamentDao = new TournamentDao();
	
	public TournamentDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?serverTimezone=Asia/Seoul","root","1234");
		}
		catch(Exception e) {
			System.out.println("TournamentDao DB연동" + e);
		}
	}

	// 메소드 시작
	
	
	////대회등록
	public boolean add(Tournament tournament) {
		try {
			String sql= "insert into 대회(대회이름,대회날짜,참가인원,대회이미지경로,상금1등,상금2등,상금3등,참가비)values(?,?,?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, tournament.get대회이름());
			ps.setString(2, tournament.get대회날짜());
			ps.setInt(3, tournament.get참가인원());
			ps.setString(4, tournament.get대회이미지경로());
			ps.setInt(5, tournament.get상금1위());
			ps.setInt(6, tournament.get상금2위());
			ps.setInt(7, tournament.get상금3위());
			ps.setInt(8, tournament.get참가비());
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("대회등록" + e);}
		return false;
	}
	
	////대회날짜,시간 중복
	public String addcheck(String cdate) {
		try {
			String sql = "select * from 대회 where 대회날짜=?";
			ps= con.prepareStatement(sql);
			ps.setString(1, cdate);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
			
		}
		catch(Exception e) {System.out.println("날짜중복: "+ e);}
		return null;
	}
	
	////모든 대회 출력
	public ArrayList<Tournament> list(){
		
		ArrayList<Tournament> tournamentlist = new ArrayList<>();
		try {
			String sql = "select * from 대회";
			ps = con.prepareStatement(sql);
			rs= ps.executeQuery();
			while(rs.next()) {
				Tournament tournament = new Tournament(
				rs.getInt(1),
				rs.getString(2),
				rs.getString(3),
				rs.getInt(4),
				rs.getString(5),
				rs.getInt(6),
				rs.getInt(7),
				rs.getInt(8),
				rs.getInt(9));
				tournamentlist.add(tournament);
			}
			return tournamentlist;
		}
		catch(Exception e) {System.out.println("대회리스트" + e);}
		return null;
	}
	
	///관리자대회수정
	public boolean cupdate(int 대회번호, String 제목,String cdate,int 참가인원,String addcimg ,int creward1,int creward2,int creward3,int 참가비) {
		try {
			String sql = "update 대회 set 대회이름=? , 대회날짜=?, 참가인원=?, 대회이미지경로=?, 상금1등=?, 상금2등=?, 상금3등=?, 참가비=? where 대회번호=?";
			ps = con.prepareStatement(sql);
			ps.setString( 1 , 제목 );
			ps.setString( 2 , cdate );
			ps.setInt( 3 , 참가인원 );
			ps.setString( 4 , addcimg );
			ps.setInt( 5 , creward1 );
			ps.setInt( 6 , creward2  );
			ps.setInt( 7 , creward3  );
			ps.setInt( 8 , 참가비  );
			ps.setInt(9, 대회번호);
			ps.executeUpdate();
			return true;
		}catch(Exception e ) { System.out.println( "[SQL 오류]"+e  ); }
		return false;
		}
	
	///관리자 대회삭제
	public boolean cdelete(int cnum) {
		try {
			String sql = "delete from 대회 where 대회번호=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, cnum);
			ps.executeUpdate();
			return true;
		}catch (Exception e) {
		}return false;
	}
	
	///메인홈 대회테이블 불러오기
	public ObservableList<Tournament> clist() {
		
		ObservableList<Tournament> clist  = FXCollections.observableArrayList();
		
			try {
			///sql작성
			String sql = "select * from 대회";
			////조작
			ps=con.prepareStatement(sql);
			///실행executeQuery();
			rs = ps.executeQuery();
			///결과
			while(rs.next()) {
				Tournament tournament = new Tournament(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getInt(8),
						rs.getInt(9)
						);
						
						clist.add(tournament);	
			}
			return clist;
		}
		catch(Exception e) {System.out.println("main"+e);}
		return null;}
	
	
	
}