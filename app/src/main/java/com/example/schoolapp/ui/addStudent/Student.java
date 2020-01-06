package com.example.schoolapp.ui.addStudent;

public class Student {
    private String first_name;
    private String middle_nam;
    private String last_name;
    private String gender;
    private String date_of_birth;
    private String email;
    private long phone_number;
    private int place_of_residence;

    public Student(String first_name,
                   String middle_name,
                   String last_name,
                   String gender,
                   String date_of_birth,
                   String email,
                   long phone_number,
                   int place_of_residence
                   ){
        this.first_name = first_name;
        this.middle_nam = middle_name;
        this.last_name = last_name;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.phone_number = phone_number;
        this.place_of_residence = place_of_residence;
    }
}
