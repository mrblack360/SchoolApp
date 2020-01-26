package com.example.schoolapp.ui.addStaff;

import android.app.DatePickerDialog;
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
import com.example.schoolapp.User;
import com.example.schoolapp.ui.addStudent.Student;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class addStaff extends Fragment {
    public String selected_gender = "";
    public int location_id = 0;
    public View onCreateView(@Nullable LayoutInflater inflater,
                             ViewGroup container,
                             Bundle saveInstanceState){
        final DatabaseHelper database = new DatabaseHelper(getContext());

        View root = inflater.inflate(R.layout.fragment_add_staff, container,false);

        final EditText first_name = root.findViewById(R.id.staff_first_name);
        final EditText middle_name = root.findViewById(R.id.staff_middle_name);
        final EditText last_name = root.findViewById(R.id.staff_last_name);
        RadioGroup gender = root.findViewById(R.id.gender_selection);
        final EditText date_of_birth = root.findViewById(R.id.birth_date);
        final EditText email = root.findViewById(R.id.email);
        final EditText phone_number = root.findViewById(R.id.phone_number);
        final Spinner region = root.findViewById(R.id.region);
        final Spinner district = root.findViewById(R.id.district);
        final Spinner ward = root.findViewById(R.id.ward);
        Button add_staff = root.findViewById(R.id.add_staff_button);


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
                // Populate districts
                List<String> districtsList = database.getDistricts(region.getItemAtPosition(position).toString());
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, districtsList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                district.setAdapter(dataAdapter);

                // Listen to district selection
                district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        // Populate districts
                        List<String> wardList = database.getWards(district.getItemAtPosition(position).toString());
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, wardList);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        ward.setAdapter(dataAdapter);

                        ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                location_id = database.wardCode(ward.getItemAtPosition(position).toString());
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {
                                // TODO Nitakushughulikia baadae
                            }
                        });

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.gender_male:
                        selected_gender = "MALE";
                        break;
                    case R.id.gender_female:
                        selected_gender = "FEMALE";
                        break;
                }
            }
        });

        add_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(first_name.getText().toString().length()<1 ||
                        middle_name.getText().toString().length()<1 ||
                        last_name.getText().toString().length()<1 ||
                        email.getText().toString().length()<1 ||
                        selected_gender.length()<1 ||
                        phone_number.getText().toString().length()<5||
                        location_id ==0
                ){
                    Toast.makeText(getContext(), "All fields are MANDATORY!", Toast.LENGTH_LONG).show();
                }
                else{
                    String staff_id = database.generateStaffId();
                    User user = new User(staff_id, last_name.getText().toString().toUpperCase(),
                            "staff");
                    Staff staff = new Staff(staff_id,
                            first_name.getText().toString(),
                            middle_name.getText().toString(),
                            last_name.getText().toString(),
                            selected_gender,
                            date_of_birth.getText().toString(),
                            email.getText().toString(),
                            Long.parseLong(phone_number.getText().toString().trim()),
                            location_id);
                    database.addUser(user);
                    database.addStaff(staff);
                    Toast.makeText(getContext(), "STAFF ADDED SUCCESSFULLY\n with Staff " +
                            "Number: "+staff_id, Toast.LENGTH_LONG).show();
                    first_name.setText (setEmpty ());
                    middle_name.setText (setEmpty ());
                    last_name.setText (setEmpty ());
                    date_of_birth.setText (setEmpty ());
                    email.setText (setEmpty ());
                    phone_number.setText (setEmpty ());
                }
            }
        });

        return root;
    }

    public String setEmpty(){
        return "";
    }
}
