package com.backend_ecommerce.backend_ecommerce.services.shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.backend_ecommerce.backend_ecommerce.models.utils.PageFilter;

public class CreatePageableTest {

    private CreatePageable createPageable;

    @BeforeEach
    public void setUp() {
        createPageable = new CreatePageable();
    }

    @Test
    public void testExecute() {
        PageFilter pageFilter = new PageFilter();
        pageFilter.setPage(0);
        pageFilter.setSize(10);
        pageFilter.setSortField("name");
        pageFilter.setSortOrder("asc");

        Pageable pageable = createPageable.execute(pageFilter);

        assertNotNull(pageable);
        assertEquals(PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name")), pageable);
    }

    @Test
    public void testExecute_WithDescendingOrder() {
        PageFilter pageFilter = new PageFilter();
        pageFilter.setPage(1);
        pageFilter.setSize(20);
        pageFilter.setSortField("price");
        pageFilter.setSortOrder("desc");

        Pageable pageable = createPageable.execute(pageFilter);

        assertNotNull(pageable);
        assertEquals(PageRequest.of(1, 20, Sort.by(Sort.Direction.DESC, "price")), pageable);
    }
}
