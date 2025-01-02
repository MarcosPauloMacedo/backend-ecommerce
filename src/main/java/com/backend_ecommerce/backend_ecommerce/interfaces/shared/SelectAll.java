package com.backend_ecommerce.backend_ecommerce.interfaces.shared;

import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;

public interface SelectAll<T> {
    PageResponse<T> selectAll(PageFilter pageFilter);
}
