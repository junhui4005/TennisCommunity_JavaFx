package controller.board;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.home.Homemain;
import controller.login.Login;
import dao.BoardDao;

import dto.Board;
import dao.CommentDao;
import dao.MemberDao;
import dto.Comment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Boardview implements Initializable {
	
	@FXML
    private Button btnredelete;
	
	@FXML
    private Label lbltitle;

    @FXML
    private Button btnback;

    @FXML
    private ImageView img;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btndelete;

    @FXML
    private TextArea txtbcontent;

    @FXML
    private Label txtbdate;

    @FXML
    private Label txtmid;

    @FXML
    private Button btnrewrite;

    @FXML
    private TextArea txtrecontent;

    @FXML
    private TableView<Comment> replytable;

    @FXML
    private Button btnreplyupdate;
    
    public static Comment reply;
    

    @FXML
    public void prompt(MouseEvent event) {
    	txtrecontent.setText("");
    }
    public static Board board = controller.board.Board.board ;

    // �Խñ� �� �޼ҵ�
    public void boardviewinfo() {
    	Board board = controller.board.Board.board;
		System.out.println(board.get�Խ���ī�װ�());
		if(board.get�̹������() == null) {
		lbltitle.setText("���� : "  + board.get�Խ�������());
		txtbdate.setText("��Ͻð� : " +board.get�Խ����ۼ���() );
		txtmid.setText("���̵� : " + board.get�ۼ��ھ��̵�() );
		txtbcontent.setText(board.get�Խ��ǳ���());
		img.setImage(null);
		}
		txtbcontent.setEditable(false);
		if(board.get�̹������() != null) {
			lbltitle.setText("���� : "  + board.get�Խ�������());
			txtbdate.setText("��Ͻð� : " +board.get�Խ����ۼ���() );
			txtmid.setText("���̵� : " + board.get�ۼ��ھ��̵�());
			txtbcontent.setText(board.get�Խ��ǳ���());
			img.setImage(new Image(board.get�̹������()));
		}
		if( !board.get�ۼ��ھ��̵�().equals(Login.member.getȸ�����̵�()) ) {
			txtrecontent.setText("");
			btnupdate.setVisible(false);
			btndelete.setVisible(false);
			
			commenttableshow();
			
		}
    	
    }

    // ��� ���̺� �޼ҵ�
    public void commenttableshow() {
    	// 1. �� �Խù� ��ȣ
    	int bnum = controller.board.Board.board.get�Խ��ǹ�ȣ();
    	
    	// 2. �� �Խù��� ���� ��۸���Ʈ
    	ObservableList<Comment> commentlist = CommentDao.commentDao.clist(bnum);
    	
    	System.out.println(commentlist.toString());
    	TableColumn tc = replytable.getColumns().get(0);
    	tc.setCellValueFactory(new PropertyValueFactory<>("��۹�ȣ"));
    	
    	tc = replytable.getColumns().get(1);
    	tc.setCellValueFactory(new PropertyValueFactory<>("�ۼ���"));
    	
    	tc = replytable.getColumns().get(2);
    	tc.setCellValueFactory(new PropertyValueFactory<>("����ۼ���"));
    	
    	tc = replytable.getColumns().get(3);
    	tc.setCellValueFactory(new PropertyValueFactory<>("��۳���"));
    	btnreplyupdate.setVisible(false);
    	btnredelete.setVisible(false);
		
    	
    	// ��� ���ý� �ؽ�Ʈ���ڿ� ��۳��� ǥ��
    	replytable.setItems(commentlist);
    	replytable.setOnMouseClicked( e ->{ 
    		reply = replytable.getSelectionModel().getSelectedItem(); 
    		txtrecontent.setText(replytable.getSelectionModel().getSelectedItem().get��۳���());
    		
    		
    		
    		if(!reply.get�ۼ���().equals(Login.member.getȸ�����̵�())) {
    			btnreplyupdate.setVisible(false);
    			txtrecontent.setText("");
    			btnredelete.setVisible(false);
    			//txtrecontent.setEditable(false);
    			
    			
    			//btnrewrite.setVisible(false);
    		}
    		else {btnreplyupdate.setVisible(true);
    		btnrewrite.setVisible(true);
    		btnredelete.setVisible(true);
    		}
    	}
    			
    	);
    	
    }
    
    @FXML
    void back(ActionEvent event) {
    	// �Խñۺ��� ���������� �ڷΰ��� ������ �Խñ۸���Ʈ 
    	Home.instance.loadpage("/view/board/board.fxml");

    }

    @FXML
    void delete(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setHeaderText("�ش� �Խù� �����ұ��?");
    	Optional<ButtonType> optional = alert.showAndWait();
    	if(optional.get() == ButtonType.OK) {
    		
    		BoardDao.boardDao.bdelete(controller.board.Board.board.get�Խ��ǹ�ȣ());
    		// �Խñۺ��� ���������� ���� ������ �Խñ� ����Ʈ
        	Home.instance.loadpage("/view/board/board.fxml");
    	}
    	

    }
    boolean upcheck = true; // ���� ��ư ����ġ ����
    @FXML
    void replyupdate(ActionEvent event) {
    	int bnum = reply.get��۹�ȣ();
    	String ��ۼ������� = txtrecontent.getText();
    	Alert alert = new Alert(AlertType.INFORMATION);
    	boolean result = CommentDao.commentDao.rupdate(��ۼ�������, bnum);
    	
    	
    	if(upcheck) {
    		alert.setHeaderText("��� ������ ��ư �����ּ���");
	    	alert.showAndWait();
	    	txtrecontent.setEditable(true);
	    	btnrewrite.setVisible(false);
	    	btnupdate.setText("�����Ϸ�");
    		upcheck = false;
    	}
    	else {
    		if(result ) {
        		
    			alert.setHeaderText(" �� ���� ����");
    		
    		alert.showAndWait();
    		reply.set��۳���(��ۼ�������);
    		btnupdate.setText("����");
			upcheck = true;
    		Home.instance.loadpage("/view/board/boardview.fxml");
        	}
    		
    	}
    	

    }

    @FXML
    void rewrite(ActionEvent event) {
    	// �Խñۺ��� ���������� ����ۼ��ϸ� �Խñۺ���������


    	String rcontent = txtrecontent.getText();
    	if(rcontent.equals("")) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("������ �Է��ϼ���");
			alert.showAndWait();
			//Home.instance.loadpage("/view/board/boardview.fxml");
    		System.out.println("�ȵ� ���ư�");
    		return;
    	}
    	int mnum = Login.member.getȸ����ȣ();
    	int bnum = controller.board.Board.board.get�Խ��ǹ�ȣ();    	
    	
    	Comment comment = new Comment(0, bnum, mnum, MemberDao.memberDao.getid(mnum), rcontent, null);
    	
    	boolean result = CommentDao.commentDao.rwrite(comment);
    	
    	
    	if(result) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("��� ��� ����");
    		alert.showAndWait();
    		txtrecontent.setText("");
    		commenttableshow();
    		//Home.instance.loadpage("/view/board/boardview.fxml");
    	}
    	
    	
    }
    
    @FXML
    void redelete(ActionEvent event) {
    	
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("�ش� ����� �����ұ��?");
	Optional<ButtonType> optional = alert.showAndWait();
	if(optional.get() == ButtonType.OK) {
		
		CommentDao.commentDao.rdelete(reply.get��۹�ȣ());
		// �Խñۺ��� ���������� ���� ������ �Խñ� ����Ʈ
    	Home.instance.loadpage("/view/board/boardview.fxml");
	}
    	

    }

    @FXML
    void update(ActionEvent event) {
    	// �Խñۺ��� ���������� �����ϱ� ������ ������������ �̵�
    	Home.instance.loadpage("/view/board/boardupdate.fxml");
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		commenttableshow();
		boardviewinfo();
		
		
		
		
	}

}