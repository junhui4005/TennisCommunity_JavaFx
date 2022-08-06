package controller.home;

import java.net.URL;
import java.util.ResourceBundle;

import controller.competition.Competition;
import controller.competition.Competitionview;
import controller.login.Login;
import dao.BoardDao;
import dao.TournamentDao;
import dto.Board;
import dto.Tournament;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Homemain implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		boardlist = BoardDao.boardDao.blist();
		list();	
		
		
		tournamentlist = TournamentDao.tournamentDao.clist();
		list2();	
	}
	
	@FXML
    private ImageView mainimg;

    @FXML
    private TableView<Board> boardtable;

    @FXML
    private TableView<Tournament> ctable;
    
    public static ObservableList<Tournament> tournamentlist = TournamentDao.tournamentDao.clist();
    
    public static ObservableList<Board> boardlist = BoardDao.boardDao.blist();
    
    public void list() {
   	 
    	// 1. db���� ��� �Խñ� ��������
      	ObservableList<Board> boardlist = BoardDao.boardDao.blist2(1);
      
      	// 2. tableview �� �߰� 
      	TableColumn tc = boardtable.getColumns().get(0);
      	tc.setCellValueFactory(new PropertyValueFactory<>("�Խ��ǹ�ȣ"));
      	
      	tc = boardtable.getColumns().get(1); 
      	tc.setCellValueFactory(new PropertyValueFactory<>("�Խ�������"));
      	
      	tc = boardtable.getColumns().get(2); 
      	tc.setCellValueFactory(new PropertyValueFactory<>("�Խ����ۼ���"));
      	
      	
      	// 3. tableview �� list ����
      	boardtable.setItems(boardlist);
      		//	���̺��(fxid).setItems( Observablelist ) ; // ���̺� ǥ���� ����Ʈ(�Ϲ��� arraylist x) ����
      	
      	// tableview ���� �ش� ���� Ŭ�������� �̺�Ʈ
      	// boardtable.setOnMouseClicked(e -> {�����ڵ�}); ���̺��� Ŭ��������'
      	
      	boardtable.setOnMouseClicked(e -> {
      	// 1. ���̺��� Ŭ���� ��ü�� �ӽð�ü�� ����	
      	controller.board.Board.board = boardtable.getSelectionModel().getSelectedItem(); // Ŭ���� board ��ü ȣ��
      	
      	if(controller.board.Board.board ==null) {}
      	else {
      		System.out.println("1");
			boolean result = BoardDao.boardDao.viewlog(Login.member.getȸ�����̵�());
			if(result) {
				
				BoardDao.boardDao.view(controller.board.Board.board.get�Խ��ǹ�ȣ());
				Home.instance.loadpage("/view/board/boardview.fxml");
			}
      	else {
      		// 3. ��������ȯ
      		Home.instance.loadpage("/view/board/boardview.fxml");
      		}
      		}
      		}
      	);

        }
    
    public void list2(){
    	ObservableList<Tournament> tournamentlist = TournamentDao.tournamentDao.clist();
		
    	////db���ˤ� ��� �Խñ� ��������
    	///tableview�� �߰�
    	
    	TableColumn tc = ctable.getColumns().get(0);
    	
    	tc.setCellValueFactory(new PropertyValueFactory<>("��ȸ��ȣ"));
    	
    	tc = ctable.getColumns().get(1);
    	tc.setCellValueFactory(new PropertyValueFactory<>("��ȸ�̸�"));
    	
    	tc = ctable.getColumns().get(2);
    	tc.setCellValueFactory(new PropertyValueFactory<>("��ȸ��¥"));
    	
    	
    	
    	
    	
    	ctable.setItems(tournamentlist);
    	
    	
    	/// tableview�����ش� ���� Ŭ�������� �̺�Ʈ
    	ctable.setOnMouseClicked( e -> { 
    		
    		int ��ȸ��ȣ = ctable.getSelectionModel().getSelectedItem().get��ȸ��ȣ();
    		String ��ȸ�̸� = ctable.getSelectionModel().getSelectedItem().get��ȸ�̸�();
    		String ��ȸ��¥ = ctable.getSelectionModel().getSelectedItem().get��ȸ��¥();
    		int �����ο� = ctable.getSelectionModel().getSelectedItem().get�����ο�();
    		String ��ȸ�̹������ = ctable.getSelectionModel().getSelectedItem().get��ȸ�̹������();
    		int ���1�� = ctable.getSelectionModel().getSelectedItem().get���1��();
    		int ���2�� = ctable.getSelectionModel().getSelectedItem().get���2��();
    		int ���3�� = ctable.getSelectionModel().getSelectedItem().get���3��();
    		int ������  = ctable.getSelectionModel().getSelectedItem().get������();
    		
    		Tournament tn = new Tournament(��ȸ��ȣ, ��ȸ�̸�, ��ȸ��¥, �����ο�, ��ȸ�̹������, ���1��, ���2��, ���3��, ������);
    		
    		
    		
    		Competition.selectcnum = ctable.getSelectionModel().getSelectedItem();

    		Home.instance.loadpage("/view/competition/competitionview.fxml");
    		
    	});
    }
    
    
    
    @FXML
    void maininfo(MouseEvent event) {
    	Home.instance.loadpage("/view/home/maininfo.fxml");
    }

	
	
}
