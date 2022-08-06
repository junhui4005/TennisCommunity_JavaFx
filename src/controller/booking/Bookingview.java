package controller.booking;

import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.home.Home;
import dao.BookingDao;
import dao.SalesDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class Bookingview implements Initializable{
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtbnum.setText("�����ȣ : " + Bookinglist.selectedbooking.get�����ȣ());
		txtbdate.setText("���೯¥ : " + Bookinglist.selectedbooking.get���೯¥());
		txtbprice.setText("����ݾ� : " + Bookinglist.selectedbooking.get����ݾ�());
		String[] booking = Bookinglist.selectedbooking.get����ð�().split(","); // ������ ���� ���� �ҷ��ͼ� ���ڿ��ڸ��� 
		// ���೻���� 1-1,2-1,3-1,2-5,3-4 �̷����·� ����Ǿ�����
		Arrays.sort(booking);
		// ���� (Ŭ���ߴ� ������� ����Ǳ⶧���� ���׹����� �׷��� ���Ľ�Ŵ)
		int i = 0 ;
		String text = "";
		for(String temp : booking) { // �߶�� ���ڿ� ��ŭ �ݺ��� X-Y -> X ��Ʈ��ȣ Y �ð�   �ݺ��ؼ� text�� �����س��� text�� label�� set��Ŵ 
			if(i==(booking.length)) {
				break;
			}
			String[] ���೻�� = temp.split("-");
			if(���೻��[0].equals("1")) {
				if(���೻��[1].equals("1")) {
					text += "A��Ʈ - 08:00~10:00\n";
				}
				else if(���೻��[1].equals("2")) {
					text += "A��Ʈ - 10:00~12:00\n";
				}
				else if(���೻��[1].equals("3")) {
					text += "A��Ʈ - 12:00~14:00\n";
				}
				else if(���೻��[1].equals("4")) {
					text += "A��Ʈ - 14:00~16:00\n";
				}
				else {
					text += "A��Ʈ - 16:00~18:00\n";
				}
			}
			else if(���೻��[0].equals("2")) {
				if(���೻��[1].equals("1")) {
					text += "B��Ʈ - 08:00~10:00\n";
				}
				else if(���೻��[1].equals("2")) {
					text += "B��Ʈ - 10:00~12:00\n";
				}
				else if(���೻��[1].equals("3")) {
					text += "B��Ʈ - 12:00~14:00\n";
				}
				else if(���೻��[1].equals("4")) {
					text += "B��Ʈ - 14:00~16:00\n";
				}
				else {
					text += "B��Ʈ - 16:00~18:00\n";
				}
			}
			else if(���೻��[0].equals("3")) {
				if(���೻��[1].equals("1")) {
					text += "C��Ʈ - 08:00~10:00\n";
				}
				else if(���೻��[1].equals("2")) {
					text += "C��Ʈ - 10:00~12:00\n";
				}
				else if(���೻��[1].equals("3")) {
					text += "C��Ʈ - 12:00~14:00\n";
				}
				else if(���೻��[1].equals("4")) {
					text += "C��Ʈ - 14:00~16:00\n";
				}
				else {
					text += "C��Ʈ - 16:00~18:00\n";
				}
			}
			else if(���೻��[0].equals("4")) {
				if(���೻��[1].equals("1")) {
					text += "D��Ʈ - 08:00~10:00\n";
				}
				else if(���೻��[1].equals("2")) {
					text += "D��Ʈ - 10:00~12:00\n";
				}
				else if(���೻��[1].equals("3")) {
					text += "D��Ʈ - 12:00~14:00\n";
				}
				else if(���೻��[1].equals("4")) {
					text += "D��Ʈ - 14:00~16:00\n";
				}
				else {
					text += "D��Ʈ - 16:00~18:00\n";
				}
			}
			else {
				if(���೻��[1].equals("1")) {
					text += "E��Ʈ - 08:00~10:00\n";
				}
				else if(���೻��[1].equals("2")) {
					text += "E��Ʈ - 10:00~12:00\n";
				}
				else if(���೻��[1].equals("3")) {
					text += "E��Ʈ - 12:00~14:00\n";
				}
				else if(���೻��[1].equals("4")) {
					text += "E��Ʈ - 14:00~16:00\n";
				}
				else {
					text += "E��Ʈ - 16:00~18:00\n";
				}
			}
			i++;
		}
		txtbtime.setText(text);  // �߶�� ���ڿ� ��ŭ �ݺ��� X-Y -> X ��Ʈ��ȣ Y �ð�   �ݺ��ؼ� text�� �����س��� text�� label�� set��Ŵ 
	}
	
    @FXML
    private Label txtbnum;

    @FXML
    private Label txtbdate;

    @FXML
    private Label txtbtime;

    @FXML
    private Label txtbprice;

    @FXML
    private Button btncancel;

    @FXML
    private Button btnback;

    @FXML
    private Button btnchange;

    @FXML
    void back(ActionEvent event) {
    	Home.instance.loadpage("/view/booking/bookinglist.fxml");
    }

    @FXML
    void cancel(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("���� ������ ����Ͻðڽ��ϱ�?");
    	Optional<ButtonType> optional = alert.showAndWait();
    	if(optional.get() == ButtonType.OK) {
    		SalesDao.salesDao.deletesales(Bookinglist.selectedbooking.get�����ȣ()); // ������ҽ� ���⿡���� ����
    		BookingDao.bookingDao.deletebooking(Bookinglist.selectedbooking.get�����ȣ()); // ������ҽ� ���೻�� ����
    		Home.instance.loadpage("/view/booking/bookinglist.fxml");
    	}
    	
    }

    @FXML
    void change(ActionEvent event) {
    	Bookingctr.pagecheck = 1 ; // ���� �������� �����ϱ����� ����ġ
    	Home.instance.loadpage("/view/booking/booking.fxml"); // ���� ��������
    }

}
