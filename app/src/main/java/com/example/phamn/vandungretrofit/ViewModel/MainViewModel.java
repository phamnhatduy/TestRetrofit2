package com.example.phamn.vandungretrofit.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.example.phamn.vandungretrofit.Model.QuestionPart1;
import com.example.phamn.vandungretrofit.Service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends AndroidViewModel{

    public MutableLiveData<List<QuestionPart1>> listQuestionPart1 = new MutableLiveData<>();
    public MutableLiveData<QuestionPart1> question = new MutableLiveData<>();
    public List<QuestionPart1> listQuestion = new ArrayList<>();
    String URL_GET_PRODUCT = "https://myhost2018.000webhostapp.com/Test1/Part1/";

    public MainViewModel(@NonNull Application application) {
        super(application);
        getAllQuestion();
    }

    public void getAllQuestion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://myhost2018.000webhostapp.com/Test1/Part1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<List<QuestionPart1>> call = apiService.getAllQuestion();
        call.enqueue(new Callback<List<QuestionPart1>>() {
            @Override
            public void onResponse(Call<List<QuestionPart1>> call, Response<List<QuestionPart1>> response) {
                List<QuestionPart1> questionPart1List = response.body();
                for (int i = 0; i < questionPart1List.size() ; i++) {
                    //listQuestion.add(questionPart1List.get(i));
                }
                question.setValue(questionPart1List.get(2));
            }

            @Override
            public void onFailure(Call<List<QuestionPart1>> call, Throwable t) {
                //Log.e(Tag, "onFailure: " + t.getMessage());
            }
        });
    }

    public MutableLiveData<List<QuestionPart1>> getListQuestionPart1() {
        return listQuestionPart1;
    }

    public MutableLiveData<QuestionPart1> getQuestion() {
        return question;
    }
}
