package com.microservice.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

import com.microservice.demo.dto.ProductEvent;
import com.microservice.demo.entity.Product;
import com.microservice.demo.repsiotory.ProductCommandRepository;

@Service
public class ProductCommandService {

	@Autowired
	private ProductCommandRepository repository ;
	
	@Autowired
	private KafkaTemplate<String, Object> KafkaTemplate;
	public Product createProduct(ProductEvent productEvent){
        Product productDO = repository.save(productEvent.getProduct());
        ProductEvent event=new ProductEvent("CreateProduct", productDO);
        KafkaTemplate.send("product-event-topic", event);
        return productDO;
	}
	
//    public Product createProduct(ProductEvent productEvent){
//		Product productDO = repository.save(ProductEvent.getProduct());
//        Product productDO = repository.save(productEvent.getProduct());
//
//		ProductEvent event = new ProductEvent("CreateProduct" ,productDO )
//        
//		KafkaTemplate.send("product-event-topic", productEvent);
//		return productDO;
//	}
	
	public Product updateProduct(long id , ProductEvent productEvent) {
		Product existingProduct = repository.findById(id).get();
//		Product newProduct =ProductEvent.getProduct();
        Product newProduct=productEvent.getProduct();

		existingProduct.setName(newProduct.getName());
		existingProduct.setPrice(newProduct.getPrice());
		existingProduct.setDescription(newProduct.getDescription());
		Product productDO = repository.save(existingProduct);
		
		ProductEvent event= new ProductEvent("UpdateProduct" ,productDO);
		KafkaTemplate.send("product-event-topic" , event );
		return productDO;
	}
}
