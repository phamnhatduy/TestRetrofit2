package com.example.phamn.testretrofit2.Service;

import com.example.phamn.testretrofit2.Model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("getallproducts.php")
    Call<List<Products>> getAllProduct();
}
