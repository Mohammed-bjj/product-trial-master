package com.alten.shop.utils.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.springframework.data.domain.Sort.Direction;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginationConfig {
    
    @Value("${app.pagination.default-page}")
    private int defaultPage;
    
    @Value("${app.pagination.default-size-public}")
    private int defaultSizePublic;

    @Value("${app.pagination.default-size-admin}")
    private int defaultSizeAdmin;

    @Value("${app.pagination.default-sort}")
    private  String defaultSort;

    public Pageable createPageableForPublic(Integer page, Integer size, String sort) {
        return getPageable(page, size, sort, defaultSizePublic);
    }
    
    public Pageable createPageableForAdmin(Integer page, Integer size, String sort) {
        return getPageable(page, size, sort, defaultSizeAdmin);
    }

    private Pageable getPageable(Integer page, Integer size, String sort, int defaultSizeAdmin) {
        Direction direction = defaultSort.equalsIgnoreCase(sort) ? Direction.ASC : Direction.DESC;
        int actualPage = page != null ? page : defaultPage;
        int actualSize = size != null ? size : defaultSizeAdmin;
        return PageRequest.of(actualPage, actualSize, Sort.by(direction, "price"));
    }
}