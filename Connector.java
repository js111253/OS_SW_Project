import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

//[���ֿ�] ������ ��� Ŀ����
public class Connector {
   //���� ��� �� ����� ��Ʈ��
   Socket socket;
   OutputStream outputStream;
   InputStream inputStream;
   DataOutputStream dataOutputStream;
   DataInputStream dataInputStream;
   Operator operator;
   
   // ��¥ ��ȯ(�ֹ� �ð� ��¿�)
   Calendar cal = Calendar.getInstance();
   SimpleDateFormat simpledateFormat = new SimpleDateFormat("yyyy / MM / dd / HH:mm:ss");
   String date = simpledateFormat.format(cal.getTime());
   
   Connector(Operator _operator){
      operator = _operator;
      try {
         // ���� ����
         socket = new Socket("localhost", 6080);
         System.out.println("Ŭ���̾�Ʈ > �������� ���� ����");
         outputStream = this.socket.getOutputStream();
         inputStream = this.socket.getInputStream();
         dataOutputStream = new DataOutputStream(outputStream);
         dataInputStream = new DataInputStream(inputStream);
         MessageListener msgListener = new MessageListener(socket,operator);
         msgListener.start();
         // �������� �Ѿ�� �ֹ� ���� �� �ֹ� �ð� ���
         /*
         String msg = null;
         msg = dataInputStream.readUTF();
         operator.ownerFrame.textArea.append(msg+"�� �ֹ��Ǿ����ϴ�... �ֹ��ð�: "+date+"\n");
         operator.ownerFrame.textArea.setCaretPosition(operator.ownerFrame.textArea.getText().length());
         */
         /*
         String msg = null;
         while(true) {
            msg = dataInputStream.readUTF();
            operator.ownerFrame.textArea.append(msg + "�� �ֹ��Ǿ����ϴ�.... �ֹ��ð�: "+date+"\n");
            operator.ownerFrame.textArea.setCaretPosition(operator.ownerFrame.textArea.getText().length());
         }
         */
      } catch(Exception e) {
         
      }
   }
   
   //��� �߰� ������ ������ ������ ���� �޼ҵ�(�Ҹ��� ����)
   boolean sendPlus(String _name, String _quant) {
      boolean flag = false;
      // name(���ĸ�), quant(����), msg(�Ѿ�� �޼���)
      String name = _name;
      String quant = _quant;
      String msg = null;
      
      try {
         
         dataOutputStream.writeUTF("PLUS"+"//"+name+"//"+quant); //�������� StringTokenizer�� �̿��� ���ǹ��� ����("PLUS") ���ĸ�� �������� �̾��ؾ���!!!!
         msg=dataInputStream.readUTF();   // ���� �������� ����� ���� �������("plus_ok")���ڿ��� �Ѿ���� ���ּ���~~
         System.out.println(msg);
   //      Socket socket = null;
   //      OutputStream os = socket.getOutputStream();

         // ��� �߰� �� �Ѿ�� �޼����� plus_ok�� �� true ����
         if(msg.equals("plus_ok")) {
            System.out.println("Ŭ���̾�Ʈ Ŀ���� plus_ok Ȯ��");
            flag = true;
         // plus_ok �Ѿ������ �ʴ´ٸ� false ����
         } else {
            flag = false;
         }
      } catch(Exception e) {
         
      }
      return flag;
   }
   
   //��� ���� ������ ������ ������ ���� �޼ҵ�(�Ҹ��� ����)
   boolean sendMinus(String _name, String _quant) {
      boolean flag = false;
      // name(���ĸ�), quant(����), msg(�Ѿ�� �޼���)
      String name = _name;
      String quant = _quant;
      String msg = null;
      
      try {
         
         dataOutputStream.writeUTF("MINUS"+"//"+name+"//"+quant); // �������� StringTokenizer�� �̿��� ���ǹ��� ����("MINUS") ���ĸ�� �������� �̾Ƴ�����
         msg = dataInputStream.readUTF();   // ���� �������� ����� ���� ������� ("minus_ok")���ڿ��� �Ѿ���� ���ּ���~~
         
         // ��� ���� �� �Ѿ�� �޼����� minus_ok�� �� true ����
         if(msg.equals("minus_ok")) {
            flag = true;
         // minus_ok�� �Ѿ���� �ʴ´ٸ� false ����
         } else {
            flag = false;
         }
      } catch(Exception e){
         
      }
      return flag;
   }

}
class MessageListener extends Thread{
   //������� ����¿�
   Socket socket;
   InputStream inStream;
   DataInputStream dataInStream;
   Operator mainOperator;
   //�ֹ� �ð��� �˱�����
   Calendar cal = Calendar.getInstance();
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd / HH:mm:ss");
   String date = sdf.format(cal.getTime());
   
   //������
   MessageListener(Socket _s, Operator _o){
      socket =_s;
      mainOperator = _o;
   }
   //������ ���� �޼ҵ�
   public void run() {
      try {
         //�ش�������� ��Ʈ�� ����
         inStream = this.socket.getInputStream();
         dataInStream = new DataInputStream(inStream);
         boolean flag=true;
         String msg=null;
            while(flag) {
               msg = dataInStream.readUTF();   //�޾ƿ� �ֹ� ����
               mainOperator.ownerFrame.textArea.append(msg+"�� �ֹ��Ǿ����ϴ�. �ֹ��ð� >> "+date+"\n");
               mainOperator.ownerFrame.textArea.setCaretPosition(mainOperator.ownerFrame.textArea.getText().length());
               
            }
         
         
      }catch(Exception e) {
         
      }
   }
   
}