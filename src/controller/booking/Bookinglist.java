package controller.booking;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import controller.home.Home;
import controller.login.Login;
import dao.BookingDao;
import dto.Booking;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Bookinglist implements Initializable{
	
	public static Booking selectedbooking ;
	ArrayList<Booking> list ;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		list = BookingDao.bookingDao.bookinglist(Login.member.getȸ����ȣ()); // �α����� ȸ���� ���೻�� �����ͼ� ����
		show();
	}
	
	@FXML
	private Label lbltest;
	
    @FXML
    private ScrollPane scrollpane;

    @FXML
    private VBox vbox;
	
    
	void show() {
		GridPane gridpane = new GridPane();
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DATE) ; // ���� ��¥ �����س��� (���� ��¥ ���� ���೻���� �Ⱥ����ַ���)
		int month = calendar.get(Calendar.MONTH)+1 ; // ���� ��¥ �����س��� (���� ��¥ ���� ���೻���� �Ⱥ����ַ���)
		int year = calendar.get(Calendar.YEAR) ; // ���� ��¥ �����س��� (���� ��¥ ���� ���೻���� �Ⱥ����ַ���)
		int i = 0 ;
		for(Booking temp : list) { // ���೻����ŭ �ݺ���
			String[] checkday = temp.get���೯¥().split("-"); // ��¥ �ڸ��� 2022-04-20 -> 2022    04    20		
			if(Integer.parseInt(checkday[0]) == year){ // ���೯¥�� ���� ������ ������ (��)
				if(Integer.parseInt(checkday[1]) == month) { // ���೯¥�� ���� ������ ������ (��)
					if(Integer.parseInt(checkday[2])>=day) { // ���೯¥�� ���� ���� ���ĸ� (��)
						Button button = new Button();
						button.setPrefWidth(470);					
						button.setPrefHeight(70);
						button.setId("test");
						button.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());
						button.setText("�����ȣ : " + temp.get�����ȣ()+"\n���೯¥ : "+temp.get���೯¥()+"\n����ݾ� : "+temp.get����ݾ�()+" ��");
						button.setOnAction(e -> {
							selectedbooking = temp ;
							Home.instance.loadpage("/view/booking/bookingview.fxml");
						});
						button.setFont(Font.font(15));
						gridpane.add(button, 0, i);
						i++;
					}
					else {} // ���೯¥�� �� �� ������ ��¥�� �����̸� ���� �ȸ���
				}
				else if (Integer.parseInt(checkday[1]) > month){ // ���೯¥�� ���� ���� ���ĸ� (��)
					Button button = new Button();
					button.setPrefWidth(470);
					button.setPrefHeight(70);
					button.setId("test");
					button.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());
					button.setText("�����ȣ : " + temp.get�����ȣ()+"\n���೯¥ : "+temp.get���೯¥()+"\n����ݾ� : "+temp.get����ݾ�()+" ��");
					button.setOnAction(e -> {
						selectedbooking = temp ;
						Home.instance.loadpage("/view/booking/bookingview.fxml");
					});
					button.setFont(Font.font(15));
					gridpane.add(button, 0, i);
					i++;		
				}
				else {} // ���೯¥�� ���� ���� �����̸� (��) ��ư �ȸ���
			}
			else if(Integer.parseInt(checkday[0]) > year){ // ��������... �Ф�
				Button button = new Button();
				button.setPrefWidth(470);
				button.setPrefHeight(70);
				button.setId("test");
				button.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());
				button.setText("�����ȣ : " + temp.get�����ȣ()+"\n���೯¥ : "+temp.get���೯¥()+"\n����ݾ� : "+temp.get����ݾ�()+" ��");
				button.setOnAction(e -> {
					selectedbooking = temp ;
					Home.instance.loadpage("/view/booking/bookingview.fxml");
				});
				button.setFont(Font.font(15));
				gridpane.add(button, 0, i);
				i++;
				
			}
			
			else {}
		}
		vbox.getChildren().add(gridpane);	
	}
}
