package com.babymate.product.model;

import java.sql.*;
import java.util.*;


public class ProductJDBCDAO implements ProductDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/babymate?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO product (product_no, product_name, category_id, price,status, status_update_time, product_icon, feature_desc, spec_desc, note, remark) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT product_id, product_no, product_name, category_id, price,status, status_update_time, product_icon, feature_desc, spec_desc, note, remark, update_time FROM product order by product_id";
	private static final String GET_ONE_STMT = 
			"SELECT product_id, product_no, product_name, category_id, price,status, status_update_time, product_icon, feature_desc, spec_desc, note, remark, update_time FROM product where product_id = ?";
	private static final String DELETE = 
			"DELETE FROM product where product_id = ?";
	private static final String UPDATE = 
			"UPDATE product set product_no=?, product_name=?, category_id=?, price=?, status=?, status_update_time=?, product_icon=?, feature_desc=?, spec_desc=?, note=?, remark=? where product_id = ?";

	@Override
	public void insert(ProductVO productVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productVO.getProduct_no());
			pstmt.setString(2, productVO.getProduct_name());
			pstmt.setInt(3, productVO.getCategory_id());
			pstmt.setDouble(4, productVO.getPrice());
			pstmt.setInt(5, productVO.getStatus());
			pstmt.setTimestamp(6, productVO.getStatus_update_time());
			pstmt.setBytes(7, productVO.getProduct_icon());
			pstmt.setString(8, productVO.getFeature_desc());
			pstmt.setString(9, productVO.getSpec_desc());
			pstmt.setString(10, productVO.getNote());
			pstmt.setString(11, productVO.getRemark());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(ProductVO productVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productVO.getProduct_no());
			pstmt.setString(2, productVO.getProduct_name());
			pstmt.setInt(3, productVO.getCategory_id());
			pstmt.setDouble(4, productVO.getPrice());
			pstmt.setInt(5, productVO.getStatus());
			pstmt.setTimestamp(6, productVO.getStatus_update_time());
			pstmt.setBytes(7, productVO.getProduct_icon());
			pstmt.setString(8, productVO.getFeature_desc());
			pstmt.setString(9, productVO.getSpec_desc());
			pstmt.setString(10, productVO.getNote());
			pstmt.setString(11, productVO.getRemark());
			pstmt.setInt(12, productVO.getProduct_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer product_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, product_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ProductVO findByPrimaryKey(Integer product_id) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, product_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				productVO = new ProductVO();
				productVO.setProduct_no(rs.getString("product_no"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setCategory_id(rs.getInt("category_id"));
				productVO.setPrice(rs.getDouble("price"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setStatus_update_time(rs.getTimestamp("status_update_time"));
				productVO.setProduct_icon(rs.getBytes("product_icon"));
				productVO.setFeature_desc(rs.getString("feature_desc"));
				productVO.setSpec_desc(rs.getString("spec_desc"));
				productVO.setNote(rs.getString("note"));
				productVO.setRemark(rs.getString("remark"));
				productVO.setUpdate_time(rs.getTimestamp("update_time"));
				productVO.setProduct_id(rs.getInt("product_id"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				productVO = new ProductVO();
				productVO.setProduct_no(rs.getString("product_no"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setCategory_id(rs.getInt("category_id"));
				productVO.setPrice(rs.getDouble("price"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setStatus_update_time(rs.getTimestamp("status_update_time"));
				productVO.setProduct_icon(rs.getBytes("product_icon"));
				productVO.setFeature_desc(rs.getString("feature_desc"));
				productVO.setSpec_desc(rs.getString("spec_desc"));
				productVO.setNote(rs.getString("note"));
				productVO.setRemark(rs.getString("remark"));
				productVO.setUpdate_time(rs.getTimestamp("update_time"));
				productVO.setProduct_id(rs.getInt("product_id"));
				list.add(productVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		
		ProductJDBCDAO dao = new ProductJDBCDAO();
		
		// 新增
//		ProductVO productVO1 = new ProductVO();
//		productVO1.setProduct_no("B0021");
//		productVO1.setProduct_name("拼圖");
//		productVO1.setCategory_id(2);
//		productVO1.setPrice(99.0);
//		productVO1.setStatus(1);
//		productVO1.setStatus_update_time(Timestamp.valueOf("2025-08-25 08:00:00"));
//		productVO1.setFeature_desc("嬰幼兒學習");
//		productVO1.setSpec_desc("1歲以上");
//		productVO1.setNote("測試資料");
//		productVO1.setRemark("測試資料");
//		dao.insert(productVO1);
		
		// 修改
//		ProductVO productVO2 = new ProductVO();
//		productVO2.setProduct_id(100001);
//		productVO2.setProduct_no("B0001");
//		productVO2.setProduct_name("有聲玩具");
//		productVO2.setCategory_id(2);
//		productVO2.setPrice(759.0);
//		productVO2.setStatus(1);
//		productVO2.setStatus_update_time(Timestamp.valueOf("2025-08-25 08:00:00"));
//		productVO2.setFeature_desc("嬰幼兒學習");
//		productVO2.setSpec_desc("1歲以上");
//		productVO2.setNote("測試資料");
//		productVO2.setRemark("測試資料");
//		dao.update(productVO2);
		
		// 刪除
//		dao.delete(100019);
		
		// 單筆查詢
		ProductVO productVO3 = dao.findByPrimaryKey(100006);
		System.out.print(productVO3.getProduct_id() + ",");
		System.out.print(productVO3.getProduct_no() + ",");
		System.out.print(productVO3.getProduct_name() + ",");
		System.out.print(productVO3.getCategory_id() + ",");
		System.out.print(productVO3.getPrice() + ",");
		System.out.print(productVO3.getStatus() + ",");
		System.out.print(productVO3.getStatus_update_time() + ",");
		System.out.print(productVO3.getProduct_icon() + ",");
		System.out.print(productVO3.getFeature_desc() + ",");
		System.out.print(productVO3.getSpec_desc() + ",");
		System.out.print(productVO3.getNote() + ",");
		System.out.print(productVO3.getRemark() + ",");
		System.out.println(productVO3.getUpdate_time() + ",");
		System.out.println("-----------------------------");
		
		
		// 查詢全部資料
		List<ProductVO> list = dao.getAll();
		for(ProductVO aProduct : list) {
			System.out.print(aProduct.getProduct_id() + ",");
			System.out.print(aProduct.getProduct_no() + ",");
			System.out.print(aProduct.getProduct_name() + ",");
			System.out.print(aProduct.getCategory_id() + ",");
			System.out.print(aProduct.getPrice() + ",");
			System.out.print(aProduct.getStatus() + ",");
			System.out.print(aProduct.getStatus_update_time() + ",");
			System.out.print(aProduct.getProduct_icon() + ",");
			System.out.print(aProduct.getFeature_desc() + ",");
			System.out.print(aProduct.getSpec_desc() + ",");
			System.out.print(aProduct.getNote() + ",");
			System.out.print(aProduct.getRemark() + ",");
			System.out.print(aProduct.getUpdate_time() + ",");
			System.out.println();
		}
	}

}
