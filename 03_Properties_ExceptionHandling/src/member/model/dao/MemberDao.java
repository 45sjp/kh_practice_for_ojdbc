package member.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.exception.MemberException;
import member.model.vo.Member;
import member.model.vo.MemberDel;

public class MemberDao {
	
	Properties prop = new Properties();
	
	public MemberDao() {
		// member-query.properties의 내용 불러오기
		try {
			prop.load(new FileReader("resources/member-query.properties"));
//			System.out.println("> member-query.properties 로드 완료!"); // 확인용
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 메뉴 1.
	 */
	public List<Member> findAllMember(Connection conn) {
		String sql = prop.getProperty("findAllMember");
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
			throw new MemberException("전체 회원 목록 조회 오류", e);
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
		String sql = prop.getProperty("findMemberById");
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
			throw new MemberException("아이디로 회원 조회 오류", e);
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
		String sql = prop.getProperty("findMemberByName");
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
			throw new MemberException("이름으로 회원 조회 오류", e);
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
		String sql = prop.getProperty("insertMember");
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
			throw new MemberException("회원 가입 오류", e);
		} finally {
			// 3. 자원 반납(pstmt) - [주의] conn 반환하지마세요.(트랜잭션 처리 전입니다.)
			close(pstmt);
		}
		return result;
	}
	
	/*
	 * 메뉴 5.
	 */
	public int updateMember(Connection conn, Object rawData, String id, Object updateData) {
		String sql = prop.getProperty("updateMember");
		sql = sql.replace("#", (String)rawData);
		// ex) update member set name = '테스트' where id = 'test'
		
		// 커스텀 예외 클래스 생성해서 예외 테스트
//		 String sql = "update member set " + rawData + " = ? where iddddd = ?";
		// member.model.exception.MemberException: updateMember 예외
		// java.sql.SQLSyntaxErrorException: ORA-00904: "IDDDDD": 부적합한 식별자
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			// 1. PreparedStatement객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, updateData);
			pstmt.setString(2, id);
			
			// 2. 쿼리 실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("회원 정보 변경 오류", e); // RuntimeException이며 업무처리 예외로 만들어줌
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
		String sql = prop.getProperty("deleteMember");
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
			throw new MemberException("회원 탈퇴 오류", e);
		} finally {
			// 3. 자원 반납
			close(pstmt);
		}
		return result;
	}
	
	/*
	 * 메뉴 7.
	 */
	public List<MemberDel> findAllMemberDel(Connection conn) {
		String sql = prop.getProperty("findAllMemberDel");
		List<MemberDel> delList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			// 1. PreparedStatement객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 2. 쿼리 실행
			rset = pstmt.executeQuery();
			
			// 3. ResultSet 처리 -> Member객체 전환 후 list에 추가
			while(rset.next()) {
				MemberDel memberDel = new MemberDel();
				memberDel.setId(rset.getString("id"));
				memberDel.setName(rset.getString("name"));
				memberDel.setGender(rset.getString("gender"));
				memberDel.setBirthday(rset.getDate("birthday"));
				memberDel.setEmail(rset.getString("email"));
				memberDel.setAddress(rset.getString("address"));
				memberDel.setRegDate(rset.getTimestamp("reg_date"));
				memberDel.setDelDate(rset.getDate("del_date"));
				delList.add(memberDel);
			}
			
		} catch (SQLException e) {
			throw new MemberException("탈퇴 회원 정보 조회 오류", e);
		}
		
		// 4. 자원 반납
		close(rset);
		close(pstmt);
		
		return delList;
	}

}
