package com.superapp.market.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.superapp.market.dto.ProductDTO;
import com.superapp.market.service.IProductService;

@RestController
@CrossOrigin(origins = { "*" }, allowCredentials = "false", allowedHeaders = "*")
@RequestMapping("/products")
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private IProductService iProductService;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> readProducts(@RequestHeader("X-ID") String user) {
		LOGGER.info("Entrando al metodo ---> ProductController.readProducts() por el user " + user);
		List<ProductDTO> listProducts = iProductService.readProducts();
		return ResponseEntity.status(HttpStatus.CREATED).header("X-ID", user).body(listProducts);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> readProduct(@PathVariable String id, @RequestHeader("X-ID") String user) {
		LOGGER.info("Entrando al metodo ---> ProductController.readProduct(String id) por el user " + user);
		ProductDTO product = iProductService.readProduct(id);
		return ResponseEntity.status(HttpStatus.OK).header("X-ID", user).body(product);
	}
	
	@PostMapping
	public ResponseEntity<List<ProductDTO>> createProduct(@RequestBody ProductDTO product, @RequestHeader("X-ID") String user) {
		LOGGER.info("Entrando al metodo ---> ProductController.createProduct(ProductDTO product) por el user " + user);
		List<ProductDTO> listProducts = iProductService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).header("X-ID", user).body(listProducts);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<List<ProductDTO>> deleteProduct(@PathVariable String id, @RequestHeader("X-ID") String user) {
		LOGGER.info("Entrando al metodo ---> ProductController.deleteProduct(String id) por el user " + user);
		List<ProductDTO> listProducts = iProductService.deleteProduct(id);
		return ResponseEntity.status(HttpStatus.OK).header("X-ID", user).body(listProducts);
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @RequestBody ProductDTO product, @RequestHeader("X-ID") String user) {
		LOGGER.info("Entrando al metodo ---> ProductController.updateProduct(String id, ProductDTO product) por el user " + user);
		product.setId(id);
		product = iProductService.updateProduct(product);
		return ResponseEntity.status(HttpStatus.OK).header("X-ID", user).body(product);
	}

}
