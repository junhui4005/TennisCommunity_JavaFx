package dto;

public class Booking {
	private int ����ȸ����ȣ;
	private int �����ȣ;
	private String ���೯¥;
	private String ����ð�; // 08��~10�� : 1 / 10��~12�� : 2 / ~~~~
//	private int ��Ʈ��ȣ;
	private int ����ݾ�;
	
	public Booking() {
		
	}

	public Booking(int ����ȸ����ȣ, int �����ȣ, String ���೯¥, String ����ð�, /*int ��Ʈ��ȣ,*/ int ����ݾ�) {
		super();
		this.����ȸ����ȣ = ����ȸ����ȣ;
		this.�����ȣ = �����ȣ;
		this.���೯¥ = ���೯¥;
		this.����ð� = ����ð�;
//		this.��Ʈ��ȣ = ��Ʈ��ȣ;
		this.����ݾ� = ����ݾ�;
	}

	public int get����ȸ����ȣ() {
		return ����ȸ����ȣ;
	}

	public void set����ȸ����ȣ(int ����ȸ����ȣ) {
		this.����ȸ����ȣ = ����ȸ����ȣ;
	}

	public int get�����ȣ() {
		return �����ȣ;
	}

	public void set�����ȣ(int �����ȣ) {
		this.�����ȣ = �����ȣ;
	}

	public String get���೯¥() {
		return ���೯¥;
	}

	public void set���೯¥(String ���೯¥) {
		this.���೯¥ = ���೯¥;
	}

	public String get����ð�() {
		return ����ð�;
	}

	public void set����ð�(String ����ð�) {
		this.����ð� = ����ð�;
	}

//	public int get��Ʈ��ȣ() {
//		return ��Ʈ��ȣ;
//	}
//
//	public void set��Ʈ��ȣ(int ��Ʈ��ȣ) {
//		this.��Ʈ��ȣ = ��Ʈ��ȣ;
//	}

	public int get����ݾ�() {
		return ����ݾ�;
	}

	public void set����ݾ�(int ����ݾ�) {
		this.����ݾ� = ����ݾ�;
	}
	
	
}
