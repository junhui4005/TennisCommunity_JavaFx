package dto;

public class Mytournamentlist {
	
	// �ʵ�
	private int ��ȸ��ȣ;
	private String ��ȸ�̸�;
	private String ��ȸ��¥;
	private int ��ϼ���;
	private int ��Ϲ������;
	
	// ������
	public Mytournamentlist() {}

	public Mytournamentlist(int ��ȸ��ȣ, String ��ȸ�̸�, String ��ȸ��¥, int ��ϼ���, int ��Ϲ������) {
		super();
		this.��ȸ��ȣ = ��ȸ��ȣ;
		this.��ȸ�̸� = ��ȸ�̸�;
		this.��ȸ��¥ = ��ȸ��¥;
		this.��ϼ��� = ��ϼ���;
		this.��Ϲ������ = ��Ϲ������;
	}



	// �޼ҵ�

	public String get��ȸ�̸�() {
		return ��ȸ�̸�;
	}

	public int get��ȸ��ȣ() {
		return ��ȸ��ȣ;
	}

	public void set��ȸ��ȣ(int ��ȸ��ȣ) {
		this.��ȸ��ȣ = ��ȸ��ȣ;
	}

	public void set��ȸ�̸�(String ��ȸ�̸�) {
		this.��ȸ�̸� = ��ȸ�̸�;
	}

	public String get��ȸ��¥() {
		return ��ȸ��¥;
	}

	public void set��ȸ��¥(String ��ȸ��¥) {
		this.��ȸ��¥ = ��ȸ��¥;
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
