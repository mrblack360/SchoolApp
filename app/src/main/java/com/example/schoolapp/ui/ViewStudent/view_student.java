package com.example.schoolapp.ui.ViewStudent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.schoolapp.DatabaseHelper;
import com.example.schoolapp.R;

import java.util.List;

public class view_student extends Fragment {
    Spinner listView ;
    public DatabaseHelper database = new DatabaseHelper(getContext());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_view_student, container, false);
        listView = root.findViewById(R.id.list_view);
        List<String> studentList = database.StudentList();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, studentList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listView.setAdapter(dataAdapter);

        return root;
    }

}
