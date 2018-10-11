package com.example.phamn.testretrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.phamn.testretrofit2.Adapter.ProductAdapter;
import com.example.phamn.testretrofit2.Model.Products;
import com.example.phamn.testretrofit2.Service.APIService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view) RecyclerView recyclerview;
    RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Products> productList;
    private ProductAdapter productAdapter;
    String TAG = MainActivity.class.getSimpleName();
    String URL_GET_PRODUCT = "http://dev.androidcoban.com/blog/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productList = new ArrayList<>();
        ButterKnife.bind(this);
        addControl();
    }

    private void addControl() {
        recyclerview.setHasFixedSize(true);
        // Create 2 col
        mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerview.setLayoutManager(mLayoutManager);
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(productList, MainActivity.this);
        recyclerview.setAdapter(productAdapter);
    }

    @OnClick(R.id.button)
    public void getProduct() {
        getAllProduct();
        // productAdapter.notifyDataSetChanged();
    }

    private void getAllProduct() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_GET_PRODUCT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<Products>> call = apiService.getAllProduct();
        call.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                List<Products> productsList = response.body();
                for (int i = 0; i<productsList.size() ; i++) {
                    productList.add(productsList.get(i));
                    Log.d(TAG, "onResponse" + productsList.get(i).toString());
                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
