package com.capg.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.capg.rest.entity.Product;
import com.capg.rest.exception.ProductNotFoundException;
import com.capg.rest.repository.ProductRepository;

@Service
	public class ProductService 
	{
		@Autowired
		ProductRepository productRepository;
		
		@Transactional(readOnly = true)
		public List<Product> getAllProducts()
		{
			List<Product> plist = productRepository.findAll();
			if(!plist.isEmpty())
				return plist;
			throw new ProductNotFoundException("No products found");
			//return productRepository.findAll();
		}
		@Transactional(readOnly = true)
		public Product getProductById(int productId)
		{
			Optional<Product> op = productRepository.findById(productId);
			if(op.isPresent())
				return op.get();
			throw new ProductNotFoundException("No product found with ID: "+productId);
		}
		
		@Transactional(readOnly = false)
		public boolean insertOrModifyProduct(Product product)
		{
			Product p = productRepository.save(product);
			return p!= null;
		}
		
		@Transactional(readOnly = false)
		public boolean deleteProductById(int productId)  throws ProductNotFoundException
		{
			long count= productRepository.count();
			productRepository.deleteById(productId);
			if(count > productRepository.count()) {
				return true;
			}
			throw new ProductNotFoundException("Product with id "+ productId +" not found to delete");
		}
		
		@Transactional(readOnly = true)
		public Product getProductByName(String productName)
		{
			Optional<Product> op = productRepository.findByProductName(productName);
			if(op.isPresent())
				return op.get();
			throw new ProductNotFoundException("No product found with Name: "+productName);
		}
	}
