package com.company.rest.service;

import com.company.rest.domain.Quote;
import org.springframework.stereotype.Service;

public interface ParsingService {
    Quote parse(String url);
}
