package member.view;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import member.controller.MemberController;
import member.model.vo.Member;

/**
 * view단 클래스
 * 	- 메뉴노출, 사용자입력값 처리, 결과값 출력
 */
public class MemberMenu {
	
	private Scanner sc = new Scanner(System.in);
	private MemberController memberController = new MemberController();
	
	public void mainMenu() {
		String menu = "===== 회원관리 =====\n"
	                + "1. 회원 전체 조회\n"
	                + "2. 회원 아이디 조회\n"
	                + "3. 회원 이름 검색\n"
	                + "4. 회원 가입\n"
	                + "5. 회원 정보 변경\n"
	                + "6. 회원 탈퇴\n"
	                + "0. 프로그램 종료\n"
	                + "------------------\n"
	                + "선택 : ";
		
		while(true) {
			System.out.print(menu);
			String choice = sc.next();
			
			Member member = null;
			int result = 0;
			String id = null;
			String name = null;
			List<Member> list = null;
			
			switch (choice) {
			case "1":
				list = memberController.findAllMember();
				printMemberList(list);
				break;
			case "2":
				id = inputMemberId();
				member = memberController.finMemberById(id);
				printMember(member);
				break;
			case "3":
				name = inputMemberName();
				list = memberController.findMemberByName(name);
				printMemberList(list);
				break;
			case "4":
				member = inputMember();
				result = memberController.insertMember(member);
				printResulting(result, "회원 가입 성공!", "회원 가입 실패!");
				break;
			case "5":
				UpdateMenu();
				break;
			case "6":
				member = deleteMember(id, name);
				
				if(member == null) {
					System.out.println("> 조회된 회원이 없습니다.");
				}
				
				result = memberController.deleteMember(member);
				printResulting(result , "회원 탈퇴 완료!", "회원 탈퇴 취소!");
				break;
			case "0": return;
			default:
				System.out.println("> 잘못 입력하셨습니다.");
			}
		}
	}

	/**
	 * 2. 조회할 아이디 입력 메소드
	 * @return
	 */
	private String inputMemberId() {
		System.out.println("> 조회할 아이디를 입력하세요.");
		System.out.print("아이디 : ");
		return sc.next();
	}
	
	/**
	 * 회원 1명 출력 메소드
	 * @param member
	 */
	private void printMember(Member member) {
		if(member == null) {
			System.out.println("> 조회된 회원이 없습니다.");
		}
		else {
			System.out.println("> 조회 결과");
			System.out.println("=====================================================");
			System.out.printf("id : %s%n", member.getId());
			System.out.printf("name : %s%n", member.getName());
			System.out.printf("gender : %s%n", member.getGender());
			System.out.printf("birthday : %s%n", member.getBirthday());
			System.out.printf("email : %s%n", member.getEmail());
			System.out.printf("address : %s%n", member.getAddress());
			System.out.printf("regDate : %s%n", member.getRegDate());
			System.out.println("-----------------------------------------------------");
			System.out.println();
		}
	}
	
	/**
	 * 3. 조회할 회원명 입력 메소드
	 * @return
	 */
	private String inputMemberName() {
		System.out.println("> 조회할 이름을 입력하세요.");
		System.out.print("이름 : ");
		return sc.next();
	}
	
	/**
     * 여러행 조회결과 출력 메소드
     * (list가 null인 경우 없음)
     * - 0행 : empty list가 리턴됨
     * - n행 : 1행 이상의 not empty list가 리턴됨
     * @param list
     */
    public void printMemberList(List<Member> list) {
        if(list == null || list.isEmpty()) {
            System.out.println("> 조회된 결과가 없습니다.");
        }
        else {
            System.out.println("> 조회 결과");
            System.out.println("=====================================================");
            System.out.printf("%15s%15s%15s%15s%15s%15s%15s%n", 
                                "id", "name", "gender", "birthday", "email", "address", "regDate");
            System.out.println("-----------------------------------------------------");
            for(Member member : list) {
                System.out.printf("%15s%15s%15s%15s%15s%15s%15s%n", 
                        member.getId(), 
                        member.getName(),
                        member.getGender(),
                        member.getBirthday(),
                        member.getEmail(),
                        member.getAddress(),
                        member.getRegDate());
            }
            System.out.println("-----------------------------------------------------");
            System.out.println();
        }
    }

