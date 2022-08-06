package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.login.Login;
import dto.Member;
import dto.Mytournamentlist;
import dto.Record;
import dto.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class RecordDao {
	
	private Connection con ;
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public static RecordDao recordDao = new RecordDao();
	
	public RecordDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?serverTimezone=Asia/Seoul","root","1234");
		}
		catch(Exception e) {
			System.out.println("RecordDao DB연동" + e);
		}
	}

	// 메소드 시작
	
	
	////신청한 회원 기록등록
	public boolean apply(Record record) {
		try {
		String sql = "insert into 대회기록(대회번호,대회기록회원번호)values(?,?)";
		ps=con.prepareStatement(sql);
		ps.setInt(1, record.get기록대회번호());
		ps.setInt(2, record.get기록회원번호());
		ps.executeUpdate();
		return true;
		}catch(Exception e) {System.out.println("대회신청" + e);}
		return false;
	}
	
	
	////신청한 회원 출력
	public ObservableList<Record> list(int cnum){
		try {
			ObservableList<Record> recordlist = FXCollections.observableArrayList();
			
			String sql = "select a.* ,b.회원아이디 from 대회기록 a left join 회원 b on a.대회기록회원번호 = b.회원번호 where 대회번호=? order by 대회기록순위 asc";
			ps=con.prepareStatement(sql);
			ps.setInt(1, cnum);
			rs=ps.executeQuery();
			
		while(rs.next()) {
			Record record = new Record(
					rs.getInt(1), 
					rs.getString(6),
					rs.getInt(2), 
					rs.getInt(3), 
					rs.getInt(4), 
					rs.getInt(5));
		
			recordlist.add(record);
		}
		return recordlist;
			
		}catch(Exception e) {}
		return null;
		
	}
	
	
	/////순위입력
	public boolean rankingadd(int 기록순위, int 기록번호) {
		try {
			String sql = "update 대회기록 set 대회기록순위=? where 대회기록번호=?";
			// 2. SQL 조작
						ps = con.prepareStatement(sql);
						ps.setInt( 1 , 기록순위 );
						ps.setInt(2, 기록번호);
					// 3. SQL 실행
						ps.executeUpdate();
					// 4. SQL 결과
						return true;
					}catch(Exception e ) { System.out.println( "[SQL 오류]"+e  ); }
					return false; 
				
	}
	
	////1등상금저장
	public boolean addreward1(int 기록1등상금,int 기록번호) {
		try {
			String sql = "update 대회기록 set 대회기록받은상금=? where 대회기록번호=?";
			ps = con.prepareStatement(sql);
			ps.setInt( 1 , 기록1등상금 );
			ps.setInt(2, 기록번호);
			ps.executeUpdate();
			// 4. SQL 결과
				return true;
		}catch(Exception e) {}
		return false;
	}
	
	
	public boolean addreward2(int 기록2등상금,int 기록번호) {
		try {
			String sql = "update 대회기록 set 대회기록받은상금=? where 대회기록번호=?";
			ps = con.prepareStatement(sql);
			ps.setInt( 1 , 기록2등상금 );
			ps.setInt(2, 기록번호);
			ps.executeUpdate();
			// 4. SQL 결과
				return true;
		}catch(Exception e) {}
		return false;
	}
	public boolean addreward3(int 기록3등상금,int 기록번호) {
		try {
			String sql = "update 대회기록 set 대회기록받은상금=? where 대회기록번호=?";
			ps = con.prepareStatement(sql);
			ps.setInt( 1 , 기록3등상금 );
			ps.setInt(2, 기록번호);
			ps.executeUpdate();
			// 4. SQL 결과
				return true;
		}catch(Exception e) {}
		return false;
	}
	
	
	////신청한 인원수 찾기
	public String cmember(int 대회번호) {
		try {
		String sql = "select count(*) from 대회기록 where 대회번호 =?;";
		
		ps = con.prepareStatement(sql);
		ps.setInt(1, 대회번호);
		rs =ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		
		}catch(Exception e) {}
		return null;
	}
	
	
	////만약 회원이 해당 대회에 이미 참가신청한 기록이 있을 때
	public boolean cmemberapply(int 신청회원번호,int 대회번호) {
		try {
			String sql = "select * from 대회기록 where 대회기록회원번호=? && 대회번호=?";
			ps= con.prepareStatement(sql);
			ps.setInt(1, 신청회원번호);
			ps.setInt(2, 대회번호);
			rs = ps.executeQuery();
			if(rs.next()) return true;
			
		}catch(Exception e) {System.out.println(e);}
		return false;
	}
	
	///대회신청취소
	public boolean cmemberdelete(int 신청회원번호, int 대회번호) {
		try {
			String sql = "delete from 대회기록 where 대회번호=? && 대회기록회원번호=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, 대회번호);
			ps.setInt(2, 신청회원번호);
			ps.executeUpdate();
			return true;
			
		}catch (Exception e) {
			System.out.println(대회번호+ 신청회원번호);
		}
		return false;
	}
	
	
	// 회원별로 대회리스트 호출
	public ObservableList<Mytournamentlist> mtlist(int mnum){
		ObservableList<Mytournamentlist> myrecordlist = FXCollections.observableArrayList();
		String sql = "SELECT a.* ,b.대회이름, b.대회날짜  FROM 대회기록 a left join 대회 b on a.대회번호 = b.대회번호 where 대회기록회원번호=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			rs = ps.executeQuery();
			while(rs.next()) {

				Mytournamentlist mtl = new Mytournamentlist(rs.getInt(2), 
						rs.getString(6), 
						rs.getString(7), 
						rs.getInt(4), 
						rs.getInt(5) );
				myrecordlist.add(mtl);
			} // while e
			return myrecordlist;
		}catch(Exception e) {System.out.println("[SQL 오류-회원별대회리스트]"+e);}
		return null;
	} // 회원별 대회리스트 호출 end
	
	///회원번호를 통한 아이디추출
	public String searchid(int 회원번호){
		String sql = "select * from 회원 where 회원번호=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, 회원번호);
			rs = ps.executeQuery();
			while(rs.next()) {
				Member member = new Member(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getInt(8),
						rs.getString(9) );
				return rs.getString(2);
			}
		}catch(Exception e) {System.out.println(e);}
			return null;
		}
	
	///
	
}