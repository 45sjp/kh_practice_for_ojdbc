package member.model.service;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import member.model.dao.MemberDao;
import member.model.vo.Member;
import member.model.vo.MemberDel;

/**
 * service클래스
 * 
 * ------------------------------------------------------- Service 시작
 * 1. 드라이버 클래스 등록
 * 2. Connection객체 생성(setAutoCommit(false))
 * ------------------------------------------------------- Dao 시작
 * 		3. PreparedStatement객체 생성(미완성 sql & 값대입)
 * 		4. 쿼리 실행 (및 ResultSet 처리:DQL)
 * 		5. 자원반납(pstmt, rset)
 * ------------------------------------------------------- Dao 끝
 * 6. 트랜잭션 처리(DML)
 * 7. 자원 반납(conn)
 * ------------------------------------------------------- Service 끝
 * 
 * DB 연결, Connection객체 생성, 트랜잭션 처리 부분을 Service가 담당 => 중요!
 * 쿼리 작성 관련만 Dao가 담당
 */
public class MemberService {
	
	private MemberDao memberDao = new MemberDao();
	
	/*
	 * 메뉴 1.
	 */
	public List<Member> findAllMember() {
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. dao 요청
		List<Member> list = memberDao.findAllMember(conn);
		
		// 3. 자원 반납
		close(conn);
		
		return list;
	}
	
	/*
	 * 메뉴 2.
	 */
	public Member findMemberById(String id) {
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. dao 요청
		Member member = memberDao.findMemberById(conn, id);
		
		// 3. 자원 반납
		close(conn);
		
		return member;
	}
	
	/*
	 * 메뉴 3.
	 */
	public List<Member> findMemberByName(String name) {
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. dao 요청
		List<Member> list = memberDao.findMemberByName(conn, name);
		
		// 3. 자원 반납
		close(conn);
		
		return list;
	}
	
	/*
	 * 메뉴 4.
	 */
	public int insertMember(Member member) {
		int result = 0;
		
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// try~catch절 단축키 : Alt+Shift+Z
		try {
			// 2. dao 요청
			result = memberDao.insertMember(conn, member);
			
			// 3. 트랜잭션 처리
			commit(conn);
			
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			// 4. 자원 반납
			close(conn);
		}
		return result;
	}
	
	/*
	 * 메뉴 5.
	 */
	public int updateMember(Object rawData, String id, Object updateData) {
		int result = 0;
		
		// 1. Connection 생성
		Connection conn = getConnection();
		
		try {
			// 2. dao 요청
			result = memberDao.updateMember(conn, rawData, id, updateData);
			
			// 3. 트랜잭션 처리
			commit(conn);
			
		} catch (Exception e) {
			rollback(conn);
			throw e; // cotroller에서 분기처리할 수 있도록 다시 던짐
		} finally {
			// 4. 자원 반납
			close(conn);
		}
		return result;
	}
	
	/*
	 * 메뉴 6.
	 */
	public int deleteMember(Member member) {
		int result = 0;
		
		// 1. Connection 생성
		Connection conn = getConnection();
		
		try {
			// 2. dao 요청
			result = memberDao.deleteMember(conn, member);
			
			// 3. 트랜잭션 처리
			commit(conn);
			
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			// 4. 자원 반납
			close(conn);
		}
		return result;
	}
	
	/*
	 * 메뉴 7.
	 */
	public List<MemberDel> findAllMemberDel() {
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. dao 요청
		List<MemberDel> delList = memberDao.findAllMemberDel(conn);
		
		// 3. 자원 반납
		close(conn);
		
		return delList;
	}

}
