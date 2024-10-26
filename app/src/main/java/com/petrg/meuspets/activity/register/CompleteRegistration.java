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
import com.petrg.meuspets.activity.main.MainActivity;
import com.petrg.meuspets.callbacks.register.RegisterCallback;
import com.petrg.meuspets.implementation.Structure;
import com.petrg.meuspets.model.LoginModel;
import com.petrg.meuspets.model.UsuarioModel;
import com.petrg.meuspets.service.RegisterService;

public class CompleteRegistration extends AppCompatActivity implements Structure {
    private CheckBox toAccept;
    private Button register, cancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termo_uso);
        initializeUI();
        setupListeners();
        disableButton();
    }

    @Override
    public void initializeUI() {
        toAccept = findViewById(R.id.text_confirm_termo);
        register = findViewById(R.id.button_register);
        cancel = findViewById(R.id.button_register_cancelar);
    }

    @Override
    public void setupListeners() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toAccept.isChecked()) {
                    enableButton();
                    UsuarioModel user = (UsuarioModel) getIntent().getSerializableExtra("usuarios");
                    RegisterService registerService = new RegisterService(CompleteRegistration.this);
                    registerService.registerUser(user, new RegisterCallback() {
                        @Override
                        public void onAuthSuccess() {
                            ((CompleteRegistration) CompleteRegistration.this).runOnUiThread(() -> {
                                Toast.makeText(CompleteRegistration.this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();
                            });
                            Intent loginScreen = new Intent(CompleteRegistration.this, MainActivity.class);
                            startActivity(loginScreen);
                        }

                        @Override
                        public void onAuthFailure() {
                            ((CompleteRegistration) CompleteRegistration.this).runOnUiThread(() -> {
                                Toast.makeText(CompleteRegistration.this, "Ocorreu um erro inesperado durante o registro. Por favor, tente novamente mais tarde.", Toast.LENGTH_LONG).show();
                            });
                        }

                        @Override
                        public void onServerFailure() {
                            ((CompleteRegistration) CompleteRegistration.this).runOnUiThread(() -> {
                                Toast.makeText(CompleteRegistration.this, "Estamos enfrentando um problema no servidor. Por favor, tente novamente mais tarde..", Toast.LENGTH_LONG).show();
                            });
                        }
                    });

                } else {
                    toAccept.setError("Você deve aceitar os termos e condições.");
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeScreen = new Intent(CompleteRegistration.this, MainActivity.class);
                startActivity(homeScreen);
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

    //Receive information
    private UsuarioModel receiveInformation(String user, String password) {
        LoginModel userDados = new LoginModel(user, password);
        Intent intent = getIntent();
        UsuarioModel userData = new UsuarioModel(
                intent.getStringExtra("name"),
                intent.getStringExtra("surname"),
                intent.getStringExtra("email"),
                intent.getStringExtra("cpf"),
                intent.getStringExtra("telephone"),
                intent.getStringExtra("date"),
                userDados
        );
        return userData;
    }
}
