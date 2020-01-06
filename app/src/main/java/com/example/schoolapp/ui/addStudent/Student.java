package com.example.schoolapp.ui.addStudent;

public class Student {
    public String student_id;
    public String first_name;
    public String middle_name;
    public String last_name;
    public String gender;
    public String date_of_birth;
    public String email;
    public long phone_number;
    public int location_id;

    public Student(String student_id,
                   String first_name,
                   String middle_name,
                   String last_name,
                   String gender,
                   String date_of_birth,
                   String email,
                   long phone_number,
                   int location_id
                   ){
        this.student_id = student_id;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.email = email;
        this.phone_number = phone_number;
        this.location_id = location_id;
    }
}
