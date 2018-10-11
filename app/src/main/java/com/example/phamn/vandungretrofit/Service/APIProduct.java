package com.example.phamn.vandungretrofit.Service;

import com.example.phamn.vandungretrofit.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIProduct {
    @GET("getallproducts.php")
    Call<List<Product>> getAllProduct();

}
