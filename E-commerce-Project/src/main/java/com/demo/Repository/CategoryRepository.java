package com.demo.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.Entity.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}

