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
	String[] menu = {"�޴�0", "�޴�1", "�޴�2", "�޴�3", "�޴�4", "�޴�5", "�޴�6", "�޴�7", "�޴�8", "�޴�9", "�޴�10", "�޴�11", "�޴�12", "�޴�13"};
	int[] menun = {1,1,1,1,1,1,1,1,1,1,1,1,1,1}; //�޴��� ��� ��
	int[] menug = {1000,2000,3000,4000,5000,6000,7000,8000,9000,10000,11000,12000,13000,14000}; //�޴� ������ ����

	String[] jangm = {"", "", "", "", "", "", "", "", "", "", "", "", "", ""}; //��ٱ����� ���� �޴��� �̸��� ��� �迭
	int[] jangs = {0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //��ٱ����� ���� �޴��� ������ ��� �����迭
	int jangg = 0; //��ٱ��Ͽ� ��� ǰ����� �Ѿ�
	
	JLabel[] ib = {new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel()};
	JButton[] talb = {new JButton("�ֹ�"), new JButton("�ֹ�"),new JButton("�ֹ�"),new JButton("�ֹ�"),new JButton("�ֹ�"),new JButton("�ֹ�"),new JButton("�ֹ�"),new JButton("�ֹ�"), new JButton("�ֹ�"),new JButton("�ֹ�"),new JButton("�ֹ�"),new JButton("�ֹ�"),new JButton("�ֹ�"),new JButton("�ֹ�")};
	JButton[] talf = {new JButton(""), new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(), new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()};
	JLabel[] talc = {new JLabel(menu[0]), new JLabel(menu[1]), new JLabel(menu[2]), new JLabel(menu[3]), new JLabel(menu[4]), new JLabel(menu[5]), new JLabel(menu[6]), new JLabel(menu[7]), new JLabel(menu[8]), new JLabel(menu[9]), new JLabel(menu[10]), new JLabel(menu[11]), new JLabel(menu[12]), new JLabel(menu[13])};
	JLabel[] tald = {new JLabel("��� : "), new JLabel("��� : "), new JLabel("��� : "), new JLabel("��� : "), new JLabel("��� : "), new JLabel("��� : "), new JLabel("��� : "), new JLabel("��� : "), new JLabel("��� : "), new JLabel("��� : "), new JLabel("��� : "), new JLabel("��� : "), new JLabel("��� : "), new JLabel("��� : ")};
	JLabel[] tale = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel()}; //���� �޴��� ���
	JLabel[] talg = {new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel()}; //���� �޴��� ����
	
	JLabel jangn = new JLabel("��ٱ���");//��ٱ����� ��ġ�� �˷��ִ� ��
	JLabel jangch = new JLabel("�� �հ�ݾ� : 0��");//��ٱ����� �� �հ�ݾ��� ǥ��
	int b2s = -1;
	JButton b1 = new JButton("����");//������ ���� ȭ������ ���� ��ư 
	JButton b2 = new JButton("�����ֹ�");//������ ���� �ֹ����� �Ҽ� �ִ��� �����Ѵ� ��ư
	int lastw = 0; //��ٱ����� ���ڸ� �� ���� ���� �ε��� ��ȣ�� ����Ű�� ���� 
	

	int w_width = 1450; //Ű����ũ â�� �ʺ�
	int w_height = 1050; //Ű����ũ â�� ����
	
	Client jin = null;
	public Socket mySocket;
	
	FirstWindow(Client key){
		jin = key;
		setTitle("Ű����ũ ����");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		act2 actb = new act2(); //�ֹ���ư 
		act4 actd = new act4(); //��ٱ��� ǰ�� ����
		act3 actc = new act3();
		for(int i = 0; i < 14; i++) {
			talb[i].setLayout(null);
			add(talb[i]);
			talb[i].setForeground(Color.BLUE);
			talb[i].setBackground(Color.YELLOW);
			talb[i].setFont(new Font("���ʷҹ���", Font.BOLD, 20));
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
			talf[i].setFont(new Font("���ʷҹ���", Font.BOLD, 15));
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
			tald[i].setFont(new Font("���� ���", Font.BOLD, 15));
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
			tale[i].setFont(new Font("���� ���", Font.BOLD, 15));
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
			talc[i].setFont(new Font("���� ���", Font.BOLD, 15));
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
			talg[i].setFont(new Font("���� ���", Font.BOLD, 15));
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
		jangn.setFont(new Font("����", Font.BOLD, 30));
		jangn.setHorizontalAlignment(JLabel.CENTER);
		jangn.setBounds(-25, 560, 175, 40);
		
		jangch.setLayout(null);
		add(jangch);
		jangch.setForeground(new Color(128,0,255));
		jangch.setFont(new Font("���", Font.BOLD, 30));
		jangch.setHorizontalAlignment(JLabel.LEFT);
		jangch.setBounds(150, 560, 1450, 40);
		
		b1.setLayout(null);
		b1.setForeground(Color.WHITE);
		b1.setBackground(Color.BLACK);
		b1.setBounds(1320, 725, 100, 50);
		act acta = new act();
		b1.addActionListener(acta);
		b1.setFont(new Font("���", Font.PLAIN, 20));
		add(b1);
		b2.setLayout(null);
		b2.setForeground(Color.BLACK);
		b2.setBackground(new Color(255, 0, 255));
		b2.setBounds(1320, 655, 100, 50);
		b2.addActionListener(acta);
		b2.setFont(new Font("���", Font.BOLD, 16));
		add(b2);	
		setResizable(false);
		setSize(w_width, w_height);
		//J�� J��ư���� ����
	}
	
	void rise() {
		for(int i = 0; i < 14; i++) {
			if(jangm[i].equals("")) {
				lastw = i;
				break;
			}
		}
		//��ٱ����� ���ڸ� �� ���� ���� �ε��� ��ȣ�� ����Ű�� ���� ����
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
			jangch.setText("�� �հ�ݾ� : " + chong + "��");
			jangg = chong;
		}else {
			jangch.setText("�� �հ�ݾ� : ???��");
		}
	}else {
		jangch.setText("�� �հ�ݾ� : 0��");
	}
		//��ٱ����� �� �հ�ݾ� ����� ���
		for(int i = 0; i < 14; i++) {
			if(jangs[i] > 0) {
			talf[i].setText(jangm[i] + " " + jangs[i] + "�κ�");
			}else {
		    talf[i].setText("");	
			}
		}
	}
	//��ٱ��� ��ư�� �ؽ�Ʈ ���
	class act implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton bb = (JButton) e.getSource();
			if(bb.getText().equals("�����ֹ�")) {
				if(lastw != 0) {
					String mese = "";
					boolean jon2 = true;
					boolean jonpass = false;
				for(j = 0; j < lastw; j++) {
					boolean jon = false;
					for(int ii = 0; ii < 14; ii++) {
						if(jangm[j].equals(menu[ii])) {
							if(jangs[j] > menun[ii]) {
								mese += jangm[j] + "�� ��� �ֹ��Ͻ� " + jangm[j] + "�� �������� �����մϴ�.\n"; //jangm[j] -> ���� ������ �޴�(��ٱ��Ͽ� ����ִ�) 
								jon2 = false;
							}
							jon = true;
							break;
						}
						
					}
					if(jon == false) {
						JOptionPane.showMessageDialog(null, "���� �޴��� ���� ������ ��ٱ��Ͽ� �ֽ��ϴ�.\n�� ������ ��ٱ��Ͽ��� �������ֽʽÿ�.", "�ȳ�", JOptionPane.WARNING_MESSAGE);
						jonpass = true;
						break;
					}
				}
				if(jonpass == false) {
				if(jon2 == true) {
					int pardon = JOptionPane.showConfirmDialog(null, "�����ֹ��� �����Ͻðڽ��ϱ�?\n" + "�� �հ�ݾ� : " + jangg , "Confirm",  JOptionPane.YES_NO_OPTION);
					if(pardon ==  JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "�����ֹ��� �Ϸ�Ǿ����ϴ�.", "���� �ֹ� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
						//���� �̾��ִ� �ڵ� �ۼ� - ������ �ֹ��� �޴� ������
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
					JOptionPane.showMessageDialog(null, mese, "���� �ֹ� ����", JOptionPane.WARNING_MESSAGE);
				}
				
				}
			}
			}else if(bb.getText().equals("����")) {
				jin.w1.setVisible(false);
				jin.w2.setVisible(true);
			}
		}
	}
	//�����ֹ� ��ư�� ����Ǵ� �׼Ǹ�����
	class act2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton bb = (JButton) e.getSource();
			
			for(int i = 0; i < 14; i++) {
			if(bb == talb[i]) {
				if(!menu[i].equals("")) {
				String imname = JOptionPane.showInputDialog( menu[i] +"��(��) �� �κ� �ֹ��Ͻðڽ��ϱ�?");
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
	//�ֹ� ��ư�� ����Ǵ� �׼� ������
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
	//���ڿ��� ���ڷθ� �̷�����ִ��� Ȯ���ϴ� �Լ�
	class act4 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton bb = (JButton) e.getSource();
			for(int ii = 0; ii < 14; ii++) {
			if(bb == talf[ii]) {
				if(jangs[ii] != 0) {
				int pardon = JOptionPane.showConfirmDialog(null, jangm[ii] + " " + jangs[ii] +"�κ��� ��ٱ��� ǰ�񿡼� �����Ͻðڽ��ϱ�?", "Confirm",  JOptionPane.YES_NO_OPTION);
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
//��ٱ��� ��ư�� ����Ǵ� �׼� ������
	class act3 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton bb = (JButton) e.getSource();
			for(int i = 0; i < 20; i++) {
				
			}
			
		}
	}



