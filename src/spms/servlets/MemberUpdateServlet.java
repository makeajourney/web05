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

import spms.dao.MemberDao;
import spms.vo.Member;


@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ServletContext sc = this.getServletContext();
			
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			
			request.setAttribute("member", 
					memberDao.selectOne(
							Integer.parseInt(request.getParameter("no"))
							));
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberUpdateForm.jsp");
			rd.forward(request, response);
			/*
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원정보</title></head>");
			out.println("<body><h1>회원정보</h1>");
			out.println("<form action='update' method='post'>");
			out.println("번호: <input type='text' name='no' value='" +
				request.getParameter("no") + "' readonly><br>");
			out.println("이름: <input type='text' name='name'" +
				" value='" + rs.getString("MNAME")  + "'><br>");
			out.println("이메일: <input type='text' name='email'" +
				" value='" + rs.getString("EMAIL")  + "'><br>");
			out.println("가입일: " + rs.getDate("CRE_DATE") + "<br>");
			out.println("<input type='submit' value='저장'>");
			out.println("<input type='button' value='삭제' "
					+ "onclick='location.href=\"delete?no=" + 
					request.getParameter("no") + "\";'>");
			out.println("<input type='button' value='취소'" + 
				" onclick='location.href=\"list\"'>");
			out.println("</form>");
			out.println("</body></html>");
			*/
			
		} catch (Exception e) {
			throw new ServletException(e);
		} 
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// CharacterEncodingFilter에서 처리
		//request.setCharacterEncoding("UTF-8");

		try {
			ServletContext sc = this.getServletContext();
			
			MemberDao memberDao = (MemberDao) sc.getAttribute("memberDao");
			
			memberDao.update(new Member()
					.setEmail(request.getParameter("email"))
					.setName(request.getParameter("name"))
					.setNo(Integer.parseInt(request.getParameter("no"))));
			
			response.sendRedirect("list");
			
		} catch (Exception e) {
			// throw new ServletException(e);
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
}
