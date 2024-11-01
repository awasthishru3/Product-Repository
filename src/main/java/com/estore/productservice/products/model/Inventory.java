package com.estore.productservice.products.model;

import java.util.Date;

import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {
	private Integer inventoryId;
	private Integer productId;
	private String productName;
	private String skuCode;
	private Long quantity;
	private boolean isStockEmpty;
	private Date fulfilledDate;
	
	
}
