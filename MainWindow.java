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
	JLabel ga1 = new JLabel("ID");
	JLabel ga2 = new JLabel("PASSWORD");
	JLabel ga3 = new JLabel("ǳ���� �ٻ�� �̾߱�");
	JTextField can1 = new JTextField();
	JPasswordField can2 = new JPasswordField();
	JButton b1 = new JButton("�ֹ�ȭ������");
	Client jin = null;
	ImageIcon img = new ImageIcon("help.png");
	

	MainWindow(Client key){
		jin = key;
		setTitle("Ű����ũ ���� - ����");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ga1.setIcon(img);
		ga1.setPreferredSize(new Dimension(1045, 617));
		ga2.setPreferredSize(new Dimension(70, 30));
		ga3.setPreferredSize(new Dimension(370, 50));
		can1.setPreferredSize(new Dimension(300, 30));
		can2.setPreferredSize(new Dimension(300, 30));
		b1.setPreferredSize(new Dimension(185, 30));

		ga1.setForeground(Color.BLUE);
		ga2.setForeground(Color.BLUE);
		ga3.setForeground(Color.BLUE);
		b1.setBackground(jin.orange);

		gg.setBackground(Color.WHITE);
		ga3.setFont(new Font("���ʷҹ���", Font.BOLD + Font.ITALIC, 35));
		ga3.setHorizontalAlignment(JLabel.CENTER);
	
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
}
	class act implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton bb = (JButton) e.getSource();
			if(bb.getText().equals("�ֹ�ȭ������")) {
				jin.w2.setVisible(false);
				jin.w1.setVisible(true);
			}
		}
	}
}
