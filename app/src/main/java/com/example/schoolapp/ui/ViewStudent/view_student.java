package com.example.schoolapp.ui.ViewStudent;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.schoolapp.DatabaseHelper;
import com.example.schoolapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class view_student extends Fragment {
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final DatabaseHelper database = new DatabaseHelper(getContext());

        View root = inflater.inflate(R.layout.fragment_view_student, container, false);
        listView = root.findViewById(R.id.list_view);
        Cursor studentCursor = database.getStudentsCursor();


        CursorAdapter studentAdapter;
        studentAdapter= new SimpleCursorAdapter(getContext(),
                R.layout.student_list,
                studentCursor,
                new String[]{"_id","first_name", "middle_name", "last_name"},
                new int[]{R.id.student_id, R.id.first_name,
                        R.id.middle_name,
                        R.id.last_name},
                0);
        listView.setAdapter(studentAdapter);

        listView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialogbox = new AlertDialog.Builder (getContext ());
                dialogbox.setIcon (R.drawable.ic_person_add_24px);
                dialogbox.setPositiveButton ("BACK", null);
                Cursor studentDetailsCursor = database.getStudentDetailsCursor(position);
                String studentDetails="";
                studentDetailsCursor.moveToFirst ();
                    String student_id = studentDetailsCursor.getString (0);
                    String firstName = studentDetailsCursor.getString (1);
                    String middleName = studentDetailsCursor.getString (2);
                    String lastName = studentDetailsCursor.getString (3);
                    String gender = studentDetailsCursor.getString (4);
                    String age = age(studentDetailsCursor.getString (5));
                    String email = studentDetailsCursor.getString (6);
                    String phone_number = studentDetailsCursor.getString (7);
                    String location =
                            database.getLocation(Integer.parseInt (studentDetailsCursor.getString (8)));
                    studentDetails = "Name\t:\t"+lastName+", "+firstName+" "+middleName+"\n"+
                            "Gender\t:\t"+gender+"\n"+
                            "Age\t:\t"+age+"\n"+
                            "Email\t:\t"+email+"\n"+
                            "Phone\t:\t"+phone_number+"\n"+
                            "Home\t:\t"+location;
                dialogbox.setTitle (student_id);
                dialogbox.setMessage (studentDetails);
                dialogbox.show ();
            }
        });

        return root;
    }

    public String age(String dateOfBirth){
        DateFormat year = new SimpleDateFormat ("yyyy");
        Date date = new Date ();
        int birthYear = Integer.parseInt (dateOfBirth.substring (6));
        int currentYear = Integer.parseInt (year.format (date));
        return (currentYear-birthYear) + " years";
    }

}
