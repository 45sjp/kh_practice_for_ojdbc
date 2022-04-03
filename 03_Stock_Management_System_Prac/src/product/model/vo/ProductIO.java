package product.model.vo;

import java.sql.Timestamp;

public class ProductIO extends Product {
	
	private int no;					// 입출고번호
	private String productId;		// 상품id
	private int count;				// 수량
	private String status;			// 입출고여부
	private Timestamp ioDatetime;	// 입출고시각
	
	public ProductIO() {
		super();
	}
	
	public ProductIO(int no, String productId, int count, String status, Timestamp ioDatetime) {
		super();
		this.no = no;
		this.productId = productId;
		this.count = count;
		this.status = status;
		this.ioDatetime = ioDatetime;
	}
	
	public ProductIO(int no, String productId, String brand, String name, int count, String status, Timestamp ioDatetime) {
		super(brand, name);
		this.no = no;
		this.productId = productId;
		this.count = count;
		this.status = status;
		this.ioDatetime = ioDatetime;
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getIoDatetime() {
		return ioDatetime;
	}

	public void setIoDatetime(Timestamp ioDatetime) {
		this.ioDatetime = ioDatetime;
	}

	@Override
	public String toString() {
		return "ProductIO [no=" + no + ", productId=" + productId + ", count=" + count + ", status=" + status
				+ ", ioDatetime=" + ioDatetime + ", getId()=" + getId() + ", getBrand()=" + getBrand() + ", getName()="
				+ getName() + ", getPrice()=" + getPrice() + ", getMonitorSize()=" + getMonitorSize() + ", getOs()="
				+ getOs() + ", getStorage()=" + getStorage() + ", getRegDate()=" + getRegDate() + ", getStock()="
				+ getStock() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}
	
}
