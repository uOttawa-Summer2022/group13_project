package com.example.project.ui.login;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.project.R;

import org.w3c.dom.Text;

public class RegistrationActivity extends AppCompatActivity {

    boolean isFNameValid = false;
    boolean isLNameValid = false;
    boolean isEmailValid = false;
    boolean isPassValid = false;
    boolean isRoleSelected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        final EditText firstName = findViewById(R.id.editTextPersonName);
        final EditText lastName = findViewById(R.id.editTextLastName);
        final EditText email = findViewById(R.id.editTextEmailAddress);
        final EditText password = findViewById(R.id.editTextTextPassword);
        final RadioButton roleStu = findViewById(R.id.radioButton2);
        final RadioButton roleIns = findViewById(R.id.radioButton3);
        final Button submitButton = findViewById(R.id.submitButton);
        submitButton.setEnabled(false);



        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(firstName.getText().toString() != null) {
                    isFNameValid = true;
                }
                if(enableSubmitButton()){
                    submitButton.setEnabled(true);
                }
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    email.setError(null);
                    isEmailValid = true;
                }
                else{
                    email.setError("Must contain @");
                }
                if(enableSubmitButton()){
                    submitButton.setEnabled(true);
                }
            }
        });
        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(lastName.getText().toString() != null) {
                    isLNameValid = true;
                }
                if(enableSubmitButton()){
                    submitButton.setEnabled(true);
                }
            }


        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(password.getText() != null && password.getText().toString().trim().length() > 5){
                    password.setError(null);
                    isPassValid = true;
                }else{
                    password.setError("Must be >5");
                }

               if(enableSubmitButton()){
                   submitButton.setEnabled(true);
               }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add to database
                String msg = firstName.getText().toString() + " is successfully registered";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                Intent loginI = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(loginI);

            }

        });
        roleStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = roleStu.isChecked();
                if (checked) {
                    isRoleSelected = true;
                    if (enableSubmitButton()) {
                        submitButton.setEnabled(true);
                    }
                }
            }
        });
        roleIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = roleIns.isChecked();
                if (checked) {
                    isRoleSelected = true;
                    if (enableSubmitButton()) {
                        submitButton.setEnabled(true);
                    }
                }
            }
        });


    }

    private boolean enableSubmitButton(){
        if(isPassValid && isFNameValid && isLNameValid && isEmailValid && isRoleSelected){
            return true;
        }
        else{
            return false;
        }
    }



}