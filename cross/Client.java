package kiosk_user_client;

import java.awt.Color;

public class Client {

	
	DBLink base = null;
	FirstWindow w1 = null;
	MainWindow w2 = null;

	Color orange = new Color(255,127,39);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client m = new Client(); //클라이언트 창을 구성하는 객체
		m.base = new DBLink(m); //소켓통신을 담당하는 객체
		m.w1 = new FirstWindow(m); // 주문하는 고객 클라이언트를 구성하는 객체
		m.w2 =  new MainWindow(m);// 도움말 창을 구성하는 객체
		while(true) {
			for(int i = 0; i < 14; i++) {
				m.w1.tale[i].setText("" + m.w1.menun[i]);
				m.w1.talg[i].setText(m.w1.menug[i] + "원"); //키오스크 음식메뉴의 이름과 가격을 지속적으로 표시해주는 반복문
			}
		}
		
	}

}