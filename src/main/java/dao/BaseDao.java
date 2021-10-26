package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {
	private static Connection conn;
	
	static {
		String sql="jdbc:mysql://localhost:3306/web?serverTimezone=UTC&characterEncoding=utf-8&useUnicode=true";
		String user = "root";
		String password ="12345678";
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(sql, user, password) ;
			
		}catch(Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public Connection getConnection() {
		return conn;
	}

}
