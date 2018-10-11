package com.example.phamn.vandungretrofit.Service;

import com.example.phamn.vandungretrofit.Model.QuestionPart1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("json.php")
    Call<List<QuestionPart1>> getAllQuestion();
}
