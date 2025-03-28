package com.demo.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.Entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
}


