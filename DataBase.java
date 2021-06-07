import java.sql.*;
import java.sql.Connection;
import java.io.*;

public class DataBase {
	Connection con;
	Statement stmt;
	String url = "jdbc:mysql://localhost/team11?serverTimezone=Asia/Seoul";
	String root;	// root
	String passwd;	// �ڱ� ��й�ȣ
	
	DataBase(){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch(Exception e) {
			System.out.println("����̺� ���� ����");
		}
	}
	
	// [�մԿ� Ŭ���̾�Ʈ] �ֹ� ���� ���� ����
	boolean orderFood(String _s) {
		boolean flag = false;
		String name = _s;
		String minusStr = "UPDATE food SET quantity = quantity-1 WHERE name = '" + name + "'";
		System.out.println("DB >>>>> "+ name);
		
		try {
			con = DriverManager.getConnection(url,root,passwd);
			stmt = con.createStatement();	
			stmt.executeUpdate(minusStr);	
			System.out.println("DB >>>>  "+name+" �ֹ��Ͻ� ������ ������ �����Ǿ����ϴ�");
			flag = true;
			
		} catch(Exception e) {
			
		} finally {
			
			try {
				stmt.close();
				con.close();
				
			} catch(Exception e) {
				
			}
			
		}
		return flag;
		
	}
	// [�մԿ� Ŭ���̾�Ʈ] �ֹ� ���� �� �ջ� �ݾ�
	String amountPrice(String _s) {
		String name = _s;
		String sql = "SELECT price FROM food WHERE name ='"+name+"'";
		String price=null;
		
		try {
			con = DriverManager.getConnection(url,root,passwd);	
			stmt = con.createStatement();	
			ResultSet rs = stmt.executeQuery(sql);	
			
			while(rs.next()) {
				price = rs.getString("price");	
				System.out.println("DB >>>>> "+ price);
			}
			rs.close();
			
		} catch(Exception e) {
			
		} finally {
			
			try {
				
				stmt.close();
				con.close();
			}catch(Exception e) {
				
			}
		}
		return price;
		
	}
	
	// [�մԿ� Ŭ���̾�Ʈ] �մԿ��� ������ ���� ��ǰ ��� ��Ȳ
	String quant(String _q) {
		String name = _q;
		String quant = null;
		String sql = "SELECT quantity FROM food WHERE name = '"+name+"'";
		
		try {
			con = DriverManager.getConnection(url, root, passwd);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				quant = rs.getString("quantity");
				System.out.println("DB >>>> " + quant);
			}
		} catch(Exception e) {
			
		} finally {
			
			try {
				stmt.close();
				con.close();
			} catch(Exception e) {
				
			}
		} 
		return quant;
	}
	
	// [���ֿ� Ŭ���̾�Ʈ] ���� ��� �߰�
	boolean plusFood(String _n, String _q) {
		boolean flag = false;
		String quantity = _q;
		String name = _n;
		String plusStr = "UPDATE food SET quantity = quantity+" + quantity + " WHERE name = '" + name + "'";
		System.out.println("DB >>> " + name + " " + quantity);
		
		try {
			con = DriverManager.getConnection(url,root,passwd);
			stmt = con.createStatement();
			stmt.executeUpdate(plusStr);
			flag = true;
			System.out.println("DB >>>> "+ name +" �ش� ������ ������ " +quantity + " ��ŭ �߰��Ǿ����ϴ�");
		} catch(Exception e) {
			
		} finally {
			
			try {
				stmt.close();
				con.close();
			} catch(Exception e) {
				
			}
		}
		return flag;
	}
	// [���ֿ� Ŭ���̾�Ʈ] ���� ��� ����
	boolean minusFood(String _n, String _q) {
		boolean flag = false;
		String quantity = _q;
		String name = _n;
		String minusStr = "UPDATE food SET quantity = quantity-"+quantity+" WHERE name = '"+name+"'";
		System.out.println("DB >>>> " +name+" "+quantity);
		try {
			con = DriverManager.getConnection(url,root,passwd);
			stmt = con.createStatement();
			stmt.executeUpdate(minusStr);
			flag = true;
			System.out.println("DB >>>> "+name+" �ش� ������ ������ "+quantity+" ��ŭ �����Ǿ����ϴ�.");
		}catch(Exception e) {
			
		}finally{
			try {
				//�ݾ���
				stmt.close();
				con.close();
			}catch(Exception e) {
				
			}
		}
		return flag;
		
	}

}
