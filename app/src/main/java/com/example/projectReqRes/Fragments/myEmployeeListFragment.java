package com.example.projectReqRes.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.projectReqRes.R;

import com.example.projectReqRes.halper.DataClass;
import com.example.projectReqRes.halper.MyEmployeeListAdapter;
import com.example.projectReqRes.halper.UserList;
import com.example.projectReqRes.Interface.CallBack_SendClick;
import com.example.projectReqRes.utilitis.MySPv1;
import com.google.gson.Gson;


public class myEmployeeListFragment extends Fragment {

    static MyEmployeeListAdapter adapter;
    private static UserList Employees;
    private CallBack_SendClick callBack_sendClick;
    static ListView listView;

    public static void TakeEmpWithId(String id, Context context) {
        Employees = new UserList(Employees.RemoveEmpWithId(id));
        removeEmployee(Employees, context);
    }


    public void setCallBack_sendClick(CallBack_SendClick callBack_sendClick) {
        this.callBack_sendClick = callBack_sendClick;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = view.findViewById(R.id.listView);
        loadEmployeesFromDB(listView);
        myOnClick(view,listView);


        return view;
    }

    private void loadEmployeesFromDB(ListView listView) {
        // uploading the values on the "savedValues" and make it a string
        String fromSP = MySPv1.getString(getContext(),"savedValues","");
        // making the string of values in to an ItemList
        Employees = new Gson().fromJson(fromSP,UserList.class);

        adapter = new MyEmployeeListAdapter(getContext(), Employees.inOrder());
        listView.setAdapter(adapter);

    }

    private static void removeEmployee(UserList Employees, Context context) {
        // implementing json
        String ItemJson = new Gson().toJson(Employees);

        // inserting the json file to "savedValues"
        MySPv1.putString(context ,"savedValues", ItemJson);

        adapter = new MyEmployeeListAdapter(context, Employees.getUsers());
        listView.setAdapter(adapter);

    }

    private void myOnClick(View view,ListView listView ) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataClass employee = (DataClass) parent.getItemAtPosition(position);
                callBack_sendClick.EmployeeChosen(employee);
            }
        });
    }


}