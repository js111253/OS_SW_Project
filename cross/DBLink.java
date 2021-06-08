package kiosk_user_client;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class DBLink {
	Socket socket = null;
	OutputStream outStream = null;
	DataOutputStream dataOutStream = null;
	InputStream inStream = null;
	DataInputStream dataInStream = null;
	ML ml = null;
	Client jin = null;                          //소켓통신을 위한 변수들
	
	
	DBLink(Client key){
		jin = key;
		try{
			socket = new Socket("localhost", 6080);
			outStream = this.socket.getOutputStream();
			dataOutStream = new DataOutputStream(outStream);
			inStream = this.socket.getInputStream();
			dataInStream = new DataInputStream(inStream);
			ml = new ML(socket, inStream, dataInStream, key); //소켓생성
			ml.start();
			}catch(Exception e) {
				
			}
	}
	
	void save() {
		String str = "";

		try {
			dataOutStream.writeUTF(str);
                                                       //음식
		}catch(Exception e) {
			
		}	
		
		
	}
	
}

class ML extends Thread{
	Socket so;
	InputStream is;
	DataInputStream dis;
	Client jjin;
	String au;
	
	ML(Socket sok, InputStream in, DataInputStream din, Client c){
		so = sok;
		is = in;
		dis = din;
		jjin = c;
	}

	public void run() {

		try {
			while(true) {
				/*
				String m = jjin.base.dataInStream.readUTF();
				StringTokenizer tkn = new StringTokenizer(m, "$^");
				String head = tkn.nextToken();
				String tail = tkn.nextToken();
				if(head.equals(jjin.base.sen) || head.equals(jjin.base.Sen)) {
					jjin.base.level = Integer.parseInt(tkn.nextToken());
					jjin.base.exp = Integer.parseInt(tkn.nextToken());
					jjin.base.preHP = Integer.parseInt(tkn.nextToken());
					jjin.base.wweapon = Integer.parseInt(tkn.nextToken());
					jjin.base.wclothes = Integer.parseInt(tkn.nextToken());
					jjin.base.wshield = Integer.parseInt(tkn.nextToken());
					jjin.base.what = Integer.parseInt(tkn.nextToken());
					jjin.base.wshoes = Integer.parseInt(tkn.nextToken());
					jjin.base.gold = Integer.parseInt(tkn.nextToken());
					jjin.base.wealth1 = Integer.parseInt(tkn.nextToken());
					jjin.base.wealth2 = Integer.parseInt(tkn.nextToken());
					jjin.base.wealth3 = Integer.parseInt(tkn.nextToken());
					jjin.base.wealth4 = Integer.parseInt(tkn.nextToken());
					jjin.base.wealth5 = Integer.parseInt(tkn.nextToken());
					jjin.base.battle = Integer.parseInt(tkn.nextToken());
					jjin.base.stage = Integer.parseInt(tkn.nextToken());
					jjin.base.stagex = Integer.parseInt(tkn.nextToken());
					jjin.base.stagey = Integer.parseInt(tkn.nextToken());
					
					for(int i = 0; i < 20; i++) {
						jjin.base.item[i] = Integer.parseInt(tkn.nextToken());
					}
					jjin.base.day = Integer.parseInt(tkn.nextToken());
					jjin.base.story = Integer.parseInt(tkn.nextToken());
					jjin.base.hpitemlimit = Integer.parseInt(tkn.nextToken());
					if(head.equals(jjin.base.Sen)) {
						jjin.w1.setVisible(false);
						jjin.w3.setVisible(true);
						}else{
							jjin.base.heroname = tail;
							if(jjin.base.battle == 1) {
								jjin.base.statcal();
								jjin.base.day += 3;
								jjin.base.preHP = jjin.base.maxHP;
								jjin.base.exp -= (int) (jjin.base.maxexp * 0.1);
								if(jjin.base.exp < 0) {
									jjin.base.exp = 0;
								}
								jjin.base.hpitemlimit = 0;
								jjin.base.battle = 0;
								jjin.w4.returntown();
								jjin.base.save();
								JOptionPane.showMessageDialog(null, "전투중에 게임이 종료되어 해당 전투가 패배 처리되었습니다.", "안내", JOptionPane.INFORMATION_MESSAGE);
							}
							jjin.w4.entertown();
							jjin.w1.setVisible(false);
							if(jjin.w8.on == false) {
							jjin.w4.setVisible(true);
							}
							if(jjin.base.stage == 0) {
								jjin.w4.npcmeet();
							}
							
							}
				
				}
				*/
				
			}
		}catch(Exception e) {
		}
		}
}