package com.example.Amit.data.model.products;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("products")
	private List<Product> products;

	public List<Product> getProducts(){
		return products;
	}
}