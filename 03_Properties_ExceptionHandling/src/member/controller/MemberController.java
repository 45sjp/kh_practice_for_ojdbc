package member.controller;

import java.util.List;

import member.model.service.MemberService;
import member.model.vo.Member;
import member.model.vo.MemberDel;

/**
 * controller클래스
 * 	- service단 업무요청
 */
public class MemberController {
	
	private MemberService memberService = new MemberService();
	
	/**
	 * 1. service에 전체회원정보목록 요청
	 * @param member
	 * @return
	 */
	public List<Member> findAllMember() {
		List<Member> list = null;
		try {
			list = memberService.findAllMember();
		} catch (Exception e) {
			// 1. 오류 로그 출력(관리자용) : 오류 로그 파일을 따로 만들어줄 수도 있음!
//			e.printStackTrace();
			// 2. 사용자 통보
			System.err.println("[" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의해주십시오.");
		}
		return list;
	}
	
	/**
	 * 2. service에 id로 회원정보찾기 요청
	 * @param id
	 * @return
	 */
	public Member findMemberById(String id) {
		Member member = null;
		try {
			member = memberService.findMemberById(id);
		} catch (Exception e) {
			System.err.println("[" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의해주십시오.");
		}
		return member;
	}
	
	/**
	 * 3. service에 이름으로 회원정보찾기 요청
	 * @param name
	 * @return
	 */
	public List<Member> findMemberByName(String name) {
		List<Member> list = null;
		try {
			list = memberService.findMemberByName(name);
		} catch (Exception e) {
			System.err.println("[" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의해주십시오.");
		}
		return list;
	}
	
	/**
	 * 4. service에 신규회원추가 요청
	 * @param member
	 * @return
	 */
	public int insertMember(Member member) {
		int result = 0;
		try {
			result = memberService.insertMember(member);
		} catch (Exception e) {
			System.err.println("[" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의해주십시오.");
		}
		return result;
	}
	
	/**
	 * 5. service에 회원정보변경 요청
	 * @param string
	 * @param id
	 * @param updateData
	 * @return
	 */
	public int updateMember(Object rawData, String id, Object updateData) {
		int result = 0;
		try {
			result = memberService.updateMember(rawData, id, updateData);
		} catch (Exception e) {
			// 1. 오류 로그 출력
//			e.printStackTrace();
			// 2. 사용자 통보
			System.err.println("[" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의해주십시오.");
		}
		return result;
	}

	/**
	 * 6. service에 회원정보삭제 요청
	 * @param member
	 * @return
	 */
	public int deleteMember(Member member) {
		int result = 0;
		try {
			result = memberService.deleteMember(member);
		} catch (Exception e) {
			System.err.println("[" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의해주십시오.");
		}
		return result;
	}
	
	/**
	 * 7. service에 탈퇴회원정보목록 요청
	 * @return
	 */
	public List<MemberDel> findAllMemberDel() {
		List<MemberDel> delList = null;
		try {
			delList = memberService.findAllMemberDel();
		} catch (Exception e) {
			System.err.println("[" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의해주십시오.");
		}
		return delList;
	}	

}
