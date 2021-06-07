import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Server {
   ServerSocket serverSocket;
   DataBase dataBase;
//   ArrayList<Connection> clients = new ArrayList<Connection>();

   public static void main(String[] args) {
      Server server = new Server();
      server.dataBase = new DataBase();
      try {
         server.serverSocket = new ServerSocket(6080);
         System.out.println("[키오스크서버] 서버 생성");
         while(true) {
            Socket socket = server.serverSocket.accept();
            Connection c = new Connection(server,socket);
         //   server.clients.add(c);
            c.start();
            System.out.println("클라이언트 실행...");
         }
      } catch(SocketException e) {
         System.out.println("[키오스크서버] 소켓 관련 예외 발생...");
         System.out.println(e.getMessage());
      } catch(IOException e) {
         System.out.println("[키오스크서버] 입출력 예외 발생...");
         System.out.println(e.getMessage());
      }

   }

}
class Connection extends Thread {
   Socket socket;
   Server server;
   OutputStream os;
   InputStream is;
   DataOutputStream dos;
   DataInputStream dis;
   
   Connection(Server server, Socket socket){
      this.server = server;
      this.socket = socket;
   }
   
   public void run() {
      System.out.println("키오스크서버  -> " + this.socket.toString() + "에서 접속되었습니다. ");
   
      try {
         os = this.socket.getOutputStream();
         is = this.socket.getInputStream();
         dos = new DataOutputStream(os);
         dis = new DataInputStream(is);
         String message = null;
         
         while(true) {
            /*
            message = dis.readUTF();
            System.out.println(message);
            String str;
            //StringTokenizer stk = new StringTokenizer(message, "//");
            String[] a = message.split("//"); // -> plus//name//quant
            System.out.println("메뉴"+a[1]+"주문수량"+ a[2]);
            //DataOutputStream dataOutputStream = null;
            //dataOutputStream.writeUTF("plus_ok");
            str =  a[0];
            */
            message = dis.readUTF();
            System.out.println("서버로 넘어온 데이터: "+message);
            StringTokenizer stk = new StringTokenizer(message, "//");
            
            switch(stk.nextToken()) {
            case "PLUS" :
               /*
               DataOutputStream dataOutputStream = null;
               dataOutputStream.writeUTF("plus_ok");
               */
               System.out.println("case문 확인용: ");
               String pName= stk.nextToken();
               String pQuant = stk.nextToken();
               System.out.println(pName+pQuant);
               if(server.dataBase.plusFood(pName, pQuant)) {
                  dos.writeUTF("plus_ok");
                  System.out.println("plus_ok 확인");
               } else {
                  System.out.println("plus_ok 실패");
               }
            case "MINUS" :
               /*
               DataOutputStream dataOutputStream1 = null;
               dataOutputStream1.writeUTF("minus_ok");
               */
               
               String mName= stk.nextToken();
               String mQuant = stk.nextToken();
               if(server.dataBase.plusFood(mName, mQuant)) {
                  dos.writeUTF("minus_ok");
                  System.out.println("minus_ok 확인");
               } else {
                  System.out.println("minus_ok 실패");
               }
            }
            
            
         }
      } catch(Exception e) {
         System.out.println(e.getMessage());
         System.out.println("예외 발생");
      }
   }
}
