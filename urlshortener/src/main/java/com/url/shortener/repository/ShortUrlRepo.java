package com.url.shortener.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.url.shortener.model.UrlModel;

@Repository
public interface ShortUrlRepo extends CrudRepository<UrlModel, String> {
	
}
