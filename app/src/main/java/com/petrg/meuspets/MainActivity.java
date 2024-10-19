package com.petrg.meuspets;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.petrg.meuspets.model.LoginModel;
import com.petrg.meuspets.service.LoginService;

public class MainActivity extends AppCompatActivity {

    private EditText userLogin, userPassword;
    private Button buttonLogin;
    private CheckBox checkBox;
    private LoginService loginService;
    private SharedPreferences sharedPreferences;
    //Others
    private static final String PREFS_NAME = "login_prefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REMEMBER_ME = "remember_me";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //get preferences.
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean rememberMe = sharedPreferences.getBoolean(KEY_REMEMBER_ME, false);
        if(rememberMe){
            String savedUsername = sharedPreferences.getString(KEY_USERNAME, "");
            String savedPassword = sharedPreferences.getString(KEY_PASSWORD, "");
            userLogin.setText(savedUsername);
            userPassword.setText(savedPassword);
            checkBox.setChecked(true);
        }
        loginService = new LoginService(this);
        userLogin = findViewById(R.id.login_editextUser);
        userPassword = findViewById(R.id.login_editextPassword);
        checkBox = findViewById(R.id.checkBox);
        buttonLogin = findViewById(R.id.login_Button_Conectar);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginModel userDados = new LoginModel(userLogin.getText().toString(), userPassword.getText().toString());
                loginService.autenticar(userDados, MainActivity.this);
            }
        });

    }
}