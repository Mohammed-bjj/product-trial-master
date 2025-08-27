package com.alten.shop.utils.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PaginationConfig {
    
    @Value("${app.pagination.default-page}")
    private int defaultPage;
    
    @Value("${app.pagination.default-size-public}")
    private int defaultSizePublic;
    
    @Value("${app.pagination.default-size-admin}")
    private int defaultSizeAdmin;
    
    public Pageable createPageableForPublic(Integer page, Integer size) {
        int actualPage = page != null ? page : defaultPage;
        int actualSize = size != null ? size : defaultSizePublic;
        return PageRequest.of(actualPage, actualSize);
    }
    
    public Pageable createPageableForAdmin(Integer page, Integer size) {
        int actualPage = page != null ? page : defaultPage;
        int actualSize = size != null ? size : defaultSizeAdmin;
        return PageRequest.of(actualPage, actualSize);
    }
}