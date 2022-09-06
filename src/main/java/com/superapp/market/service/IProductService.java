package com.superapp.market.service;

import java.util.List;

import com.superapp.market.dto.ProductDTO;

public interface IProductService {
	
	List<ProductDTO> readProducts();
	
	List<ProductDTO> addProduct(ProductDTO product);
	
	List<ProductDTO> deleteProduct(String idProduct);
	
	ProductDTO readProduct(String idProduct);
	
	ProductDTO updateProduct(ProductDTO product);

}
