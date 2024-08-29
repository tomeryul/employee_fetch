package com.example.projectReqRes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projectReqRes.Interface.RequestUser;
import com.example.projectReqRes.halper.DataClass;
import com.example.projectReqRes.halper.UserData;
import com.example.projectReqRes.halper.UserList;
import com.example.projectReqRes.utilitis.MySPv1;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {


    UserList userList;

    Button Gsonbutton;
    Button HTTPbutton;
    private AppCompatImageView SAP_IMG_App;
    private MaterialTextView SAP_LBL_Title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        initView();


        userList = new UserList();


    }

    private void getAllUsers(RequestUser requestUser) {

        for(int i = 1 ; i <= 12 ; i++) {
            getUserByid(requestUser, String.valueOf(i));
        }

        // Start a non-visual countdown timer for 10 seconds
        new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {
                // You can log the remaining time if needed (optional)
                Log.d("Timer", "seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                // Perform the action when the timer finishes
                Log.d("Timer", "Timer finished!");

                // i can move to a deferent activity after i finish the job of collecting the Users
                // implementing json
                String ItemJson = new Gson().toJson(userList);

                // inserting the json file to "savedValues"
                MySPv1.putString(getApplicationContext(),"savedValues", ItemJson);

                Log.e("from HTTP",userList.toString());  // Call your next function here
                IntentEmployeeActivity();
            }

        }.start();

    }

    public void getUserByid(RequestUser requestUser, String Uid){

        requestUser.getUser(Uid).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                assert response.body() != null;
                DataClass dataClass = new DataClass();
                dataClass.setId(Uid);
                dataClass.setFirst_name(response.body().getData().getFirst_name());
                dataClass.setLast_name(response.body().getData().getLast_name());
                dataClass.setEmail(response.body().getData().getEmail());
                dataClass.setAvatar(response.body().getData().getAvatar());

                userList.addUser(dataClass);

            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
            }
        });

    }

    private void findView() {
        Gsonbutton = findViewById(R.id.SAP_BTN_From_Gson);
        HTTPbutton = findViewById(R.id.SAP_BTN_From_Http);
        SAP_IMG_App= findViewById(R.id.SAP_IMG_app);
        SAP_LBL_Title = findViewById(R.id.SAP_LBL_title);
    }

    private void initView() {
        Gsonbutton.setOnClickListener(v -> listActivityFromGson());
        HTTPbutton.setOnClickListener(v -> listActivityFromHTTP());

    }

    private void listActivityFromHTTP() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RequestUser requestUser = retrofit.create(RequestUser.class);

        getAllUsers(requestUser);
    }

    private void listActivityFromGson() {
        // uploading the values on the "savedValues" and make it a string
        String fromSP = MySPv1.getString(getApplicationContext(),"savedValues","");
        // making the string of values in to an ItemList
        userList = new Gson().fromJson(fromSP,UserList.class);
        Log.e("from Gson",userList.toString());  // Call your next function here
        IntentEmployeeActivity();
    }

    private void IntentEmployeeActivity(){
        Intent intent = new Intent(this, ListEmployeeActivity.class);
        startActivity(intent);
        finish();
    }

}
