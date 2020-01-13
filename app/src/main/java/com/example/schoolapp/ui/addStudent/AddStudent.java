package com.example.schoolapp.ui.addStudent;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.schoolapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddStudent extends Fragment{
    public View onCreateView(@Nullable LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_add_student, container, false);

         EditText first_name = root.findViewById(R.id.student_first_name);
         EditText middle_name = root.findViewById(R.id.student_middle_name);
         EditText last_name = root.findViewById(R.id.student_last_name);
         RadioGroup gender = root.findViewById(R.id.gender_selection);
         EditText date_of_birth = root.findViewById(R.id.birth_date);
         EditText email = root.findViewById(R.id.email);
         EditText phone_number = root.findViewById(R.id.phone_number);
         Spinner region = root.findViewById(R.id.region);
         Spinner district = root.findViewById(R.id.district);
         Spinner ward = root.findViewById(R.id.ward);
         Button add_student = root.findViewById(R.id.add_student_button);

         final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        date_of_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return root;
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

//        date_of_birth.setText(sdf.format(myCalendar.getTime()));
    }
}
