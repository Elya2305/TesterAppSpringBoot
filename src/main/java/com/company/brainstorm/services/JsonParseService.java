package com.company.rest.service;

import com.company.rest.domain.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JsonParseService implements ParsingService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Quote parse(String url) {
        return restTemplate.getForObject(url, Quote.class);
    }
}
