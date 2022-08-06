package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dto.Member;


public class MemberDao {

	private Connection con ;
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public static MemberDao memberDao = new MemberDao();
	
	public MemberDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?serverTimezone=Asia/Seoul","root","1234");
		}
		catch(Exception e) {
			System.out.println("MemberDao DB연동" + e);
		}
	}

	// 메소드 시작
	
	// 1. 회원가입 메소드
	public boolean signup(Member member) {
		String sql = "insert into 회원(회원아이디, 회원비밀번호, 회원이름, 회원생년월일, 회원이메일, 회원전화번호, 회원포인트) values(?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, member.get회원아이디());
			ps.setString(2, member.get회원비밀번호());
			ps.setString(3, member.get회원이름());
			ps.setString(4, member.get회원생년월일());
			ps.setString(5, member.get회원이메일());
			ps.setString(6, member.get회원전화번호());
			ps.setInt(7, member.get회원포인트());
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[DB 연동 오류-회원가입]"+e);}
		return false;
	} // 회원가입 end
	
	// 1-2. 아이디 중복체크 메소드(인수 : 아이디)
	public boolean idcheck(String id) {
		String sql = "select * from 회원 where 회원아이디=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			} // if e
		}catch(Exception e) {System.out.println("[DB 연동 오류-아이디중복체크]"+e);}
		return false;
	} // 아이디 중복체크 end
	
//	// 1-3. 생년월일형태 체크 메소드
//	public void birthcheck(String birth) {
//		Calendar cal = Calendar.getInstance(); // 입력받은 월의 마지막일을 구하기위함
//		int year = Integer.parseInt(birth.substring(0, 2));
//		int month = Integer.parseInt(birth.substring(2, 4));
//		int day = Integer.parseInt(birth.substring(4, 6));
//		int eday = cal.getActualMaximum(cal.DAY_OF_MONTH); // 입력받은 월의 마지막일을 구함
//		if(month>=1 && month<=12 && day>=1 && day<=eday) {
//			if(year>=00 && year<=22) {
//				int year2 = 2000+year;
//			}else {
//				int year2 = 1900+year;
//			} // else e
//			int birth2 = year2+month+day;
//		}
//		
//	}
	

	// 2. 로그인 메소드(인수 : 아이디, 비밀번호)
	public boolean login(String id, String password) {
		String sql = "select * from 회원 where 회원아이디=? and 회원비밀번호=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			} // if e
		}catch(Exception e) {System.out.println("[DB 연동 오류-로그인]"+e);}
		return false;
	} // 로그인 end
	
	// 3. 아이디찾기 메소드(인수 : 이름, 생년월일, 전화번호)
	public String findid(String name, String phone) {
		String sql = "select * from 회원 where 회원이름=? and 회원전화번호=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phone);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(2);
			} // if e
		}catch(Exception e) {System.out.println("[DB 연동 오류-아이디찾기]"+e);}
		return null;
	} // 아이디찾기  end
	
	// 4. 비밀번호찾기 메소드(인수 : 아이디, 이메일, 전화번호)
	public String findpassword(String name, String id, String phone) {
		String sql = "select * from 회원 where 회원이름=? and 회원아이디=? and 회원전화번호=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, id);
			ps.setString(3, phone);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(3);
			} // if e
		}catch(Exception e) {System.out.println("[DB 연동 오류-비밀번호찾기]"+e);}
		return null;
	} // 비밀번호찾기 end
	
	// 5. 회원정보수정 메소드
	public boolean memberupdate(int mnum, String password, String name, String email, String phone) {
		String sql = "update 회원 set 회원비밀번호=?, 회원이름=?, 회원이메일=?, 회원전화번호=? where 회원번호=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, phone);
			ps.setInt(5, mnum);
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[DB 연동 오류-회원정보수정]"+e);}
		return false;
	} // 회원정보수정 end
	
	// 6. 회원탈퇴 메소드
	public boolean memberdelete(int mnum) {
		String sql = "delete from 회원 where 회원번호=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[DB 연동 오류-회원탈퇴]"+e);}
		return false;
	} // 회원탈퇴 end
	
	// 7. 아이디로 회원정보 호출
	public Member getmember(String id) {
		String sql = "select * from 회원 where 회원아이디=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				Member member = new Member(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getInt(8),
						rs.getString(9) );
				return member;
			} // if e
		}catch(Exception e) {System.out.println("[DB 연동 오류-회원정보호출]"+e);}
		return null;
	}
	
	// 8. 회원번호로 회원아이디 호출
	public String getid(int mnum) {
		String sql = "select 회원아이디 from 회원 where 회원번호=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, mnum);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			} // if e
		}catch(Exception e) {System.out.println("[DB 연동 오류-아이디호출]"+e);}
		return null;
	} // 회원번호로 아이디 호출
	
	///대회신청시 사용한 포인트가 있다면 기존의 포인트에서 마이너스
	public boolean cminuspoint(int 회원번호,int 포인트갱신) {
		try {
			String sql = "update 회원 set 회원포인트=?  where 회원번호=?";
			
			ps=con.prepareStatement(sql);
			ps.setInt(1, 포인트갱신);
			ps.setInt(2, 회원번호);
			ps.executeUpdate();
			return true;
			
		}catch(Exception e) {}
		return false;
	}
	
	public void pointlog2 (String id) {
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String pointdate = sdf.format(date);
			String sql = "select * from 조회수 where 조회수아이디=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()==false) { // 로그인날짜 기록이 없으면 새로 생성
				String sql2 = "insert into 조회수 (조회수아이디,조회수로그) values(?,?)";
				ps = con.prepareStatement(sql2);
				ps.setString(1, id);
				ps.setString(2, pointdate);
				ps.executeUpdate();
				return;
			}
			else { // 로그인 기록이 있을시
				if(rs.getString(2).equals(pointdate)) { // 로그인 기록과 로그인한 날짜가 같으면
					return; //
				}
				else if(!rs.getString(2).equals(pointdate)) { // 로그인기록과 로그인한날짜가 다르면 로그인날짜 저장
					String sql3 = "update 조회수 set 조회수로그=?, 조회수게시판번호=? where 조회수아이디=?";
					ps = con.prepareStatement(sql3);
					ps.setString(1, pointdate);
					ps.setString(2, null); // 로그인기록에 읽었던 게시글 번호들 초기화
					ps.setString(3, id);
					ps.executeUpdate();
					return;
				}
			}
		}
		catch(Exception e) {
			System.out.println("[SQL 오류/포인트로그]" + e );
		}
	}
}