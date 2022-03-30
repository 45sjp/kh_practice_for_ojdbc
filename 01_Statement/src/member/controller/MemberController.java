package member.controller;

import java.util.List;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberController {
	
	private MemberDao memberDao = new MemberDao();
	
	/**
	 * 사용자의 회원가입 요청을 처리하는 메소드
	 * 1. dao에 회원가입 요청 후 int 반환
	 * 2. menu에 int 반환
	 * @param newMember
	 * @return
	 */
	public int insertMember(Member newMember) {
		int result = memberDao.insertMember(newMember); // 동일한 이름의 insertMember메소드 생성
//		System.out.println("result@MemberController = " + result); // 중간확인용
		return result;
	}

	public List<Member> selectAll() {
		List<Member> list = memberDao.selectAll();
		return list;
	}
	
	public Member selectOne(String id) {
		Member member = memberDao.selectOne(id);
		return member;
	}

	public int deleteMember(Member member) {
		int result = memberDao.deleteMember(member);
		return result;
	}

	public List<Member> selectByName(String name) {
		List<Member> list = memberDao.selectByName(name);
		return list;
	}

	public int updateMember(Member member) {
		int result = memberDao.updateMember(member);
		return result;
	}
	
}
