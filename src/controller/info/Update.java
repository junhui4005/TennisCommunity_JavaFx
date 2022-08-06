package controller.info;

import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;

public class Update implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtemail.setText(Login.member.getȸ���̸���());
		txtmname.setText(Login.member.getȸ���̸�());
		txtphone.setText(Login.member.getȸ����ȭ��ȣ());
		txtpwupdate.setText(Login.member.getȸ����й�ȣ());
		txtemail.setDisable(true);
		txtmname.setDisable(true);
		txtphone.setDisable(true);
		txtpwupdate.setDisable(true);
	}

    @FXML
    void clear1(MouseEvent event) {txtmname.setText("");}

    @FXML
    void clear2(MouseEvent event) {txtemail.setText("");}

    @FXML
    void clear3(MouseEvent event) {txtphone.setText("");}

    @FXML
    void clear4(MouseEvent event) {txtpwupdate.setText("");}

	   @FXML
	    private TextField txtemail;

	    @FXML
	    private TextField txtmname;

	    @FXML
	    private Button btnupdate;

	    @FXML
	    private Label lblpwerror;

	    @FXML
	    private Button btnmpwconfirm;

	    @FXML
	    private Button btnback;

	    @FXML
	    private TextField txtphone;

	    @FXML
	    private PasswordField txtmpassword;

	    @FXML
	    private PasswordField txtpwupdate;
	    


    @FXML
    void back(ActionEvent event) {
    	Home.instance.loadpage("/view/info/info.fxml");
    }

    @FXML
    void pwconfirm(ActionEvent event) {
    	if(txtmpassword.getText().equals(Login.member.getȸ����й�ȣ())) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("������ ������ �Է����ֽñ� �ٶ��ϴ�");
    		alert.showAndWait();
    		txtmpassword.setText("");
    		txtmpassword.setDisable(true);
    		txtemail.setDisable(false);
    		txtmname.setDisable(false);
    		txtphone.setDisable(false);
    		txtpwupdate.setDisable(false);
    		
    	}else { // ���� �α��ε� ȸ����й�ȣ�� �Էº�й�ȣ�� �ٸ���
    		Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("��й�ȣ Ȯ�����ֽñ� �ٶ��ϴ�.");
    		alert.showAndWait();
    		txtmpassword.setText("");
    
    	} // else e

    }

    @FXML
    void update(ActionEvent event) {
    	int mnum = Login.member.getȸ����ȣ();
    	String name = txtmname.getText();
    	String email = txtemail.getText();
    	String phone = txtphone.getText();
    	String password = txtpwupdate.getText();
    	
    	boolean result = MemberDao.memberDao.memberupdate(mnum, password, name, email, phone);
    	if(result) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("ȸ�������� �����Ǿ����ϴ�.[�ٽ� �α������ּ���]");
    		alert.showAndWait();
    		Login.member = null;
    		Main.instance.loadpage("/view/login/login.fxml");
    		
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("ȸ������ ���� ����[�����ڿ��� ����");
    		alert.showAndWait();
    		txtmname.setText("");
    		txtemail.setText("");
    		txtphone.setText("");
    		txtpwupdate.setText("");
    	} // else e

    }
	
}
