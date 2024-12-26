package com.backend_ecommerce.backend_ecommerce.interfaces.shared;

public interface Mapper<Request,Response, Entity> {
    public Entity toEntity(Request request);
    public Response toResponse(Entity entity);
}
