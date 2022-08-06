package controller.competition;

import java.awt.JobAttributes.DialogType;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.booking.Bookingctr;
import controller.home.Home;
import controller.home.Homemain;
import controller.login.Login;
import dao.MemberDao;
import dao.RecordDao;
import dao.TournamentDao;
import dto.Member;
import dto.Record;
import dto.Tournament;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Competitionview implements Initializable {
	
	
	public static Tournament tournament;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Bookingctr.bookingday = null ;
    	Bookingctr.bookingtime = null ;
    	
    	
    	Tournament tournament = Competition.selectcnum ;
    	System.out.println(tournament);
    	txtctitle.setText(tournament.get��ȸ�̸�());
    	txtcdate.setText(tournament.get��ȸ��¥());
    	txtctitle.setText(tournament.get��ȸ�̸�());
    	txtcdate.setText("��ȸ��¥: " + tournament.get��ȸ��¥());
    	txtccount.setText("�����ο�: "+tournament.get�����ο�()+"��");
    	txtcreward1.setText("1����: "+tournament.get���1��()+"��");
    	txtcreward2.setText("1����: "+tournament.get���2��()+"��");
    	txtcreward3.setText("1����: "+tournament.get���3��()+"��");
    	txtcprice.setText("������: "+tournament.get������()+"��");
    	img.setImage(new Image(Competition.selectcnum.get��ȸ�̹������()));
    	
    	entrytable.setVisible(false);
    		
    		///��ȸ���Ῡ��
    		String ��ȸ�� = tournament.get��ȸ��¥();
    		
    		String [] ��ȸ���� = ��ȸ��.split(" ");
    		
    		String ��ȸ��¥ = ��ȸ����[0]; ////yyyy-MM-dd
    		String ��ȸ�Ͻ� = ��ȸ����[1]; ////HH:mm
    		
    		String [] yMd = ��ȸ��¥.split("-");
    		String [] Hm = ��ȸ�Ͻ�.split(":");
    		
    		int ��ȸ�� = Integer.parseInt(yMd[0]);
    		int ��ȸ�� = Integer.parseInt(yMd[1]);
    		int ��ȸ�� = Integer.parseInt(yMd[2]);
    		
    		int ��ȸ�� = Integer.parseInt(Hm[0]);
    		int ��ȸ�� = Integer.parseInt(Hm[0]);
    		
    		Date date = new Date();
    		SimpleDateFormat ���ó�¥ǥ�� = new SimpleDateFormat("yyyy,MM,dd,HH,mm");
    		String ���ó�¥ = ���ó�¥ǥ��.format(date);
    		
    		String []  ���ó� = ���ó�¥.split(",");
    		
    		int ���ó⵵ = Integer.parseInt(���ó�[0]);
    		int ���ÿ� = Integer.parseInt(���ó�[1]);
    		int ������ = Integer.parseInt(���ó�[2]);
    		int ���ý� = Integer.parseInt(���ó�[3]);
    		int ���ú� = Integer.parseInt(���ó�[4]);
    		
    		LocalDateTime ���� = LocalDateTime.of(���ó⵵,���ÿ�, ������, ���ý�, ���ú�);
    		LocalDateTime ��ȸ�ϴ³� = LocalDateTime.of(��ȸ��, ��ȸ��, ��ȸ�� , ��ȸ��, ��ȸ��);
    		
    		Duration duration = Duration.between(����, ��ȸ�ϴ³�);
    		long milliSecond = duration.toMinutes();
    		///�����ڰ� �α��� �� ���
        	if(Login.member.getȸ�����̵�().equals("admin")) {
        		btnapply.setVisible(false);
        		btnapplycancel.setVisible(false);
    		///��ȸ��
    		if(milliSecond>0) {
    			btnactivation.setText("������û�Ⱓ");
    		}
    		
    		///��ȸ��¥�� ������ ���
    		else if(milliSecond<=0) {
    			btnactivation.setText("��ȸ����");
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("��ȸ������ ����Ǿ����ϴ�.");
    			alert.showAndWait();
    			
    			btnapply.setVisible(false);
    			btnapplycancel.setVisible(false);
    			
    			entrytable.setVisible(true);
    			
    			int cnum= Competition.selectcnum.get��ȸ��ȣ();
    			
    			
    			ObservableList<Record> recordlist = RecordDao.recordDao.list(cnum);
    			
    			///db�� ��� �Խñ� ��������
    			TableColumn tc = entrytable.getColumns().get(0);
    			
    			tc.setCellValueFactory(new PropertyValueFactory<>("��ϼ���"));
    			
    			tc = entrytable.getColumns().get(1);
    			
//    			int ȸ����ȣ = Integer.parseInt("���ȸ����ȣ");
//    			String ���̵� = RecordDao.recordDao.searchid(ȸ����ȣ);
    			tc.setCellValueFactory(new PropertyValueFactory<>("ȸ�����̵�"));
    			
    			tc = entrytable.getColumns().get(2);
    			tc.setCellValueFactory(new PropertyValueFactory<>("��Ϲ������"));
    			
    			entrytable.setItems(recordlist);
    			
    			///table �� ��ü�� �������� ��
    			entrytable.setOnMouseClicked(e->{
    				
    				entrytable.getSelectionModel().getSelectedItem();
    				
    			});
    			
    		}
    	}
    	
    	///�Ϲ�ȸ���� �α����� ���
    	else {
    		btndelete.setVisible(false);
    		btnupdate.setVisible(false);
    		addranking.setVisible(false);
    		
    		///��ȸ��
    		if(milliSecond>0) {
    			btnactivation.setText("������û�Ⱓ");
    		}
    		
    		///��ȸ��¥�� ������ ���
    		else if(milliSecond<=0) {
    			btnactivation.setText("��ȸ����");
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("��ȸ������ ����Ǿ����ϴ�.");
    			alert.showAndWait();
    			
    			btnapply.setVisible(false);
    			btnapplycancel.setVisible(false);
    			
    			entrytable.setVisible(true);
    			
    			int cnum= Competition.selectcnum.get��ȸ��ȣ();
    			
    			
    			ObservableList<Record> recordlist = RecordDao.recordDao.list(cnum);
    			System.out.println(recordlist.toString());
    			///db�� ��� �Խñ� ��������
    			TableColumn tc = entrytable.getColumns().get(0);
    			
    			tc.setCellValueFactory(new PropertyValueFactory<>("��ϼ���"));
    			
    			tc = entrytable.getColumns().get(1);
    			
//    			int ȸ����ȣ = Integer.parseInt("���ȸ����ȣ");
//    			String ���̵� = RecordDao.recordDao.searchid(ȸ����ȣ);
    			tc.setCellValueFactory(new PropertyValueFactory<>("ȸ�����̵�"));
    			
    			tc = entrytable.getColumns().get(2);
    			tc.setCellValueFactory(new PropertyValueFactory<>("��Ϲ������"));
    			
    			entrytable.setItems(recordlist);
    			
    			///table �� ��ü�� �������� ��
    			entrytable.setOnMouseClicked(e->{
    				
    				entrytable.getSelectionModel().getSelectedItem();
    				
    			});
    		}
    		
    	}
	}
	
	@FXML
    private Button btndelete;

    @FXML
    private Button btnback;

    @FXML
    private Button btnupdate;

    @FXML
    private ImageView img;

    @FXML
    private Text txtcprice;

    @FXML
    private Text txtcreward2;

    @FXML
    private Text txtccount;

    @FXML
    private Button btnapply;

    @FXML
    private Button btnapplycancel;

    @FXML
    private Label txtcdate;

    @FXML
    private TableView<Record> entrytable;
    
    
    
    @FXML
    private Button btnactivation;

    @FXML
    private TextField txtctitle;

    @FXML
    private Button addranking;

    @FXML
    private Text txtcreward1;

    @FXML
    private Text txtcreward3;

    @FXML
    void activation(ActionEvent event) {
    	
    }

    @FXML
    void add(ActionEvent event) {
    	
    	Home.instance.loadpage("/view/competition/competitionaddranking.fxml");
    }
   

    @FXML
    void apply(ActionEvent event) {
    	
    	int ��ȸ��ȣ = Competition.selectcnum.get��ȸ��ȣ();
    	int �����ο� = Competition.selectcnum.get�����ο�();
    	
    	////�����ο�����
    	String result = RecordDao.recordDao.cmember(��ȸ��ȣ);
    	
    	////�����ο��� �� ���� ���
    	if(Integer.parseInt(result)>=�����ο�) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("��û �����Ǿ����ϴ�.");
    		alert.showAndWait();
    	}
    	////�����ο��� �̴��� ���
    	else {
    		
    		int ��ûȸ����ȣ = Login.member.getȸ����ȣ();
    
    		boolean ������û�� = RecordDao.recordDao.cmemberapply(��ûȸ����ȣ,��ȸ��ȣ);
   
    	
    		///���� ȸ���� �̹� ������û�� ���� ���
    		if(������û��) {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("��û�� �̷��� �ֽ��ϴ�.");
    			alert.showAndWait();
    		}
    		
    		else{Home.instance.loadpage("/view/competition/competitionpay.fxml");}
    	}
    }

    @FXML
    void back(ActionEvent event) {
	Home.instance.loadpage("/view/competition/competition.fxml");
    }

    @FXML
    void cancel(ActionEvent event) {
    	
    	
    	
    	int ��ûȸ����ȣ = Login.member.getȸ����ȣ();
    	int ��ȸ��ȣ = Competition.selectcnum.get��ȸ��ȣ();
		boolean ������û�� = RecordDao.recordDao.cmemberdelete(��ûȸ����ȣ, ��ȸ��ȣ);
    	
		System.out.println(������û��);
		
    	if(������û��) {
    		Alert alert = new Alert( AlertType.CONFIRMATION );
    		alert.setHeaderText("�ش� ��ȸ��û�� ����Ͻðڽ��ϱ�?");
    		Optional<ButtonType> optional = alert.showAndWait();
    		if( optional.get() == ButtonType.OK ) {
    		Home.instance.loadpage("/view/competition/competition.fxml");
    			}
    	}
    	else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("�ش� ��ȸ�� ������û�� �̷��� �����ϴ�.");
    		alert.showAndWait();
    	}
    }
	
	////������ ��ȸ����
    @FXML
    void delete(ActionEvent event) {
    	Alert alert = new Alert( AlertType.CONFIRMATION );
		alert.setHeaderText("�ش� ��ȸ�� �����Ͻðڽ��ϱ�?");
		Optional<ButtonType> optional = alert.showAndWait();
		if( optional.get() == ButtonType.OK ) {
		TournamentDao.tournamentDao.cdelete( Competition.selectcnum.get��ȸ��ȣ() );
		
		Home.instance.loadpage("/view/competition/competition.fxml");
	}
    }
    ////�����ڴ�ȸ����
    @FXML
    void update(ActionEvent event) {
    	Home.instance.loadpage("/view/competition/competitionupdate.fxml");
    }

   

}
