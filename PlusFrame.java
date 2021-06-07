import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

// 재고 추가 프레임
public class PlusFrame extends JFrame{
	JPanel panel = new JPanel(new FlowLayout());
	JLabel name = new JLabel("재고 추가 음식명");
	JTextField nameField = new JTextField();
	
	JLabel quant = new JLabel("재고 추가 수량");
	JTextField quantField = new JTextField();
	JButton b1 = new JButton("추가");
	JButton b2 = new JButton("취소");
	Operator operator;
	
	
	PlusFrame(Operator _operator){
		operator = _operator;
		MyActionListener actionListener = new MyActionListener();	//버튼클릭시 동작할 ActionListener객체
		setTitle("재고 추가 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);	//컨텐트팬 패널로 변경

		
		name.setPreferredSize(new Dimension (250,30));
		nameField.setPreferredSize(new Dimension (120, 20));
		quant.setPreferredSize(new Dimension (250,30));
		quantField.setPreferredSize(new Dimension (120,20));
		b1.setPreferredSize(new Dimension (185,40));
		b2.setPreferredSize(new Dimension (185,40));
		nameField.setBackground(new Color (250,250,250));
		nameField.setBorder(new LineBorder (Color.BLACK));
		quantField.setBackground(new Color (250,250,250));
		quantField.setBorder(new LineBorder (Color.BLACK));
		
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
	
	// 버튼 클릭시 동작 기능 수행
	class MyActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			
			// 추가 버튼 클릭시
			if(b.getText().equals("추가")) {
				
				//텍스트 필드 입력값
				String enterName = nameField.getText();
				String enterQuant = quantField.getText();
				
				// 커넥터로 입력된 값 보내고 결과값 받음(true 리턴시)
				if(operator.connector.sendPlus(enterName, enterQuant)) {
					nameField.setText("");
					quantField.setText("");;
					dispose();
				}
			// 취소 버튼 클릭시	
			} else if(b.getText().equals("취소")) {
				nameField.setText("");
				quantField.setText("");;
				dispose();	
			}
		}
	}

}
