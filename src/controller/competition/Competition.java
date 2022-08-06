package controller.competition;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.booking.Bookingctr;
import controller.home.Home;
import controller.login.Login;
import controller.login.Loginpane;
import dao.TournamentDao;
import dto.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Competition implements Initializable {
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Bookingctr.bookingday = null ;
    	Bookingctr.bookingtime = null ;
    	if(!Login.member.getȸ�����̵�().equals("admin")) {

        		btncompetition.setVisible(false);
       	}
    	//��ȸ����Ʈ ��������
    	ArrayList<Tournament> tournamentlist = TournamentDao.tournamentDao.list();
    	
    	///���ݸ����
    	GridPane gridPane = new GridPane();
    	
    	gridPane.setPadding(new Insets(10));
    	
    	gridPane.setHgap(20);
    	gridPane.setVgap(20);
    	
    	//�ݺ���
    	int i = 0;
    	for(int row =0; row <tournamentlist.size(); row++) {//��

    		for(int col =0; col<1; col++) {//��

    			///�̹���
    			ImageView imageView = new ImageView(new Image(tournamentlist.get(i).get��ȸ�̹������()));
    			imageView.setFitHeight(200);
    			imageView.setFitWidth(1090);
    			
    			
    			Button button = new Button(null, imageView);
    			
    			button.setStyle("-fx-background-color:transparent");
    			
    			//��ư�� id�� �ֱ�
    			button.setId(i+"");
    			//��ư Ŭ���̺�Ʈ
    			button.setOnAction(e -> {
    				System.out.println(e.toString());
    				///Ŭ���� ��ư�� id ��������
    				int id =Integer.parseInt(e.toString().split(",")[0].split("=")[2]);
    				//Ŭ���� ��ȸ��� ����
    				selectcnum =  tournamentlist.get(id);
    				
    				Home.instance.loadpage("/view/competition/competitionview.fxml");
    				
    			} );
    			
    			gridPane.add(button, col, row);
    			
    			i++;
    			}
    		}
    	
    	///vbox�� �׸��� �ֱ�
    		vbox.getChildren().add(gridPane);
    	
	}

	public static Tournament selectcnum;///���õ� ��ư�� ��� ����
	
    @FXML
    private Button btncompetition;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private VBox vbox;

    @FXML
    void addcompetition(ActionEvent event) {
    	if(Login.member.getȸ�����̵�().equals("admin")) {
    		Home.instance.loadpage("/view/competition/competitionadd.fxml");
    	}
    	else {
    		btncompetition.setVisible(false);
    	}	
   	}

}
