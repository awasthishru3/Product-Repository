package com.estore.productservice.products.model;

import java.util.Date;

import com.estore.productservice.products.service.ProductService.ProductServiceBuilder;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String skuCode;
	private Date mfgDate;
	private Long price;
	private String brand;
	private Inventory inventory;
	

	
	/*
	 * public ProductServiceBuilder inventory(Integer inventoryId, Integer
	 * productId, String productName, String skuCode2, Long quantity, Boolean
	 * isStockEmpty, Date fulfilledDate) { // TODO Auto-generated method stub return
	 * new ProductServiceBuilder(new Inventory(inventoryId, productId,
	 * productName,skuCode2, quantity, isStockEmpty , fulfilledDate)); }
	 */
}

