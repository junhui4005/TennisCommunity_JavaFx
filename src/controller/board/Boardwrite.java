package controller.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;



import dao.BoardDao;
import dao.MemberDao;
import dto.Board;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class Boardwrite implements Initializable {
	
    @FXML
    private Button btnwrite;

    @FXML
    private TextField txtbname;

    @FXML
    private TextArea txtbcontent;

    @FXML
    private Button btnimg;

    @FXML
    private RadioButton opt1;

    @FXML
    private ToggleGroup category;

    @FXML
    private RadioButton opt2;

    @FXML
    private ImageView img;

    @FXML
    private Button btnback;

    @FXML
    private Label txtpath;

    @FXML
    void back(ActionEvent event) {
    	// ���ۼ����������� �ڷΰ��� ������ �Խñ� ����Ʈ��
    	Home.instance.loadpage("/view/board/board.fxml");

    }
    
    

    private String pimage = null;
    @FXML
    void imgadd(ActionEvent event) { // �̹������ ��ư Ŭ��������
    	javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
    	fileChooser.getExtensionFilters().add(new javafx.stage.FileChooser.ExtensionFilter("�̹�������:image file","*png","*jpeg","*jpg","*gif" ));
    	
    	File file = fileChooser.showOpenDialog(new javafx.stage.Stage());
    	
    	txtpath.setText("���ϰ�� : " + file.getPath());
    	
    	pimage = file.toURI().toString();
    	
    	javafx.scene.image.Image image = new javafx.scene.image.Image(pimage);
    	img.setImage(image);
    	
    	// *������ ������ ����������Ʈ ������ ����(�̵�)
    	
    	try {
    	// 1. ���� �Է½�Ʈ��
    	FileInputStream inputStream = new FileInputStream(file);
    	
    	// 2. ���� ��� ��Ʈ��
    	
		File copyfile = new File("C:/Users/504/git/Team2EZEN/Team2EZEN/src/img/"+file.getName());
    	FileOutputStream outputStream = new FileOutputStream(copyfile);
    	byte[] bytes = new byte[1024*1024*1024];
    	int size;
    	while( (size = inputStream.read(bytes)) > 0) {
    		outputStream.write(bytes,0,size);
    		
    	}
    	pimage = copyfile.toURI().toString();
    	// �뷮�� ū ��쿡�� ��Ʈ�� ���� �ʼ�
    	inputStream.close();
    	outputStream.close();
    	
    	}catch(Exception e) {System.out.println(e);}
    	
    }
    
    @FXML
    void write(ActionEvent event) {
    	
    	// 1.��Ʈ�ѿ� �Էµ� ������ ��������
	    	String bname = txtbname.getText();
	    	String bcontent = txtbcontent.getText();
	    	   if(bname.equals("") || bcontent.equals("")) {
	   	    	Alert alert = new Alert(AlertType.INFORMATION);
	   			alert.setHeaderText("������ �Է��ϼ���");
	   			alert.showAndWait();
	       		return;
	   	    }
	    
	    		// * ī�װ�
	    	int category = 0 ;
	    	if(opt1.isSelected()) {category=1;}
	    	if(opt2.isSelected()) {category=2;}
	    int mnum = Login.member.getȸ����ȣ();
	    	
	    	// 2.��üȭ
	    Board board = new Board(0,mnum,MemberDao.memberDao.getid(mnum),category,bname,bcontent,pimage,0,null);
	    
	    	// 3. dbó��
	    boolean result = BoardDao.boardDao.bwrite(board);
	    
	 
	    	
	      	// 4. ���ó��
		    if(result) {
		    	Alert alert = new Alert(AlertType.INFORMATION);
		    	alert.setHeaderText("�Խñ� ��� �Ϸ�");
		    	alert.showAndWait();
		    	Home.instance.loadpage("/view/board/board.fxml");
		    }
	    
	  
 	    	
    	
    	
    	// ���ۼ����������� �ڷΰ��� ������ �Խñ� ����Ʈ��
    	Home.instance.loadpage("/view/board/board.fxml");

    }
	    
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	category.selectToggle(opt1);
	    	
	    }

}