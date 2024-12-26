package com.backend_ecommerce.backend_ecommerce.interfaces.shared;

public interface DataService <Response, Request> {
    public Response save(Request request);
    public Response update(Request request);
    public Response selectById(Long id);
    public Page<Response> selectAll(int page, int size);
    public void delete(Request request);
}
