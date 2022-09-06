package com.superapp.market.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.superapp.market.dto.ProductDTO;
import com.superapp.market.service.ProductService;

@Repository
public class ProductDAO implements IProductDAO{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@Override
	public List<ProductDTO> readProducts() {
	    return readListProducts();
	}
	
	@Override
	public ProductDTO readProduct(String idProduct) {
		String currentProduct;
		ProductDTO product = new ProductDTO();
		
		try {
			
			BufferedReader bufferReader = new BufferedReader(new FileReader(getFileProducts()));
			while((currentProduct = bufferReader.readLine()) != null) {
				
			    if(-1 != currentProduct.trim().indexOf(idProduct)) {
			        String[] values = currentProduct.split("\\|");
			        if(values.length == 4) {
			        	product.setId(values[0]);
				        product.setName(values[1]);
				        product.setDescription(values[2]);
				        product.setPrice(Double.valueOf(values[3]));
			        }
			        break;
			    } 
			    
			}
			bufferReader.close(); 
		} catch (IOException e) {
			LOGGER.error("Error get product data ..........", e);
		}
		purgeFileProduct();
		return product;
	}

	@Override
	public List<ProductDTO> addProduct(ProductDTO product) {
		try {
			FileWriter fileWritter = new FileWriter(getFileProducts(),true);        
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            StringBuilder data = new StringBuilder();
            
            data.append(product.getId()).append("|").append(product.getName()).append("|")
            	.append(product.getDescription()).append("|").append(product.getPrice());
            
            bufferWritter.newLine();
            bufferWritter.write(data.toString());
            
            bufferWritter.close();
            fileWritter.close();
		} catch (IOException e) {
			LOGGER.error("Error to write data new product ..........", e);
		} 
		purgeFileProduct();
		return readListProducts();
	}

	@Override
	public List<ProductDTO> deleteProduct(String idProduct) {
		try {
			String currentProduct;
			File tempFile = new File("tempFile.txt");
	
			BufferedReader bufferReader = new BufferedReader(new FileReader(getFileProducts()));
			BufferedWriter bufferWritter = new BufferedWriter(new FileWriter(tempFile));
			
			while((currentProduct = bufferReader.readLine()) != null) {
				
			    if(-1 != currentProduct.trim().indexOf(idProduct)) {
			    	continue;
			    } 
			    
			    bufferWritter.write(currentProduct);
			    bufferWritter.newLine();
			}
			bufferWritter.close(); 
			bufferReader.close(); 
			tempFile.renameTo(getFileProducts());
		} catch (IOException e) {
			LOGGER.error("Error to delete product ..........", e);
		}
		purgeFileProduct();
		return readListProducts();
	}
	
	@Override
	public ProductDTO updateProduct(ProductDTO product) {
		ProductDTO currentProduct = readProduct(product.getId());
		if(null == product.getName() || "".equals(product.getName())) {
			product.setName(currentProduct.getName());
		}
		if(null == product.getDescription() || "".equals(product.getDescription())) {
			product.setDescription(currentProduct.getDescription());
		}
		if(null == product.getPrice() || "".equals(product.getPrice())) {
			product.setPrice(currentProduct.getPrice());
		}
		deleteProduct(product.getId());
		addProduct(product);
		return product;
	}
	
	private void purgeFileProduct() {
		try {
			String currentProduct;
			File tempFile = new File("tempFile.txt");
	
			BufferedReader bufferReader = new BufferedReader(new FileReader(getFileProducts()));
			BufferedWriter bufferWritter = new BufferedWriter(new FileWriter(tempFile));
			
			while((currentProduct = bufferReader.readLine()) != null) {
				
			    if("".equals(currentProduct.trim())) {
			    	continue;
			    } 
			    
			    bufferWritter.write(currentProduct);
			    bufferWritter.newLine();
			}
			bufferWritter.close(); 
			bufferReader.close(); 
			tempFile.renameTo(getFileProducts());
		} catch (IOException e) {
			LOGGER.error("Error to purge product ..........", e);
		}
	}
	
	private List<ProductDTO> readListProducts() {
		Scanner input;
	    List<ProductDTO> products = new ArrayList<>();
		try {
			Resource resource = new ClassPathResource("products.txt");
			input = new Scanner(resource.getFile());
			while(input.hasNextLine()) {
		        ProductDTO product = new ProductDTO();
		        String[] values = input.nextLine().split("\\|");
		        if(values.length == 4) {
		        	product.setId(values[0]);
			        product.setName(values[1]);
			        product.setDescription(values[2]);
			        product.setPrice(Double.valueOf(values[3]));
			        products.add(product);
		        }
		    }
		} catch (IOException e) {
			LOGGER.error("Error to read data products ..........", e);
		} 
		return products;
	}

	private File getFileProducts() throws FileNotFoundException {
		Resource resource = new ClassPathResource("products.txt");
		try {
			return resource.getFile();
		} catch (IOException e) {
			LOGGER.error("Error to read data products ..........", e);
			throw new FileNotFoundException("File not exits in project");
		}
	}

}
