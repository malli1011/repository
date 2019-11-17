package com.demo1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectorsExample {

	public static void main(String[] args) {
		Map<Integer, Product> productMap = new HashMap<Integer, Product>();
		for (int i = 1; i <= 5; i++) {
			productMap.put(i, new Product(i, "product" + i, i * 250.5f));
		}
		productMap.forEach((key, value) -> System.out.println("Key::" + key + " Value::" + value));

		Map<Integer, Product> filteredMap = productMap.entrySet().stream().filter(s -> s.getValue().productPrice > 750)
				.collect(Collectors.toMap(key -> key.getKey(), value -> value.getValue()));
		
		filteredMap.forEach((key, value) -> System.out.println("Filtered:::Key::" + key + " Value::" + value));
		
		System.out.println("Count ::"+filteredMap.entrySet().stream().count());
		
		List list = filteredMap.entrySet().stream().collect(Collectors.toList());
		System.out.println(list);
		
	}

}

class Product {
	Integer productId;
	String productName;
	Float productPrice;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ "]";
	}

	public Product(Integer productId, String productName, Float productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
	}

}
