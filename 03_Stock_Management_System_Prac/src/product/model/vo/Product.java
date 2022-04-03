package product.model.vo;

import java.sql.Date;

public class Product {

	private String id; 			// 상품id
	private String brand; 		// 브랜드
	private String name; 		// 상품명
	private int price; 			// 가격
	private int monitorSize; 	// 모니터 사이즈
	private String os; 			// 운영체제
	private int storage; 		// 저장용량
	private Date regDate; 		// 등록일
	private int stock; 			// 재고
	
	public Product() {
		super();
	}
	
	public Product(String brand, String name) {
		this.brand = brand;
		this.name = name;
	}

	public Product(String id, String brand, String name, int price, int monitorSize, String os, int storage,
			int stock) {
		this(brand, name);
		this.id = id;
		this.price = price;
		this.monitorSize = monitorSize;
		this.os = os;
		this.storage = storage;
		this.stock = stock;
	}
	
	public Product(String id, String brand, String name, int price, int monitorSize, String os, int storage,
			Date regDate, int stock) {
		this(id, brand, name, price, monitorSize, os, storage, stock);
		this.regDate = regDate;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMonitorSize() {
		return monitorSize;
	}

	public void setMonitorSize(int monitorSize) {
		this.monitorSize = monitorSize;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", brand=" + brand + ", name=" + name + ", price=" + price + ", monitorSize="
				+ monitorSize + ", os=" + os + ", storage=" + storage + ", regDate=" + regDate + ", stock=" + stock
				+ "]";
	}
	
}
