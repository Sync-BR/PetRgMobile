package com.petrg.meuspets.activity.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.petrg.meuspets.R;
import com.petrg.meuspets.implementation.Structure;

public class CompleteRegistration extends AppCompatActivity implements Structure {
    private CheckBox toAccept;
    private Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termo_uso);
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
                //Ação para completa registro
            }
        });
    }

    @Override
    public void disableButton() {

    }

    @Override
    public void enableButton() {

    }
}
