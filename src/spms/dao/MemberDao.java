package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import spms.util.DBConnectionPool;
import spms.vo.Member;

public class MemberDao {
	DBConnectionPool connPool;
	
	public void setDbConnectionPool (DBConnectionPool connPool) {
		this.connPool = connPool;
	}
	
	public List<Member> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = connPool.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
				"SELECT MNO, MNAME, EMAIL, CRE_DATE" +
				" FROM MEMBERS" +
				" ORDER BY MNO ASC");
			
			ArrayList<Member> members = new ArrayList<Member>();
			
			while (rs.next()) {
				members.add(new Member()
					.setNo(rs.getInt("MNO"))
					.setName(rs.getString("MNAME"))
					.setEmail(rs.getString("EMAIL"))
					.setCreatedDate(rs.getDate("CRE_DATE")));
			}
			return members;
		} catch(Exception e) {
			throw e;
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close(); } catch(Exception e) {}
			if (connection != null) connPool.returnConnection(connection);
		}
	}
	
	public int insert(Member member) throws Exception {
		/* 회원 등록 */
		Connection connection = null;
		PreparedStatement stmt = null;

		try {
			connection = connPool.getConnection();
			stmt = connection.prepareStatement(
					"INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
					+ " VALUES (?,?,?,NOW(),NOW())");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			if (connection != null) connPool.returnConnection(connection);
		}
	}
	
	public int delete(int no) throws Exception {
		/* 회원 삭제 */
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = connPool.getConnection();
			stmt = connection.createStatement();
					
			return stmt.executeUpdate(
					"DELETE FROM MEMBERS WHERE MNO = " + no);
		} catch (Exception e) {
			throw e;
		} finally {
			try { if (stmt != null) stmt.close();} catch(Exception e) {}
			if (connection != null) connPool.returnConnection(connection );
		}
	}
	
	public Member selectOne(int no) throws Exception {
		/* 회원 상세 정보 조회 */
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = connPool.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
				"SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS" + 
				" WHERE MNO=" + no);	
			rs.next();
			
			Member member = new Member()
					.setNo(rs.getInt("MNO"))
					.setName(rs.getString("MNAME"))
					.setEmail(rs.getString("EMAIL"))
					.setCreatedDate(rs.getDate("CRE_DATE"));
			
			return member;

		} catch (Exception e) {
			throw e;
			
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			if (connection != null) connPool.returnConnection(connection );
		}
	}
	
	public int update(Member member) throws Exception {
		/* 회원 정보 변경 */
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = connPool.getConnection();
			stmt = connection.prepareStatement(
					"UPDATE MEMBERS SET EMAIL=?, MNAME=?, MOD_DATE=now()"
					+ " WHERE MNO=?");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			if (connection != null) connPool.returnConnection(connection);
		}
	}
	
	public Member exist(String email, String password) throws Exception {
		/* 있으면  Member 객체 리턴, 없으면 null 리턴 */
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = connPool.getConnection();
			stmt = connection.prepareStatement(
					"SELECT MNAME, EMAIL FROM MEMBERS" +
					" WHERE EMAIL=? AND PWD=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Member member = new Member()
						.setEmail(rs.getString("EMAIL")).setName(rs.getString("MNAME"));
				return member;
			}
			else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			try { if (rs != null) rs.close();} catch (Exception e){}
			try { if (stmt != null) stmt.close();} catch (Exception e){}
			if (connection != null) connPool.returnConnection(connection);
		}
	}
	
}
