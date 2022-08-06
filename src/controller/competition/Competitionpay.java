package controller.competition;

import java.net.URL;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.MemberDao;
import dao.RecordDao;
import dto.Member;
import dto.Record;
import dto.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Competitionpay implements Initializable{

	public static Record selectnum;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		
		///���� ����Ʈ �ȳ�
		lblfee.setText("������: "+Competition.selectcnum.get������()+"��");
		lblpoint.setText("��������Ʈ: " + Login.member.getȸ������Ʈ()+"��");
		txtpoint.setText("0");
		
		lblerror.setVisible(false);
		lblamount.setText("�����ݾ�: " + Competition.selectcnum.get������() +" ��");
		
	}
	  @FXML
	    private Label lblfee;

	    @FXML
	    private Label lblpoint;

	    @FXML
	    private TextField txtpoint;

	    @FXML
	    private Button btnpoint;

	    @FXML
	    private Label lblamount;

	    @FXML
	    private Label lblerror;
	    
	    @FXML
	    private TextField txtcash;

	    @FXML
	    private Button btnpay;

	    @FXML
	    void pay(ActionEvent event) {
	    	
	    	////view�� ��ü ��������
	    	int member = Login.member.getȸ����ȣ();
	    	Tournament tournament  = Competition.selectcnum;
	    	
	    	///�����ݾ� 
	    	String [] �����ݾ� = lblamount.getText().split(" ");
	    	int ������ = Integer.parseInt(�����ݾ�[1]);
	    	System.out.println(������);
	    	////
	    	if( (Competition.selectcnum.get������()-Integer.parseInt(txtpoint.getText()))>Integer.parseInt(txtcash.getText())) {
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setHeaderText("�����ݾ��� �����մϴ�.");
	    		alert.showAndWait();
	    	}
	    	else {
	    
	    	int ȸ����ȣ =Login.member.getȸ����ȣ();
	    	int ����Ʈ���� = (Login.member.getȸ������Ʈ()-Integer.parseInt(txtpoint.getText()));
	    	////����� ����Ʈ��ŭ �������Ʈ���� ���̳ʽ� 
	    	///dbó��
	    	MemberDao.memberDao.cminuspoint(ȸ����ȣ,����Ʈ����);
	    		
	    	//��üȭ
	    	Record record = new Record(0,MemberDao.memberDao.getid(ȸ����ȣ), tournament.get��ȸ��ȣ(),ȸ����ȣ, 0, 0);
	    		///dbó��
	    	boolean result = RecordDao.recordDao.apply(record);
	    	
	    	if(result) {
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setHeaderText("��ȸ������û �Ϸ�");
	    		alert.showAndWait();
	    		
	    		Home.instance.loadpage("/view/competition/competition.fxml");
	    		
	    		
	    		
	    		}
	    	}
	    }

	    @FXML
	    void point(ActionEvent event) {
	    	lblerror.setVisible(true);
	    	
	    	try {
		    	if(Integer.parseInt(txtpoint.getText())<=0 || Integer.parseInt(txtpoint.getText()) > Login.member.getȸ������Ʈ() ) {
		    		lblerror.setText("����Ʈ�� �߸� �Է��ϼ̽��ϴ�.");
		    		txtpoint.setText("0");
		    		lblamount.setText("�����ݾ�: " +( Competition.selectcnum.get������()-Integer.parseInt(txtpoint.getText()))+" ��");
		    	
		    	}
		    	else if(Integer.parseInt(txtpoint.getText())%100 != 0){
		    		lblerror.setText("����Ʈ�� 100�� �����θ� ��밡���մϴ�.");	   	
		    		txtpoint.setText("0");
		    		lblamount.setText("�����ݾ�: " +( Competition.selectcnum.get������()-Integer.parseInt(txtpoint.getText()))+" ��");
		    	}
		    	else if(Integer.parseInt(txtpoint.getText())<=0 || Integer.parseInt(txtpoint.getText())>Competition.selectcnum.get������()) {
		    		lblerror.setText("�����ݾ��� �ʰ��ϼ̽��ϴ�.");
		    		txtpoint.setText("0");
		    		lblamount.setText("�����ݾ�: " +( Competition.selectcnum.get������()-Integer.parseInt(txtpoint.getText()))+" ��");
		    		
		    	}
		    	else {
		    		lblerror.setText(txtpoint.getText() + "����Ʈ ����մϴ�.");
		    		lblamount.setText("�����ݾ�: " +( Competition.selectcnum.get������()-Integer.parseInt(txtpoint.getText()))+" ��");
		    		
		    	}
    		}
    		catch(Exception e) {
    			lblerror.setText("����Ʈ�� �߸� �Է��ϼ̽��ϴ�.");	
    		} 		
	    }
	
	
}
