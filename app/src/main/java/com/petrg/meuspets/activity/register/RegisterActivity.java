package com.petrg.meuspets.activity.register;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.petrg.meuspets.R;
import com.petrg.meuspets.activity.main.MainActivity;
import com.petrg.meuspets.callbacks.register.ValidationCallback;
import com.petrg.meuspets.callbacks.register.ValidationCpfCallback;
import com.petrg.meuspets.implementation.Structure;
import com.petrg.meuspets.service.register.Validation;

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
                if(checkAllFields()){
                    Validation validation = new Validation(RegisterActivity.this);
                    validation.validationEmail(textEmail.getText().toString(), new ValidationCallback() {
                        @Override
                        public void onAuthSuccess() {
                            validation.validationCpf(textCpf.getText().toString(), new ValidationCpfCallback() {
                                @Override
                                public void onAuthSuccess() {
                                    Intent loginRegistrationScreen = new Intent(RegisterActivity.this, RegisterLoginActivity.class);
                                    loginRegistrationScreen.putExtra("name", textName.getText().toString());
                                    loginRegistrationScreen.putExtra("surname", textSurname.getText().toString());
                                    loginRegistrationScreen.putExtra("email", textEmail.getText().toString());
                                    loginRegistrationScreen.putExtra("cpf", textCpf.getText().toString());
                                    loginRegistrationScreen.putExtra("telephone", textTelephone.getText().toString());
                                    loginRegistrationScreen.putExtra("date", textDate.getText().toString());
                                    startActivity(loginRegistrationScreen);
                                }

                                @Override
                                public void onAuthFailure() {
                                    ((RegisterActivity) RegisterActivity.this).runOnUiThread(() -> {
                                        Toast.makeText(RegisterActivity.this, "O CPF informado já está cadastrado no sistema. ", Toast.LENGTH_LONG).show();
                                    });
                                    enableButton();
                                }

                                @Override
                                public void onServerFailure() {
                                    ((RegisterActivity) RegisterActivity.this).runOnUiThread(() -> {
                                        Toast.makeText(RegisterActivity.this, " Estamos enfrentando um problema no servidor. Por favor, tente novamente mais tarde.. ", Toast.LENGTH_LONG).show();
                                    });
                                    enableButton();
                                }
                            });
                        }

                        @Override
                        public void onAuthFailure() {
                            ((RegisterActivity) RegisterActivity.this).runOnUiThread(() -> {
                                Toast.makeText(RegisterActivity.this, "O Email informado já está cadastrado no sistema. ", Toast.LENGTH_LONG).show();
                            });
                            enableButton();
                        }

                        @Override
                        public void onServerFailure() {
                            ((RegisterActivity) RegisterActivity.this).runOnUiThread(() -> {
                                Toast.makeText(RegisterActivity.this, " Estamos enfrentando um problema no servidor. Por favor, tente novamente mais tarde.. ", Toast.LENGTH_LONG).show();
                            });
                            enableButton();
                        }
                    });
                } else {
                    enableButton();
                }

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

    public boolean checkAllFields() {
        if (textName.length() == 0) {
            textName.setError("Por favor, insira o nome.");
            return false;
        } else if (textEmail.length() == 0) {
            textEmail.setError("O campo de e-mail não pode estar vazio.");
            return false;
        } else if (textCpf.length() == 0) {
            textCpf.setError("O CPF não pode estar em branco.");
            return false;
        } else if (textDate.length() == 0) {
            textDate.setError("É necessário selecionar uma data.");
            return false;
        }
        return true;
    }

}