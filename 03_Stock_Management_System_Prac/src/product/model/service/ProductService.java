package product.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import product.model.dao.ProductDao;
import product.model.vo.Product;
import product.model.vo.ProductIO;

public class ProductService {
	
	private ProductDao productDao = new ProductDao();
	
	/**
	 * 1. dao에 전체상품목록 요청
	 * @return
	 */
	public List<Product> selectAllProduct() {
		Connection conn = getConnection();
		List<Product> list = productDao.selectAllProduct(conn);
		close(conn);
		return list;
	}
	
	/**
	 * 2. dao에 상품검색 요청
	 * @param inputData
	 * @param searchData
	 * @return
	 */
	public List<Product> searchProduct(String inputData, String searchData) {
		Connection conn = getConnection();
		List<Product> list = productDao.searchProduct(conn, inputData, searchData);
		close(conn);
		return list;
	}
	
	/**
	 * 3. dao에 신규상품등록 요청
	 * @param product
	 * @return
	 */
	public int insertProduct(Product product) {
		int result = 0;
		Connection conn = getConnection();
		
		try {
			result = productDao.insertProduct(conn, product);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}
	
	/**
	 * 4. dao에 상품정보 변경 요청
	 * @param rawData
	 * @param id
	 * @param updateData
	 * @return
	 */
	public int updateProduct(Object rawData, String id, Object updateData) {
		int result = 0;
		Connection conn = getConnection();
		
		try {
			result = productDao.updateProduct(conn, rawData, id, updateData);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}
	
	/**
	 * 4/5. dao에 상품 1행 조회 요청
	 * @param id
	 * @return
	 */
	public Product selectOneProduct(String id) {
		Connection conn = getConnection();
		Product product = productDao.selectOneProduct(conn, id);
		close(conn);
		return product;
	}
	
	/**
	 * 5. dao에 상품삭제 요청
	 * @param id
	 * @return
	 */
	public int deleteProduct(String id) {
		int result = 0;
		Connection conn = getConnection();
		
		try {
			result = productDao.deleteProduct(conn, id);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}
	
	/**
	 * 6.1. dao에 상품입출고내역 요청
	 * @param id 
	 * @return
	 */
	public List<ProductIO> selectAllProductIO(String id) {
		Connection conn = getConnection();
		List<ProductIO> ioList = productDao.selectAllProductIO(conn, id);
		close(conn);
		return ioList;
	}
	
	/**
	 * 6.2. dao에 상품 입출고 변경 요청
	 * @param rawIOData
	 * @param id
	 * @param updateIOData
	 * @return
	 */
	public int insertProductIO(String rawIOData, String id, ProductIO updateIOData) {
		int result = 0;
		Connection conn = getConnection();
		
		try {
			result = productDao.insertProductIO(conn, rawIOData, id, updateIOData);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

}
