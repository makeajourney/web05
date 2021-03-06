package spms.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/auth/login")
public class LogInServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("viewUrl", "/auth/LogInForm.jsp");
		
//		RequestDispatcher rd = request.getRequestDispatcher("/auth/LogInForm.jsp");
//		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Member member = null;
		
		try {
			ServletContext sc = this.getServletContext();

			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			
			member = memberDao.exist(request.getParameter("email"), request.getParameter("password"));
			
			if (member != null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				
				request.setAttribute("viewUrl", "redirect:../member/list.do");
				
//				response.sendRedirect("../member/list");
			}
			else {
				request.setAttribute("viewUrl", "/auth/LogInFail.jsp");
				
//				RequestDispatcher rd = request.getRequestDispatcher("/auth/LogInFail.jsp");
//				rd.forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException(e);
		}
	}

}
