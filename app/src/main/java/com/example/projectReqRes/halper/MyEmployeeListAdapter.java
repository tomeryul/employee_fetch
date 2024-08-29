package com.example.projectReqRes.halper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projectReqRes.R;

import java.util.ArrayList;

public class MyEmployeeListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<DataClass> Users = new ArrayList<>();


    public MyEmployeeListAdapter(Context context, ArrayList<DataClass> Users) {
        this.context = context;
        this.Users = Users;
    }

    public void loadImage(String imageURL, ImageView imageView){
        Glide
                .with(this.context)
                .load(imageURL)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }

    @Override
    public int getCount() {
        return Users.size();
    }

    @Override
    public Object getItem(int position) {
        return Users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.my_employee_item, parent, false);
        }

        TextView tvFirstName = convertView.findViewById(R.id.employee_LBL_first_name);
        TextView tvLastName = convertView.findViewById(R.id.employee_LBL_last_name);
        TextView tvId = convertView.findViewById(R.id.employee_LBL_id);
        TextView tvEmail = convertView.findViewById(R.id.employee_LBL_email);
        ImageView ivEmp = convertView.findViewById(R.id.emp_IMG_employee);

        DataClass item = (DataClass) getItem(position);

        tvFirstName .setText("First name: "+item.getFirst_name());
        tvLastName .setText("Last name: "+item.getLast_name());
        tvId.setText("Id: "+item.getId());
        tvEmail.setText("Email: "+item.getEmail());
        loadImage(item.getAvatar(), ivEmp); // image from url

        return convertView;
    }

}
