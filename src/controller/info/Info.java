package controller.info;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.Main;
import controller.home.Home;
import controller.login.Login;
import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class Info implements Initializable{
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblid.setText(Login.member.getȸ�����̵�());
		lblemail.setText(Login.member.getȸ���̸���());
		lblname.setText(Login.member.getȸ���̸�());
		lblphone.setText(Login.member.getȸ����ȭ��ȣ());
		lblpoint.setText(String.valueOf(Login.member.getȸ������Ʈ())+"��");
	}
	
		@FXML
	    private Label lblid;

	    @FXML
	    private Label lblemail;

	    @FXML
	    private Label lblname; // ȸ���̸�

	    @FXML
	    private Label lblpoint;

	    @FXML
	    private Label lblphone;// ȸ����ȭ��ȣ

	    @FXML
	    private Button btnpoint;

	    @FXML
	    private Button btncview;

	    @FXML
	    private Button btnmupdate;

	    @FXML
	    private Button btnmdelete;


	    @FXML
	    void cview(ActionEvent event) {
	    	Home.instance.loadpage("/view/info/competitionlist.fxml");
	    }

	    @FXML
	    void delete(ActionEvent event) { // ȸ��Ż�� ��ư Ŭ��
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    		alert.setHeaderText("ȸ�� Ż�� �����Ͻðڽ��ϱ�?");
	    	Optional<ButtonType> optional = alert.showAndWait();
	    	if(optional.get()==ButtonType.OK) { // Ȯ�ι�ư Ŭ��
	    		boolean result = MemberDao.memberDao.memberdelete(Login.member.getȸ����ȣ());
	    		if(result) { // Ż�𼺰��̸�
	    			Login.member = null;
	    			Main.instance.loadpage("/view/login/login.fxml");
	    		}else { // 
	    			alert.setHeaderText("Ż�����");
	    		}
	    	}
	    }

	    @FXML
	    void mupdate(ActionEvent event) {
	    	Home.instance.loadpage("/view/info/update.fxml");
	    }

	    @FXML
	    void point(ActionEvent event) {
	    	Home.instance.loadpage("/view/info/pointadd.fxml");
	    }
	    

}
