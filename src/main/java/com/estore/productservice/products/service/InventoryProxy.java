package com.estore.productservice.products.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.estore.productservice.products.model.Inventory;

//@FeignClient(name="inventory-server", url="localhost:8084")
@FeignClient(name="inventory-server")
public interface InventoryProxy {
	@GetMapping("/inventory/getInventoryDetails/{productName}")
	public ResponseEntity<Inventory> getProductInventoryDetails(@PathVariable String productName);
	
}
