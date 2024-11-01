package com.estore.productservice.products.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.estore.productservice.products.entity.ProductEntity;
import com.estore.productservice.products.exceptions.ProductAlreadyPresentException;
import com.estore.productservice.products.exceptions.ProductNotFoundException;
import com.estore.productservice.products.model.Inventory;
import com.estore.productservice.products.model.Products;
import com.estore.productservice.products.repo.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Service
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	InventoryProxy proxy;
	
	@Autowired
	RestTemplate restTemplate;

	public Products getProductById(Integer id) {
		ProductEntity productEntity = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found with id = " + id));
	//	Inventory inventoryDetails = new Inventory(productEntity.getId(),productEntity , null, null, false, null)
		Map<String, String> uriVariable = new java.util.HashMap<>();
		uriVariable.put("name", productEntity.getName());
		ResponseEntity<Inventory> inventoryDetails = restTemplate.getForEntity("http://localhost:8084/inventory/getInventoryDetails/{name}", Inventory.class, uriVariable);
		Products product = Products.builder().id(productEntity.getId()).name(productEntity.getName())
				.skuCode(productEntity.getSkuCode()).mfgDate(productEntity.getMfgDate()).price(productEntity.getPrice())
				.brand(productEntity.getBrand())
				.inventory(inventoryDetails.getBody())
				.build();

		return product;

	}

	public ResponseEntity<Products> saveProductDetails(Products products) {

		ProductEntity productIsPresent = productRepository.findByName(products.getName());

		if (null == productIsPresent) {
			ProductEntity productEntity = ProductEntity.builder().name(products.getName())
					.skuCode(products.getSkuCode()).mfgDate(products.getMfgDate()).price(products.getPrice())
					.brand(products.getBrand()).build();
			ProductEntity savedProductEntity = productRepository.save(productEntity);
			Inventory inventory = Inventory.builder().productId(savedProductEntity.getId())
					.productName(products.getName())
					.skuCode(products.getSkuCode())
					.quantity(products.getInventory().getQuantity())
					.isStockEmpty(products.getInventory().isStockEmpty())
					.fulfilledDate(products.getInventory().getFulfilledDate()).build();
					
			ResponseEntity<Inventory> savedInventory = restTemplate.postForEntity("http://localhost:8084/inventory/save/inventory/details", inventory,  Inventory.class);
			Products savedProducts = convertEntityToDTOObject(savedProductEntity);
			savedProducts.setInventory(savedInventory.getBody());
			return new ResponseEntity(savedProducts, HttpStatus.CREATED);
		} else {
			throw new ProductAlreadyPresentException("Product is already present with Id= " + productIsPresent.getId());
		}
	}

	public ResponseEntity<List<Products>> getAllProducts() {
		// TODO Auto-generated method stub
		List<ProductEntity> productEntityList = productRepository.findAll();
		List<Products> productDTOList = productEntityList.stream().map(x -> convertEntityToDTOObject(x)).toList();
		return new ResponseEntity<List<Products>>(productDTOList, HttpStatus.OK);
	}

	public Products convertEntityToDTOObject(ProductEntity productEntity) {
		return Products.builder().id(productEntity.getId()).name(productEntity.getName())
				.skuCode(productEntity.getSkuCode()).mfgDate(productEntity.getMfgDate()).brand(productEntity.getBrand())
				.price(productEntity.getPrice()).build();

	}

	public Products getProductByIdUsingFeign(Integer id) {
		ProductEntity productEntity = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found with id = " + id));
	
		
		ResponseEntity<Inventory> inventoryDetails = proxy.getProductInventoryDetails(productEntity.getName());
		Products product = Products.builder().id(productEntity.getId()).name(productEntity.getName())
				.skuCode(productEntity.getSkuCode()).mfgDate(productEntity.getMfgDate()).price(productEntity.getPrice())
				.brand(productEntity.getBrand())
				.inventory(inventoryDetails.getBody())
				.build();

		return product;
	}

	
}
