package com.url.shortener.controller;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortener.model.UrlModel;
import com.url.shortener.service.UrlShortenerService;

@RestController
public class UrlShortenerControllerImpl implements UrlShortenerContoller {

	@Autowired
	private UrlShortenerService urlShortenerService;

	@Override
	@PostMapping("/api/shorturl")
	public ResponseEntity<UrlModel> getShortUrlModel(@RequestBody String url) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		UrlModel urlModel = urlShortenerService.getShortUrlModel(url);
		return ResponseEntity.ok(urlModel);
	}

	@Override
	@GetMapping("/api/shorturl/{shorturl}")
	public ResponseEntity<URI> getUrlFromShortUrl(@PathVariable("shorturl") String shortUrl) throws URISyntaxException, UnsupportedEncodingException {
		String urlStr = urlShortenerService.getUrlFromShortUrl(shortUrl);
		urlStr = java.net.URLEncoder.encode(urlStr, "UTF-8");
		System.out.println(urlStr);
		if (null == urlStr) {
			return ResponseEntity.notFound().build();
		}
		URI url = new URI(urlStr);
		return new ResponseEntity<>(url, HttpStatus.TEMPORARY_REDIRECT);
	}
}
