package controller.competition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import controller.booking.Bookingctr;
import controller.home.Home;
import dao.TournamentDao;
import dto.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Competitionadd implements Initializable {
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Bookingctr.bookingday = null ;
    	Bookingctr.bookingtime = null ;
	}
	
	@FXML
    private Button btnadd;

    @FXML
    private TextField txtcname;

   

    @FXML
    private TextField txtcprice;

    @FXML
    private Button btnimg;

    @FXML
    private ImageView img;

    @FXML
    private Button btnback;

    @FXML
    private Label txtpath;

    @FXML
    private DatePicker txtcdate;

    @FXML
    private RadioButton opt1;

    @FXML
    private ToggleGroup timecategory;

    @FXML
    private RadioButton opt2;

    @FXML
    private TextField txtccount;

    @FXML
    private TextField txtcreward1;

    @FXML
    private TextField txtcreward2;

    @FXML
    private TextField txtcreward3;

    @FXML ///��ȸ���
    void add(ActionEvent event) {
    	String cname = txtcname.getText();
    	int ccount = Integer.parseInt(txtccount.getText()) ;
    	int cprice = Integer.parseInt(txtcprice.getText());
    	int creward1 = Integer.parseInt(txtcreward1.getText());
    	int creward2 = Integer.parseInt(txtcreward2.getText());
    	int creward3 = Integer.parseInt(txtcreward3.getText());
    	
    	
    	String ctime = null;
    		if(opt1.isSelected()) {
    			ctime="18:00";
    		}
    		else if(opt2.isSelected()) {
    			ctime="20:00";
    		}
    	
    	String cdate = txtcdate.getValue().toString()+" "+ctime;
    	
    	
    	///DBó��
    	 String result2 = TournamentDao.tournamentDao.addcheck(cdate);
    	
    	
    	///���࿡ �ش� ��¥, �ð��� ��ϵ� ��ȸ�� �ִٸ�
    	if(cdate==result2) {
    		System.out.println(result2);
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setHeaderText("�ش� ��¥�� ��ϵ� ��ȸ�� �ֽ��ϴ�.");
    		alert.showAndWait();
    		Home.instance.loadpage("/view/competition/competitionadd.fxml");
    	}
    	else {
    		
    		if(addcimg==null) {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("�̹����� ������ּ���");
    			alert.showAndWait();
    		}
    		else {
    		///��üȭ
    		Tournament tournament = new Tournament(0, cname, cdate, ccount, addcimg ,creward1,creward2,creward3, cprice);
    		
    		//DBó��
    		boolean result = TournamentDao.tournamentDao.add(tournament);
    		
    		if(result) {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("��ȸ��� �Ϸ�");
    			alert.showAndWait();
    			Home.instance.loadpage("/view/competition/competition.fxml");
    		}
    		else {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText("��ȸ��� ����");
    			alert.showAndWait();
    		}
    		}
    	}
    		
    }

    private String addcimg = null;

    @FXML
    void back(ActionEvent event) {
    	Home.instance.loadpage("/view/competition/competition.fxml");
    }

    @FXML
    void imgadd(ActionEvent event) {
    	//���ϼ��� Ŭ����
    	FileChooser fileChooser = new FileChooser();
    	//���� ���� ����
    	fileChooser.getExtensionFilters().add(new ExtensionFilter("�̹�������:img file", "*png","*jpeg","*jpg","*gif"));
    	//���ϼ��� ȭ�� ����
    	File file = fileChooser.showOpenDialog(new Stage());
    	//������ ȭ�� ��� �ؽ�Ʈ
    	txtpath.setText("���ϰ��: " + file.getPath());
    	//���ϰ��
    	addcimg = file.toURI().toString();
    	//�̸�����
    	Image image = new Image(addcimg);
    	img.setImage(image);
    	
    	//���� ������Ʈ ������ ���� ���� �̵��ϱ�
    	try {
    	//�����Է� 
    		FileInputStream inputStream = new FileInputStream(file);
    	//���� ���
    		File copyfile = new File("C:/Users/504/git/Team2EZEN/Team2EZEN/src/img/"+file.getName());
    		FileOutputStream outputStream = new FileOutputStream(copyfile);
    	//����Ʈ �迭 ����
    		byte [] bytes =  new byte[1024*1024*1024];
    	//�ݺ����� �̿��� �Է°� ��� �о����
    		int size;
    		while((size=inputStream.read(bytes))>0) {
    			outputStream.write(bytes,0,size);
    		}
    		inputStream.close();
    		outputStream.close();
    	//���ϸ� db����
    		addcimg = copyfile.toURI().toString();
    	}
    	catch(Exception e) {System.out.println(e);}
    }

}
