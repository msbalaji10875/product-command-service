package com.javatechie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.dto.ProductEvent;
import com.javatechie.entity.Product;
import com.javatechie.service.ProductCommandService;

@RestController
@RequestMapping("/products")
public class ProductCommandController {
	
	@Autowired
	private ProductCommandService productCommandService;
	
	@PostMapping
	public Product createProduct(@RequestBody ProductEvent productDO) {
		
		return productCommandService.createProduct(productDO);
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id,@RequestBody ProductEvent product) {
		return productCommandService.updateProduct(id, product);
	}
	
	

}
