package com.microservice.demo.repsiotory;

import com.microservice.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductCommandRepository extends JpaRepository <Product , Long>{

	
}
