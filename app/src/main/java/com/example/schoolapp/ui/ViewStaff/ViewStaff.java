package com.example.schoolapp.ui.ViewStaff;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.schoolapp.DatabaseHelper;
import com.example.schoolapp.R;

public class ViewStaff extends Fragment {
    ListView listView;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        final DatabaseHelper database = new DatabaseHelper(getContext());
        View root = inflater.inflate (R.layout.fragment_view_staff, container, false);
        listView = root.findViewById(R.id.list_view);
        Cursor staffsCursor = database.getStaffsCursor ();

        CursorAdapter studentAdapter;
        studentAdapter= new SimpleCursorAdapter (getContext(),
                R.layout.staff_list,
                staffsCursor,
                new String[]{"_id","first_name", "middle_name", "last_name"},
                new int[]{R.id.staff_id, R.id.first_name,
                        R.id.middle_name,
                        R.id.last_name},
                0);
        listView.setAdapter(studentAdapter);

        return root;
    }
}
