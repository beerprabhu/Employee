package com.example.employee;

import com.example.employee.model.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPICall {

    @GET("users")
    Call<List<DataModel>> getUsers();

}
