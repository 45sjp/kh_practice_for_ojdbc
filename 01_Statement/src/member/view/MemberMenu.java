package member.view;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import member.controller.MemberController;
import member.model.vo.Member;

/**
 * view패키지 클래스
 * 	- 사용자가 보게 될 화면을 담당하는 클래스
 * 	- 메뉴, 사용자입력값 처리, 요청한 데이터 출력
 */
public class MemberMenu {
	
	private Scanner sc = new Scanner(System.in);
	private MemberController controller = new MemberController();
	
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
			
			List<Member> list = null;
			Member newMember = null;
			Member updateMember = null;
			Member member = null;
			int result = 0;
			
			String id = null;
			String name = null;
			
			switch (choice) {
			case "1":
				list = controller.selectAll();
				printMemberList(list);
				break;
			case "2":
				id = inputId();
				member = controller.selectOne(id);
				printMember(member);
				break;
			case "3":
				name = inputName();
				list = controller.selectByName(name);
				printMemberList(list);
				break;
			case "4":
				newMember = inputMember();
				result = controller.insertMember(newMember); // DML이므로 행의 수(int) 리턴
				System.out.println(result > 0 ? "> 회원 가입 성공!" : "> 회원 가입 실패!");
				break;
			case "5":
				id = inputId();
				member = controller.selectOne(id);
				printMember(member); // 변경 전 정보 출력
				
				updateMember = updateMember(member);
				result = controller.updateMember(member);
				
				System.out.println(result > 0 ? "> 회원 정보 변경 성공!" : "> 회원 정보 변경 실패!");
				printMember(member); // 변경 후 정보 출력
				break;
			case "6":
				member = deleteMember();
				result = controller.deleteMember(member);
				System.out.println(result > 0 ? "> 회원 탈퇴 성공!" : "> 회원 탈퇴 실패!");
				break;
			case "0": return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	/**
	 * 1. 여러 행 조회결과를 출력하는 메소드
	 * (list가 null인 경우 없음)
	 * 	- 0행
	 * 	- n행
	 * @param list
	 */
	public void printMemberList(List<Member> list) {
		if(list.isEmpty()) {
			System.out.println("> 조회된 행이 없습니다.");
		}
		else {
			System.out.println("> 조회결과");
			System.out.println("==================================================");
			System.out.printf("%15s%15s%15s%15s%15s%15s%15s%n",
								"id", "name", "gender", "birthday", "email", "address", "regDate");
			System.out.println("--------------------------------------------------");
			for(Member member : list ) {
				System.out.printf("%15s%15s%15s%15s%15s%15s%15s%n",
							member.getId(), member.getName(), member.getGender(), member.getBirthday(),
							member.getEmail(), member.getAddress(), member.getRegDate());
			}
			System.out.println("--------------------------------------------------");
			System.out.println();
		}
	}
	
	/**
	 * 2. 조회할 id를 입력하는 메소드
	 * @return
	 */
	public String inputId() {
		System.out.println("----- 조회할 id 입력 -----");
		System.out.print("id : ");
		return sc.next();
	}
	
	/**
	 * 3. 조회할 이름을 입력하는 메소드
	 * @return
	 */
	public String inputName() {
		System.out.println("----- 조회할 이름 입력 -----");
		System.out.print("name : ");
		return sc.next();
	}

	/**
	 * 회원 1명 출력 메소드
	 * 	- null
	 * 	- member객체 1개
	 * @param member
	 */
	public void printMember(Member member) {
		if(member == null) {
			System.out.println("> 조회된 회원이 없습니다.");
		}
		else {
			System.out.println("> 조회결과");
			System.out.println("=========================");
			System.out.printf("id : %s%n", member.getId());
			System.out.printf("name : %s%n", member.getName());
			System.out.printf("gender : %s%n", member.getGender());
			System.out.printf("birthday : %s%n", member.getBirthday());
			System.out.printf("email : %s%n", member.getEmail());
			System.out.printf("address : %s%n", member.getAddress());
			System.out.printf("regDate : %s%n", member.getRegDate());
			System.out.println("-------------------------");
			System.out.println();
		}
	}

	/**
	 * 4. 사용자입력값으로 member객체 생성 메소드
	 * @return
	 */
	public Member inputMember() {
		System.out.println("----- 새 회원 정보 입력 -----");
		System.out.print("아이디 : ");
		String id = sc.next();
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("성별(M/F) : ");
		String gender = String.valueOf(sc.next().toUpperCase().charAt(0));
		System.out.print("생일(예: 19990909) : ");
		String _birthday = sc.next();
		Date birthday = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			birthday = new Date(sdf.parse(_birthday).getTime()); // java.util.Date -> java.sql.Date
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.print("이메일 : ");
		String email = sc.next();
		sc.nextLine(); //개행문자 날리기
		System.out.print("주소 : ");
		String address = sc.nextLine();
		return new Member(id, name, gender, birthday, email, address, null);
	}
	
	/**
	 * 5. 사용자입력값으로 member객체 변경 메소드
	 * @return
	 */
	private Member updateMember(Member member) {
		System.out.println("----- 변경할 회원 정보 입력 -----");
		System.out.print("이름 : ");
		String name = sc.next();
		member.setName(name);
		
		System.out.print("생일 : ");
		String _birthday = sc.next();
		Date birthday = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			birthday = new Date(sdf.parse(_birthday).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		member.setBirthday(birthday);
		
		System.out.print("이메일 : ");
		String email = sc.next();
		member.setEmail(email);
		
		sc.nextLine();
		
		System.out.print("주소 : ");
		String address = sc.nextLine();
		member.setAddress(address);
		
		return member;
	}
	
	/**
	 * 6. 사용자입력값으로 member객체 삭제 메소드
	 * @return
	 */
	public Member deleteMember() {
		System.out.println("----- 삭제할 회원 정보 입력 -----");
		System.out.print("아이디 : ");
		String id = sc.next();
		System.out.print("이름 : ");
		String name = sc.next();
		return new Member(id, name);
	}
	
}
