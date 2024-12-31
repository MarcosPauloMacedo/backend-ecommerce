package com.backend_ecommerce.backend_ecommerce.interfaces.shared;

public interface DataService <Response, Request> {
    public Response save(Request request);
    public Response update(Long id, Request request);
    public Response selectById(Long id);
    public void delete(Long id);
}
