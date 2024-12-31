package com.backend_ecommerce.backend_ecommerce.services.product;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import com.backend_ecommerce.backend_ecommerce.models.entity.Product;
import com.backend_ecommerce.backend_ecommerce.models.utils.PageProductFilter;

public class ProductSearchTest {

    @InjectMocks
    private ProductSearch productSearch;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute_WithName() {
        PageProductFilter filter = new PageProductFilter();
        filter.setName("Test");

        Specification<Product> specification = productSearch.execute(filter);

        assertNotNull(specification);
    }

    @Test
    public void testExecute_WithCategory() {
        PageProductFilter filter = new PageProductFilter();
        filter.setCategory("Electronics");

        Specification<Product> specification = productSearch.execute(filter);

        assertNotNull(specification);
    }

    @Test
    public void testExecute_WithPriceRange() {
        PageProductFilter filter = new PageProductFilter();
        filter.setMinPrice(100.0);
        filter.setMaxPrice(500.0);

        Specification<Product> specification = productSearch.execute(filter);

        assertNotNull(specification);
    }

    @Test
    public void testExecute_WithAllFilters() {
        PageProductFilter filter = new PageProductFilter();
        filter.setName("Test");
        filter.setCategory("Electronics");
        filter.setMinPrice(100.0);
        filter.setMaxPrice(500.0);

        Specification<Product> specification = productSearch.execute(filter);

        assertNotNull(specification);
    }

    @Test
    public void testExecute_WithNoFilters() {
        PageProductFilter filter = new PageProductFilter();

        Specification<Product> specification = productSearch.execute(filter);

        assertNotNull(specification);
    }
}
