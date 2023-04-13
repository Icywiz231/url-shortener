package com.url.shortener.controller;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import org.springframework.http.ResponseEntity;

import com.url.shortener.model.UrlModel;

public interface UrlShortenerContoller {
	
	public ResponseEntity<UrlModel> getShortUrlModel (String url) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	public ResponseEntity<URI> getUrlFromShortUrl (String shortUrl) throws URISyntaxException, UnsupportedEncodingException;
}