	/**
	 * 4. 신규회원정보를 입력 받는 메소드
	 * @return
	 */
	private Member inputMember() {
		Member member = new Member();
		
		System.out.println("> 신규 회원 정보를 입력하세요.");
		System.out.print("아이디 : ");
		member.setId(sc.next());
		System.out.print("이름 : ");
		member.setName(sc.next());
		System.out.print("성별(M/F) : ");
		member.setGender(String.valueOf(sc.next().toUpperCase().charAt(0)));
		System.out.print("생일(예:19990314) : ");
		String _birthday = sc.next();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date birthday = null;
		try {
			long millis = sdf.parse(_birthday).getTime();
			birthday = new Date(millis);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		member.setBirthday(birthday);
		System.out.print("이메일 : ");
		member.setEmail(sc.next());
		sc.nextLine();
		System.out.print("주소 : ");
		member.setAddress(sc.nextLine());
		return member;
	}

	/**
	 * DML 처리 결과를 출력하는 메소드
	 * @param result
	 * @param successMsg
	 * @param failureMsg
	 */
	private void printResulting(int result, String successMsg, String failureMsg) {
		if(result > 0)
			System.out.println("> " + successMsg);
		else
			System.out.println("> " + failureMsg);
	}
	
	/**
	 * 5. 회원정보변경 메뉴 메소드
	 */
	private void UpdateMenu() {
		String updateMenu = "****** 회원 정보 변경 메뉴 ******\n"
						  + "1. 이름 변경\n"
						  + "2. 생일 변경\n"
						  + "3. 이메일 변경\n"
						  + "4. 주소 변경\n"
						  + "9. 메인메뉴 돌아가기\n"
						  + "-----------------------------\n"
						  + "선택 : ";
		
		String id = inputMemberId();
		Member member = memberController.finMemberById(id);
		printMember(member);
		
		String updateData = null;
		int result = 0;
		
		if(member != null) {
			while(true) {
				System.out.print(updateMenu);
				String choice = sc.next();
				
				switch (choice) {
				case "1":
					updateData = inputUpdateData("이름");
					result = memberController.updateMember("name", id, updateData);
					break;
				case "2":
					updateData = inputUpdateData("생일");
					result = memberController.updateMember("birthday", id, updateData);
					break;
				case "3":
					updateData = inputUpdateData("이메일");
					result = memberController.updateMember("email", id, updateData);
					break;
				case "4":
					updateData = inputUpdateData("주소");
					result = memberController.updateMember("address", id, updateData);
					break;
				case "9": return;
				default:
					System.out.println("잘못 입력하셨습니다.");
				}
				
				printResulting(result, "회원 정보 변경 성공!", "회원 정보 변경 실패!");
				member = memberController.finMemberById(id);
				printMember(member);
			}
		}
		else {
			return;
		}
	}
	
	/**
	 * 변경하고자 하는 데이터 입력 메소드
	 * @param rawData
	 * @return
	 */
	private String inputUpdateData(String rawData) {
		String updateData = null;
		
		System.out.println("----- 회원 정보 변경 > " + rawData + " 변경 ------");
		if(!(rawData.equals("생일"))) {
			System.out.print(rawData + " : ");
			sc.nextLine();
			updateData = sc.nextLine();
		}
		else if(rawData.equals("생일")) {
			System.out.print("생일(예:19990314) : ");
			updateData = sc.next();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date birthday = null;
			try {
				long millis = sdf.parse(updateData).getTime();
				birthday = new Date(millis);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("잘못 입력하셨습니다.");
		}
		return updateData;
	}
	
	/**
	 * 6. 사용자입력값으로 member객체 삭제 메소드
	 * @return
	 */
	public Member deleteMember(String id, String name) {
		System.out.println("----- 탈퇴할 회원 정보 입력 -----");
		System.out.print("아이디 : ");
		id = sc.next();
		System.out.print("이름 : ");
		name = sc.next();
		return new Member(id, name);
	}
}
