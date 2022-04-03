package product.view;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import product.controller.ProductController;
import product.model.vo.Product;
import product.model.vo.ProductIO;

public class ProductMenu {
	
	private Scanner sc = new Scanner(System.in);
	private ProductController productController = new ProductController();
	
	public void mainMenu() {
		String menu = "************　상품 재고 관리 프로그램　************\r\n"
					+ "1. 전체 상품조회\r\n"
					+ "2. 상품검색 메뉴\r\n"
					+ "3. 상품등록\r\n"
					+ "4. 상품정보 변경 메뉴\r\n"
					+ "5. 상품삭제\r\n"
					+ "6. 상품입출고 메뉴\r\n"
					+ "0. 프로그램 종료\r\n"
					+ "**********************************************\r\n"
					+ "> 선택 : ";
		
		do {
			System.out.print(menu);
			String choice = sc.next();
			
			Product product = null;
			List<Product> list = null;
			int result = 0;
			
			switch (choice) {
			case "1":
				list = productController.selectAllProduct();
				printProductList(list);
				break;
			case "2":
				searchMenu();
				break;
			case "3":
				product = inputProduct();
				result = productController.insertProduct(product);
				printResulting(result, "상품등록이 완료되었습니다.", "상품등록에 실패하였습니다.");
				break;
			case "4":
				updateMenu();
				break;
			case "5":
				deleteProduct();
				break;
			case "6":
				ioMenu();
				break;
			case "0": return;
			default:
				System.err.println("잘못 입력하셨습니다.");
			}
		} while(true);
	}
	
	/**
	 * 전체 상품 목록 조회 메소드
	 * @param list
	 */
	private void printProductList(List<Product> list) {
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("#,###원");
		
		if(list == null || list.isEmpty()) {
            System.out.println("> 조회된 결과가 없습니다.");
        }
		else {
			System.out.println("***************** 전체 상품조회 *****************");
			System.out.println("> 전체 상품조회 결과");
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			System.out.printf("%s%20s%15s%20s%20s%35s%n", "id", "brand", "name", "price", "spec", "stock");
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			for(Product product : list) {
				System.out.printf("%s%8s%16s%23s%12s인치 / %s / %sGB%12s%n",
						product.getId(),
						product.getBrand(),
						product.getName(),
						df.format(product.getPrice()),
						product.getMonitorSize(),
						product.getOs(),
						product.getStorage(),
						product.getStock());
			}
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			System.out.println("**********************************************");
			System.out.println();
		}
	}
	
