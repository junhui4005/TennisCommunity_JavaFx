package controller.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.BoardDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Boardupdate implements Initializable {
	
	@FXML
    private Button btnupdate;

    @FXML
    private TextField txtbtitle;

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
    private Button btnimgdelete;
    
    int bnum = Board.board.get�Խ��ǹ�ȣ();
    private String pimage = null;
    
    
    @FXML
    void imgdelete(ActionEvent event) {
    	// ��ư��Ŭ���Ǹ� �̹��� ���� (set�޼ҵ�)
    
    	img.setImage(null);
    	
    	
    	// �̹��� ��ΰ��� null�� �ֱ�
    	BoardDao.boardDao.updateimgdel(controller.board.Boardview.board.get�Խ��ǹ�ȣ());
    	Board.board.set�̹������(null);
    	// ��ΰ��� null�� �Ǵµ� �̹����� �� �״��??
    	
    
    }

    @FXML
    void back(ActionEvent event) {
    	// �����ϱ����������� �ڷΰ��� ������ �Խñۺ��� ������
    	Home.instance.loadpage("/view/board/boardview.fxml");
    	

    }
   

    @FXML
    void imgadd(ActionEvent event) {
    	
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
    	controller.board.Board.board.set�̹������(pimage);
    	
    	}catch(Exception e) {System.out.println(e);}
    	
    }

    @FXML
    void update(ActionEvent event) {
    	int category ;
    	if(opt1.isSelected()) {
    		category = 1;
    	}
    	else if(opt2.isSelected()) {
    		category = 2;
    	}
    	else {
    		category = 1;
    	}
    	if( pimage == null ) pimage = Board.board.get�̹������();
    	
    	// �����ϱ����������� ������ �Ϸ�Ǹ� �Խñۺ��� ������
    	String ���������� =txtbtitle.getText();
    	String �����ĳ��� =txtbcontent.getText();
//    	ObservableList<dto.Board> boardlist = BoardDao.boardDao.blist();
//    	controller.board.Board.board =  boardlist.get(Board.board.get�Խ��ǹ�ȣ());
    	
    	//String �������̹��� = img.getImage();
    	
    	//Home.instance.loadpage("/view/board/boardview.fxml");
    	
    	boolean result = BoardDao.boardDao.bupdate(category, txtbtitle.getText(), txtbcontent.getText(), pimage, Board.board.get�Խ��ǹ�ȣ());
    	if( result ) {
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(" �� ���� ����");
				if(Boardview.board.get�̹������()!=null) {
					System.out.println("�̹������ ����");
					Board.board.set�Խ�������(����������);
					Board.board.set�Խ��ǳ���(�����ĳ���);
					Board.board.set�̹������(pimage);
					
				}else {
					
					System.out.println("�̹������ ����");
			
			Board.board.set�Խ�������(����������);
			Board.board.set�Խ��ǳ���(�����ĳ���);
			Board.board.set�̹������(null);
			img.setImage(null);
				}
			alert.showAndWait();
			
    		Home.instance.loadpage("/view/board/boardview.fxml");
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setHeaderText(" �� ���� ���� [�����ڿ��Թ���]");
    		alert.showAndWait();
    	}

    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	txtbtitle.setText(Board.board.get�Խ�������());
    	txtbcontent.setText(Board.board.get�Խ��ǳ���());
    	img.setImage(null);
    	if(Board.board.get�̹������() != null) {
    		img.setImage(new Image(Board.board.get�̹������()));
    		
    	}
    	if(Board.board.get�Խ���ī�װ�() == 1) {
    		category.selectToggle(opt1);
    	}else {category.selectToggle(opt2);}
    	
    }

}