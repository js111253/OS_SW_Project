import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
// ���ֿ� ������
public class OwnerFrame extends JFrame{
	JPanel panel = new JPanel(new BorderLayout());
	JTextArea textArea = new JTextArea();
	JButton plus = new JButton("���� ��� �߰�");
	JButton minus = new JButton("���� ��� ����");
	JButton cancel = new JButton("�ֹ� ��Ȳ �����");
	JPanel centerPanel = new JPanel(new BorderLayout());
	JPanel southPanel = new JPanel(new GridLayout(0,3));
	Operator operator;
	
	OwnerFrame(Operator _operator){
		operator = _operator;
		MyActionListener actionListener = new MyActionListener();
		
		setTitle("���ֿ�������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		
		panel.add(centerPanel, BorderLayout.CENTER);
		panel.add(southPanel, BorderLayout.SOUTH);
		centerPanel.add(textArea, BorderLayout.CENTER);
		southPanel.add(plus);
		southPanel.add(minus);
		southPanel.add(cancel);
		
		centerPanel.setPreferredSize(new Dimension (panel.getWidth(),600));
		southPanel.setPreferredSize(new Dimension (panel.getWidth(), 60));
		textArea.setBackground(new Color(220,220,220));
		panel.setBorder(new LineBorder (Color.BLACK));
		centerPanel.setBorder(new LineBorder(Color.BLACK));
		
		plus.addActionListener(actionListener);
		minus.addActionListener(actionListener);
		cancel.addActionListener(actionListener);
		
		setSize(700,650);
		setVisible(true);
		
	}
	// ��ư Ŭ���� ���� ��� ����
	class MyActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			
			// ���� ��� �߰� ��ư Ŭ����
			if(b.getText().equals("���� ��� �߰�")) {
				operator.plusFrame.setVisible(true);	// PlusFrame ����
				
			// ���� ��� ���� ��ư Ŭ����
			} else if(b.getText().equals("���� ��� ����")) {
				operator.minusFrame.setVisible(true);	// MinusFrame ����
				
			// �ֹ� ��Ȳ ����� ��ư Ŭ����
			} else if(b.getText().equals("�ֹ� ��Ȳ �����")) {
				textArea.setText("");
			}
		}
	}

}