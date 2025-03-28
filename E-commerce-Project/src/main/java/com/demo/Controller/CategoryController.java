package com.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.Entity.Category;
import com.demo.Service.EcommerceService;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private EcommerceService service;

    @GetMapping
    public Page<Category> getAllCategories(Pageable pageable) {
        return service.getAllCategories(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return service.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return service.saveCategory(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return service.getCategoryById(id).map(existingCategory -> {
        	 category.setId(id);
            return ResponseEntity.ok(service.saveCategory(category));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (service.getCategoryById(id).isPresent()) {
            service.deleteCategory(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
