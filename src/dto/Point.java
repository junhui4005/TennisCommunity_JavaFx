package dto;

public class Point {

	private int ����Ʈ����ȸ����ȣ;
	private int ��������Ʈ;
	private String ���޳�¥;
	
	public Point() {
		
	}

	public Point(int ����Ʈ����ȸ����ȣ, int ��������Ʈ, String ���޳�¥) {
		super();
		this.����Ʈ����ȸ����ȣ = ����Ʈ����ȸ����ȣ;
		this.��������Ʈ = ��������Ʈ;
		this.���޳�¥ = ���޳�¥;
	}

	public int get����Ʈ����ȸ����ȣ() {
		return ����Ʈ����ȸ����ȣ;
	}

	public void set����Ʈ����ȸ����ȣ(int ����Ʈ����ȸ����ȣ) {
		this.����Ʈ����ȸ����ȣ = ����Ʈ����ȸ����ȣ;
	}

	public int get��������Ʈ() {
		return ��������Ʈ;
	}

	public void set��������Ʈ(int ��������Ʈ) {
		this.��������Ʈ = ��������Ʈ;
	}

	public String get���޳�¥() {
		return ���޳�¥;
	}

	public void set���޳�¥(String ���޳�¥) {
		this.���޳�¥ = ���޳�¥;
	}
	
	
	
}
