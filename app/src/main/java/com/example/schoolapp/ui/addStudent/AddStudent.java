package com.example.schoolapp.ui.addStudent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.schoolapp.R;

public class AddStudent extends Fragment{
    public View onCreateView(@Nullable LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_add_student, container, false);
        return root;
    }
}
