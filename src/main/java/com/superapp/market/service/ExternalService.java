package com.superapp.market.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.superapp.market.dto.ExternalDTO;

@Service
public class ExternalService implements IExternalService{
	
	@Value("${service.external.url}")
	private String urlExternal;

	@Override
	public List<ExternalDTO> getExternalData() {
		RestTemplate rest = new RestTemplate();
		ResponseEntity<List<ExternalDTO>> externalData = rest.exchange(urlExternal, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<ExternalDTO>>() {});
		return externalData.getBody();
	}

}
