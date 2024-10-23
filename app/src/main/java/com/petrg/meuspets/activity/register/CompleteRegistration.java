package com.petrg.meuspets.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.petrg.meuspets.R;
import com.petrg.meuspets.implementation.Structure;
import com.petrg.meuspets.model.UsuarioModel;

public class CompleteRegistration extends AppCompatActivity implements Structure {
    private CheckBox toAccept;
    private Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termo_uso);
        initializeUI();
        setupListeners();

    }

    @Override
    public void initializeUI() {
        toAccept = findViewById(R.id.text_confirm_termo);
        register = findViewById(R.id.button_register);
    }

    @Override
    public void setupListeners() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toAccept.isChecked()){
                    disableButton();
                    UsuarioModel user = (UsuarioModel) getIntent().getSerializableExtra("usuarios");
                } else {
                    toAccept.setError("Você deve aceitar os termos e condições.");
                }
            }
        });
    }

    @Override
    public void disableButton() {
        register.setEnabled(false);
    }

    @Override
    public void enableButton() {
        register.setEnabled(true);
    }


}
