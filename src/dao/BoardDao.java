package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import dto.Board;
import dto.Comment;
import dto.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class BoardDao {
	
	private Connection con ;
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public static BoardDao boardDao = new BoardDao();
	
	public BoardDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?serverTimezone=Asia/Seoul","root","1234");
		}
		catch(Exception e) {
			System.out.println("BoardDao DB연동" + e);
		}
	}

	// 메소드 시작
	
	// 1. 글 작성 메소드
	public boolean bwrite(Board board) {
		String sql = "insert into 게시판(게시판작성자,게시판카테고리, 게시판제목, 게시판내용,게시판이미지경로,게시판조회수) values(?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, board.get게시판작성자());
			ps.setInt(2, board.get게시판카테고리());
			ps.setString(3, board.get게시판제목());
			ps.setString(4, board.get게시판내용());
			ps.setString(5, board.get이미지경로());
			ps.setInt(6, board.get게시판조회수());			
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[SQL 오류-글 작성]"+e);}
		return false;
	} // 글 작성 end
	
	// 2. 글 삭제 메소드
	public boolean bdelete(int bnum) {
		String sql = "delete from 게시판 where 게시판번호=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, bnum);
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[SQL 오류-글 삭제]"+e);}
		return false;
	} // 글 삭제 end
	
	// 3. 글 수정 메소드
	public boolean bupdate(int category, String title, String contents, String image, int bnum) {
		String sql = "update 게시판 set 게시판카테고리=?, 게시판제목=?, 게시판내용=? ,게시판이미지경로=? where 게시판번호=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, category);
			ps.setString(2, title);
			ps.setString(3, contents);
			ps.setString(4, image);
			ps.setInt(5, bnum);
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[SQL 오류-글 수정]"+e);}
		return false;
	} // 글 수정 end
	
	// 4. 모든 작성 글 호출 메소드
	public ObservableList<Board> blist() {
		ObservableList<Board> boardlist = FXCollections.observableArrayList();
		
		try {
			String sql = "SELECT a.* ,b.회원아이디  FROM 게시판 a left join 회원 b on a.게시판작성자 = b.회원번호 order by 게시판번호 desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) { // 게시판번호, 작성자, 작성자아이디,게시판카테고리, 제목, 내용, 경로, 조회수, 작성일
				Board board = new Board(rs.getInt(1),
						rs.getInt(2),
						rs.getString(9),
						rs.getInt(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getString(8)
						);
				if(board.get작성자아이디()!=null) { boardlist.add(board);}
			} // while e
			return boardlist;
			}catch(Exception e) {System.out.println("[SQL 오류-글 호출]"+e);}
		return null;
	} // 모든 작성 글 호출 end
	
	// 4. 모든 작성 글 호출 메소드
	public ObservableList<Board> blist2(int category) {
		ObservableList<Board> boardlist = FXCollections.observableArrayList();
		
		try {
			String sql = "SELECT a.* ,b.회원아이디  FROM 게시판 a left join 회원 b on a.게시판작성자 = b.회원번호 where 게시판카테고리=? order by 게시판번호 desc";
			ps = con.prepareStatement(sql);
			ps.setInt(1, category);
			rs = ps.executeQuery();
			while(rs.next()) { // 게시판번호, 작성자, 작성자아이디,게시판카테고리, 제목, 내용, 경로, 조회수, 작성일
				Board board = new Board(rs.getInt(1),
						rs.getInt(2),
						rs.getString(9),
						rs.getInt(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getString(8)
						);
				if(board.get작성자아이디()!=null) { boardlist.add(board);}	
			} // while e
			return boardlist;
			}catch(Exception e) {System.out.println("[SQL 오류-글 호출]"+e);}
		return null;
	} // 모든 작성 글 호출 end
	
	
	
	
	// 조회수 메소드
		public boolean view(int bnum) {
			try {
				String sql = "update 게시판 set 게시판조회수=? where 게시판번호=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, controller.board.Board.board.get게시판조회수()+1);
				ps.setInt(2, bnum);
				ps.executeUpdate();
				return true;
			}
			catch(Exception e) {
				System.out.println("[조회수 메소드]" + e);
			}
			return false;
		}
		
		// 9. 조회수 로그 메소드 (DB써서 만든거)
		public boolean viewlog (String id) {
			try {
				String sql = "select * from 조회수 where 조회수아이디=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String pointdate = sdf.format(date);
			// 4. SQL 결과
				if(rs.next()==false) {  // ???????? 이부분을 없애면 에러가 뜸 어차피 로그인할때 pointlog table에 값이 생성되므로
										// rs.next가 false일때가 없는데....
					String sql2 = "insert into 조회수 (조회수아이디,조회수로그,조회수게시판번호) values(?,?,?)";
					ps = con.prepareStatement(sql2);
					ps.setString(1, id);
					ps.setString(2, pointdate);
					ps.setString(3, controller.board.Board.board.get게시판번호()+"");
					ps.executeUpdate();
					return true;
				}
				else { 
					if(rs.getString(3)==null) { // 게시글 읽은 내역이 없을때
						String sql3 = "update 조회수 set 조회수게시판번호=? where 조회수아이디=?";
						ps = con.prepareStatement(sql3);
						ps.setString(1, controller.board.Board.board.get게시판번호()+"");
						ps.setString(2, id);
						ps.executeUpdate();
						return true;
					}
					// 게시글 읽은 내역이 있을때
					String[] nums = rs.getString(3).split(","); // 게시글번호들을 문자열배열로 저장
					for(String temp : nums) { // 배열에서 클릭한 게시물번호가 있으면  false
						if(temp.equals(controller.board.Board.board.get게시판번호()+"")) { // 읽었음						
							return false;
						}
					} 
					// 없으면 번호 추가
					String sql3 = "update 조회수 set 조회수게시판번호=? where 조회수아이디=?";
					ps = con.prepareStatement(sql3);
					ps.setString(1, rs.getString(3)+","+controller.board.Board.board.get게시판번호());
					ps.setString(2, id);
					ps.executeUpdate();
					return true;
				}
			}
			catch(Exception e) {
				System.out.println("[SQL 오류/조회수로그]" + e );
			}
			return false;
		} // 조회수 로그 메소드 (DB써서 만든거)
		public void updateimgdel(int bnum){
			
			String sql = "update 게시판 set 게시판이미지경로 = ? where 게시판번호 =?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, null);
				ps.setInt(2, bnum);
				ps.executeUpdate();
			} catch (Exception e) {System.out.println("sql오류 이미지삭제"+e);}
			
		}
	
		
} // class end
