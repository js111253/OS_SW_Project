package kiosk_user_client;

import java.awt.Color;

public class Client {

	
	DBLink base = null;
	FirstWindow w1 = null;
	MainWindow w2 = null;

	Color orange = new Color(255,127,39);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client m = new Client(); //Ŭ���̾�Ʈ â�� �����ϴ� ��ü
		m.base = new DBLink(m); //��������� ����ϴ� ��ü
		m.w1 = new FirstWindow(m); // �ֹ��ϴ� �� Ŭ���̾�Ʈ�� �����ϴ� ��ü
		m.w2 =  new MainWindow(m);// ���� â�� �����ϴ� ��ü
		while(true) {
			for(int i = 0; i < 14; i++) {
				m.w1.tale[i].setText("" + m.w1.menun[i]);
				m.w1.talg[i].setText(m.w1.menug[i] + "��"); //Ű����ũ ���ĸ޴��� �̸��� ������ ���������� ǥ�����ִ� �ݺ���
			}
		}
		
	}

}