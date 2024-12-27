package com.backend_ecommerce.backend_ecommerce.models.response;

import java.util.List;

import lombok.Data;

@Data
public class PageResponse <Response> {
    private List<Response> content;
    private long totalPages;
}
