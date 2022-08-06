package controller.login;

import java.net.URL;
import java.util.ResourceBundle;

import dao.MemberDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Findidpane implements Initializable{
	
	// �Է¿���üũ, �߻��� ����ó�� �ؾ���
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
    @FXML
    private Button btnfindid;

    @FXML
    private Button btnback;

    @FXML
    private Label lblconfirm;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtphone;

    @FXML
    void back(ActionEvent event) {
    	Login.instance.loadpage("/view/login/loginpane.fxml");
    }

    @FXML
    void findid(ActionEvent event) {
    	String phone = txtphone.getText();
    	String id = MemberDao.memberDao.findid(txtname.getText(), phone);
    	if(id!=null) {
    		lblconfirm.setText(id);
    	}
    	else {
    		lblconfirm.setText("�ش������� ���� ȸ���� �����ϴ�.");
    	}
    }

}
