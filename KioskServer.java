import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class KioskServer {
	Socket mysc = null; 
	ServerSocket ss = null;
	ArrayList<Client> clients = new ArrayList<Client>();

	public static void main(String[] args) {
		KioskServer server = new KioskServer();
		try {
			server.ss = new ServerSocket(55555);
			System.out.println("Kiosk Server 생성");
			Socket socket = server.ss.accept();
			Client  c = new Client(socket);
			server.clients.add(c);
			c.start();

		}catch(SocketException e) {
			System.out.println("Kiosk Server 종료");
		}catch(IOException e) {
			System.out.println("Kiosk Server 예외 발생");
		}

	}

}

class Client extends Thread{
	Socket socket;
	Client(Socket _s) {
		this.socket = _s;	
	}
	public void run( ) {
		try {
			OutputStream out = socket.getOutputStream();
			DataOutputStream dout = new DataOutputStream(out);
		
			while(true) {			
				dout.writeUTF("KioskSystem 실행중");
				break;
			}
		}catch(Exception e) {
			System.out.println("예외 발생");
		}
	}
}
