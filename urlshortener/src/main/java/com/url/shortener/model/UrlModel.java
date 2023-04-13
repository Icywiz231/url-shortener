package com.url.shortener.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UrlModel {
	
	public UrlModel() {} 
	
	@Id
	private String hashCode;
	
	@Column(columnDefinition="TEXT")
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHashCode() {
		return hashCode;
	}
	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}
	@Override
	public String toString() {
		return "UrlModel [hashCode=" + hashCode + ", url=" + url + "]";
	}
}
