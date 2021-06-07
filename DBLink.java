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
	Client jin = null;
	
	
	DBLink(Client key){
		jin = key;
		try{
			socket = new Socket("localhost", 6080);
			outStream = this.socket.getOutputStream();
			dataOutStream = new DataOutputStream(outStream);
			inStream = this.socket.getInputStream();
			dataInStream = new DataInputStream(inStream);
			ml = new ML(socket, inStream, dataInStream, key);
			ml.start();
			}catch(Exception e) {
				
			}
	}
	
	void save() {
		String str = "";

		try {
			dataOutStream.writeUTF(str);

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
				
				
			}
		}catch(Exception e) {
		}
		}
}