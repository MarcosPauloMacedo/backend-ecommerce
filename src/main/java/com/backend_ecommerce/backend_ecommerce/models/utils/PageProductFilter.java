package com.backend_ecommerce.backend_ecommerce.models.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageProductFilter {
    private String name;
    private String category;
    private Double minPrice;
    private Double maxPrice;
}
