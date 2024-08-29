package com.example.projectReqRes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.projectReqRes.halper.DataClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

public class EditEmployeeActivity extends AppCompatActivity {

    String StringEmp;
    DataClass TheEmployee;

    private AppCompatEditText RP_ET_first_name;
    private AppCompatEditText RP_ET_last_name;
    private AppCompatEditText RP_ET_email;
    Button RP_BTN_register_changes;
    private FloatingActionButton RP_FAB_return;
    private AppCompatImageView SAP_IMG_emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employee);

        myIntent();
        findView();
        initView();

    }

    private void initView() {
        RP_ET_first_name.setHint("first name: "+TheEmployee.getFirst_name());
        RP_ET_last_name.setHint("last name: "+TheEmployee.getLast_name());
        RP_ET_email.setHint("email: "+TheEmployee.getEmail());
        loadImage(TheEmployee.getAvatar(),SAP_IMG_emp);
        RP_BTN_register_changes.setOnClickListener(v -> registerChanges());
        RP_FAB_return.setOnClickListener(view -> GoToListEmployeeActivity());
    }

    private void GoToListEmployeeActivity() {
        Intent intent = new Intent(this, ListEmployeeActivity.class);
        startActivity(intent);
        finish();
    }

    private void registerChanges() {
        String first_name = RP_ET_first_name.getText().toString();
        String last_name = RP_ET_last_name.getText().toString();
        String email = RP_ET_email.getText().toString();
        if(first_name.isEmpty()){
            first_name = TheEmployee.getFirst_name();
        }
        if(last_name.isEmpty()){
            last_name = TheEmployee.getLast_name();
        }
        if(email.isEmpty()){
            email = TheEmployee.getEmail();
        }

        Intent intent = new Intent(this, ListEmployeeActivity.class);
        intent.putExtra("first_name", first_name);
        intent.putExtra("last_name", last_name);
        intent.putExtra("email", email);
        intent.putExtra("id", TheEmployee.getId());
        startActivity(intent);
        finish();
    }

    private void findView() {
        RP_ET_first_name = findViewById(R.id.RP_ET_First_name);
        RP_ET_last_name = findViewById(R.id.RP_ET_Last_name);
        RP_ET_email = findViewById(R.id.RP_ET_Email);
        RP_BTN_register_changes = findViewById(R.id.RP_BTN_Register_Changes);
        RP_FAB_return = findViewById(R.id.RP_FAB_Return);
        SAP_IMG_emp = findViewById(R.id.SAP_IMG_Emp);
    }

    private void myIntent() {
        Intent intent = getIntent();
        StringEmp = intent.getStringExtra("StringEmp");
        TheEmployee = new Gson().fromJson(StringEmp, DataClass.class);
        Log.e("Edit emp: ",TheEmployee.toString());
    }

    public void loadImage(String imageURL, ImageView imageView){
        Glide
                .with(this)
                .load(imageURL)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }

}