import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;

public class FirstWindow extends JFrame{
	int j;
	String[] menu = {"메뉴0", "메뉴1", "메뉴2", "메뉴3", "메뉴4", "메뉴5", "메뉴6", "메뉴7", "메뉴8", "메뉴9", "메뉴10", "메뉴11", "메뉴12", "메뉴13"};
	int[] menun = {1,1,1,1,1,1,1,1,1,1,1,1,1,1}; //메뉴의 재고 수
	int[] menug = {1000,2000,3000,4000,5000,6000,7000,8000,9000,10000,11000,12000,13000,14000}; //메뉴 음식의 가격

	String[] jangm = {"", "", "", "", "", "", "", "", "", "", "", "", "", ""}; //장바구니의 음식 메뉴의 이름이 담긴 배열
	int[] jangs = {0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //장바구니의 음식 메뉴의 수량이 담긴 정수배열
	int jangg = 0; //장바구니에 담긴 품목들의 총액
	
	JLabel[] ib = {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
	JButton[] talb = {new JButton("주문"), new JButton("주문"),new JButton("주문"),new JButton("주문"),new JButton("주문"),new JButton("주문"),new JButton("주문"),new JButton("주문"), new JButton("주문"),new JButton("주문"),new JButton("주문"),new JButton("주문"),new JButton("주문"),new JButton("주문")};
	JButton[] talf = {new JButton(""), new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(), new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()};
	JLabel[] talc = {new JLabel(menu[0]), new JLabel(menu[1]), new JLabel(menu[2]), new JLabel(menu[3]), new JLabel(menu[4]), new JLabel(menu[5]), new JLabel(menu[6]), new JLabel(menu[7]), new JLabel(menu[8]), new JLabel(menu[9]), new JLabel(menu[10]), new JLabel(menu[11]), new JLabel(menu[12]), new JLabel(menu[13])};
	JLabel[] tald = {new JLabel("재고 : "), new JLabel("재고 : "), new JLabel("재고 : "), new JLabel("재고 : "), new JLabel("재고 : "), new JLabel("재고 : "), new JLabel("재고 : "), new JLabel("재고 : "), new JLabel("재고 : "), new JLabel("재고 : "), new JLabel("재고 : "), new JLabel("재고 : "), new JLabel("재고 : "), new JLabel("재고 : ")};
	JLabel[] tale = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel()}; //음식 메누의 재고
	JLabel[] talg = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel()}; //음식 메뉴의 가격
	
	JLabel jangn = new JLabel("장바구니");//장바구니의 위치를 알려주는 곳
	JLabel jangch = new JLabel("총 합계금액 : 0원");//장바구니의 총 합계금액이 표기
	int b2s = -1;
	JButton b1 = new JButton("도움말");//누르면 도움말 화면으로 가는 버튼 
	JButton b2 = new JButton("최종주문");//누르면 최종 주물문을 할수 있는지 검증한는 버튼
	int lastw = 0; //장바구니의 빈자리 중 가장 낮은 인덱스 번호를 가리키는 변수 
	

	int w_width = 1450; //키오스크 창의 너비
	int w_height = 1050; //키오스크 창의 높이
	
	Client jin = null;
	public Socket mySocket;
	
