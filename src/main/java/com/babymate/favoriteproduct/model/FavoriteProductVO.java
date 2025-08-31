package com.babymate.favoriteproduct.model;

import java.sql.Date;

public class FavoriteProductVO implements java.io.Serializable {
	private Integer favorite_product_id;
	private Integer member_id;
	private Integer product_id;
	private Date update_time;
	
	public Integer getFavorite_product_id() {
		return favorite_product_id;
	}
	public void setFavorite_product_id(Integer favorite_product_id) {
		this.favorite_product_id = favorite_product_id;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	
}
