import java.sql.*;
import java.sql.Connection;
import java.io.*;

public class DataBase {
	Connection con;
	Statement stmt;
	String url = "jdbc:mysql://localhost/team11?serverTimezone=Asia/Seoul";
	String root;	// root
	String passwd;	// 자기 비밀번호
	
	DataBase(){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch(Exception e) {
			System.out.println("드라이브 연결 오류");
		}
	}
	
	// [손님용 클라이언트] 주문 음식 수량 차감
	boolean orderFood(String _s) {
		boolean flag = false;
		String name = _s;
		String minusStr = "UPDATE food SET quantity = quantity-1 WHERE name = '" + name + "'";
		System.out.println("DB >>>>> "+ name);
		
		try {
			con = DriverManager.getConnection(url,root,passwd);
			stmt = con.createStatement();	
			stmt.executeUpdate(minusStr);	
			System.out.println("DB >>>>  "+name+" 주문하신 음식의 수량이 차감되었습니다");
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
	// [손님용 클라이언트] 주문 음식 총 합산 금액
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
	
	// [손님용 클라이언트] 손님에게 제공될 현재 상품 재고 현황
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
	
	// [업주용 클라이언트] 음식 재고 추가
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
			System.out.println("DB >>>> "+ name +" 해당 음식의 수량이 " +quantity + " 만큼 추가되었습니다");
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
	// [업주용 클라이언트] 음색 재고 차감
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
			System.out.println("DB >>>> "+name+" 해당 음식의 수량이 "+quantity+" 만큼 차감되었습니다.");
		}catch(Exception e) {
			
		}finally{
			try {
				//닫아줌
				stmt.close();
				con.close();
			}catch(Exception e) {
				
			}
		}
		return flag;
		
	}

}
