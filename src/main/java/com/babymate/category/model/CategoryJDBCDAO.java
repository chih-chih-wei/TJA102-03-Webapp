package com.babymate.category.model;

public class CategoryJDBCDAO {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/babymate?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO category category_name VALUES ?";
	private static final String GET_ALL_STMT = 
			"SELECT category_id, category_name, update_time FROM category";
	private static final String DET_ONE_STMT = 
			"SELECT category_id, category_name, update_time FROM category WHERE category_id = ?";
	private static final String 
	
	
	
}
