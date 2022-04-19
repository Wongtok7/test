package com.firstdevelop.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.firstdevelop.boot.entity.Order;
import com.firstdevelop.boot.entity.Product;
//DBに保存処理用メソッド
@Mapper
public interface OrderMapper {
	public void insert(
			@Param("quantity")int quantity, 
			@Param("productID")int productID, 
			@Param("userID")Long userID);
	
	public List<Order> searchByUserId(@Param("userID")Long id);
}
