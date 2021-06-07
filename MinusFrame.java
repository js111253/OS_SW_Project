import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class MinusFrame extends JFrame{
	
	JPanel panel = new JPanel(new FlowLayout());
	JLabel name = new JLabel("��� ���� ���ĸ�");
	JTextField nameField = new JTextField();
	JLabel quant = new JLabel("��� ���� ����");
	JTextField quantField = new JTextField();
	JButton b1 = new JButton("����");
	JButton b2 = new JButton("���");
	Operator operator;
	
	MinusFrame(Operator _operator){
		operator = _operator;
		MyActionListener actionListener = new MyActionListener();	
		setTitle("��� ���� ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);	
		
		name.setPreferredSize(new Dimension(250,30));
		nameField.setPreferredSize(new Dimension(120, 20));
		quant.setPreferredSize(new Dimension(250,30));
		quantField.setPreferredSize(new Dimension(120,20));
		b1.setPreferredSize(new Dimension(185,40));
		b2.setPreferredSize(new Dimension(185,40));
		nameField.setBackground(new Color(250,250,250));
		nameField.setBorder(new LineBorder(Color.BLACK));
		quantField.setBackground(new Color(250,250,250));
		quantField.setBorder(new LineBorder(Color.BLACK));
		
		panel.add(name);
		panel.add(nameField);
		panel.add(quant);
		panel.add(quantField);
		panel.add(b1);
		panel.add(b2);
		
		b1.addActionListener(actionListener);
		b2.addActionListener(actionListener);
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation( (screenSize.width - frameSize.width)/ 2, (screenSize.height - frameSize.height) / 2 );
		setSize(400,155);
	}
	
	// ��ư Ŭ���� ���� ��� ����
	class MyActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();	
			
			// ���� ��ư Ŭ����
			if(b.getText().equals("����")) {
				String enterName = nameField.getText();
				String enterQuant = quantField.getText();
				
				// ������ �Էµ� �� �����ְ� ����� ����(true ���Ͻ�)
				if(operator.connector.sendMinus(enterName,enterQuant)) {
					nameField.setText("");
					quantField.setText("");
					dispose();	
				}
			// ��� ��ư Ŭ����	
			} else if(b.getText().equals("���")) {
				nameField.setText("");
				quantField.setText("");
				dispose();	
			}
		}
	}
}
