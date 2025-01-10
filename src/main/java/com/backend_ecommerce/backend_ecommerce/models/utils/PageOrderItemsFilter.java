package com.backend_ecommerce.backend_ecommerce.models.utils;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageOrderItemsFilter {
    private String category;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
