package com.superapp.market.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.superapp.market.dto.ExternalDTO;
import com.superapp.market.service.IExternalService;

@RestController
@CrossOrigin(origins = { "*" }, allowCredentials = "false", allowedHeaders = "*")
@RequestMapping("/external")
public class ExternalControlelr {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private IExternalService iExternalService;
	
	@GetMapping
	public ResponseEntity<List<ExternalDTO>> readExternalData(@RequestHeader("X-ID") String user) {
		LOGGER.info("Entrando al metodo ---> ExternalControlelr.readExternalData() por el user " + user);
		List<ExternalDTO> listExternal = iExternalService.getExternalData();
		return ResponseEntity.status(HttpStatus.CREATED).header("X-ID", user).body(listExternal);
	}

}
