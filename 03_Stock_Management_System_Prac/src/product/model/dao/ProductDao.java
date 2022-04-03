package product.model.dao;

import static common.JdbcTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import product.model.vo.Product;
import product.model.vo.ProductIO;

public class ProductDao {
	
	Properties prop = new Properties();
	
	public ProductDao() {
		try {
			prop.load(new FileReader("resources/product-query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 메뉴 1.
	 */
	public List<Product> selectAllProduct(Connection conn) {
		String sql = prop.getProperty("selectAllProduct");
		List<Product> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product product = new Product();
				product.setId(rset.getString("id"));
				product.setBrand(rset.getString("brand"));
				product.setName(rset.getString("name"));
				product.setPrice(rset.getInt("price"));
				product.setMonitorSize(rset.getInt("monitor_size"));
				product.setOs(rset.getString("os"));
				product.setStorage(rset.getInt("storage"));
				product.setStock(rset.getInt("stock"));
				list.add(product);
			}
		} catch (SQLException e) {
			throw new ProductException("전체 상품조회 오류", e);
		}
		
		close(rset);
		close(pstmt);

		return list;
	}
	
	/*
	 * 메뉴 2.
	 */
	public List<Product> searchProduct(Connection conn, String inputData, String searchData) {
		String sql = prop.getProperty("searchProduct");
		sql = sql.replace("#", inputData);
		List<Product> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchData + "%");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product product = new Product();
				product.setId(rset.getString("id"));
				product.setBrand(rset.getString("brand"));
				product.setName(rset.getString("name"));
				product.setPrice(rset.getInt("price"));
				product.setMonitorSize(rset.getInt("monitor_size"));
				product.setOs(rset.getString("os"));
				product.setStorage(rset.getInt("storage"));
				product.setStock(rset.getInt("stock"));
				list.add(product);
			}
		} catch (SQLException e) {
			throw new ProductException("상품검색 오류", e);
		}
		
		close(rset);
		close(pstmt);
		
		return list;
	}
	
	/*
	 * 메뉴 3.
	 */
	public int insertProduct(Connection conn, Product product) {
		String sql = prop.getProperty("insertProduct");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getId());
			pstmt.setString(2, product.getBrand());
			pstmt.setString(3, product.getName());
			pstmt.setInt(4, product.getPrice());
			pstmt.setInt(5, product.getMonitorSize());
			pstmt.setString(6, product.getOs());
			pstmt.setInt(7, product.getStorage());
			pstmt.setString(8, product.getId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("상품등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/*
	 * 메뉴 4.
	 */
	public int updateProduct(Connection conn, Object rawData, String id, Object updateData) {
		String sql = prop.getProperty("updateProduct");
		sql = sql.replace("#", (String)rawData);
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, updateData);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("상품정보 변경 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/*
	 * 메뉴 4/5.
	 */
	public Product selectOneProduct(Connection conn, String id) {
		String sql = prop.getProperty("selectOneProduct");
		Product product = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				product = new Product(rset.getString("id"), rset.getString("brand"),
						   			  rset.getString("name"), rset.getInt("price"),
						   			  rset.getInt("monitor_size"), rset.getString("os"),
						   			  rset.getInt("storage"), rset.getInt("stock"));
			}
		} catch (SQLException e) {
			throw new ProductException("선택상품 조회 오류", e);
		}
		
		close(rset);
		close(pstmt);
		
		return product;
	}
	
	/*
	 * 메뉴 5.
	 */
	public int deleteProduct(Connection conn, String id) {
		String sql = prop.getProperty("deleteProduct");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("상품삭제 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/*
	 * 메뉴 6.1.
	 */
	public List<ProductIO> selectAllProductIO(Connection conn, String id) {
		String sql = prop.getProperty("selectAllProductIO");
		List<ProductIO> ioList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductIO productIO = new ProductIO();
				productIO.setNo(rset.getInt("no"));
				productIO.setProductId(rset.getString("product_id"));
				productIO.setName(rset.getString("name"));
				productIO.setBrand(rset.getString("brand"));
				productIO.setCount(rset.getInt("count"));
				productIO.setStatus(rset.getString("status"));
				productIO.setIoDatetime(rset.getTimestamp("io_datetime"));
				ioList.add(productIO);
			}
		} catch (SQLException e) {
			throw new ProductException("최근 입출고내역 조회 오류", e);
		}
		
		close(rset);
		close(pstmt);
		
		return ioList;
	}
	
	/*
	 * 메뉴 6.2-3.
	 */
	public int insertProductIO(Connection conn, String rawIOData, String id, ProductIO updateIOData) {
		String sql = prop.getProperty("insertProductIO");
		sql = sql.replace("#", rawIOData);
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, updateIOData.getStock());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductException("상품 재고상태 변경 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

}