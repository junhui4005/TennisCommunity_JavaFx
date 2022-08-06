package controller.info;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import controller.Main;
import controller.home.Home;
import controller.login.Login;
import dao.PointDao;
import dao.SalesDao;
import dto.Member;
import dto.Point;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Pointadd implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblpoint.setText(String.valueOf(Login.member.getȸ������Ʈ())+"����Ʈ");
		lblallpoint.setText(String.valueOf(Login.member.getȸ������Ʈ())+"����Ʈ");
		lbladdpointconfirm.setText("");
	
	}
	
	  @FXML
	    private Label lblpoint;

	    @FXML
	    private TextField txtpoint;

	    @FXML
	    private Label lbladdpointconfirm;

	    @FXML
	    private Button btnaddpoint;

	    @FXML
	    private Button btnback;

	    @FXML
	    private Label lblallpoint;

	    @FXML
	    void addpoint(ActionEvent event) {
	    	int mnum = Login.member.getȸ����ȣ();
	    	int inputpoint = Integer.parseInt(txtpoint.getText());
	    	int chargepoint = inputpoint+(inputpoint/10);
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	String pdate = sdf.format(new Date());
	    	
	    	Point point = new Point(mnum, chargepoint, pdate);
	    	
	    	PointDao.pointDao.pointadd(point);
	    	int pnum = SalesDao.salesDao.callpnum(mnum, pdate);
	    	SalesDao.salesDao.pointaddsales(pdate, inputpoint, pnum);
	  
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    			alert.setHeaderText("����Ʈ ���� �Ϸ�!");
	    		alert.showAndWait();	
	    			lbladdpointconfirm.setText(txtpoint.getText()+"����  �����Ǿ����ϴ�.");
	    			lblallpoint.setText(String.valueOf(Login.member.getȸ������Ʈ()+chargepoint)+"����Ʈ");
	    			Login.member.setȸ������Ʈ(Login.member.getȸ������Ʈ()+chargepoint);

	    	

	    }

	    @FXML
	    void back(ActionEvent event) { // �ڷΰ����ưŬ���� ������ȭ������
	    	Home.instance.loadpage("/view/info/info.fxml");
	    	
	    }

	
}
