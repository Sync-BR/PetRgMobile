package com.petrg.meuspets.activity.register;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.petrg.meuspets.R;
import com.petrg.meuspets.activity.main.MainActivity;
import com.petrg.meuspets.implementation.Structure;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity implements Structure {
    private EditText textName, textSurname, textEmail, textCpf, textTelephone, textDate;
    private Button button_register, button_return;
    private Calendar myCalendar;

    private void updateDate() {
        String myFormat = "dd/MM/YYYY";
        SimpleDateFormat format = new SimpleDateFormat(myFormat, new Locale("pt", "BR"));
        textDate.setText(format.format(myCalendar.getTime()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        enableButton();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        initializeUI();
        setupListeners();
        getCalendar();
    }


    @Override
    public void initializeUI() {
        textName = findViewById(R.id.text_name);
        textSurname = findViewById(R.id.text_surname);
        textEmail = findViewById(R.id.text_email);
        textCpf = findViewById(R.id.text_cpf);
        textTelephone = findViewById(R.id.text_telephone);
        textDate = findViewById(R.id.text_register_date);
        button_register = findViewById(R.id.button_register_user);
        button_return = findViewById(R.id.button_register_return);

    }

    @Override
    public void setupListeners() {
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableButton();
                Intent loginRegistrationScreen = new Intent(RegisterActivity.this, RegisterLoginActivity.class);
                startActivity(loginRegistrationScreen);
            }
        });
        button_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableButton();
                Intent returnHomeScreen = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(returnHomeScreen);
            }
        });
    }

    private void getCalendar() {
        myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(year, monthOfYear, dayOfMonth);
                updateDate();
            }
        };
        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegisterActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @Override
    public void disableButton() {
        button_register.setEnabled(false);
        button_return.setEnabled(false);
    }

    @Override
    public void enableButton() {
        button_register.setEnabled(true);
        button_return.setEnabled(true);
    }
}
