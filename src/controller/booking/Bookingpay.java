package controller.booking;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.BookingDao;
import dao.MemberDao;
import dao.PointDao;
import dao.SalesDao;
import dto.Point;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Bookingpay implements Initializable {
	
	String[] fee = Bookingctr.bookingtime.split(","); // ���೯¥ �߶�α� 1-1,1-2,1-3, �̷������� ����Ǵϱ� , �߶� �迭���� ���ϱ� ���� ����
														// �迭���� * 10000 �� �̿���
	DecimalFormat df = new DecimalFormat("#,##0"); // ����Ʈ�� �ݾ� ��ǥ ����
	int �̿��� ; // 
	int ���ຯ���̿��� ;
	int �����ؾ��ұݾ� ;
	int �Է��ѱݾ� ;
	int ���������Ʈ ;
	
	public void �ݾ׵��ʱ�ȭ() {
		�̿��� = 0 ;
		���ຯ���̿��� = 0 ;
		�����ؾ��ұݾ�  = 0;
		�Է��ѱݾ� = 0 ;
		���������Ʈ = 0 ;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		�̿��� = 10000*fee.length; // �迭���� * 10000 �̿���
		�����ؾ��ұݾ� = �̿���; // �����ؾ��� �ݾ� = �̿���
		txtpoint.setText("0");
		if(Bookingctr.pagecheck==2) { // ���ຯ���϶�
			���ຯ���̿��� = �̿���-Bookinglist.selectedbooking.get����ݾ�(); // �̿��ݿ��� ���� ����ݾ� ����ŭ
			�����ؾ��ұݾ� = ���ຯ���̿��� ; // �����ؾ��ұݾ� �� �� ����� ������ ����Ʈ ��붧����
			lblpay.setText("���ຯ��");
			if(���ຯ���̿���<=0) { // ��������ݾ��� ���濹��ݾ׺��� Ŭ��
				if(���ຯ���̿���==0) {
					lblfee.setText("�����ݾ� ����");
				}
				else {
					lblfee.setText("���� ���� �����ݾ׺��� �̿����� �۾� ������ ����Ʈ�� ȯ�޵˴ϴ�.");
				}
				lblamount.setText("�����ݾ� ����");
				txtcash.setText("0");
				txtcash.setDisable(true);
				txtpoint.setDisable(true);
				btnpay.setText("���ຯ���ϱ�");	
			}
			else {  // ��������ݾ׺��� ���濹���� �ݾ��� �� Ŭ��
				lblfee.setText("�̿��� : " + df.format(���ຯ���̿���)+" ��");
				lblamount.setText("�����ݾ� : " + df.format(�����ؾ��ұݾ�)+" ��");
				txtcash.setText("0");
			}	
			lblpoint.setText("���� ����Ʈ : " + df.format(Login.member.getȸ������Ʈ()) + " ��");	
			Bookingctr.pagecheck=0; // ���ຯ�� ���� ����ġ �ʱ�ȭ
		}
		else {	// �Ϲ� �����϶� (������ �ƴҶ�)	
			lblfee.setText("�̿��� : " + df.format(�̿���)+" ��");
			lblpoint.setText("���� ����Ʈ : " + df.format(Login.member.getȸ������Ʈ()) + " ��");
			lblamount.setText("�����ݾ� : " + df.format(�����ؾ��ұݾ�)+" ��");
			txtcash.setText("0");
		}
	}
	
    @FXML
    private Label lblfee;

    @FXML
    private Label lblpoint;
    
    @FXML
    private Label lblpay;

    @FXML
    private TextField txtpoint;

    @FXML
    private Button btnpoint;

    @FXML
    private Label lblamount;

    @FXML
    private Label lblconfirm;
    
    @FXML
    private TextField txtcash;

    @FXML
    private Button btnpay;
    
    @FXML
    private Button btnback;

    @FXML
    void back(ActionEvent event) {
    	if(lblpay.getText().equals("���ຯ��")) {
    		Home.instance.loadpage("/view/booking/bookinglist.fxml");
    	}
    	Home.instance.loadpage("/view/booking/booking.fxml");
    }
    
    
    @FXML
    void pay(ActionEvent event) {
    	try { // try �������� -> ���ڿ� �ٸ��� �Է¹����� ������
    		�Է��ѱݾ� = Integer.parseInt(txtcash.getText());
    		if(lblfee.getText().equals("���� ���� �����ݾ׺��� �̿����� �۾� ������ ����Ʈ�� ȯ�޵˴ϴ�.")) { // ����Ʈ ȯ���ϰ��
    			SalesDao.salesDao.changesales(Bookinglist.selectedbooking.get�����ȣ(), �����ؾ��ұݾ�); // �����ؾ��ұݾ��� -�̹Ƿ� DB�� �׸�ŭ ������Ŵ
    			BookingDao.bookingDao.changebooking(Bookinglist.selectedbooking.get�����ȣ(), Bookingctr.bookingday, Bookingctr.bookingtime, �����ؾ��ұݾ�); // ���ຯ��
    			Alert alert = new Alert(AlertType.INFORMATION);
		    	alert.setHeaderText("���ຯ�� �Ϸ�. ������ ����Ʈ�� ȯ�޵Ǿ����ϴ�.");
		    	alert.showAndWait();
		    	Bookingctr.bookingday = null ; // ����������� Ŭ���ؿ� ����Ǿ��� ���� �ʱ�ȭ
		    	Bookingctr.bookingtime = null ; // ����������� Ŭ���ؿ� ����Ǿ��� ���� �ʱ�ȭ
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    	Point point = new Point(Login.member.getȸ����ȣ(), (-�����ؾ��ұݾ�), sdf.format(new Date())); // ����Ʈ ȯ���� ���� dto��ü ����
		    	PointDao.pointDao.pointadd(point);
		    	Login.member = MemberDao.memberDao.getmember(Login.member.getȸ�����̵�()); // ����Ʈ ����
		    	
		    	�ݾ׵��ʱ�ȭ(); // �̿���, �����ݾ� ���.... �ʱ�ȭ��Ŵ
		    	Home.instance.loadpage("/view/booking/booking.fxml");	
    			
    		}
    		else {
	    		if(lblpay.getText().equals("���ຯ��")){ // ���� �����϶�
		    		if( �����ؾ��ұݾ� <= �Է��ѱݾ� ){
		    			SalesDao.salesDao.changesales(Bookinglist.selectedbooking.get�����ȣ(), �����ؾ��ұݾ�);
		    			BookingDao.bookingDao.changebooking(Bookinglist.selectedbooking.get�����ȣ(), Bookingctr.bookingday, Bookingctr.bookingtime, �����ؾ��ұݾ�);
		    			Alert alert = new Alert(AlertType.INFORMATION);
				    	alert.setHeaderText("���ຯ�� �Ϸ�. �������ּż� �����մϴ�.");
				    	alert.showAndWait();
				    	Bookingctr.bookingday = null ;
				    	Bookingctr.bookingtime = null ;
				    	if(���������Ʈ>0) {
					    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					    	Point point = new Point(Login.member.getȸ����ȣ(), (-���������Ʈ), sdf.format(new Date())); // ����Ʈ ȯ���� ���� dto��ü ����
					    	PointDao.pointDao.pointadd(point);
				    	}
				    	Home.instance.loadpage("/view/booking/booking.fxml");	
				    	�ݾ׵��ʱ�ȭ();
		    			
		    		}
		    		else { // �ݾ��� �����ϸ� 
			    		Alert alert = new Alert(AlertType.INFORMATION);
			    		alert.setHeaderText("�ݾ��� �����մϴ�.");
			    		alert.showAndWait();
		    		}
		    		
		    	}
		    	else { // �Ϲݿ��� (���ຯ�� �ƴҶ�)
			    	if(�����ؾ��ұݾ� <= �Է��ѱݾ�) {
				    	BookingDao.bookingDao.booking(Bookingctr.bookingday,Bookingctr.bookingtime, �����ؾ��ұݾ�);
				    	int bookingnum = SalesDao.salesDao.callbnum(Bookingctr.bookingday, Bookingctr.bookingtime); 	
				    	SalesDao.salesDao.salesbooking(Bookingctr.bookingday, �����ؾ��ұݾ�, bookingnum);
				    	Alert alert = new Alert(AlertType.INFORMATION);
				    	alert.setHeaderText("���� �Ϸ�. �������ּż� �����մϴ�.");
				    	alert.showAndWait();
				    	Bookingctr.bookingday = null ;
				    	Bookingctr.bookingtime = null ;
				    	if(���������Ʈ>0) {
					    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					    	Point point = new Point(Login.member.getȸ����ȣ(), (-���������Ʈ), sdf.format(new Date())); // ����Ʈ ȯ���� ���� dto��ü ����
					    	PointDao.pointDao.pointadd(point);
				    	}
				    	�ݾ׵��ʱ�ȭ();
				    	Home.instance.loadpage("/view/booking/booking.fxml");	    
			    	}
			    	else {
			    		Alert alert = new Alert(AlertType.INFORMATION);
			    		alert.setHeaderText("�ݾ��� �����մϴ�.");
			    		alert.showAndWait();
			    	}
		    	}
    		}
    	}
    	catch(Exception e) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("�ݾ��� ����� �Է����ּ���");
    		alert.showAndWait();
    	}
    }

    @FXML
    void point(ActionEvent event) {	
		try {
	    	if(Integer.parseInt(txtpoint.getText())<=0 || Integer.parseInt(txtpoint.getText()) > Login.member.getȸ������Ʈ() ) {
	    		lblconfirm.setText("����Ʈ�� �߸� �Է��ϼ̽��ϴ�.");		
	    	}
	    	else if(Integer.parseInt(txtpoint.getText())%100 != 0){
	    		lblconfirm.setText("����Ʈ�� 100�� �����θ� ��밡���մϴ�.");	   		
	    	}
	    	else {
	    		���������Ʈ = Integer.parseInt(txtpoint.getText());
	    		if(lblpay.getText().equals("���ຯ��")) {
	    			if((���ຯ���̿���-���������Ʈ)<0 ) { // ����
			    		�����ؾ��ұݾ� = 0;
			    		���������Ʈ=���ຯ���̿���; 		
			    		lblconfirm.setText(df.format(���������Ʈ)+ "����Ʈ ����մϴ�.");
			    		txtpoint.setText(���������Ʈ+"");
			    		lblamount.setText("�����ݾ׾���");
	    			}
		    		else {
			    		�����ؾ��ұݾ� = ���ຯ���̿���-���������Ʈ;
			    		lblconfirm.setText(df.format(���������Ʈ)+ "����Ʈ ����մϴ�.");
			    		lblamount.setText("�����ݾ� : " + df.format(�����ؾ��ұݾ�)+" ��");
		    		}
	    		}
	    		else{
	    			if((�̿���-���������Ʈ)<0) { // ����
			    		�����ؾ��ұݾ� = 0;
			    		���������Ʈ=�̿���;
			    		lblconfirm.setText(df.format(���������Ʈ) + "����Ʈ ����մϴ�.");
			    		txtpoint.setText(���������Ʈ+"");
			    		lblamount.setText("�����ݾ� : " + df.format(�����ؾ��ұݾ�)+" ��");
	    			}
	    			else {
			    		�����ؾ��ұݾ� = �̿���-���������Ʈ;
			    		lblconfirm.setText(df.format(���������Ʈ) + "����Ʈ ����մϴ�.");
			    		lblamount.setText("�����ݾ� : " + df.format(�����ؾ��ұݾ�)+" ��");
	    			}
	    		}
	    	}
		}
		catch(Exception e) {
			lblconfirm.setText("����Ʈ�� �߸� �Է��ϼ̽��ϴ�.");	
		} 		
    }
}
