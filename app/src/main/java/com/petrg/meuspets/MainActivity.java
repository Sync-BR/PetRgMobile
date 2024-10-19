package com.petrg.meuspets;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.petrg.meuspets.callbacks.AuthCallback;
import com.petrg.meuspets.interfaces.MainActivityInterface;
import com.petrg.meuspets.model.LoginModel;
import com.petrg.meuspets.service.LoginService;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {
    private static final String PREFS_NAME = "login_prefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER_ME = "remember_me";

    private EditText userLogin, userPassword;
    private Button buttonLogin, buttonRegister;
    private CheckBox checkBox;
    private LoginService loginService;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initializeUI();
        setupListeners();
        getPreferences();
    }

    private void initializeUI() {
        loginService = new LoginService(this);
        userLogin = findViewById(R.id.login_editextUser);
        userPassword = findViewById(R.id.login_editextPassword);
        checkBox = findViewById(R.id.checkBox);
        buttonLogin = findViewById(R.id.login_Button_Conectar);
        buttonRegister = findViewById(R.id.login_Button_Cadastrar);
    }

    private void setupListeners() {
        buttonLogin.setOnClickListener(view -> {
            disableButton();
            authenticateUser();
        });

        buttonRegister.setOnClickListener(view -> {
            disableButton();
            startActivity(new Intent(MainActivity.this, null));
        });
    }

    private void authenticateUser() {
        LoginModel userDados = new LoginModel(userLogin.getText().toString(), userPassword.getText().toString());
        loginService.autenticar(userDados, MainActivity.this, new AuthCallback() {
            @Override
            public void onAuthSuccess() {
                startActivity(new Intent(MainActivity.this, null));
            }

            @Override
            public void onAuthFailure() {
                enableButton();
            }
        });
    }

    @Override
    public void disableButton() {
        buttonLogin.setEnabled(false);
        buttonRegister.setEnabled(false);
    }

    @Override
    public void enableButton() {
        buttonLogin.setEnabled(true);
        buttonRegister.setEnabled(true);
    }

    @Override
    public void getPreferences() {
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean rememberMe = sharedPreferences.getBoolean(KEY_REMEMBER_ME, false);
        if (rememberMe) {
            String savedUsername = sharedPreferences.getString(KEY_USERNAME, "");
            String savedPassword = sharedPreferences.getString(KEY_PASSWORD, "");
            userLogin.setText(savedUsername);
            userPassword.setText(savedPassword);
            checkBox.setChecked(true);
        }
    }
}
