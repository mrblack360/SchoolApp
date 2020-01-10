package com.example.schoolapp.ui.addStudent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.schoolapp.R;

public class AddStudent extends Fragment{
    public View onCreateView(@Nullable LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_add_student, container, false);

        final EditText first_name = root.findViewById(R.id.student_first_name);
        final EditText middle_name = root.findViewById(R.id.student_middle_name);
        final EditText last_name = root.findViewById(R.id.student_last_name);
        final RadioGroup gender = root.findViewById(R.id.gender_selection);
        final EditText date_of_birth = root.findViewById(R.id.birth_date);
        final EditText email = root.findViewById(R.id.email);
        final EditText phone_number = root.findViewById(R.id.phone_number);
        final Spinner region = root.findViewById(R.id.region);
        final Spinner district = root.findViewById(R.id.district);
        final Spinner ward = root.findViewById(R.id.ward);
        final Button add_student = root.findViewById(R.id.add_student_button);
        return root;
    }
}
