package member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import member.model.vo.Member;

public class MemberDao {
	
	String driverClass = "";
	String url = "";
	String user = "";
	String password = "";
	
	/**
	 * db에 접속, 쿼리를 실행하는 메소드
	 * @param newMember
	 * @return
	 */
	public int insertMember(Member newMember) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into member values(?, ?, ?, ?, ?, ?, default)"; // 미완성 sql문
		
		try {
			// 1. driver class 등록
			Class.forName(driverClass);
			
			// 2. Connection객체 생성(SetAutoCommit(false))
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			
			// 3. PreparedStatement객체 생성(미완성 sql 전달 & 값대입)
			// ?의 값을 데이터타입별로 세팅해줌
			// ?의 개수가 다를 경우 에러 발생함
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newMember.getId());
			pstmt.setString(2, newMember.getName());
			pstmt.setString(3, newMember.getGender());
			pstmt.setDate(4, newMember.getBirthday());
			pstmt.setString(5, newMember.getEmail());
			pstmt.setString(6, newMember.getAddress());
			
			// 4. PreparedStatement 실행 및 int(처리 행수) 반환
			result = pstmt.executeUpdate(); // insert, update, delete 상관 없음. DML은 모두 executeUpdate()!
			
			// 5.1. 트랜잭션 처리
			conn.commit();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			// 5.2. 트랜잭션 처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			// 6. 자원 반납
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result; // return 0하지 않도록 주의!
	}
	
	/**
	 * member테이블의 모든 행을 리턴하는 메소드
	 * @return
	 */
	public List<Member> selectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from member order by reg_date desc";
		List<Member> list = new ArrayList<>();
		
		try {
			// 1. driver class 등록
			Class.forName(driverClass);
			
			// 2. Connection객체 생성
			conn = DriverManager.getConnection(url, user, password);
			
			// 3. PreparedStatement객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			
			// 4. PreparedStatement 실행 및 ResultSet 반환
			rset = pstmt.executeQuery();
			
			// 5. Result 한 행씩 fetch, Member객체 전환 후 list에 추가
			// 한 행씩 접근해 처리
			while(rset.next()) {
				String id = rset.getString("id");
				String name = rset.getString("name");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				String address = rset.getString("address");
				Timestamp regDate = rset.getTimestamp("reg_date");
				Member member = new Member(id, name, gender, birthday, email, address, regDate);
				list.add(member);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public Member selectOne(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from member where id = ?";
		Member member = null;
		
		try {
			// 1. driver class 등록
			Class.forName(driverClass);
			
			// 2. Connection객체 생성
			conn = DriverManager.getConnection(url, user, password);
			
			// 3. PreparedStatement객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			// 4. PreparedStatement 실행 및 ResultSet 반환
			rset = pstmt.executeQuery();
			
			// 5. ResultSet 한행씩 fetch. Member객체 전환
			while(rset.next()) {
				String name = rset.getString("name");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				String address = rset.getString("address");
				Timestamp regDate = rset.getTimestamp("reg_date");
				member = new Member(id, name, gender, birthday, email, address, regDate);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 반납
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}

	public int deleteMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from member where id = ? and name = ?";
		int result = 0;
		
		try {
			// 1. driver class 등록
			Class.forName(driverClass);
			
			// 2. Connection객체 생성(setAutoCommit(false))
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			
			// 3. PreparedStatement객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			
			// 4. Statement실행 및 int(처리행수) 반환
			result = pstmt.executeUpdate();
			
			// 5.1. 트랜잭션 처리 commit
			if(result > 0) {
				conn.commit();
			}
		} catch (Exception e) {
			// 5.2. 트랜잭션 처리 rollback
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// 6. 자원 반납
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}

	public List<Member> selectByName(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from member where name like ?";
		List<Member> list = new ArrayList<>();
		
		try {
			// 1. driver class 등록
			Class.forName(driverClass);
			
			// 2. Connection객체 생성
			conn = DriverManager.getConnection(url, user, password);
			
			// 3. PreparedStatement객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			
			// 4. PreparedStatement 실행 및 ResultSet 반환
			rset = pstmt.executeQuery();
			
			// 5. ResultSet 한 행씩 fetch. Member객체 전환
			while(rset.next()) {
				String id = rset.getString("id");
				name = rset.getString("name");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				String address = rset.getString("address");
				Timestamp regDate = rset.getTimestamp("reg_date");
				Member member = new Member(id, name, gender, birthday, email, address, regDate);
				list.add(member);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6. 자원 반납
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public int updateMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update member set name = ?, birthday = ?, email = ? , address = ? where id = ?";
		int result = 0;
		
		try {
			// 1. driver class 등록
			Class.forName(driverClass);
			
			// 2. Connection객체 생성(setAutoCommit(false))
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			
			// 3. PreparedStatement객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setDate(2, member.getBirthday());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getId());
			
			// 4. PreparedStatement 실행 및 int(처리 행수) 반환
			result = pstmt.executeUpdate();
			
			// 5.1. 트랜잭션 처리 commit
			if(result > 0) {
				conn.commit();
			}
		} catch (Exception e) {
			// 5.2. 트랜잭션 처리 rollback
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// 6. 자원 반납
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
