package com.backend_ecommerce.backend_ecommerce.models.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageFilter {
    private int page;
    private int size;
    private String sortOrder;
    private String sortField;
}
