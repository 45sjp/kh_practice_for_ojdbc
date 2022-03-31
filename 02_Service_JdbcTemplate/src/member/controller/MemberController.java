package member.controller;

import java.util.List;

import member.model.service.MemberService;
import member.model.vo.Member;

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
		List<Member> list = memberService.findAllMember();
		return list;
	}
	
	/**
	 * 2. service에 id로 회원정보찾기 요청
	 * @param id
	 * @return
	 */
	public Member finMemberById(String id) {
		Member member = memberService.findMemberById(id);
		return member;
	}
	
	/**
	 * 3. service에 이름으로 회원정보찾기 요청
	 * @param name
	 * @return
	 */
	public List<Member> findMemberByName(String name) {
		List<Member> list = memberService.findMemberByName(name);
		return list;
	}
	
	/**
	 * 4. service에 신규회원추가 요청
	 * @param member
	 * @return
	 */
	public int insertMember(Member member) {
		int result = memberService.insertMember(member);
		return result;
	}
	
	/**
	 * 5. service에 회원정보변경 요청
	 * @param string
	 * @param id
	 * @param updateData
	 * @return
	 */
	public int updateMember(String rawData, String id, String updateData) {
		int result = memberService.updateMember(rawData, id, updateData);
		return result;
	}

	/**
	 * 6. service에 회원정보삭제 요청
	 * @param member
	 * @return
	 */
	public int deleteMember(Member member) {
		int result = memberService.deleteMember(member);
		return result;
	}	

}
