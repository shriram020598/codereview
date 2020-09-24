package com.htc.main;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.htc.beans.Product;

public class ProductClientData 
{
	public static void main(String[] args) 
	{
		RestTemplate restTemplate=new RestTemplate();
		
		Product product=new Product("P12345", "MotrolaG5Plus", 9, 19000.0);
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.add("Content-type", "application/json");
		HttpEntity<Product> httpEntity=new HttpEntity<Product>(product, httpHeaders);
		
		//add product in server
		boolean status=restTemplate.postForObject("http://localhost:8080/addProduct", httpEntity, Boolean.class);
		System.out.println(status);
		System.out.println("Product has Been Added Successfully");
		
		//get product on productId
		String productId="P99";
		ResponseEntity<Product> responseEntity=restTemplate.getForEntity("http://localhost:8080/getProduct/"+productId, Product.class);
		Product product2=responseEntity.getBody();
		System.out.println(product2);
		
		//get all products
		List<Product> list=restTemplate.getForObject("http://localhost:8080/allProducts", List.class);
		System.out.println(list);
		
		//remove the product
		String productId2="P9";
		restTemplate.delete("http://localhost:8080/removeProduct/"+productId2);
		
		//get all products
		List<Product> list2=restTemplate.getForObject("http://localhost:8080/allProducts", List.class);
		System.out.println(list2);
	}
}
