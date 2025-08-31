package com.babymate.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;


public class ProductDAO implements ProductDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TJA102-03-Webapp");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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
			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, product_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

}
