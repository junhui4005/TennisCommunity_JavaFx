package dto;

public class Comment {

	private int ��۹�ȣ;
	private int ��۰Խ��ǹ�ȣ; // �Խ��ǹ�ȣ
	private int ����ۼ���; // ȸ�����̵�
	private String �ۼ���;
	private String ��۳���;
	private String ����ۼ���;
	
	public Comment() {
		
	}

	public Comment(int ��۹�ȣ, int ��۰Խ��ǹ�ȣ, int ����ۼ���, String �ۼ���, String ��۳���, String ����ۼ���) {
		super();
		this.��۹�ȣ = ��۹�ȣ;
		this.��۰Խ��ǹ�ȣ = ��۰Խ��ǹ�ȣ;
		this.����ۼ��� = ����ۼ���;
		this.�ۼ��� = �ۼ���;
		this.��۳��� = ��۳���;
		this.����ۼ��� = ����ۼ���;
	}
	
	// ��� ȣ�� ����Ʈ

	public int get��۹�ȣ() {
		return ��۹�ȣ;
	}

	public void set��۹�ȣ(int ��۹�ȣ) {
		this.��۹�ȣ = ��۹�ȣ;
	}

	public int get��۰Խ��ǹ�ȣ() {
		return ��۰Խ��ǹ�ȣ;
	}

	public void set��۰Խ��ǹ�ȣ(int ��۰Խ��ǹ�ȣ) {
		this.��۰Խ��ǹ�ȣ = ��۰Խ��ǹ�ȣ;
	}

	public int get����ۼ���() {
		return ����ۼ���;
	}

	public void set����ۼ���(int ����ۼ���) {
		this.����ۼ��� = ����ۼ���;
	}

	public String get�ۼ���() {
		return �ۼ���;
	}

	public void set�ۼ���(String �ۼ���) {
		this.�ۼ��� = �ۼ���;
	}

	public String get��۳���() {
		return ��۳���;
	}

	public void set��۳���(String ��۳���) {
		this.��۳��� = ��۳���;
	}

	public String get����ۼ���() {
		return ����ۼ���;
	}

	public void set����ۼ���(String ����ۼ���) {
		this.����ۼ��� = ����ۼ���;
	}
	
	


	

	
}
