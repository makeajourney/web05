package spms.listeners;

import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import spms.dao.MemberDao;
import spms.util.DBConnectionPool;

@WebListener
public class ContextLoaderListener implements ServletContextListener{

	// Connection conn;
	DBConnectionPool connPool;
	
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 웹애플리케이션이 시작될 때 실행
		try {
			ServletContext sc = event.getServletContext();
			
			// Class.forName(sc.getInitParameter("driver"));
			// conn = DriverManager.getConnection(
			//		sc.getInitParameter("url"),
			//		sc.getInitParameter("username"),
			//		sc.getInitParameter("password"));
			
			connPool = new DBConnectionPool(
					sc.getInitParameter("driver"),
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			
			MemberDao memberDao = new MemberDao();
			// memberDao.setConnection(conn);
			memberDao.setDbConnectionPool(connPool);
			
			sc.setAttribute("memberDao", memberDao);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// 웹애플리케이션이 종료될 때 실행
		//try {
		//	conn.close();
		//} catch (Exception e) {}
		connPool.closeAll();
	}
}
