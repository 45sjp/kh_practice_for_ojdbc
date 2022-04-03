package product.controller;

import java.util.List;

import product.model.service.ProductService;
import product.model.vo.Product;
import product.model.vo.ProductIO;

public class ProductController {
	
	private ProductService productService = new ProductService();
	
	/**
	 * 1. service에 전체상품목록 요청
	 * @return
	 */
	public List<Product> selectAllProduct() {
		List<Product> list = null;
		try {
			list = productService.selectAllProduct();
		} catch (Exception e) {
			System.err.println("> [" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의해주십시오.");
		}
		return list;
	}
	
	/**
	 * 2. service에 상품검색 요청
	 * @param inputData
	 * @param searchData
	 * @return
	 */
	public List<Product> searchProduct(String inputData, String searchData) {
		List<Product> list = null;
		try {
			list = productService.searchProduct(inputData, searchData);
		} catch (Exception e) {
			System.err.println("> [" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의해주십시오.");
		}
		return list;
	}
	
	/**
	 * 3. service에 신규상품등록 요청
	 * @param product
	 * @return
	 */
	public int insertProduct(Product product) {
		int result = 0;
		try {
			result = productService.insertProduct(product);
		} catch (Exception e) {
			System.err.println("> [" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의해주십시오.");
		}
		return result;
	}
	
	/**
	 * 4. service에 상품정보 변경 요청
	 * @param rawData
	 * @param id
	 * @param updateData
	 * @return
	 */
	public int updateProduct(Object rawData, String id, Object updateData) {
		int result = 0;
		try {
			result = productService.updateProduct(rawData, id, updateData);
		} catch(Exception e) {
			System.err.println("> [" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의해주십시오.");
		}
		return result;
	}
	
	/**
	 * 4/5. service에 상품 1행 조회 요청
	 * @param id
	 * @return
	 */
	public Product selectOneProduct(String id) {
		Product product = null;
		try {
			product = productService.selectOneProduct(id);
		} catch (Exception e) {
			System.err.println("> [" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의해주십시오.");
		}
		return product;
	}
	
	/**
	 * 5. service에 상품삭제 요청
	 * @param id
	 * @return
	 */
	public int deleteProduct(String id) {
		int result = 0;
		try {
			result = productService.deleteProduct(id);
		} catch (Exception e) {
			System.err.println("> [" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의해주십시오.");
		}
		return result;
	}
	
	/**
	 * 6.1. service에 상품입출고내역 요청
	 * @param id 
	 * @return
	 */
	public List<ProductIO> selectAllProductIO(String id) {
		List<ProductIO> ioList = null;
		try {
			ioList = productService.selectAllProductIO(id);
		} catch (Exception e) {
			System.err.println("> [" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의해주십시오.");
		}
		return ioList;
	}
	
	/**
	 * 6.2. service에 상품 입출고 변경 요청
	 * @param rawIOData
	 * @param id
	 * @param updateIOData
	 * @return
	 */
	public int insertProductIO(String rawIOData, String id, ProductIO updateIOData) {
		int result = 0;
		try {
			result = productService.insertProductIO(rawIOData, id, updateIOData);
		} catch (Exception e) {
			System.err.println("> [" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의해주십시오.");
		}
		return result;
	}
	
}
