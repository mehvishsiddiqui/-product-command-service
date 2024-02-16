package com.microservice.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.demo.dto.ProductEvent;
import com.microservice.demo.entity.Product;
import com.microservice.demo.service.ProductCommandService;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

	 @Autowired
	 private ProductCommandService commandService;
	 
	 @PostMapping
	 public Product createProduct(@RequestBody ProductEvent productEvent) {
			return commandService.createProduct(productEvent);
		}
	 
	 @PutMapping("/{id}")
	 public Product updateProduct(@PathVariable Long id , @RequestBody ProductEvent productEvent) {
		 return commandService.updateProduct(id, productEvent);
	 }
}
