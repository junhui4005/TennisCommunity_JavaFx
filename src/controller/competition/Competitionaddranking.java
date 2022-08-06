package controller.competition;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import controller.home.Home;
import dao.PointDao;
import dao.RecordDao;
import dto.Point;
import dto.Record;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Competitionaddranking implements Initializable{

	public static Record record;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		int cnum= Competition.selectcnum.get��ȸ��ȣ();
		
		ObservableList<Record> recordlist = RecordDao.recordDao.list(cnum);
		
		///db�� ��� �Խñ� ��������
		TableColumn tc = entrytable.getColumns().get(0);
		
		tc.setCellValueFactory(new PropertyValueFactory<>("��Ϲ�ȣ"));
		
		tc = entrytable.getColumns().get(1);
		
		tc.setCellValueFactory(new PropertyValueFactory<>("ȸ�����̵�"));
		
		tc = entrytable.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("��ϼ���"));
		
		entrytable.setItems(recordlist);
		
		///table �� ��ü�� �������� ��
		entrytable.setOnMouseClicked(e->{
			
			record = entrytable.getSelectionModel().getSelectedItem();
			System.out.println(record);
		});
		
	}
	
	 @FXML
	    private TableView<Record> entrytable;
	 
	 @FXML
	    private Label recordnum;

	
	  @FXML
	    private Button btnadd;

	    @FXML
	    private TextField txtscore;

	    @FXML
	    private Button btnback;

	    @FXML
	    void add(ActionEvent event) {
	    	
	    	
	    	int ��Ϲ�ȣ = record.get��Ϲ�ȣ();
	    	int ��ϼ��� = Integer.parseInt(txtscore.getText());
	    	int ���1���� = Competition.selectcnum.get���1��();
	    	int ���2���� = Competition.selectcnum.get���2��();
	    	int ���3���� = Competition.selectcnum.get���3��();
	    	
	    	boolean result = RecordDao.recordDao.rankingadd(��ϼ���, ��Ϲ�ȣ);
	    	
	    	if(result) {
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    	String pdate = sdf.format(new Date());
	    		int ���ȸ����ȣ = record.get���ȸ����ȣ();
	    		////�����Է½� ��� �ڵ� db����
	    		if(��ϼ���==1){
	    			RecordDao.recordDao.addreward1(���1����,��Ϲ�ȣ);
	    			Home.instance.loadpage("/view/competition/competitionaddranking.fxml");
	    			
	    			Point point = new Point(���ȸ����ȣ, ���1����, pdate);
	    			PointDao.pointDao.rewardpointadd1(point);
	    			
	    		}
	    		else if(��ϼ���==2){
	    			RecordDao.recordDao.addreward2(���2����,��Ϲ�ȣ);
	    			Home.instance.loadpage("/view/competition/competitionaddranking.fxml");
	    			Point point = new Point(���ȸ����ȣ, ���2����, pdate);
	    			PointDao.pointDao.rewardpointadd1(point);
	    		
	    		}
	    		else if(��ϼ���==3) {
	    			RecordDao.recordDao.addreward3(���3����,��Ϲ�ȣ);
	    			Home.instance.loadpage("/view/competition/competitionaddranking.fxml");
	    			Point point = new Point(���ȸ����ȣ, ���3����, pdate);
	    			PointDao.pointDao.rewardpointadd1(point);
	    		}
	    		else {
	    			Home.instance.loadpage("/view/competition/competitionaddranking.fxml");
	    		}
	    		
	    	}
	    	
	    
	    	
	    }

	    @FXML
	    void back(ActionEvent event) {
	    	Home.instance.loadpage("/view/competition/competitionview.fxml");
	    }

	
}
