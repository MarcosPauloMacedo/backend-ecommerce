package com.backend_ecommerce.backend_ecommerce.interfaces.shared;

import com.backend_ecommerce.backend_ecommerce.models.response.PageResponse;

public interface DataService <Response, Request> {
    public Response save(Request request);
    public Response update(Long id, Request request);
    public Response selectById(Long id);
    public PageResponse<Response> selectAll(int page, int size);
    public void delete(Long id);
}
