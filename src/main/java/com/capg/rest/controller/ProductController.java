package com.capg.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capg.rest.entity.Product;
import com.capg.rest.exception.ProductNotFoundException;
import com.capg.rest.service.ProductService;

//@Controller
//@ResponseBody
@RestController // - it is a combination of both responsebody and controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Product>> getAllProducts() {
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}

	@GetMapping(value = "/{productId}", produces = "application/json")
	public ResponseEntity<Product> getProductId(@PathVariable int productId) {
		return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.OK);
	}
	@GetMapping(value="/productName/{productName}",produces = "application/json")
	public ResponseEntity<Product> getProductName(@PathVariable String productName)
	{
		return new ResponseEntity<>(productService.getProductByName(productName), HttpStatus.OK);

	}
	
	@PostMapping(value="/", consumes = "application/json")
	public HttpStatus addProductDetails(@RequestBody Product product)
	{
		if(productService.insertOrModifyProduct(product))
			return HttpStatus.OK;
		return HttpStatus.NOT_MODIFIED;
	}
	
	@PutMapping(value="/", consumes = "application/json")
	public HttpStatus modifyProductDetails(@RequestBody Product product)
	{
		if(productService.insertOrModifyProduct(product))
			return HttpStatus.OK;
		return HttpStatus.NOT_MODIFIED;
	}
	
	@DeleteMapping(value="/{productId}")
	public HttpStatus deleteProductById(@PathVariable int productId) {
//		if(productService.deleteProductById(productId))  {
//			return HttpStatus.OK;
//		}
//		return HttpStatus.NOT_FOUND;
		productService.deleteProductById(productId);
		return HttpStatus.OK;
	}
	
	/*
	 * @ExceptionHandler(ProductNotFoundException.class) public HttpStatus
	 * productNotFoundExceptionhandler(ProductNotFoundException ex) {
	 * System.out.println(ex); return HttpStatus.NO_CONTENT; }
	 */

}
