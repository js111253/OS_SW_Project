import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
// 업주용 프레임
public class OwnerFrame extends JFrame{
	JPanel panel = new JPanel(new BorderLayout());
	JTextArea textArea = new JTextArea();
	JButton plus = new JButton("음식 재고 추가");
	JButton minus = new JButton("음식 재고 차감");
	JButton cancel = new JButton("주문 현황 지우기");
	JPanel centerPanel = new JPanel(new BorderLayout());
	JPanel southPanel = new JPanel(new GridLayout(0,3));
	Operator operator;
	
	OwnerFrame(Operator _operator){
		operator = _operator;
		MyActionListener actionListener = new MyActionListener();
		
		setTitle("업주용프레임");
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
	// 버튼 클릭시 동작 기능 수행
	class MyActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			
			// 음식 재고 추가 버튼 클릭시
			if(b.getText().equals("음식 재고 추가")) {
				operator.plusFrame.setVisible(true);	// PlusFrame 오픈
				
			// 음식 재고 차감 버튼 클릭시
			} else if(b.getText().equals("음식 재고 차감")) {
				operator.minusFrame.setVisible(true);	// MinusFrame 오픈
				
			// 주문 현황 지우기 버튼 클릭시
			} else if(b.getText().equals("주문 현황 지우기")) {
				textArea.setText("");
			}
		}
	}

}