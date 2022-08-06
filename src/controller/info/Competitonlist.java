package controller.info;

import java.net.URL;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.RecordDao;
import dto.Mytournamentlist;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Competitonlist implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		competitiontableshow();
		
	}
	
	  @FXML
	  private TableView<Mytournamentlist> boardtable;

	    @FXML
	    private Button btnback;

	    @FXML
	    void back(ActionEvent event) {
	    	Home.instance.loadpage("/view/info/Info.fxml");
	    }
	    
	    // �� ��ȸ��� ���̺� �޼ҵ�
	    public void competitiontableshow() {
	    	int mnum = Login.member.getȸ����ȣ();
	    	ObservableList<Mytournamentlist> mylist = RecordDao.recordDao.mtlist(mnum);
	    	
	    	TableColumn tc = boardtable.getColumns().get(0);
	    	tc.setCellValueFactory(new PropertyValueFactory<>("��ȸ��ȣ"));
	    	
	    	tc = boardtable.getColumns().get(1);
	    	tc.setCellValueFactory(new PropertyValueFactory<>("��ȸ�̸�"));
	    	
	    	tc = boardtable.getColumns().get(2);
	    	tc.setCellValueFactory(new PropertyValueFactory<>("��ȸ��¥"));
	    	
	    	tc = boardtable.getColumns().get(3);
	    	tc.setCellValueFactory(new PropertyValueFactory<>("��ϼ���"));
	    	
	    	tc = boardtable.getColumns().get(4);
	    	tc.setCellValueFactory(new PropertyValueFactory<>("��Ϲ������"));
	    	
	    	boardtable.setItems(mylist);
	    } // �� ��ȸ��� ���̺� end
	    

	
}
