package com.backend_ecommerce.backend_ecommerce.interfaces.user;

import com.backend_ecommerce.backend_ecommerce.interfaces.shared.DataService;
import com.backend_ecommerce.backend_ecommerce.models.request.UserRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.UserResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;

public interface DataServiceUser extends DataService<UserResponse, UserRequest> {
    PageResponse<UserResponse> selectAll(PageFilter pageFilter);
    Boolean existsByEmail(String email);
    void updatePassword(String email, String password);
}
