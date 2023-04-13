package com.url.shortener.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.shortener.model.UrlModel;
import com.url.shortener.repository.ShortUrlRepo;

@Service
public class UrlShortenerService {
	
	@Autowired
	private ShortUrlRepo shortUrlRepo;
	
	private Logger logger = LoggerFactory.getLogger(UrlShortenerService.class);
	
	public UrlModel getShortUrlModel(String url) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//		String byteHashCode = new String(MessageDigest.getInstance("SHA-512").digest(url.getBytes()));
		String byteHashCode = Base62Encoder.encode(url);
		logger.info("Generated hashcode = " + new String(byteHashCode) + " for url: " + url);
		
		Optional<UrlModel> urlModelFromRepo = findUrlFromHashCode(byteHashCode);
		if (urlModelFromRepo.isPresent()) {
			logger.info("Url found in repo");
			return urlModelFromRepo.get();
		}
		UrlModel model = new UrlModel();
		model.setHashCode(byteHashCode);
		model.setUrl(url);
		
		System.out.println(model.getUrl());
		
		shortUrlRepo.save(model);
		logger.info("Short url generated successfully.");
		return model;
	}
	
	private Optional<UrlModel> findUrlFromHashCode(String hashCode) {
		return shortUrlRepo.findById(hashCode);
	}

	public String getUrlFromShortUrl(String shortUrl) {
		Optional<UrlModel> optionalUrl = shortUrlRepo.findById(shortUrl);
		if (optionalUrl.isEmpty()) {
			logger.info("Url not found");
			return null;
		}
		logger.info("Url found: " + optionalUrl.get().getUrl());
		return optionalUrl.get().getUrl();
	}

}
