package com.javatechie.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.javatechie.dto.ProductEvent;
import com.javatechie.entity.Product;
import com.javatechie.repository.ProductRepository;

@Service
public class ProductCommandService {
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	public Product createProduct(ProductEvent productEvent) {
		Product productDO = repository.save(productEvent.getProduct());
		ProductEvent pe = new ProductEvent("CreateProduct",productDO);
		kafkaTemplate.send("product-event-topic", pe);
		return productDO;
	}
	
	public Product updateProduct(Long id,ProductEvent productEvent) {
		Product exsProduct = repository.findById(id).get();
		Product newProd = productEvent.getProduct();
		exsProduct.setName(newProd.getName());
		exsProduct.setDescription(newProd.getDescription());
		exsProduct.setPrice(newProd.getPrice());
		Product productDO = repository.save(exsProduct);
		ProductEvent pe = new ProductEvent("UpdateProduct",productDO);
		kafkaTemplate.send("product-event-topic", pe);
		return productDO;
	}
	
	public void deleteroduct(Product product) {
		repository.delete(product);
	}
	
	

}
