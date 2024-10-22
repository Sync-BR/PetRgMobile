package com.petrg.meuspets.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.petrg.meuspets.R;
import com.petrg.meuspets.activity.main.MainActivity;
import com.petrg.meuspets.implementation.Structure;

public class RegisterActivity extends AppCompatActivity implements Structure {
    private EditText textName, textSurname, textEmail, textCpf, textTelephone, textDate;
    private Button button_register, button_return;

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
                //Código para proxima tela
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