	/**
	 * 2. 상품검색 메뉴 메소드
	 * 	- 아이디/상품명 모두 문자열 일부 검색 가능
	 */
	private void searchMenu() {
		String menu = "***************** 상품검색 메뉴 *****************\r\n"
					+ "1. 아이디 검색\r\n"
					+ "2. 상품명 검색\r\n"
					+ "0. 메인메뉴로 돌아가기\r\n"
					+ "**********************************************\r\n"
					+ "> 선택 : ";
		
		String searchData = null;
		List<Product> list = null;
		
		while(true) {
			System.out.print(menu);
			String choice = sc.next();
			
			switch (choice) {
			case "1":
				searchData = inputProductData("상품 id");
				list = productController.searchProduct("id", searchData);
				printProductList(list);
				break;
			case "2":
				searchData = inputProductData("상품명");
				list = productController.searchProduct("name", searchData);
				printProductList(list);
				break;
			case "0": return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	/**
	 * 2-1. 검색하고자 하는 데이터 입력 메소드
	 * @param inputData
	 * @return
	 */
	private String inputProductData(String inputData) {
		System.out.println("******************　상품검색　******************");
		System.out.println("> 조회할 " + inputData + "을(를) 입력하세요.");
		System.out.printf("> %s 입력 : ", inputData);
		sc.nextLine();
		String searchData = sc.nextLine();
		System.out.println("**********************************************");
		return searchData;
	}
	
	/**
	 * 3. 신규 상품 정보를 입력 받는 메소드
	 * @return
	 */
	private Product inputProduct() {
		Product product = new Product();
		
		System.out.println("****************　신규 상품등록　****************");
		System.out.println("> 등록할 상품의 정보를 입력하세요.");
		
		while(true) {
			System.out.print("> 상품id 입력 : ");
			product.setId(sc.next());
			
			System.out.print("> 브랜드 입력 : ");
			product.setBrand(sc.next());
			
			sc.nextLine();
			
			System.out.print("> 상품명 입력 : ");
			product.setName(sc.nextLine());
			
			System.out.print("> 가격 입력 : ");
			product.setPrice(sc.nextInt());
			
			System.out.print("> 모니터 사이즈 입력 : ");
			product.setMonitorSize(sc.nextInt());
			
			System.out.print("> 운영체제 입력 : ");
			product.setOs(sc.next());
			
			System.out.print("> 저장용량 입력 : ");
			product.setStorage(sc.nextInt());
			
			System.out.println("**********************************************");
			
			return product;
		}
	}
	
	/**
	 * 4. 상품정보 변경 메뉴 메소드
	 */
	private void updateMenu() {
		String menu = "*************** 상품정보 변경 메뉴 ***************\r\n"
					+ "1. 상품명 변경\r\n"
					+ "2. 가격 변경\r\n"
					+ "3. 사양 변경\r\n"
					+ "0. 이전메뉴로 돌아가기\r\n"
					+ "**********************************************\r\n"
					+ "> 선택 : ";
		
		String id = inputProductId();
		Product product = productController.selectOneProduct(id);
		printOneProduct(product);
		
		Object updateData = null;
		int result = 0;
		
		if(product != null) {
			while(true) {
				System.out.print(menu);
				String choice = sc.next();
				
				switch (choice) {
				case "1":
					updateData = inputUpdateData("상품명");
					result = productController.updateProduct("name", id, updateData);
					printResulting(result, "회원 정보 변경 성공!", "회원 정보 변경 실패!");
					product = productController.selectOneProduct(id);
					printOneProduct(product);
					break;
				case "2":
					updateData = inputUpdateData("가격");
					result = productController.updateProduct("price", id, updateData);
					printResulting(result, "회원 정보 변경 성공!", "회원 정보 변경 실패!");
					product = productController.selectOneProduct(id);
					printOneProduct(product);
					break;
				case "3":
					updateData = inputUpdateData("사양");
					specMenu(id);
					break;
				case "0": return;
				default:
					System.out.println("잘못 입력하셨습니다.");
				}
			}
		} else {
			return;
		}
	}
	
	/**
	 * 4.1. 변경하고자 하는 데이터 입력 메소드
	 * @param rawData
	 * @return
	 */
	private Object inputUpdateData(Object rawData) {
		Object updateData = null;
		
		System.out.println("***************** 상품정보 변경 *****************");
		System.out.printf("> %s을 변경합니다.%n", rawData);
		if(!rawData.equals("사양")) {
			System.out.printf("> %s 변경 : ", rawData);
			sc.nextLine();
			updateData = sc.nextLine();
		}
		else if(rawData.equals("사양")) {
			System.out.println("> 사양 정보 변경 메뉴로 이동합니다.");
		}
		else {
			System.out.println("잘못 입력하셨습니다.");
		}
		System.out.println("**********************************************");
		return updateData;
	}
	
	/**
	 * 4.2. 상품 사양정보 변경 메뉴 메소드
	 * @param id 
	 */
	private void specMenu(String id) {
		String menu = "************　상품 사양정보 변경 메뉴　************\r\n"
					+ "1. 모니터 사이즈 변경\r\n"
					+ "2. 운영체제 변경\r\n"
					+ "3. 저장용량 변경\r\n"
					+ "0. 이전메뉴로 돌아가기\r\n"
					+ "**********************************************\r\n"
					+ "> 선택 : ";
		
		Product product = null;
		Object updateData = null;
		int result = 0;
		
		while(true) {
			System.out.print(menu);
			String choice = sc.next();
			
			switch (choice) {
			case "1":
				updateData = inputUpdateData("모니터 사이즈");
				result = productController.updateProduct("monitor_size", id, updateData);
				break;
			case "2":
				updateData = inputUpdateData("운영체제");
				result = productController.updateProduct("os", id, updateData);
				break;
			case "3":
				updateData = inputUpdateData("저장용량");
				result = productController.updateProduct("storage", id, updateData);
				break;
			case "0": return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
			
			printResulting(result, "회원 정보 변경 성공!", "회원 정보 변경 실패!");
			product = productController.selectOneProduct(id);
			printOneProduct(product);
		}
	}

	/**
	 * 4/5. 조회할 상품 id로 찾기 메소드
	 * @return
	 */
	private String inputProductId() {
		System.out.print("> 상품id 입력 : ");
		return sc.next();
	}
	
	/**
	 * 4/5. 상품 1행 조회 메소드
	 * @param prod
	 */
	private void printOneProduct(Product product) {
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("#,###원");
		
		if(product == null) {
			System.out.println("> 조회된 결과가 없습니다.");
		}
		else {
			System.out.println("***************** 선택 상품조회 *****************");
			System.out.println("> 상품조회 결과");
			System.out.println("----------------------------------------------");
			System.out.printf("id : %s%n", product.getId());
			System.out.printf("brand : %s%n", product.getBrand());
			System.out.printf("name : %s%n", product.getName());
			System.out.printf("price : %s%n", df.format(product.getPrice()));
			System.out.printf("spec : %s인치 / %s / %sGB%n", product.getMonitorSize(), product.getOs(), product.getStorage());
			System.out.printf("stock : %s%n", product.getStock());
			System.out.println("----------------------------------------------");
			System.out.println("**********************************************");
		}
	}
	
	/**
	 * 5. 사용자입력값으로 product객체 삭제 메소드
	 * @param id
	 * @return
	 */
	private void deleteProduct() {
		Product product = null;
		String id = null;
		int result = 0;
		
		System.out.println("******************　상품삭제　******************");
		System.out.println("> 삭제할 상품의 id를 입력해주세요.");
		id = inputProductId();
		product = productController.selectOneProduct(id);
		printOneProduct(product);
		sc.nextLine();
		result = productController.deleteProduct(id);
		printResulting(result, "상품삭제가 완료되었습니다.", "상품삭제에 실패하였습니다.");
		System.out.println("**********************************************");
	}
	
	/**
	 * 6. 상품입출고 메뉴 메소드
	 * 	- 각 메뉴 선택시 현재상품목록 출력하고, 선택할 수 있어야 함
	 * 	- 입출고내역 조회시 특정상품의 최근 입출고내역 조회(입출고번호, 상품id, 상품명(브랜드), 수량, 입출고여부, 입출고시각)
	 */
	private void ioMenu() {
		String menu = "**************** 상품입출고 메뉴 ****************\r\n"
					+ "1. 입출고내역 조회\r\n"
					+ "2. 상품입고\r\n"
					+ "3. 상품출고\r\n"
					+ "0. 메인메뉴로 돌아가기\r\n"
					+ "**********************************************\r\n"
					+ "> 선택 : ";
		
		List<ProductIO> ioList = null;
		ProductIO updateIOData = null;
		String id = null;
		int result = 0;
		
		while(true) {
			System.out.print(menu);
			String choice = sc.next();
			
			switch (choice) {
			case "1":
				id = inputProductId();
				ioList = productController.selectAllProductIO(id);
				printIOList(ioList);
				break;
			case "2":
				id = inputProductId();
				ioList = productController.selectAllProductIO(id);
				printIOList(ioList);
				
				if(!ioList.isEmpty()) {
					updateIOData = inputProductIO("상품입고");
					result = productController.insertProductIO("I", id, updateIOData);
					printResulting(result, "상품입고가 완료되었습니다.", "상품입고에 실패하였습니다.");
					break;
				}
				else {
					continue;
				}
			case "3":
				id = inputProductId();
				ioList = productController.selectAllProductIO(id);
				printIOList(ioList);
				
				if(!ioList.isEmpty()) {
					updateIOData = inputProductIO("상품출고");
					result = productController.insertProductIO("O", id, updateIOData);
					printResulting(result, "상품출고가 완료되었습니다.", "상품출고에 실패하였습니다.");
					break;
				}
				else {
					continue;
				}
			case "0": return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
			
			ioList = productController.selectAllProductIO(id);
			printIOList(ioList);
		}
	}

	/**
	 * 6.1. 상품 입출고내역 조회 메소드
	 * @param ioList
	 */
	private void printIOList(List<ProductIO> ioList) {
		if(ioList == null || ioList.isEmpty()) {
            System.out.println("> 조회된 결과가 없습니다.");
        }
		else {
			System.out.println("************** 최근 입출고내역 조회 **************");
			System.out.println("> 최근 입출고내역 조회 결과");
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			System.out.printf("%s%20s%20s%30s%20s%20s%n", "no", "id", "name(brand)", "count", "status", "io_datetime");
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			for(ProductIO productIO : ioList) {
				System.out.printf("%s%20s%20s(%s)%30s%20s%30s%n",
						productIO.getNo(),
						productIO.getProductId(),
						productIO.getName(),
						productIO.getBrand(),
						productIO.getCount(),
						productIO.getStatus(),
						productIO.getIoDatetime());
			}
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			System.out.println("**********************************************");
			System.out.println();
		}
	}
	
	/**
	 * 6.2. 상품 입출고 변경 메소드
	 * @param rawIOData
	 * @return
	 */
	private ProductIO inputProductIO(String rawIOData) {
		ProductIO updateIOData = new ProductIO();
		System.out.println("******************* " + rawIOData + " *******************");
		System.out.println("> 상품재고 상태를 변경합니다.");
		System.out.printf("> %s : ", rawIOData);
		updateIOData.setStock(sc.nextInt());
		System.out.println("**********************************************");
		return updateIOData;
	}
	
	/**
	 * DML 처리 결과를 출력하는 메소드
	 * @param result
	 * @param successMsg
	 * @param failureMsg
	 */
	private void printResulting(int result, String successMsg, String failureMsg) {
		if(result > 0)
			System.out.printf("> %s%n%n", successMsg);
		else
			System.err.printf("> %s%n%n", failureMsg);
	}
	
}
