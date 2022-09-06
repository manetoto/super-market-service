package com.superapp.market.dto;

import java.io.Serializable;

public class ExternalDTO implements Serializable{

	private static final long serialVersionUID = -5715060929509381229L;
	
	private Long id;
	
	private Long userId;
	
	private String title;
	
	private String body;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
