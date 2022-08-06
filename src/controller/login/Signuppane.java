package controller.login;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import controller.booking.Bookingctr;
import controller.booking.Bookinglist;
import dao.MemberDao;
import dto.Member;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Signuppane implements Initializable {
	
	// �Է¿���üũ, �߻��� ����ó�� �ؾ���
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
    @FXML
    private TextField txtid;

    @FXML
    private Button btnsignup;

    @FXML
    private Button btnback;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private Label lblconfirm;

    @FXML
    private PasswordField txtpasswordconfirm;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtphone;

    @FXML
    private TextField txtid1;
    
    @FXML
    private TextField txtyear;
    
    @FXML
    private TextField txtmonth;
    
    @FXML
    private TextField txtday;

    @FXML
    private Button btnidconfirm;
    


    @FXML
    void back(ActionEvent event) {
    	Login.instance.loadpage("/view/login/loginpane.fxml");
    }

    @FXML
    void idconfirm(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	String id = txtid.getText();    	
    	if(txtid.getText().equals("")) {
    		alert.setHeaderText("���̵� �Է��ϼ���");
    		alert.showAndWait();
    		return;
    	}
    	else {
        	boolean result = MemberDao.memberDao.idcheck(id);
	    	if(result) {
	    		alert.setHeaderText("������� ���̵� �Դϴ�. �ٽ��Է����ּ���");
	    		alert.showAndWait();
	    		txtid.setText("");
	    		return;
	    	}
	    	else {
	    		alert.setHeaderText("��밡���� ���̵��Դϴ�");
	    		alert.showAndWait();
	    		return;
	    	}
    	}
    }
    

    @FXML
    void signup(ActionEvent event) {
    	try {
	    	String id = txtid.getText();
	    	String password = txtpassword.getText();
	    	String passwordconfirm = txtpasswordconfirm.getText();
	    	String name = txtid1.getText();
	    	String email = txtemail.getText();
	    	String year = txtyear.getText();
	    	String month = txtmonth.getText();
	    	String day = txtday.getText();
	    	String phone = txtphone.getText();
	    //	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    //	String since = sdf.format(new Date() );
	    	Calendar calendar = Calendar.getInstance();
	    	int thisyear = calendar.get(calendar.YEAR);
	    	calendar.set(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
	    	int eday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	    	if(month.length()==1) {
	    		month = "0"+month;
	    	}
	    	if(day.length()==1) {
	    		day = "0"+day;
	    	}
	    	
	    	
	    	// ���̵� �ߺ�üũ
	    	boolean result = MemberDao.memberDao.idcheck(id);
	    	if(result) {
	    		lblconfirm.setText("������� ���̵��Դϴ�.");
	    		return;
	    	}
	    	
	    	// ��й�ȣ ���� üũ
	    	if(password.length()<6 || password.length()>10 || passwordconfirm.length()<6 || passwordconfirm.length()>10) {
	    		lblconfirm.setText("�н������ 6~10�ڸ� ���̷� �Է����ּ���");
	    		return;
	    	}
	    	
	    	// ��й�ȣ ��ġ üũ
	    	if(!password.equals(passwordconfirm)) {
	    		lblconfirm.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
	    		return;
	    	}
	    	
	    	// �̸��� ����üũ
	    	boolean email_check = Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", email);
	    	if(email_check==false) { // �̸����Է¿� @�� ������
	    		lblconfirm.setText("�߸��� ������ �̸����ּ��Դϴ�");
	    		return;
	    	}
	    	// ��ȭ��ȣ ����üũ
	    	boolean phone_check = Pattern.matches("01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", phone);
	    	if(phone_check==false) {
	    		lblconfirm.setText("�߸��� ������ ��ȭ��ȣ�Դϴ�");
	    		return;
	    	}
	    	// ������� ����üũ
	    	if(1900>Integer.parseInt(year) || (Integer.parseInt(year) > thisyear)) {
	    		lblconfirm.setText("�������-'����'�� ����� �Է����ּ���");
	    		return;
	    	}
	    	
	    	if(Integer.parseInt(month) <=0 || Integer.parseInt(month) >= 13 ) {
	    		lblconfirm.setText("�������-'��'�� ����� �Է����ּ���");
	    		return;
	    	}
	    	
	    	if(Integer.parseInt(day) <=0 || Integer.parseInt(day) >= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
	    		lblconfirm.setText("�������-'��'�� ����� �Է����ּ���");
	    		return;
	    	}
	    	
	    	//* ��� ��ȿ�� ��� -> DB����
	    	// 1. ��üȭ
	    	Member member = new Member(0, id, password, name, year+"-"+month+"-"+day, email, phone, 0, "");
	    	boolean result2 = MemberDao.memberDao.signup(member);
	    	if(result2) {
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    			alert.setTitle("�˸�");
	    			alert.setHeaderText("�״Ͻ� ����ý��ۿ� ������ �����մϴ�.");
	    			alert.setContentText("ȸ������ ����");
	    		alert.showAndWait();
	        	Login.instance.loadpage("/view/login/loginpane.fxml");
	    	}else {
	    		lblconfirm.setText("[ȸ�����Խ���]DB ����(�����ڿ��� ����)");
	    		return;
	    	} // else e
    	}
    	catch(Exception e) {
    		lblconfirm.setText("�߸��Է��ϼ̽��ϴ�.");
    	}
    }

}
