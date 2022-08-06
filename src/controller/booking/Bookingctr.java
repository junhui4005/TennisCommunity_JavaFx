package controller.booking;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.stream.Collectors;

import controller.home.Home;
import dao.BookingDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Bookingctr implements Initializable {

	Calendar calendar = Calendar.getInstance();
	int checkyear = calendar.get(Calendar.YEAR); // ���� ��¥ ��� (��)
	int checkmonth = calendar.get(Calendar.MONTH)+1; // ���� ��¥ ��� (��)
	int checkday = calendar.get(Calendar.DATE) ; // ���� ��¥ ��� (��)
	int year = calendar.get(Calendar.YEAR); // ������ ��ư���� �� ���������� ��ϵǴ� ����
	int month = calendar.get(Calendar.MONTH)+1; // ������ ��ư���� �� ���������� ��ϵǴ� ��
	int day = calendar.get(Calendar.DATE) ; // ������ ��ư���� ��¥ Ŭ�������� ����Ǵ� ��
	public static String bookingday ; // ������ ��¥
	public static String bookingtime ; // ���� ���� (��Ʈ-�ð�)
	public static int pagecheck= 0 ; // ���ຯ���϶��� üũ�ϱ� ���� ����ġ
	
	/**
	 *
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Bookingctr.bookingday = null ; // ������ ���۽� null (��¥ �����ߴٰ� �ٸ��޴�(ex�Խ���) ���ٿ����� ���� �����ִ°�� ���
    	Bookingctr.bookingtime = null ;	// ������ ���۽� null (��¥ �����ߴٰ� �ٸ��޴�(ex�Խ���) ���ٿ����� ���� �����ִ°�� ���
		lblmonth.setText(month+" ��");
		lblyear.setText(year+" ��");
		if(Bookinglist.selectedbooking != null) { // selectedbooking = ���೻������ �����ϱ� Ŭ���ϸ� selectedbooking�� �����
			if(pagecheck==1) { // ���ຯ��� ��ư�̸� �� ȭ�鿡 ǥ�õǴ� ���� ����
				btnpay.setText("�����ϱ�");
				lblbooking.setText("���ຯ��");
				lblselected.setText("���೯¥ : " + Bookinglist.selectedbooking.get���೯¥());
				String[] date = Bookinglist.selectedbooking.get���೯¥().split("-"); // ���೯¥ �����ͼ� ���ڿ��ڸ��� "2022-04-20"
				year = Integer.parseInt(date[0]) ; // ���ڷ� ���� 
				month = Integer.parseInt(date[1]) ; // ���ڷ� ����
				day = Integer.parseInt(date[2]) ;	// ���ڷ� ����
				lblmonth.setText(month+"��");
				lblyear.setText(year +" ��");
				if(Integer.parseInt(date[0])>checkyear) { // ������ ������ ���� �������� ������
					show(year,month,1); // �����ѽ��� �޷º����� (1�� ��������-> day�������� �� ������ ��ư�� ��Ȱ��ȭ�� 1�Ϻ��� ��ư�� �� Ȱ��ȭ�Ǿ����)
				}
				else if(Integer.parseInt(date[0])==checkyear){  // ������ ������
					if(checkmonth< month) { // ������ ���� ���� �������� ������
						show(year,month,1); // 1�Ϻ��� ���� ��ưȰ��ȭ��Ŵ
					}
					else if(checkmonth==month){ // ������ ���� ���� �����̸�
						show(year,month,checkday); // ���ú��� ��ưȰ��ȭ��Ŵ checkday = ����
					}	
				}
				else {  // ������ ������ ����������� ������ (���೯¥�� �̹� �����־ ����Ʈ�� �ȶ߱⶧���� ��ǻ� ���ԾȵǴ� �б�) 
					show(year,day,32); // 32���� ��ư ���� ��Ȱ��ȭ
				}
				showtable(day); // ���ຯ��ȭ���϶��� ���̺��� ����� (������ ��¥�����ؾ� ���̺� ��)
				pagecheck=0; // ����ġ �ʱ�ȭ
			}
			else { // ���೻������ �����ư�� �ȴ����� �ٽ� �����ϱ� ȭ���������� selectedbooking���������� pagecheck���� ����				
				Bookinglist.selectedbooking = null; // �׷��� �ٽ� selectedbooking�� null�� ����
				show(year,month,checkday);  // ���ຯ�洭���ٰ� �ٸ��޴� ���ٿ����Ƿ� �Ϲ� �����ϱ� ȭ���̶� ����
			}
		}
		else{ // �Ϲ� ���� (selectedbooking ������)
			show(year,month,checkday);
		}
	}
	
	@FXML
	private GridPane gridpane2;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private AnchorPane anchorpane2;
    
    @FXML
    private VBox vbox;
    
    @FXML
    private Label lblmonth;
    
    @FXML
    private Label lblyear;

    @FXML
    private Label lblbooking;
    
    @FXML
    private Label lblselected;
    
    @FXML
    private Button btnnext;

    @FXML
    private Button btnprevious;

    @FXML
    private Button btnpay;
    
    @FXML
    private Button btnback;
    
    
    @FXML
    void back(ActionEvent event) {	
    	if(Bookinglist.selectedbooking!=null) {
    		Home.instance.loadpage("/view/booking/bookinglist.fxml");
    		Bookinglist.selectedbooking = null ;
    	}
    	else{
    		Home.instance.loadpage("/view/home/homemain.fxml");
    	}
    	
    }
	
    @FXML
    void pay(ActionEvent event) {
    	if(month<=9) { // �޷� ��¥�� MM ���°� �ƴ϶� M���¶� DB�������� ���ڸ��� 0�߰�
    		// �̷��� ���� �������� �޷¿� 01, 02, 03 ���� ǥ���ؾ��ϴµ�... �޷��� ���̻���...
    		bookingday = year+"-0"+month+"-"+day ;
    	}
    	else {
    		bookingday = year+"-"+month+"-"+day ;
    	}
    	if(day<=9) {
    		bookingday = year+"-0"+month+"-0"+day ;
    	}
    	else {
    		bookingday = year+"-0"+month+"-"+day ;
    	}
    		
    	if(lblbooking.getText().equals("���ຯ��")) { // ���ຯ���ϰ��
    		if (bookingtime==null){	 // ��Ʈ�� �ð��� �������� �ʾ������
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setHeaderText("������ �ð��� ��Ʈ�� �ð�ǥ���� �������ּ���.");
	    		alert.showAndWait();
    		}
	    	else { 
	    		pagecheck=2; // ���ຯ���ϰ�� ���� ������ ��ȯ�� ���� ����ġ
    			Home.instance.loadpage("/view/booking/bookingpay.fxml");
	    	}
    	}
    	else {
	    	if( lblselected.getText().equals("��¥�� �������ּ���")) { // ���ຯ�� �ƴҰ��
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setHeaderText("��¥�� �������ּ���");
	    		alert.showAndWait();
	    	}	
	    	else if (bookingtime==null){	
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setHeaderText("������ �ð��� ��Ʈ�� �ð�ǥ���� �������ּ���.");
	    		alert.showAndWait();
	    	}
	    	else {
	    		Home.instance.loadpage("/view/booking/bookingpay.fxml");
	    	}
    	}
    }
    
    @FXML
    void next(ActionEvent event) { // ���� �޷� �����ư
    	gridpane2.getChildren().clear(); // ��Ʈ, �ð� ���̺� �ʱ�ȭ
    	bookingtime = null; // ������ ��Ʈ,�ð� �ʱ�ȭ
    	vbox.getChildren().remove(0);   // �޷� �ʱ�ȭ (�޷� �ٽ� �����ϱ� ����)  	
    	if(month+1 == 13) { year +=1 ; month = 1 ;} // �ذ� �Ѿ��� year�� +1
    	else { month +=1 ;} // �ذ� �ȳѾ�� ��+1
    	lblmonth.setText(month+" ��");
    	lblyear.setText(year + " ��");
    	if(checkmonth > month){ // ����������� Ŭ���� �� ���� ������ ex) ���� 4���ε� 2��->3�� �����Ұ��
    		if(year > checkyear) { // ���� ���� �⵵���� �ڸ� 
    			show(year,month,1);  // �޷� 1�Ϻ��� Ȱ��ȭ
    			lblyear.setText(year + " ��");
    		}
    		else {
    			show(year,month,32); // ���� �����⵵���� ���̸� ex) ���� 2022�� �ε� 2021���̸� �޷� ��ư ��Ȱ��ȭ
    		}
    	}
    	else if (checkmonth == month){ show(year,month,checkday); } // ��������̸� ���� �Ϻ��� �޷�Ȱ��ȭ
    	else  { // ����������� Ŭ���� ���� ũ�� 
    		if(year < checkyear) { // ������ üũ�ؾ��� ���� 4���ε� 2021�� 5���ϰ�� �̰� ���س����� ��ư �� Ȱ��ȭ��
    			show(year,month,32); // ���� �ʾ����� �޷� ��Ȱ��ȭ
    			lblyear.setText(year + " ��");
    			}
    		else{
    			show(year,month,1); // ������ �ȴʾ����� �޷� 1�Ϻ��� Ȱ��ȭ
    		}
    	}
    }

    @FXML
    void previous(ActionEvent event) { // ������ư (next�� ���� ����)
    	gridpane2.getChildren().clear();
    	bookingtime = null;
    	vbox.getChildren().remove(0);
    	if( month-1 == 0) { year -= 1 ; month = 12 ; }
    	else{ month -=1 ;}
    	lblmonth.setText(month+" ��");
    	lblyear.setText(year + " ��");
    	if(checkmonth > month){
    		if(year > checkyear) { 
    			show(year,month,1); 
    			lblyear.setText(year + " ��");
    		}
    		else { show(year,month,32); }
    	}
    	else if (checkmonth == month){ show(year,month,checkday); }
    	else  {
    		if(year < checkyear) {
    			show(year,month,32);
    			lblyear.setText(year + " ��");
    		}
    		else{
    			show(year,month,1);
    		}
    	}	
    }
    
    
    public void show(int year, int month, int day) { // �޷�ǥ��
    	TreeMap<String, String> map = BookingDao.bookingDao.bookingcheck(year, month, day); 
    	// ���� ��, �� ���೻�� �������� (���� ��á����� ��ư ��Ȱ��ȭ �ϱ� ����)
    	calendar.set(year, month-1, 1); // ��ư�� ������ ������¥�� �޷� ǥ�� (1���� ���ۿ����� ���ϱ����� ��¥�� 1�ְ�)
    	int sweek = calendar.get(Calendar.DAY_OF_WEEK); // ���� ����
    	int eday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // �ش��ϴ� ���� ��������
    	calendar.set(year, month-1, day); // �ٽ� ���� ��¥�� ��������
		GridPane gridPane = new GridPane();
		int i = 1 ;
		for(int row = 0 ; row < 6 ; row++) { // ��        5����ϸ� ���� 30~31���� ���� �� Ƣ����°�찡 �־ 6������...
			for(int col = 0 ; col < 7 ; col++) { // ��		��~�� 7��	
				Button button = new Button();
				button.setPrefWidth(70); // vbox������ ������ �ؼ� �� �°� ���
				button.setPrefHeight(60); // vbox������ ������ �ؼ� �� �°� ���
				button.setId("btn"+i); // css �����Ű���� ���̵� �޾Ƴ���
				button.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());
				// hover��� �����Ű���� css���� �ҷ�����
				button.setOnAction( e-> // Ŭ��������
					{lblselected.setText("���೯¥ : " + year+"-"+month+"-"+button.getText()); // ��ư�������� ȭ�鿡 ��¥ ǥ��
					this.day = Integer.parseInt(button.getText()); // �ش��ϴ� ��¥�� �ð�ǥ�� ����ϱ� ���� ��¥ ����
					bookingtime = null ; // ��¥�� ������ Ŭ��������� ���̺��� Ŭ���ߴ� ��¥ �ʱ�ȭ(20�� 1-5 �����ߴٰ� 22�� Ŭ��������� ���� 1-5 ����)
					showtable(this.day); // Ŭ���ѳ�¥�� �������̺� �����ֱ�
					}
				);
				if(i<sweek) { i++; } // 1���� ���� (�ݿ����̸� ����ϱ����� ��ư �ȸ���)
				else if(i>=eday+sweek) { i++; } // �������� �Ѿ�� ��ư �ȸ��� ( gridpane�� 6*7 42ĭ�̹Ƿ� �������Ŀ� ��ư �ȸ���)
				else { // 1~���� ��ư����
					button.setText((i-sweek+1)+""); // ��¥ ǥ��
					button.setFont(Font.font(18));
					i++ ;
				}		
				if(year < checkyear) { button.setDisable(true);	} // ���� ���غ��� �� �⵵ �ϰ�� ������ ���� ��ư�� ���� ��Ȱ��ȭ
				else if (year == checkyear) { // ���� ���س⵵�ϰ��
					if(button.getText()=="" || Integer.parseInt(button.getText()) < day) { button.setDisable(true); }					
				} // �޷¿� �۾��� ���ų� ���� ���� ���� ���� �ϰ�� ��ư��Ȱ��ȭ
				else { if(button.getText()=="") { button.setDisable(true); }	} // ���� ���غ��� �� �⵵ �ϰ�� ex)2023�� �۾� ���� ��ư�� ��Ȱ��ȭ
				
				
				// map = �޼ҵ� ������ ���೻�� �����°� <��¥, ����Ÿ��> ���� ����Ǿ�����
				if(map.containsKey(button.getText())) {  // map�� ��ư ��¥�� �������
					if(map.get(button.getText()).length()>=100) { // �׳�¥�� ����Ÿ�� ���̰� 100�̻��ϰ�� x-x, ������ ����Ǳ⶧���� 4*25= 100
						button.setDisable(true); // ������ �� á���Ƿ� ��ư ��Ȱ��ȭ
						button.setText("����Ϸ�"); // �ؽ�Ʈ ����
						button.setFont(Font.font(12));
					}
				}
				gridPane.add(button, col, row);  // �� ������ ��� ��ġ���� �׸����ҿ� ��ư �߰�
			}
		}	
		vbox.getChildren().add(gridPane);		 // vbox�� �׸������� �߰�
	}	

    public void showtable(int day) {
    	gridpane2.getChildren().clear(); // ��¥�� ������ Ŭ���Ұ�� ���� ������ �ʱ�ȭ�ϰ� ���� ǥ����
    	Map<String, String> map = BookingDao.bookingDao.bookingcheck(year, month, day); // ���೻�� ��������
    	int i = 0 ;

    	for(int row = 0 ; row  <=5 ; row++ ) { // 6X6 �׸����ҿ� 1���� ��Ʈ��ȣ, 1���� �ð�
    		for(int col = 0 ; col <=5 ; col++) {
    			i++;
    			if(i==1) {}
    			else if(i==7) { // 1�࿣ �ð�
    				Label label = new Label();
					label.setText("08:00~10:00");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==13) { // 1�࿣ �ð�
    				Label label = new Label();
					label.setText("10:00~12:00");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==19) { // 1�࿣ �ð�
    				Label label = new Label();
					label.setText("12:00~14:00");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==25) { // 1�࿣ �ð�
    				Label label = new Label();
					label.setText("14:00~16:00");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==31) { // 1�࿣ �ð�
    				Label label = new Label();
					label.setText("16:00~18:00");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==2) { // 1���� ��Ʈ��ȣ
    				Label label = new Label();
					label.setText("A��Ʈ");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==3) { // 1���� ��Ʈ��ȣ
    				Label label = new Label();
					label.setText("B��Ʈ");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==4) { // 1���� ��Ʈ��ȣ
    				Label label = new Label();
					label.setText("C��Ʈ");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==5) { // 1���� ��Ʈ��ȣ
    				Label label = new Label();
					label.setText("D��Ʈ");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}
    			else if(i==6) { // 1���� ��Ʈ��ȣ
    				Label label = new Label();
					label.setText("E��Ʈ");
					label.setFont(Font.font(15));
					gridpane2.add(label, col, row);		
    			}

    			else{ // �� �ܿ� ���� ��ư
    				ToggleButton tbutton = new ToggleButton();
	    			tbutton.setPrefWidth(100);
	    			tbutton.setPrefHeight(40);
	    			tbutton.setId(row+"-"+col); // ��, ���� ������ ���̵� ���� ex)1-1, 2-3 
	    			tbutton.setText("���డ��");
					tbutton.setOnAction( e->
						{
							if(tbutton.isSelected()) { // ��ư�� ��������
								
								tbutton.setText("�����ϱ�"); // �����ϱ�� �ؽ�Ʈ ����
								if(bookingtime==null) {bookingtime = tbutton.getId()+"," ;} // ������ ������ Ÿ���� ������ �߰�
								else  {bookingtime += tbutton.getId()+"," ;} // ������ ���� ���ڿ����ٰ� ���� �߰��� ���̵� �߰�
							}
							else if(!(tbutton.isSelected())) { // ������ư�� �ٽ� ��������
								tbutton.setText("���డ��"); // �ؽ�Ʈ ������� ����
								bookingtime = bookingtime.replace((tbutton.getId()+","), ""); // ���ڿ� ����
							}
						}
					);					
					if(map.get(day+"")!=null) { // ������ ���೻������ �ش��ϴ½ð��� ���� ������
						if(map.get(day+"").contains(tbutton.getId())) {
			    			tbutton.setText("������"); // �ؽ�Ʈ ����
			    			tbutton.setDisable(true); // ��ư ��Ȱ��ȭ   			
						}
					}
					tbutton.setFont(Font.font(15));
	    			gridpane2.add(tbutton, col, row);  	// �׸����ҿ� ��ư �߰�
    			}    			
    		} 				
    	}
    }    
}
