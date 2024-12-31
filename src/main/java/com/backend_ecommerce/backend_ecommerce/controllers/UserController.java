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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    @Operation(summary = "Salvar um usuário")
    public UserResponse save(@RequestBody UserRequest request) {
        return userService.save(request);
    }

    @GetMapping()
    @Operation(summary = "Listar todos os usuários")
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
    @Operation(summary = "Buscar um usuário pelo ID")
    public UserResponse selectById(@PathVariable Long id) {
        return userService.selectById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um usuário pelo ID")
    public UserResponse update(@PathVariable Long id, @RequestBody UserRequest request) {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um usuário pelo ID")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
