package com.example.Amit.data.model.cart;

import com.example.Amit.data.model.products.Product;
import com.google.gson.annotations.SerializedName;

public class ProductsItem{

	@SerializedName("amount")
	private Double amount;

	@SerializedName("total")
	private Double total;

	@SerializedName("product")
	private Product product;

	@SerializedName("total_text")
	private String totalText;

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmount(){
		return amount;
	}

	public Double getTotal(){
		return total;
	}

	public Product getProduct(){
		return product;
	}

	public String getTotalText(){
		return totalText;
	}
}