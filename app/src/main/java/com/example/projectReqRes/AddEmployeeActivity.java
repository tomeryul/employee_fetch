package com.example.projectReqRes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.example.projectReqRes.halper.DataClass;
import com.example.projectReqRes.halper.UserList;
import com.example.projectReqRes.utilitis.MySPv1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

public class AddEmployeeActivity extends AppCompatActivity {


    DataClass TheEmployee;
    UserList Employees;
    String EmpImg = "https://cdn.prod.website-files.com/64022de562115a8189fe542a/651a82942e9abec" +
                 "eaa8d3d50_Employee-Surveys-Why-They-Are-An-Essential-Tool-For-Your-HR-Team.jpg";

    private AppCompatEditText RP_ET_id;
    private AppCompatEditText RP_ET_first_name;
    private AppCompatEditText RP_ET_last_name;
    private AppCompatEditText RP_ET_email;
    Button RP_BTN_add_employee;
    private FloatingActionButton RP_FAB_return;
    private AppCompatImageView SAP_IMG_emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        myIntent();
        findView();
        initView();
    }

    private void myIntent() {
        Intent intent = getIntent();

        // uploading the values on the "savedValues" and make it a string
        String fromSP = MySPv1.getString(this,"savedValues","");
        // making the string of values in to an UserList
        Employees = new Gson().fromJson(fromSP, UserList.class);
    }

    private void findView() {
        RP_ET_id = findViewById(R.id.RP_ET_Id);
        RP_ET_first_name = findViewById(R.id.RP_ET_First_name);
        RP_ET_last_name = findViewById(R.id.RP_ET_Last_name);
        RP_ET_email = findViewById(R.id.RP_ET_Email);
        RP_BTN_add_employee = findViewById(R.id.RP_BTN_Add_Employee);
        RP_FAB_return = findViewById(R.id.RP_FAB_Return);
        SAP_IMG_emp = findViewById(R.id.SAP_IMG_Emp);
    }

    private void initView() {
        RP_ET_first_name.setHint("id: ");
        RP_ET_first_name.setHint("first name: ");
        RP_ET_last_name.setHint("last name: ");
        RP_ET_email.setHint("email: ");
        loadImage(EmpImg,SAP_IMG_emp);
        RP_BTN_add_employee.setOnClickListener(v -> registerEmployee());
        RP_FAB_return.setOnClickListener(view -> GoToListEmployeeActivity());
    }

    private void registerEmployee() {
        String id = RP_ET_id.getText().toString();
        String first_name = RP_ET_first_name.getText().toString();
        String last_name = RP_ET_last_name.getText().toString();
        String email = RP_ET_email.getText().toString();
        if(!id.isEmpty() && !first_name.isEmpty() && !last_name.isEmpty() && !email.isEmpty()) {
            if(!Employees.idIsTaken(id)){
                TheEmployee = new DataClass();
                TheEmployee.setId(id);
                TheEmployee.setFirst_name(first_name);
                TheEmployee.setLast_name(last_name);
                TheEmployee.setEmail(email);
                TheEmployee.setAvatar(EmpImg);
                Employees.addUser(TheEmployee);

                // implementing json
                String ItemJson = new Gson().toJson(Employees);
                // inserting the json file to "savedValues"
                MySPv1.putString(this ,"savedValues", ItemJson);

                Intent intent = new Intent(this, ListEmployeeActivity.class);
                startActivity(intent);
                finish();
            }

        }
    }

    private void GoToListEmployeeActivity() {
        Intent intent = new Intent(this, ListEmployeeActivity.class);
        startActivity(intent);
        finish();
    }

    public void loadImage(String imageURL, ImageView imageView){
        Glide
                .with(this)
                .load(imageURL)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }
}