package controller.board;

import java.net.URL;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.BoardDao;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class Board implements Initializable {
	
	@FXML
    private TableView<dto.Board> boardtable;
		
    @FXML
    private Button btnwrite;

    @FXML
    private Button btnback;
    @FXML
    private ToggleGroup category;

    @FXML
    private RadioButton opt3;

    @FXML
    private RadioButton opt2;

    @FXML
    private RadioButton opt1;

    @FXML
    void accwrite(ActionEvent event) { //
    	// ���ۼ� ��ư�� ������ ���ۼ� ��������
    	Home.instance.loadpage("/view/board/boardwrite.fxml");
    }

   
	
    public static dto.Board board;
    public static ObservableList<dto.Board> boardlist = BoardDao.boardDao.blist();
    
    public void list() {
    	
    	// 1. db���� ��� �Խñ� ��������

    	// 2. tableview �� �߰� 
    	TableColumn tc = boardtable.getColumns().get(0);
    	tc.setCellValueFactory(new PropertyValueFactory<>("�Խ��ǹ�ȣ"));
    	
    	tc = boardtable.getColumns().get(1); 
    	tc.setCellValueFactory(new PropertyValueFactory<>("�Խ�������"));
    	
    	tc = boardtable.getColumns().get(2); 
    	tc.setCellValueFactory(new PropertyValueFactory<>("�ۼ��ھ��̵�"));
    	
    	tc = boardtable.getColumns().get(3); 
    	tc.setCellValueFactory(new PropertyValueFactory<>("�Խ����ۼ���"));
    	
    	tc = boardtable.getColumns().get(4); 
    	tc.setCellValueFactory(new PropertyValueFactory<>("�Խ�����ȸ��"));
    	
    	// 3. tableview �� list ����
    	boardtable.setItems(boardlist);
    		//	���̺��(fxid).setItems( Observablelist ) ; // ���̺� ǥ���� ����Ʈ(�Ϲ��� arraylist x) ����
    	
    	// tableview ���� �ش� ���� Ŭ�������� �̺�Ʈ
    	// boardtable.setOnMouseClicked(e -> {�����ڵ�}); ���̺��� Ŭ��������'
    	
    	boardtable.setOnMouseClicked(e -> {
    	// 1. ���̺��� Ŭ���� ��ü�� �ӽð�ü�� ����	
    	board = boardtable.getSelectionModel().getSelectedItem(); // Ŭ���� board ��ü ȣ��
    	// 2. ��ȸ�� ����
    	if(board ==null) {}
    	else {
			boolean result = BoardDao.boardDao.viewlog(Login.member.getȸ�����̵�());
			if(result) {
				BoardDao.boardDao.view(board.get�Խ��ǹ�ȣ());
				Home.instance.loadpage("/view/board/boardview.fxml");
			}
    	
    	else {
    		// 3. ��������ȯ
    		Home.instance.loadpage("/view/board/boardview.fxml");
    	}
    	}
    	
    	
    	});
    	
    	
    }
    
    
  public void list2() {
	 
	 
	// 1. db���� ��� �Խñ� ��������
  	ObservableList<dto.Board> boardlist = BoardDao.boardDao.blist2(1);
  
  	// 2. tableview �� �߰� 
  	TableColumn tc = boardtable.getColumns().get(0);
  	tc.setCellValueFactory(new PropertyValueFactory<>("�Խ��ǹ�ȣ"));
  	
  	tc = boardtable.getColumns().get(1); 
  	tc.setCellValueFactory(new PropertyValueFactory<>("�Խ�������"));
  	
  	tc = boardtable.getColumns().get(2); 
  	tc.setCellValueFactory(new PropertyValueFactory<>("�ۼ��ھ��̵�"));
  	
  	tc = boardtable.getColumns().get(3); 
  	tc.setCellValueFactory(new PropertyValueFactory<>("�Խ����ۼ���"));
  	
  	tc = boardtable.getColumns().get(4); 
  	tc.setCellValueFactory(new PropertyValueFactory<>("�Խ�����ȸ��"));
  	
  	// 3. tableview �� list ����
  	boardtable.setItems(boardlist);
  		//	���̺��(fxid).setItems( Observablelist ) ; // ���̺� ǥ���� ����Ʈ(�Ϲ��� arraylist x) ����
  	
  	// tableview ���� �ش� ���� Ŭ�������� �̺�Ʈ
  	// boardtable.setOnMouseClicked(e -> {�����ڵ�}); ���̺��� Ŭ��������'
  	
  	boardtable.setOnMouseClicked(e -> {
  	// 1. ���̺��� Ŭ���� ��ü�� �ӽð�ü�� ����	
  	board = boardtable.getSelectionModel().getSelectedItem(); // Ŭ���� board ��ü ȣ��
  	
  	if(board ==null) {}
  	else {
  		// 3. ��������ȯ
  		Home.instance.loadpage("/view/board/boardview.fxml");
  	}
  	
  	
  	});

    }
  
  public void list3() {
		 
		 
		// 1. db���� ��� �Խñ� ��������
	  	ObservableList<dto.Board> boardlist = BoardDao.boardDao.blist2(2);
	  
	  	// 2. tableview �� �߰� 
	  	TableColumn tc = boardtable.getColumns().get(0);
	  	tc.setCellValueFactory(new PropertyValueFactory<>("�Խ��ǹ�ȣ"));
	  	
	  	tc = boardtable.getColumns().get(1); 
	  	tc.setCellValueFactory(new PropertyValueFactory<>("�Խ�������"));
	  	
	  	tc = boardtable.getColumns().get(2); 
	  	tc.setCellValueFactory(new PropertyValueFactory<>("�ۼ��ھ��̵�"));
	  	
	  	tc = boardtable.getColumns().get(3); 
	  	tc.setCellValueFactory(new PropertyValueFactory<>("�Խ����ۼ���"));
	  	
	  	tc = boardtable.getColumns().get(4); 
	  	tc.setCellValueFactory(new PropertyValueFactory<>("�Խ�����ȸ��"));
	  	
	  	// 3. tableview �� list ����
	  	boardtable.setItems(boardlist);
	  		//	���̺��(fxid).setItems( Observablelist ) ; // ���̺� ǥ���� ����Ʈ(�Ϲ��� arraylist x) ����
	  	
	  	// tableview ���� �ش� ���� Ŭ�������� �̺�Ʈ
	  	// boardtable.setOnMouseClicked(e -> {�����ڵ�}); ���̺��� Ŭ��������'
	  	
	  	boardtable.setOnMouseClicked(e -> {
	  	// 1. ���̺��� Ŭ���� ��ü�� �ӽð�ü�� ����	
	  	board = boardtable.getSelectionModel().getSelectedItem(); // Ŭ���� board ��ü ȣ��
	  	
	  	if(board ==null) {}
	  	else {
	  		// 3. ��������ȯ
	  		Home.instance.loadpage("/view/board/boardview.fxml");
	  	}
	  	
	  	
	  	});

	    }
    
   
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		boardlist = BoardDao.boardDao.blist();
		list();	
		
	}
	
	public void viewall(){
		list();
	}
	public void view1(){
		list2();
	}
	public void view2(){
		list3();
	}
	

}
