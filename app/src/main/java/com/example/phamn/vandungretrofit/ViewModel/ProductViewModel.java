package com.example.phamn.vandungretrofit.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.example.phamn.vandungretrofit.Model.Product;
import com.example.phamn.vandungretrofit.Service.APIProduct;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductViewModel extends AndroidViewModel {
    public List<Product> productList = new ArrayList<>();
    public MutableLiveData<Product> product = new MutableLiveData<>();

    public ProductViewModel(@NonNull Application application) {
        super(application);
        getAllProduct();
    }

    private void getAllProduct() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dev.androidcoban.com/blog/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIProduct apiService = retrofit.create(APIProduct.class);
        Call<List<Product>> call = apiService.getAllProduct();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productsList = response.body();
                for (int i = 0; i< productsList.size() ; i++) {
                    //productList.add(productsList.get(i));
                }
                product.setValue(productsList.get(2));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                //Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public MutableLiveData<Product> getProduct() {
        return product;
    }
}
