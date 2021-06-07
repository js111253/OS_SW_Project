import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

//[업주용] 서버와 통신 커넥터
public class Connector {
   //소켓 통신 및 입출력 스트림
   Socket socket;
   OutputStream outputStream;
   InputStream inputStream;
   DataOutputStream dataOutputStream;
   DataInputStream dataInputStream;
   Operator operator;
   
   // 날짜 변환(주문 시간 출력용)
   Calendar cal = Calendar.getInstance();
   SimpleDateFormat simpledateFormat = new SimpleDateFormat("yyyy / MM / dd / HH:mm:ss");
   String date = simpledateFormat.format(cal.getTime());
   
   Connector(Operator _operator){
      operator = _operator;
      try {
         // 소켓 생성
         socket = new Socket("localhost", 6080);
         System.out.println("클라이언트 > 서버소켓 연결 성공");
         outputStream = this.socket.getOutputStream();
         inputStream = this.socket.getInputStream();
         dataOutputStream = new DataOutputStream(outputStream);
         dataInputStream = new DataInputStream(inputStream);
         MessageListener msgListener = new MessageListener(socket,operator);
         msgListener.start();
         // 서버에서 넘어온 주문 내역 및 주문 시간 출력
         /*
         String msg = null;
         msg = dataInputStream.readUTF();
         operator.ownerFrame.textArea.append(msg+"가 주문되었습니다... 주문시간: "+date+"\n");
         operator.ownerFrame.textArea.setCaretPosition(operator.ownerFrame.textArea.getText().length());
         */
         /*
         String msg = null;
         while(true) {
            msg = dataInputStream.readUTF();
            operator.ownerFrame.textArea.append(msg + "가 주문되었습니다.... 주문시간: "+date+"\n");
            operator.ownerFrame.textArea.setCaretPosition(operator.ownerFrame.textArea.getText().length());
         }
         */
      } catch(Exception e) {
         
      }
   }
   
   //재고 추가 음식을 서버로 보내기 위한 메소드(불리언 형태)
   boolean sendPlus(String _name, String _quant) {
      boolean flag = false;
      // name(음식명), quant(수량), msg(넘어온 메세지)
      String name = _name;
      String quant = _quant;
      String msg = null;
      
      try {
         
         dataOutputStream.writeUTF("PLUS"+"//"+name+"//"+quant); //서버에서 StringTokenizer를 이용해 조건문을 통해("PLUS") 음식명과 수량값을 뽑아해야함!!!!
         msg=dataInputStream.readUTF();   // 위에 서버에서 진행된 이후 결과값을("plus_ok")문자열로 넘어오게 해주세요~~
         System.out.println(msg);
   //      Socket socket = null;
   //      OutputStream os = socket.getOutputStream();

         // 재고 추가 후 넘어온 메세지가 plus_ok일 때 true 리턴
         if(msg.equals("plus_ok")) {
            System.out.println("클라이언트 커넥터 plus_ok 확인");
            flag = true;
         // plus_ok 넘어오지가 않는다면 false 리턴
         } else {
            flag = false;
         }
      } catch(Exception e) {
         
      }
      return flag;
   }
   
   //재고 차감 음식을 서버로 보내기 위한 메소드(불리언 형태)
   boolean sendMinus(String _name, String _quant) {
      boolean flag = false;
      // name(음식명), quant(수량), msg(넘어온 메세지)
      String name = _name;
      String quant = _quant;
      String msg = null;
      
      try {
         
         dataOutputStream.writeUTF("MINUS"+"//"+name+"//"+quant); // 서버에서 StringTokenizer를 이용해 조건문을 통해("MINUS") 음식명과 수량값을 뽑아내야함
         msg = dataInputStream.readUTF();   // 위에 서버에서 진행된 이후 결과값을 ("minus_ok")문자열로 넘어오게 해주세요~~
         
         // 재고 차감 후 넘어온 메세지가 minus_ok일 때 true 리턴
         if(msg.equals("minus_ok")) {
            flag = true;
         // minus_ok가 넘어오지 않는다면 false 리턴
         } else {
            flag = false;
         }
      } catch(Exception e){
         
      }
      return flag;
   }

}
class MessageListener extends Thread{
   //소켓통신 입출력용
   Socket socket;
   InputStream inStream;
   DataInputStream dataInStream;
   Operator mainOperator;
   //주문 시간을 알기위함
   Calendar cal = Calendar.getInstance();
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd / HH:mm:ss");
   String date = sdf.format(cal.getTime());
   
   //생성자
   MessageListener(Socket _s, Operator _o){
      socket =_s;
      mainOperator = _o;
   }
   //스레드 실행 메소드
   public void run() {
      try {
         //해당소켓으로 스트림 생성
         inStream = this.socket.getInputStream();
         dataInStream = new DataInputStream(inStream);
         boolean flag=true;
         String msg=null;
            while(flag) {
               msg = dataInStream.readUTF();   //받아온 주문 음식
               mainOperator.ownerFrame.textArea.append(msg+"가 주문되었습니다. 주문시간 >> "+date+"\n");
               mainOperator.ownerFrame.textArea.setCaretPosition(mainOperator.ownerFrame.textArea.getText().length());
               
            }
         
         
      }catch(Exception e) {
         
      }
   }
   
}