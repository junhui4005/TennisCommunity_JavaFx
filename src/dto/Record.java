package dto;

public class Record {

	private int ��Ϲ�ȣ; // DB���̺� pk
	private String ȸ�����̵�;
	private int ��ϴ�ȸ��ȣ;
	private int ���ȸ����ȣ;
	private int ��ϼ���;
	private int ��Ϲ������;
		
	public Record() {
		// TODO Auto-generated constructor stub
	}
	
	public Record(int ��Ϲ�ȣ, String ȸ�����̵�, int ��ϴ�ȸ��ȣ, int ���ȸ����ȣ, int ��ϼ���, int ��Ϲ������) {
		super();
		this.��Ϲ�ȣ = ��Ϲ�ȣ;
		this.ȸ�����̵� = ȸ�����̵�;
		this.��ϴ�ȸ��ȣ = ��ϴ�ȸ��ȣ;
		this.���ȸ����ȣ = ���ȸ����ȣ;
		this.��ϼ��� = ��ϼ���;
		this.��Ϲ������ = ��Ϲ������;
	}


	public int get��Ϲ�ȣ() {
		return ��Ϲ�ȣ;
	}

	public void set��Ϲ�ȣ(int ��Ϲ�ȣ) {
		this.��Ϲ�ȣ = ��Ϲ�ȣ;
	}



	public String getȸ�����̵�() {
		return ȸ�����̵�;
	}



	public void setȸ�����̵�(String ȸ�����̵�) {
		this.ȸ�����̵� = ȸ�����̵�;
	}



	public int get��ϴ�ȸ��ȣ() {
		return ��ϴ�ȸ��ȣ;
	}



	public void set��ϴ�ȸ��ȣ(int ��ϴ�ȸ��ȣ) {
		this.��ϴ�ȸ��ȣ = ��ϴ�ȸ��ȣ;
	}



	public int get���ȸ����ȣ() {
		return ���ȸ����ȣ;
	}



	public void set���ȸ����ȣ(int ���ȸ����ȣ) {
		this.���ȸ����ȣ = ���ȸ����ȣ;
	}



	public int get��ϼ���() {
		return ��ϼ���;
	}



	public void set��ϼ���(int ��ϼ���) {
		this.��ϼ��� = ��ϼ���;
	}



	public int get��Ϲ������() {
		return ��Ϲ������;
	}



	public void set��Ϲ������(int ��Ϲ������) {
		this.��Ϲ������ = ��Ϲ������;
	}



	

	
}
