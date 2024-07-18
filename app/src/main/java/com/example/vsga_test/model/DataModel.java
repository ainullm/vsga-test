package com.example.vsga_test.model;

public class DataModel {

    private String id, number, name, birth, gender, address;

    public DataModel(){}


    public  DataModel(String id, String name,String number,String birth,String gender, String address){
        this.id = id;
        this.name = name;
        this.address = address;
        this.number = number;
        this.gender = gender;
        this.birth = birth;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    //number
    public String getNumber(){
        return number;
    }

    public void  setNumber(String number){
        this.number = number;
    }

    //name
    public String getName(){
        return name;
    }

    public void  setName(String name){
        this.name = name;
    }

    //birth
    public String getBirth(){
        return birth;
    }

    public void  setBirth(String birth){
        this.birth = birth;
    }

    //gender
    public String getGender(){
        return gender;
    }

    public void  setGender(String gender){
        this.gender = gender;
    }

    //address
    public  String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }
}
