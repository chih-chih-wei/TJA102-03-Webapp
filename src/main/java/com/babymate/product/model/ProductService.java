package com.babymate.product.model;

import java.sql.Timestamp;
import java.util.List;

public class ProductService {
	
	private ProductDAO_interface dao;
	
	public ProductService() {
		dao = new ProductDAO();
	}
	
	public ProductVO addProduct(String product_no, String product_name, Integer category_id, Double price, Integer status, Timestamp status_update_time, byte[] product_icon, String feature_desc, String spec_desc, String note, String remark) {
		
		ProductVO productVO = new ProductVO();
		
		productVO.setProduct_no(product_no);
		productVO.setProduct_name(product_name);
		productVO.setCategory_id(category_id);
		productVO.setPrice(price);
		productVO.setStatus(status);
		productVO.setStatus_update_time(status_update_time);
		productVO.setProduct_icon(product_icon);
		productVO.setFeature_desc(feature_desc);
		productVO.setSpec_desc(spec_desc);
		productVO.setNote(note);
		productVO.setRemark(remark);
		dao.insert(productVO);
		
		return productVO;
	}
	
public ProductVO updateProduct(Integer product_id, String product_no, String product_name, Integer category_id, Double price, Integer status, Timestamp status_update_time, byte[] product_icon, String feature_desc, String spec_desc, String note, String remark) {
		
		ProductVO productVO = new ProductVO();
		
		productVO.setProduct_id(product_id);
		productVO.setProduct_no(product_no);
		productVO.setProduct_name(product_name);
		productVO.setCategory_id(category_id);
		productVO.setPrice(price);
		productVO.setStatus(status);
		productVO.setStatus_update_time(status_update_time);
		productVO.setProduct_icon(product_icon);
		productVO.setFeature_desc(feature_desc);
		productVO.setSpec_desc(spec_desc);
		productVO.setNote(note);
		productVO.setRemark(remark);
		dao.update(productVO);
		
		return productVO;
	}

	public void deleteProduct(Integer product_id) {
		dao.delete(product_id);
	}
	
	public ProductVO getOneProduct(Integer product_id) {
		return dao.findByPrimaryKey(product_id);
	}
	
	public List<ProductVO> getAll(){
		return dao.getAll();
	}
	
	public String generateProductNo(int id) {
		return "B" + String.format("%04d", id);
	}
	
}
