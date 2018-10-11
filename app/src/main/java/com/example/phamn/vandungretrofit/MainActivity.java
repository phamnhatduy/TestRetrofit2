package com.example.phamn.vandungretrofit;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamn.vandungretrofit.Model.Product;
import com.example.phamn.vandungretrofit.Model.QuestionPart1;
import com.example.phamn.vandungretrofit.Service.APIProduct;
import com.example.phamn.vandungretrofit.Service.APIService;
import com.example.phamn.vandungretrofit.ViewModel.MainViewModel;
import com.example.phamn.vandungretrofit.ViewModel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_answerA) TextView tvAnswerA;
    @BindView(R.id.tv_answerB) TextView tvAnswerB;
    @BindView(R.id.tv_answerC) TextView tvAnswerC;
    @BindView(R.id.tv_answerD) TextView tvAnswerD;

    MainViewModel mainViewModel;
    ProductViewModel productViewModel;
    private ArrayList<Product> productList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        liveDataListener();
    }

    private void liveDataListener() {
        mainViewModel.getQuestion().observe(this, new Observer<QuestionPart1>() {
            @Override
            public void onChanged(@Nullable QuestionPart1 questionPart1) {
                Toast.makeText(MainActivity.this, "zzz " + questionPart1.getNumber(), Toast.LENGTH_SHORT).show();
                tvAnswerA.setText(questionPart1.getNumber());
                //tvAnswerB.setText(questionPart1.getAnswerB());
                tvAnswerC.setText(questionPart1.getAnswerC());
                tvAnswerD.setText(mainViewModel.getQuestion().getValue().getAnswerD());
            }
        });
        productViewModel.getProduct().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(@Nullable Product product) {
                //tvAnswerA.setText(productViewModel.getProduct().getValue().getProductName());
                tvAnswerB.setText(product.getThumnail());
            }
        });
    }
}
