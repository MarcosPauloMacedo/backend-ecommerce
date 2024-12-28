package com.backend_ecommerce.backend_ecommerce.interfaces.user;

import com.backend_ecommerce.backend_ecommerce.interfaces.shared.DataService;
import com.backend_ecommerce.backend_ecommerce.models.request.UserRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.UserResponse;

public interface DataServiceUser extends DataService<UserResponse, UserRequest> {}
