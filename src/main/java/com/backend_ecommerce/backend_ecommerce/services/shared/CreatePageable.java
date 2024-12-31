package com.backend_ecommerce.backend_ecommerce.services.shared;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;

@Service
public class CreatePageable {
    
    public Pageable execute(PageFilter pageFilter) {
        Sort.Direction direction = getDirection(pageFilter.getSortOrder());

        int page = pageFilter.getPage();
        int size = pageFilter.getSize();
        Sort sort = Sort.by(direction, pageFilter.getSortField());

        return PageRequest.of(page, size, sort);
    }

    private Sort.Direction getDirection(String sortOrder) {
        return Sort.Direction.fromString(sortOrder.toUpperCase());
    }

}
