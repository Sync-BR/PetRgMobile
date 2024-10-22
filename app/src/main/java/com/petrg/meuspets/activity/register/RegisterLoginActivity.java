package com.petrg.meuspets.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.petrg.meuspets.R;
import com.petrg.meuspets.implementation.Structure;

public class RegisterLoginActivity extends AppCompatActivity implements Structure {
    private EditText username, password, repeatPassword;
    private Button buttonNext, buttonReturn;

    @Override
    protected void onResume() {
        super.onResume();
        enableButton();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario_login);
        initializeUI();
        setupListeners();
    }


    @Override
    public void initializeUI() {
        username = findViewById(R.id.text_login);
        password = findViewById(R.id.text_password);
        repeatPassword = findViewById(R.id.text_repeat_password);
        buttonNext = findViewById(R.id.button_register_user_next);
        buttonReturn = findViewById(R.id.button_register_return_next);
    }

    @Override
    public void setupListeners() {
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ação para proxima tela
                disableButton();
            }
        });
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Return to data record screen
                disableButton();
                Intent  returnDataRecordScreen = new Intent(RegisterLoginActivity.this, RegisterActivity.class);
                startActivity(returnDataRecordScreen);
            }
        });

    }

    @Override
    public void disableButton() {
        buttonNext.setEnabled(false);
        buttonReturn.setEnabled(false);
    }

    @Override
    public void enableButton() {
        buttonNext.setEnabled(true);
        buttonReturn.setEnabled(true);
    }
}
