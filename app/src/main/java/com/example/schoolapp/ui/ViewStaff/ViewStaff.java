package com.example.schoolapp.ui.ViewStaff;

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

public class ViewStaff extends Fragment {
    ListView listView;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        final DatabaseHelper database = new DatabaseHelper(getContext());
        View root = inflater.inflate (R.layout.fragment_view_staff, container, false);
        listView = root.findViewById(R.id.list_view);
        Cursor staffsCursor = database.getStaffsCursor ();

        CursorAdapter staffAdapter;
        staffAdapter= new SimpleCursorAdapter (getContext(),
                R.layout.staff_list,
                staffsCursor,
                new String[]{"_id","first_name", "middle_name", "last_name"},
                new int[]{R.id.staff_id, R.id.first_name,
                        R.id.middle_name,
                        R.id.last_name},
                0);
        listView.setAdapter(staffAdapter);

        listView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder dialogbox = new AlertDialog.Builder (getContext ());
                dialogbox.setIcon (R.drawable.ic_person_add_24px);
                dialogbox.setPositiveButton ("BACK", null);
                Cursor staffDetailsCursor = database.getStaffDetailsCursor(position);
                String staffDeatails="";
                staffDetailsCursor.moveToFirst ();
                String staff_id = staffDetailsCursor.getString (0);
                String firstName = staffDetailsCursor.getString (1);
                String middleName = staffDetailsCursor.getString (2);
                String lastName = staffDetailsCursor.getString (3);
                String gender = staffDetailsCursor.getString (4);
                String age = age(staffDetailsCursor.getString (5));
                String email = staffDetailsCursor.getString (6);
                String phone_number = staffDetailsCursor.getString (7);
                String location =
                        database.getLocation(Integer.parseInt (staffDetailsCursor.getString (8)));
                staffDeatails = "Name\t:\t"+lastName+", "+firstName+" "+middleName+"\n"+
                        "Gender\t:\t"+gender+"\n"+
                        "Age\t:\t"+age+"\n"+
                        "Email\t:\t"+email+"\n"+
                        "Phone\t:\t"+phone_number+"\n"+
                        "Home\t:\t"+location;
                dialogbox.setTitle (staff_id);
                dialogbox.setMessage (staffDeatails);
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
