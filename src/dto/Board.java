package dto;

import dao.MemberDao;

public class Board {

	private int �Խ��ǹ�ȣ;
	private int �Խ����ۼ���; // ȸ����ȣ
	private String �ۼ��ھ��̵�;
	private int �Խ���ī�װ�;
	private String �Խ�������;
	private String �Խ��ǳ���;
	private String �̹������;
	private int �Խ�����ȸ��;
	private String �Խ����ۼ���;
	
	public Board() {
	}

	public Board(int �Խ��ǹ�ȣ, int �Խ����ۼ���, String �ۼ��ھ��̵�, int �Խ���ī�װ�, String �Խ�������, String �Խ��ǳ���, String �̹������,
			int �Խ�����ȸ��, String �Խ����ۼ���) {
		super();
		this.�Խ��ǹ�ȣ = �Խ��ǹ�ȣ;
		this.�Խ����ۼ��� = �Խ����ۼ���;
		this.�ۼ��ھ��̵� = �ۼ��ھ��̵�;
		this.�Խ���ī�װ� = �Խ���ī�װ�;
		this.�Խ������� = �Խ�������;
		this.�Խ��ǳ��� = �Խ��ǳ���;
		this.�̹������ = �̹������;
		this.�Խ�����ȸ�� = �Խ�����ȸ��;
		this.�Խ����ۼ��� = �Խ����ۼ���;
	}

	public int get�Խ��ǹ�ȣ() {
		return �Խ��ǹ�ȣ;
	}

	public void set�Խ��ǹ�ȣ(int �Խ��ǹ�ȣ) {
		this.�Խ��ǹ�ȣ = �Խ��ǹ�ȣ;
	}

	public int get�Խ����ۼ���() {
		return �Խ����ۼ���;
	}

	public void set�Խ����ۼ���(int �Խ����ۼ���) {
		this.�Խ����ۼ��� = �Խ����ۼ���;
	}

	public String get�ۼ��ھ��̵�() {
		return �ۼ��ھ��̵�;
	}

	public void set�ۼ��ھ��̵�(String �ۼ��ھ��̵�) {
		this.�ۼ��ھ��̵� = �ۼ��ھ��̵�;
	}

	public int get�Խ���ī�װ�() {
		return �Խ���ī�װ�;
	}

	public void set�Խ���ī�װ�(int �Խ���ī�װ�) {
		this.�Խ���ī�װ� = �Խ���ī�װ�;
	}

	public String get�Խ�������() {
		return �Խ�������;
	}

	public void set�Խ�������(String �Խ�������) {
		this.�Խ������� = �Խ�������;
	}

	public String get�Խ��ǳ���() {
		return �Խ��ǳ���;
	}

	public void set�Խ��ǳ���(String �Խ��ǳ���) {
		this.�Խ��ǳ��� = �Խ��ǳ���;
	}

	public String get�̹������() {
		return �̹������;
	}

	public void set�̹������(String �̹������) {
		this.�̹������ = �̹������;
	}

	public int get�Խ�����ȸ��() {
		return �Խ�����ȸ��;
	}

	public void set�Խ�����ȸ��(int �Խ�����ȸ��) {
		this.�Խ�����ȸ�� = �Խ�����ȸ��;
	}

	public String get�Խ����ۼ���() {
		return �Խ����ۼ���;
	}

	public void set�Խ����ۼ���(String �Խ����ۼ���) {
		this.�Խ����ۼ��� = �Խ����ۼ���;
	}
	
	
	
}