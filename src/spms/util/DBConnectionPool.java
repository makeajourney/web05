package spms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DBConnectionPool {
	String url;
	String username;
	String password;
	
	// connection 객체 보관
	ArrayList<Connection> connList = new ArrayList<Connection>();
	
	public DBConnectionPool(String driver, String url,
			String username, String password) throws Exception {
		this.url = url;
		this.username = username;
		this.password = password;
		
		Class.forName(driver);
	}
	
	public Connection getConnection() throws Exception {
		if (connList.size() > 0) {
			Connection conn = connList.get(0);	// 커넥션 객체를 리스트에서 꺼냄
			if (conn.isValid(10)) {		// 유효성 검사를 한 다음
				return conn;			// 반환
			}
		}
				// 객체가 없을 때는 만들어서 줌.
		return DriverManager.getConnection(url, username, password);
	}
	
	// 돌려받은 객체를 리스트에 추가
	public void returnConnection(Connection conn) throws Exception {
		connList.add(conn);
	}
	
	public void closeAll() {
		for (Connection conn : connList) {
			try {conn.close();} catch (Exception e) {}
		}
	}
}
