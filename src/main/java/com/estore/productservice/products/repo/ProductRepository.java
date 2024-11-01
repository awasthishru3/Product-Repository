package com.estore.productservice.products.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estore.productservice.products.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{

	ProductEntity findByName(String name);

//	ProductEntity findByName(String string);

}
