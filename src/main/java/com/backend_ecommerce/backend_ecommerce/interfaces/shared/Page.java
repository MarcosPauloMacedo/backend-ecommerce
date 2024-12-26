package com.backend_ecommerce.backend_ecommerce.interfaces.shared;

public interface Page <T>{
    public T[] data();
    public int totalItems();
}
