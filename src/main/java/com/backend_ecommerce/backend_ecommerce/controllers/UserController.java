package com.backend_ecommerce.backend_ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend_ecommerce.backend_ecommerce.models.request.UserRequest;
import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;
import com.backend_ecommerce.backend_ecommerce.models.response.UserResponse;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;
import com.backend_ecommerce.backend_ecommerce.services.user.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public UserResponse save(@RequestBody UserRequest request) {
        return userService.save(request);
    }

    @GetMapping()
    public PageResponse<UserResponse> selectAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "ASC") String sortOrder,
        @RequestParam(defaultValue = "id") String sortField
    ) {
        var pageFilter = new PageFilter(page, size, sortOrder, sortField);
        return userService.selectAll(pageFilter);
    }

    @GetMapping("/{id}")
    public UserResponse selectById(@PathVariable Long id) {
        return userService.selectById(id);
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody UserRequest request) {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
