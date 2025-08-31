package com.babymate.category.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.babymate.product.model.ProductVO;

public class CategoryDAO implements CategoryDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}

		private static final String INSERT_STMT = 
				"INSERT INTO category category_name VALUES ?";
	private static final String GET_ALL_STMT = "SELECT categoty_id, category_name, update_time FROM category";
	private static final String GET_ONE_STMT = "SELECT categoty_id, category_name, update_time FROM category where category_id = ?";
	private static final String GET_Products_ByCategory_id_STMT = "";

	private static final String DELETE_PRODUCTs = "";
	private static final String SELETE_CATEGORY = "";

	private static final StringUPDATE = "";
	
	
	@Override
	public void insert(CategoryVO categoryVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, categoryVO.getCategory_name());

			pstmt.executeUpdate("set auto_increment_offset=10;");
			pstmt.executeUpdate("set auto_increment_increment=10;");
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
	public void update(CategoryVO categoryVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer category_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public CategoryVO findByPrimaryKey(Integer category_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ProductVO> getProductsByCategory_id(Integer category_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
