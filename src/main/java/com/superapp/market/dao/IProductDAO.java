package com.superapp.market.dao;

import java.util.List;

import com.superapp.market.dto.ProductDTO;

public interface IProductDAO {
	
	public List<ProductDTO> readProducts();
	
	public ProductDTO readProduct(String idProduct);

	public List<ProductDTO> addProduct(ProductDTO product);
	
	public List<ProductDTO> deleteProduct(String idProduct);
	
	public ProductDTO updateProduct(ProductDTO product);
}
