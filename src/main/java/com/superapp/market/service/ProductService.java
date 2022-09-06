package com.superapp.market.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superapp.market.dao.IProductDAO;
import com.superapp.market.dto.ProductDTO;

@Service
public class ProductService implements IProductService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private IProductDAO iProductDAO;

	@Override
	public List<ProductDTO> readProducts() {
		LOGGER.info("Entrando al metodo ---> ProductService.readProducts()");
		return iProductDAO.readProducts();
	}

	@Override
	public List<ProductDTO> addProduct(ProductDTO product) {
		LOGGER.info("Entrando al metodo ---> ProductService.addProduct(ProductDTO product)");
		product.setId(UUID.randomUUID().toString());
		return iProductDAO.addProduct(product);
	}

	@Override
	public List<ProductDTO> deleteProduct(String idProduct) {
		return iProductDAO.deleteProduct(idProduct);
	}

	@Override
	public ProductDTO readProduct(String idProduct) {
		return iProductDAO.readProduct(idProduct);
	}

	@Override
	public ProductDTO updateProduct(ProductDTO product) {
		return iProductDAO.updateProduct(product);
	}
	

}
