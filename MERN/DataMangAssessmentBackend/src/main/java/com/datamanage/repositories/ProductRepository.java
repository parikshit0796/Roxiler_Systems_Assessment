package com.datamanage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datamanage.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
