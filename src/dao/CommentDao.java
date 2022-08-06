package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.Comment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CommentDao {
	
	private Connection con ;
	private PreparedStatement ps ;
	private ResultSet rs ;
	
	public static CommentDao commentDao = new CommentDao();
	
	public CommentDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?serverTimezone=Asia/Seoul","root","1234");
		}
		catch(Exception e) {
			System.out.println("CommentDao DB연동" + e);
		}
	}
	
	// 메소드 시작
	
	// 1. 댓글 작성 메소드
	public boolean rwrite(Comment comment) {
		String sql = "insert into 댓글(댓글게시판번호, 댓글작성자, 댓글내용) values(?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, comment.get댓글게시판번호());
			ps.setInt(2, comment.get댓글작성자());
			ps.setString(3, comment.get댓글내용());
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("[SQL 오류-댓글 작성]"+e);}
		return false;
	} // 댓글 작성 end
	
	// 2. 해당 게시물 모든 댓글 호출 메소드
	public ObservableList<Comment> clist(int bnum){
		ObservableList<Comment> commentlist = FXCollections.observableArrayList();
		String sql = "select a.* ,b.회원아이디 from 댓글 a left join 회원 b on a.댓글작성자 = b.회원번호 where 댓글게시판번호=? order by 댓글번호 asc";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, bnum);
			rs = ps.executeQuery();
			while(rs.next()) { // 댓글번호, 댓글게시판번호, 댓글작성자, 작성자(아이디), 댓글내용, 댓글작성일
				Comment comment = new Comment(rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getString(6), ///////////////??????
						rs.getString(4), 
						rs.getString(5) );
				commentlist.add(comment);
			} // while e
			return commentlist;
			}catch(Exception e) {System.out.println("[SQL 오류-댓글 호출]"+e);}
		return null;
	} // 게시물 당 모든 댓글 호출 end
	
	// 3.댓글수정 메소드
	public boolean rupdate( String rcontent, int rnum) {
		String sql = "update 댓글 set 댓글내용=? where 댓글번호=?";
		try {
			ps =con.prepareStatement(sql);
			ps.setString(1,rcontent );
			ps.setInt(2, rnum);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("댓글수정 오류_CommentDao" + e);	}
		return false;
		
	}
	
	// 4. 댓글삭제 메소드
	public boolean rdelete(int rnum) {
		String sql = "delete from 댓글 where 댓글번호=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, rnum);
			ps.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("댓글삭제_sql오류" + e);}
		return false;
	}
	
	
	
	
	
}
