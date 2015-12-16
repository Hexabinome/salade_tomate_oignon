package com.hexabinome.saladetomateoignon;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    private Button btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        btnSignIn = (Button) findViewById(R.id.signinButton);


        inputEmail.addTextChangedListener(new LoginTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new LoginTextWatcher(inputPassword));

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                submitForm();
            }
        });
    }

    private void submitForm()
    {
        if(!validateEmail())
        {
            return;
        }
        if(!validatePassword())
        {
            return;
        }
        //changer d'activité
    }
    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
     private class LoginTextWatcher implements TextWatcher {
         private View view;

         private LoginTextWatcher(View view){
             this.view = view;
         }

         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {

         }

         @Override
         public void afterTextChanged(Editable s) {
             switch(view.getId()){
                 case R.id.input_email:
                     validateEmail();
                     break;
                 case R.id.input_password:
                     validatePassword();
                     break;
             }

         }
     }
}
