package com.example.schoolapp.ui.ViewStudent;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.schoolapp.DatabaseHelper;
import com.example.schoolapp.R;

import java.util.ArrayList;
import java.util.List;


public class view_student extends Fragment {
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final DatabaseHelper database = new DatabaseHelper(getContext());

        View root = inflater.inflate(R.layout.fragment_view_student, container, false);
        listView = root.findViewById(R.id.list_view);
        Cursor studentCursor = database.getStudentCursor();


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
                String studentDeatails = "Realaaaax";
                dialogbox.setTitle ("View Student");
                dialogbox.setMessage (studentDeatails);
                dialogbox.show ();
            }
        });

        return root;
    }

}
