package kiosk_user_client;

import javax.swing.*;
import javax.swing.event.*;


import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;


public class MainWindow extends JFrame{

	JPanel gg = new JPanel(new FlowLayout());
	JLabel ga1 = new JLabel("ID"); //의미없는 객체
	JLabel ga2 = new JLabel("PASSWORD");//의미없는 객체
	JLabel ga3 = new JLabel("풍요의 잎사귀 이야기");//의미없는 객체
	JTextField can1 = new JTextField();//의미없는 객체
	JPasswordField can2 = new JPasswordField();//의미없는 객체
	JButton b1 = new JButton("주문화면으로");//누르면 주문화면으로 가는 버튼
	Client jin = null;
	ImageIcon img = new ImageIcon("any.png"); // 도움말 그림
	

	MainWindow(Client key){
		jin = key;
		setTitle("키오스크 서비스 - 도움말");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ga1.setIcon(img); //J라벨에 도움말 이미지 파일 삽입
		ga1.setPreferredSize(new Dimension(1045, 617));//의미없음
		ga2.setPreferredSize(new Dimension(70, 30));//의미없음
		ga3.setPreferredSize(new Dimension(370, 50));//의미없음
		can1.setPreferredSize(new Dimension(300, 30));//의미없음
		can2.setPreferredSize(new Dimension(300, 30));//의미없음
		b1.setPreferredSize(new Dimension(185, 30));//의미없음

		ga1.setForeground(Color.BLUE);
		ga2.setForeground(Color.BLUE);//의미없음
		ga3.setForeground(Color.BLUE);//의미없음
		b1.setBackground(jin.orange);

		gg.setBackground(Color.YELLOW);
		ga3.setFont(new Font("함초롬바탕", Font.BOLD + Font.ITALIC, 35));//의미없음
		ga3.setHorizontalAlignment(JLabel.CENTER);//의미없음
	
		gg.add(ga1);	
		gg.add(b1);

		act actman = new act();
		b1.addActionListener(actman);

		setContentPane(gg);
		setResizable(false);
		
		setSize(1200, 700);
		
		Dimension dm = this.getSize();
		Dimension dm2 = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dm2.width - dm.width)/2, (dm2.height - dm.height)/2);
		setVisible(true);
		
		//J라벨과 J버튼의 세팅
}
	class act implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton bb = (JButton) e.getSource();
			if(bb.getText().equals("주문화면으로")) {
				jin.w2.setVisible(false);
				jin.w1.setVisible(true);
			}
		}
	}
	
	//주문화면으로 버튼에 적용되는 액션 리스너
}
