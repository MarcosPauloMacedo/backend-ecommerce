package com.backend_ecommerce.backend_ecommerce.interfaces.user;

import com.backend_ecommerce.backend_ecommerce.interfaces.shared.Mapper;
import com.backend_ecommerce.backend_ecommerce.models.entity.User;
import com.backend_ecommerce.backend_ecommerce.models.request.UserRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.UserResponse;

public interface MapperUser extends Mapper<UserRequest, UserResponse, User> {}
