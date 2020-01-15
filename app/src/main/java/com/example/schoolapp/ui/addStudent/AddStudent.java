package com.example.schoolapp.ui.addStudent;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.schoolapp.DatabaseHelper;
import com.example.schoolapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddStudent extends Fragment{
    public View onCreateView(@Nullable LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        DatabaseHelper database = new DatabaseHelper(getContext());

        View root = inflater.inflate(R.layout.fragment_add_student, container, false);

         EditText first_name = root.findViewById(R.id.student_first_name);
         EditText middle_name = root.findViewById(R.id.student_middle_name);
         EditText last_name = root.findViewById(R.id.student_last_name);
         RadioGroup gender = root.findViewById(R.id.gender_selection);
         final EditText date_of_birth = root.findViewById(R.id.birth_date);
         EditText email = root.findViewById(R.id.email);
         EditText phone_number = root.findViewById(R.id.phone_number);
         final Spinner region = root.findViewById(R.id.region);
         final Spinner district = root.findViewById(R.id.district);
         Spinner ward = root.findViewById(R.id.ward);
         Button add_student = root.findViewById(R.id.add_student_button);


         // Calendar Dialog
         final Calendar myCalendar = Calendar.getInstance();
         final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                date_of_birth.setText(sdf.format(myCalendar.getTime()));
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

        // Populate Regions Spinner
        List<String> regionsList = database.getRegions();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, regionsList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        region.setAdapter(dataAdapter);

        // Listening to region selection
        region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Toast.makeText(getContext(), region.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                district.setEnabled(true);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        return root;
    }

}
