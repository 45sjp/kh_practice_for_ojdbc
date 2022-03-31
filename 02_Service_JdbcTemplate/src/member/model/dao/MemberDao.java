package member.model.dao;

import static common.JdbcTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.model.vo.Member;

public class MemberDao {
	
	/*
	 * 메뉴 1.
	 */
	public List<Member> findAllMember(Connection conn) {
		String sql = "select * from member order by reg_date desc";
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			// 1. PreparedStatement객체 생성(미완성 sql 전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			
			// 2. 쿼리 실행
			rset = pstmt.executeQuery();
			
			// 3. ResultSet 처리 -> Member객체 전환 후 list에 추가
			while(rset.next()) {
				Member member = new Member();
				member.setId(rset.getString("id"));
				member.setName(rset.getString("name"));
				member.setGender(rset.getString("gender"));
				member.setBirthday(rset.getDate("birthday"));
				member.setEmail(rset.getString("email"));
				member.setAddress(rset.getString("address"));
				member.setRegDate(rset.getTimestamp("reg_date"));
				list.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 4. 자원 반납
		close(rset);
		close(pstmt);
		
		return list;
	}
	
	/*
	 * 메뉴 2.
	 */
	public Member findMemberById(Connection conn, String id) {
		String sql = "select * from member where id = ?";
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			// 1. PreparedStatement객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			// 2. 쿼리 실행
			rset = pstmt.executeQuery();
			
			// 3. ResultSet 처리 -> Member객체 변환
			while(rset.next()) {
				member = new Member(rset.getString("id"), rset.getString("name"),
						   			rset.getString("gender"), rset.getDate("birthday"),
						   			rset.getString("email"), rset.getString("address"),
						   			rset.getTimestamp("reg_date"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 4. 자원 반납
		close(rset);
		close(pstmt);
		
		return member;
	}
	
	/*
	 * 메뉴 3.
	 */
	public List<Member> findMemberByName(Connection conn, String name) {
		String sql = "select * from member where name like ?";
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			// 1. PreparedStatement객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%"); // where name like '%길동%'
			
			// 2. 쿼리 실행
			rset = pstmt.executeQuery();
			
			// 3. ResultSet 처리 -> Member객체 변환
			while(rset.next()) {
				Member member = new Member();
				member.setId(rset.getString("id"));
				member.setName(rset.getString("name"));
				member.setGender(rset.getString("gender"));
				member.setBirthday(rset.getDate("birthday"));
				member.setEmail(rset.getString("email"));
				member.setAddress(rset.getString("address"));
				member.setRegDate(rset.getTimestamp("reg_date"));
				list.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 4. 자원반납(pstmt, rset) - 역순!
		close(rset);
		close(pstmt);
		
		return list;
	}
	
	/*
	 * 메뉴 4.
	 */
	public int insertMember(Connection conn, Member member) {
		String sql = "insert into member "
				   + "values(?, ?, ?, ?, ?, ?, default)"; // 여러 행 작성 시 공백 놓치지 말 것
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			// 1. PreparedStatement 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getGender());
			pstmt.setDate(4, member.getBirthday());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getAddress());
			
			// 2. 쿼리 실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
//			e.printStackTrace(); // service쪽으로 예외가 던져지지 않는 상태! 예외처리를 이미 했기 때문
			// checked예외를 unchecked예외로 전환해서 던지기
			throw new RuntimeException(e); // 원래 발생한 예외를 감싸서(전환) 다시 던지기 -> service에서 트랜잭션 처리용
		} finally {
			// 3. 자원 반납(pstmt) - [주의] conn 반환하지마세요.(트랜잭션 처리 전입니다.)
			close(pstmt);
		}
		return result;
	}
	
	/*
	 * 메뉴 5.
	 */
	public int updateMember(Connection conn, String rawData, String id, String updateData) {
		String sql = "update member set " + rawData + " = ? where id = ?";
		// ex) update member set name = '테스트' where id = 'test'
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			// 1. PreparedStatement객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateData);
			pstmt.setString(2, id);
			
			// 2. 쿼리 실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			// 3. 자원 반납
			close(pstmt);
		}
		return result;
	}
	
	/*
	 * 메뉴 6.
	 */
	public int deleteMember(Connection conn, Member member) {
		String sql = "delete from member where id = ? and name = ?";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			// 1. PreparedStatement객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			
			// 2. 쿼리 실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// 3. 자원 반납
			close(pstmt);
		}
		return result;
	}

}
