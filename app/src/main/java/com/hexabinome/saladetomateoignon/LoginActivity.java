package com.hexabinome.saladetomateoignon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.hexabinome.saladetomateoignon.modele.Mock;
import com.hexabinome.saladetomateoignon.modele.Utilisateur;

public class LoginActivity extends AppCompatActivity {


    private EditText inputEmail, inputPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    private Button btnSignIn, btnSignUp;

    private boolean isValidEmail = true, isValidPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //generation des utilisateurs de test
        Mock.generateUsers();
        //verification des preferences de l'appli
        if (PrefUtils.recupererUtilisateur(this) != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        btnSignIn = (Button) findViewById(R.id.signinButton);
        btnSignUp = (Button) findViewById(R.id.signupButton);

        inputEmail.addTextChangedListener(new LoginTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new LoginTextWatcher(inputPassword));

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //redirect to subscribe activity if button clicked
                Intent intent = new Intent(LoginActivity.this, SubscribeActivity.class);
                startActivityForResult(intent, 2);
            }
        });
    }

    //react to result from child activity (inscribe)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 2) {
            finish();
        }
    }

    private void submitForm() {

        if(!Mock.existsUser(inputEmail.getText().toString()))
        {
            //l'utilisateur n'existe pas
            inputLayoutEmail.setError(getString(R.string.err_unknown_user));
            return;
        }

        Utilisateur user = Mock.getUtilisateur(inputEmail.getText().toString(), inputPassword.getText().toString());
        if (user == null) {
            //l'utilisateur existe mais le mdp n'est pas correct
            inputLayoutPassword.setError(getString(R.string.err_bad_password));
            return;
        }

        PrefUtils.sauvegardeUtilisateur(this, user);
        PrefUtils.saveBooleanToPrefs(this, PrefUtils.PREFS_FIRST_LAUNCH, true);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            isValidEmail = false;
            return false;
        } else {
            isValidEmail = true;
            inputLayoutEmail.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            isValidPassword = false;
            requestFocus(inputPassword);
            return false;
        } else {
            isValidPassword = true;
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

        private LoginTextWatcher(View view) {
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
            switch (view.getId()) {
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
            }

            if (isValidEmail && isValidPassword) {
                btnSignIn.setEnabled(true);
            } else {
                btnSignIn.setEnabled(false);
            }

        }
    }
}
