package com.example.Amit.data.model;

import com.google.gson.annotations.SerializedName;

public class AddToCartResponse{

	@SerializedName("message")
	private String message;

	public String getMessage(){
		return message;
	}
}