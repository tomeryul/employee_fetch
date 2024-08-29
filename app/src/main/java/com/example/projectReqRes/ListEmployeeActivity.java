package com.example.projectReqRes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectReqRes.Fragments.myEmployeeListFragment;
import com.example.projectReqRes.Interface.CallBack_SendClick;
import com.example.projectReqRes.halper.DataClass;
import com.example.projectReqRes.halper.UserList;
import com.example.projectReqRes.utilitis.MySPv1;
import com.google.gson.Gson;

import java.util.Objects;

public class ListEmployeeActivity extends AppCompatActivity {

    private myEmployeeListFragment listFragment;
    Button DeleteEmployee;
    Button EditEmployee;
    Button AddEmployee;

    public String id = "", firstname = "", lastname = "", email = "";
    DataClass TheEmployeeChosen;

    private CallBack_SendClick callBack_sendClick = new CallBack_SendClick() {
        @Override
        public void EmployeeChosen(DataClass employee) {
            goToSingleAdoptPet(employee);
        }
    };

    private void goToSingleAdoptPet(DataClass employee) {
        TheEmployeeChosen = employee;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employee);

        findView();
        initView();
        beginTransactions();
        myIntent();
    }

    private void findView() {
        listFragment = new myEmployeeListFragment();
        listFragment.setCallBack_sendClick(callBack_sendClick);
        DeleteEmployee = findViewById(R.id.SAP_BTN_Delete_Emp);
        EditEmployee = findViewById(R.id.SAP_BTN_Edit_Emp);
        AddEmployee = findViewById(R.id.SAP_BTN_Add_Emp);

    }
    private void initView() {
        DeleteEmployee.setOnClickListener(v -> GoToDeleteEmpActivity());
        EditEmployee.setOnClickListener(v -> GoToEditEmpActivity());
        AddEmployee.setOnClickListener(v -> GoToAddEmpActivity());
    }

    private void GoToAddEmpActivity() {
        Intent intent = new Intent(this, AddEmployeeActivity.class);
        startActivity(intent);
        finish();
    }

    private void GoToDeleteEmpActivity() {
        if (TheEmployeeChosen != null){
            myEmployeeListFragment.TakeEmpWithId(TheEmployeeChosen.getId(),this);
        }
        TheEmployeeChosen = null;
    }

    private void GoToEditEmpActivity() {
        if (TheEmployeeChosen != null){
            Intent intent = new Intent(this, EditEmployeeActivity.class);
            String EmpJson = new Gson().toJson(TheEmployeeChosen);
            intent.putExtra("StringEmp", EmpJson);
            startActivity(intent);
            finish();
        }
    }

    private void beginTransactions() {
        getSupportFragmentManager().beginTransaction().add(R.id.employee_FRAME_list, listFragment).commit();
    }

    private void myIntent() {
        Intent intent = getIntent();
        firstname = intent.getStringExtra("first_name");
        lastname = intent.getStringExtra("last_name");
        email = intent.getStringExtra("email");
        id = intent.getStringExtra("id");

        if(!Objects.equals(id, "")){
            // uploading the values on the "savedValues" and make it a string
            String fromSP = MySPv1.getString(this,"savedValues","");
            // making the string of values in to an UserList
            UserList Employees = new Gson().fromJson(fromSP, UserList.class);
            Employees.changeEmployeeVals(id,firstname,lastname,email);
            // implementing json
            String ItemJson = new Gson().toJson(Employees);
            // inserting the json file to "savedValues"
            MySPv1.putString(this ,"savedValues", ItemJson);
        }
    }

}
