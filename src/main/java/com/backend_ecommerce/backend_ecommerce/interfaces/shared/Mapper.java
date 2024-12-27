package com.backend_ecommerce.backend_ecommerce.interfaces.shared;

import java.util.List;

import org.springframework.data.domain.Page;

import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;

public interface Mapper<Request,Response, Entity> {
    public Entity toEntity(Request request);
    public Entity toEntity(Long id, Request request);
    public Response toResponse(Entity entity);
    public List<Response> toResponseList(List<Entity> entities);
    public PageResponse<Response> toResponsePage(Page<Entity> entities);
}
