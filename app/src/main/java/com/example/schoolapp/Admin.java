package com.example.schoolapp;

public class Admin {
    public String admin_id;
    public String first_name;
    public String middle_name;
    public String last_name;
    public String gender;
    public String birth_date;
    public String email;
    public int location_id;

    public void Admin(){

    }
    public void Admin(
            String admin_id,
            String first_name,
            String middle_name,
            String last_name,
            String gender,
            String birth_date,
            String email,
            int location_id){
        this.admin_id=admin_id;
        this.first_name=first_name;
        this.middle_name=middle_name;
        this.last_name=last_name;
        this.gender=gender;
        this.birth_date=birth_date;
        this.email=email;
        this.location_id=location_id;

    }

}
