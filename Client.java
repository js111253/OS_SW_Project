import java.awt.Color;

public class Client {

	DBLink base = null;
	FirstWindow w1 = null;
	MainWindow w2 = null;

	Color orange = new Color(255,127,39);
	public static void main(String[] args) {
		Client m = new Client();
		m.base = new DBLink(m);
		m.w1 = new FirstWindow(m);
		m.w2 =  new MainWindow(m);
		while(true) {
			for(int i = 0; i < 14; i++) {
				m.w1.tale[i].setText("" + m.w1.menun[i]);
				m.w1.talg[i].setText(m.w1.menug[i] + "¿ø");
			}
		}
		
	}

}
