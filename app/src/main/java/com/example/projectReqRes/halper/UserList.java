package com.example.projectReqRes.halper;


import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class UserList {


    private ArrayList<DataClass> Users = new ArrayList<>();

    public UserList() {

    }

    public void changeEmployeeVals(String id ,String firstname, String Lastname, String email){
        for(int i = 0 ; i < Users.size() ; i++)
            if(Objects.equals(Users.get(i).getId(), id)){
                Users.get(i).setFirst_name(firstname);
                Users.get(i).setLast_name(Lastname);
                Users.get(i).setEmail(email);
                break;
            }
    }

    public boolean idIsTaken(String Eid) {
        for(int i = 0 ; i < Users.size() ; i++) {
            if (Objects.equals(Users.get(i).getId(), Eid)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<DataClass> inOrder(){
        Collections.sort(this.Users, new Comparator<DataClass>() {
            @Override
            public int compare(DataClass item1, DataClass item2) {
                return Integer.compare(Integer.parseInt(item1.getId()), Integer.parseInt(item2.getId()));

            }
        });
        return Users;
    }

    public ArrayList<DataClass> RemoveEmpWithId(String Pid){
        for(int i = 0 ; i < Users.size() ; i++)
            if(Objects.equals(Users.get(i).getId(), Pid)){
                Users.remove(i);
            }
        return Users;
    }

    public void addUser(DataClass dataClass){
        Users.add(dataClass);
    }

    public ArrayList<DataClass> getUsers() {
        return Users;
    }

    public UserList(ArrayList<DataClass> users) {
        Users = users;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "Users=" + Users +
                '}';
    }

}
