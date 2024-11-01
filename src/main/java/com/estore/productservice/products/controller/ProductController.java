package com.estore.productservice.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.estore.productservice.products.exceptions.ErrorResponse;
import com.estore.productservice.products.exceptions.ProductAlreadyPresentException;
import com.estore.productservice.products.exceptions.ProductNotFoundException;
import com.estore.productservice.products.model.Products;
import com.estore.productservice.products.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/getProduct/{id}")
	public Products getProductById(@PathVariable Integer id) {
		return productService.getProductById(id);
	}

	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Products>> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@PostMapping("/saveProduct")
	public ResponseEntity<Products> saveProduct(@RequestBody Products products) {
		return productService.saveProductDetails(products);
	}


	@GetMapping("/getProduct/feign/{id}")
	public Products getProductByIdUsingFeign(@PathVariable Integer id) {
		return productService.getProductByIdUsingFeign(id);
	}
	// Exception Handler method added in CustomerController to handle
	// CustomerAlreadyExistsException

//	@ExceptionHandler(value = ProductAlreadyPresentException.class)
//	@ResponseStatus(HttpStatus.CONFLICT)
//	public ErrorResponse handleProductAlreadyExistsException(ProductAlreadyPresentException ex) {
//		return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
//	}
//	
//	@ExceptionHandler(value = ProductNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ErrorResponse handleProductNotFoundException(ProductNotFoundException ex) {
//		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
//	}

}
