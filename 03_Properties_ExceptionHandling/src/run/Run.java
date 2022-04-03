package run;

import member.view.MemberMenu;

public class Run {
	
	public static void main(String[] args) {
		/*
		 * 연쇄적으로 객체가 생성됨
		 * MemberMenu객체 생성될 때 MemberController객체 생성됨
		 * -> MemberController객체 생성될 때 MemberService객체 생성됨
		 * -> MemberService객체 생성될 때 MemberDao객체 생성됨
		 * -> MemberDao객체 생성되면서 properties 파일 로드됨
		 */
		new MemberMenu().mainMenu();
		System.out.println("----- 이용해주셔서 감사합니다. 🤩 -----");
	}
}