	FirstWindow(Client key){
		jin = key;
		setTitle("키오스크 서비스");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		act2 actb = new act2(); //주문버튼 
		act4 actd = new act4(); //장바구니 품목에 적용
		act3 actc = new act3();
		for(int i = 0; i < 14; i++) {
			talb[i].setLayout(null);
			add(talb[i]);
			talb[i].setForeground(Color.BLUE);
			talb[i].setBackground(Color.YELLOW);
			talb[i].setFont(new Font("함초롬바탕", Font.BOLD, 20));
			talb[i].addActionListener(actb);
		}
		talb[0].setBounds(10, 235, 175, 40);
		talb[1].setBounds(195, 235, 175, 40);
		talb[2].setBounds(380, 235, 175, 40);
		talb[3].setBounds(565, 235, 175, 40);
		talb[4].setBounds(750, 235, 175, 40);
		talb[5].setBounds(935, 235, 175, 40);
		talb[6].setBounds(1120, 235, 175, 40);
		talb[7].setBounds(10, 510, 175, 40);
		talb[8].setBounds(195, 510, 175, 40);
		talb[9].setBounds(380, 510, 175, 40);
		talb[10].setBounds(565, 510, 175, 40);
		talb[11].setBounds(750, 510, 175, 40);
		talb[12].setBounds(935, 510, 175, 40);
		talb[13].setBounds(1120, 510, 175, 40);
		
		for(int i = 0; i < 14; i++) {
			talf[i].setLayout(null);
			add(talf[i]);
			talf[i].setBackground(jin.orange);
			talf[i].setFont(new Font("함초롬바탕", Font.BOLD, 15));
			talf[i].addActionListener(actd);
		}
		talf[0].setBounds(10, 600, 175, 90);
		talf[1].setBounds(195, 600, 175, 90);
		talf[2].setBounds(380, 600, 175, 90);
		talf[3].setBounds(565, 600, 175, 90);
		talf[4].setBounds(750, 600, 175, 90);
		talf[5].setBounds(935, 600, 175, 90);
		talf[6].setBounds(1120, 600, 175, 90);
		talf[7].setBounds(10, 700, 175, 90);
		talf[8].setBounds(195, 700, 175, 90);
		talf[9].setBounds(380, 700, 175, 90);
		talf[10].setBounds(565, 700, 175, 90);
		talf[11].setBounds(750, 700, 175, 90);
		talf[12].setBounds(935, 700, 175, 90);
		talf[13].setBounds(1120, 700, 175, 90);
		
		for(int i = 0; i < 14; i++) {
			tald[i].setLayout(null);
			add(tald[i]);
			tald[i].setForeground(new Color(163, 73, 164));
			tald[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
		}
		tald[0].setBounds(10, 200, 175, 40);
		tald[1].setBounds(195, 200, 175, 40);
		tald[2].setBounds(380, 200, 175, 40);
		tald[3].setBounds(565, 200, 175, 40);
		tald[4].setBounds(750, 200, 175, 40);
		tald[5].setBounds(935, 200, 175, 40);
		tald[6].setBounds(1120, 200, 175, 40);
		tald[7].setBounds(10, 475, 175, 40);
		tald[8].setBounds(195, 475, 175, 40);
		tald[9].setBounds(380, 475, 175, 40);
		tald[10].setBounds(565, 475, 175, 40);
		tald[11].setBounds(750, 475, 175, 40);
		tald[12].setBounds(935, 475, 175, 40);
		tald[13].setBounds(1120, 475, 175, 40);
		
		for(int i = 0; i < 14; i++) {
			tale[i].setLayout(null);
			add(tale[i]);
			tale[i].setForeground(new Color(163, 73, 164));
			tale[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
			tale[i].setHorizontalAlignment(JLabel.RIGHT);
		}
		tale[0].setBounds(10, 200, 175, 40);
		tale[1].setBounds(195, 200, 175, 40);
		tale[2].setBounds(380, 200, 175, 40);
		tale[3].setBounds(565, 200, 175, 40);
		tale[4].setBounds(750, 200, 175, 40);
		tale[5].setBounds(935, 200, 175, 40);
		tale[6].setBounds(1120, 200, 175, 40);
		tale[7].setBounds(10, 475, 175, 40);
		tale[8].setBounds(195, 475, 175, 40);
		tale[9].setBounds(380, 475, 175, 40);
		tale[10].setBounds(565, 475, 175, 40);
		tale[11].setBounds(750, 475, 175, 40);
		tale[12].setBounds(935, 475, 175, 40);
		tale[13].setBounds(1120, 475, 175, 40);
		
		for(int i = 0; i < 14; i++) {
			talc[i].setLayout(null);
			add(talc[i]);
			talc[i].setForeground(Color.GRAY);
			talc[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
			talc[i].setHorizontalAlignment(JLabel.CENTER);
		}
		talc[0].setBounds(10, 155, 175, 40);
		talc[1].setBounds(195, 155, 175, 40);
		talc[2].setBounds(380, 155, 175, 40);
		talc[3].setBounds(565, 155, 175, 40);
		talc[4].setBounds(750, 155, 175, 40);
		talc[5].setBounds(935, 155, 175, 40);
		talc[6].setBounds(1120, 155, 175, 40);
		talc[7].setBounds(10, 430, 175, 40);
		talc[8].setBounds(195, 430, 175, 40);
		talc[9].setBounds(380, 430, 175, 40);
		talc[10].setBounds(565, 430, 175, 40);
		talc[11].setBounds(750, 430, 175, 40);
		talc[12].setBounds(935, 430, 175, 40);
		talc[13].setBounds(1120, 430, 175, 40);
		
		for(int i = 0; i < 14; i++) {
			talg[i].setLayout(null);
			add(talg[i]);
			talg[i].setForeground(new Color(0, 64, 128));
			talg[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
			talg[i].setHorizontalAlignment(JLabel.CENTER);
		}
		talg[0].setBounds(10, 180, 175, 40);
		talg[1].setBounds(195, 180, 175, 40);
		talg[2].setBounds(380, 180, 175, 40);
		talg[3].setBounds(565, 180, 175, 40);
		talg[4].setBounds(750, 180, 175, 40);
		talg[5].setBounds(935, 180, 175, 40);
		talg[6].setBounds(1120, 180, 175, 40);
		talg[7].setBounds(10, 455, 175, 40);
		talg[8].setBounds(195, 455, 175, 40);
		talg[9].setBounds(380, 455, 175, 40);
		talg[10].setBounds(565, 455, 175, 40);
		talg[11].setBounds(750, 455, 175, 40);
		talg[12].setBounds(935, 455, 175, 40);
		talg[13].setBounds(1120, 455, 175, 40);
		
		for(int i = 0; i < 14; i++) {
			ib[i].setLayout(null);
			add(ib[i]);	
			ib[i].setOpaque(true);
			ib[i].setBackground(Color.WHITE);
		}
		ib[0].setBounds(10, 10, 175, 155);
		ib[1].setBounds(195, 10, 175, 155);
		ib[2].setBounds(380, 10, 175, 155);
		ib[3].setBounds(565, 10, 175, 155);
		ib[4].setBounds(750, 10, 175, 155);
		ib[5].setBounds(935, 10, 175, 155);
		ib[6].setBounds(1120, 10, 175, 155);
		ib[7].setBounds(10, 285, 175, 155);
		ib[8].setBounds(195, 285, 175, 155);
		ib[9].setBounds(380, 285, 175, 155);
		ib[10].setBounds(565, 285, 175, 155);
		ib[11].setBounds(750, 285, 175, 155);
		ib[12].setBounds(935, 285, 175, 155);
		ib[13].setBounds(1120, 285, 175, 155);
		
		jangn.setLayout(null);
		add(jangn);
		jangn.setForeground(Color.GREEN);
		jangn.setFont(new Font("바탕", Font.BOLD, 30));
		jangn.setHorizontalAlignment(JLabel.CENTER);
		jangn.setBounds(-25, 560, 175, 40);
		
		jangch.setLayout(null);
		add(jangch);
		jangch.setForeground(new Color(128,0,255));
		jangch.setFont(new Font("고딕", Font.BOLD, 30));
		jangch.setHorizontalAlignment(JLabel.LEFT);
		jangch.setBounds(150, 560, 1450, 40);
		
		b1.setLayout(null);
		b1.setForeground(Color.WHITE);
		b1.setBackground(Color.BLACK);
		b1.setBounds(1320, 725, 100, 50);
		act acta = new act();
		b1.addActionListener(acta);
		b1.setFont(new Font("고딕", Font.PLAIN, 20));
		add(b1);
		b2.setLayout(null);
		b2.setForeground(Color.BLACK);
		b2.setBackground(new Color(255, 0, 255));
		b2.setBounds(1320, 655, 100, 50);
		b2.addActionListener(acta);
		b2.setFont(new Font("고딕", Font.BOLD, 16));
		add(b2);	
		setResizable(false);
		setSize(w_width, w_height);
		//J라벨 J버튼들의 세팅
	}
	
	void rise() {
		for(int i = 0; i < 14; i++) {
			if(jangm[i].equals("")) {
				lastw = i;
				break;
			}
		}
		//장바구니의 빈자리 중 가장 낮은 인덱스 번호를 가리키는 변수 갱신
		if(lastw != 0) {
			int chong = 0;
		for(int i = 0; i < lastw; i++) {
			boolean jon = false;
			for(int ii = 0; ii < 14; ii++) {
				if(jangm[i].equals(menu[ii])) {
					chong += menug[ii]*jangs[i];
					jon = true;
					break;
				}
				
			}
			if(jon == false) {
				chong = -1;
				break;
			}
		}
		if(chong != -1) {
			jangch.setText("총 합계금액 : " + chong + "원");
			jangg = chong;
		}else {
			jangch.setText("총 합계금액 : ???원");
		}
	}else {
		jangch.setText("총 합계금액 : 0원");
	}
		//장바구니의 총 합계금액 계산후 출력
		for(int i = 0; i < 14; i++) {
			if(jangs[i] > 0) {
			talf[i].setText(jangm[i] + " " + jangs[i] + "인분");
			}else {
		    talf[i].setText("");	
			}
		}
	}
	//장바구니 버튼의 텍스트 출력
	class act implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton bb = (JButton) e.getSource();
			if(bb.getText().equals("최종주문")) {
				if(lastw != 0) {
					String mese = "";
					boolean jon2 = true;
					boolean jonpass = false;
				for(j = 0; j < lastw; j++) {
					boolean jon = false;
					for(int ii = 0; ii < 14; ii++) {
						if(jangm[j].equals(menu[ii])) {
							if(jangs[j] > menun[ii]) {
								mese += jangm[j] + "의 재고가 주문하신 " + jangm[j] + "의 수량보다 부족합니다.\n"; //jangm[j] -> 내가 선택한 메뉴(장바구니에 담겨있는) 
								jon2 = false;
							}
							jon = true;
							break;
						}
						
					}
					if(jon == false) {
						JOptionPane.showMessageDialog(null, "현재 메뉴에 없는 음식이 장바구니에 있습니다.\n그 음식을 장바구니에서 제거해주십시요.", "안내", JOptionPane.WARNING_MESSAGE);
						jonpass = true;
						break;
					}
				}
				if(jonpass == false) {
				if(jon2 == true) {
					int pardon = JOptionPane.showConfirmDialog(null, "최종주문을 진행하시겠습니까?\n" + "총 합계금액 : " + jangg , "Confirm",  JOptionPane.YES_NO_OPTION);
					if(pardon ==  JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "최종주문이 완료되었습니다.", "최종 주문 완료", JOptionPane.INFORMATION_MESSAGE);
						//서버 이어주는 코드 작성 - 서버에 주문된 메뉴 보내기
						try {
							Socket socket = null;
							OutputStream os = socket.getOutputStream();
							os.write(jangs[j]);
							String msg = jangm[j];
							byte[] bytes = msg.getBytes("UTF-8");
							os.write(bytes);	
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, mese, "최종 주문 실패", JOptionPane.WARNING_MESSAGE);
				}
				
				}
			}
			}else if(bb.getText().equals("도움말")) {
				jin.w1.setVisible(false);
				jin.w2.setVisible(true);
			}
		}
	}
	//최종주문 버튼에 적용되는 액션리스너
	class act2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton bb = (JButton) e.getSource();
			
			for(int i = 0; i < 14; i++) {
			if(bb == talb[i]) {
				if(!menu[i].equals("")) {
				String imname = JOptionPane.showInputDialog( menu[i] +"을(를) 몇 인분 주문하시겠습니까?");
				if(imname != null && !(imname.equals(""))) {
					if(sumancheck(imname) == true) {
						int gint = Integer.parseInt(imname);
						boolean jch = true;
						for(int ii = 0; ii < 14; ii++) {
							if(jangm[ii].equals(menu[i])) {
								jangs[ii] += gint;
								jch = false;
								break;
							}
						}
						
						if(jch == true) {
							jangm[lastw] = menu[i];
							jangs[lastw] = gint;
							
						}
						rise();
					}
				}
				
			}
			}
			}

		}
	}
	//주문 버튼에 적용되는 액션 리스너
	boolean sumancheck(String a) {
		boolean o = true;
	     for(int i = 0 ; i < a.length(); i++){
	          char b = a.charAt(i); 
	          int c = (int)b;
	          if( c < 48 ||  c > 57 ) {
	                o = false;
	                break;
	          }
	     }
		return o;
	}
	//문자열이 숫자로만 이루어져있는지 확인하는 함수
	class act4 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton bb = (JButton) e.getSource();
			for(int ii = 0; ii < 14; ii++) {
			if(bb == talf[ii]) {
				if(jangs[ii] != 0) {
				int pardon = JOptionPane.showConfirmDialog(null, jangm[ii] + " " + jangs[ii] +"인분을 장바구니 품목에서 제거하시겠습니까?", "Confirm",  JOptionPane.YES_NO_OPTION);
				if(pardon ==  JOptionPane.YES_OPTION) {
					if(ii == 13) {
						jangm[ii] = "";
						jangs[ii] = 0;
					}else {
					for(int i = ii + 1; i < 20; i++) {
						if(jangs[i] != 0) {
						jangs[i - 1] = jangs[i];
						jangm[i - 1] = jangm[i];
						}else {
							jangm[i - 1] = "";
							jangs[i - 1] = 0;
							break;
						}
						if(i == 13) {
							jangm[i] = "";
							jangs[i] = 0;
						}
					}
					}
				}
				
				rise();
			}
			}
			}
	}
	}
	
	}
//장바구니 버튼에 적용되는 액션 리스너
	class act3 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton bb = (JButton) e.getSource();
			for(int i = 0; i < 20; i++) {
				
			}
			
		}
	}



