package com.babymate.category.model;

import java.util.List;
import java.util.Set;

import com.babymate.product.model.ProductVO;

public interface CategoryDAO_interface {
	public void insert(CategoryVO categoryVO);
	public void update(CategoryVO categoryVO);
	public void delete(Integer category_id);
	public CategoryVO findByPrimaryKey(Integer category_id);
	public List<CategoryVO> getAll();
	
	public Set<ProductVO> getProductsByCategory_id(Integer category_id);
	
}
