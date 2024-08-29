package com.example.projectReqRes.Interface;

import com.example.projectReqRes.halper.UserData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RequestUser {
    @GET("/api/users/{uid}")
    Call<UserData> getUser(@Path("uid") String uid);
}
