package com.hexabinome.saladetomateoignon;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hexabinome.saladetomateoignon.modele.Mock;
import com.hexabinome.saladetomateoignon.modele.Utilisateur;

public class SubscribeActivity extends AppCompatActivity {

    private EditText inputFirstName, inputLastName, inputEmail, inputPassword, inputVerifyPassword;
    private TextInputLayout inputLayoutFirstName, inputLayoutLastName, inputLayoutEmail,
            inputLayoutPassword, inputLayoutVerifyPassword;
    private Button btnValidate;

    private Toolbar toolbar;

    private RadioButton radioButtonEtudiant, radioButtonProf;

    private boolean isValidEmail = false, isValidPassword = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        inputFirstName = (EditText) findViewById(R.id.input_firstname);
        inputLastName = (EditText) findViewById(R.id.input_lastname);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        inputVerifyPassword = (EditText) findViewById(R.id.input_verify_password);
        radioButtonEtudiant = (RadioButton) findViewById(R.id.radio_etudiant);
        radioButtonProf = (RadioButton) findViewById(R.id.radio_prof);

        inputLayoutFirstName = (TextInputLayout) findViewById(R.id.input_layout_firstname);
        inputLayoutLastName = (TextInputLayout) findViewById(R.id.input_layout_lastname);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputLayoutVerifyPassword = (TextInputLayout) findViewById(R.id.input_layout_verify_password);

        btnValidate = (Button) findViewById(R.id.validateButton);
        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
        inputFirstName.addTextChangedListener(new SignupTextWatcher(inputFirstName));
        inputLastName.addTextChangedListener(new SignupTextWatcher(inputLastName));
        inputEmail.addTextChangedListener(new SignupTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new SignupTextWatcher(inputPassword));
        inputVerifyPassword.addTextChangedListener(new SignupTextWatcher(inputVerifyPassword));
    }

    private void submitForm() {
        if (!validateFirstName()) {
            return;
        }
        if (!validateLastName()) {
            return;
        }
        if (!validateEmail()) {
            return;
        }
        if (!validatePassword()) {
            return;
        }
        if (!confirmPassword()) {
            return;
        }

        Utilisateur.TypeUtilisateur typeUtilisateur = Utilisateur.TypeUtilisateur.ETUDIANT;
        if(radioButtonProf.isChecked())
            typeUtilisateur = Utilisateur.TypeUtilisateur.PROFESSEUR;

        //changer d'activité pour main activity, passer directement sur le fragment préférences ?
        Utilisateur user = new Utilisateur(
                inputLastName.getText().toString(),
                inputFirstName.getText().toString(),
                inputEmail.getText().toString(),
                inputPassword.getText().toString(), typeUtilisateur);
        //On ajoute l'utilisateur a la liste des utilisateurs de test
        Mock.addUtilisateur(user);
        //On enregistre l'utilisateur dans les preferences de l'appli
        PrefUtils.sauvegardeUtilisateur(this, user);

        //On redirige vers Mainactivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 3);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 3) {
            setResult(2);
            finish();
        }
    }

    private boolean validateFirstName() {
        String firstName = inputFirstName.getText().toString().trim();

        if (firstName.isEmpty()) {
            inputLayoutFirstName.setError(getString(R.string.err_msg_first_name));
            requestFocus(inputFirstName);
            return false;
        } else {
            inputLayoutFirstName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateLastName() {
        String LastName = inputLastName.getText().toString().trim();

        if (LastName.isEmpty()) {
            inputLayoutLastName.setError(getString(R.string.err_msg_last_name));
            requestFocus(inputLastName);
            return false;
        } else {
            inputLayoutLastName.setErrorEnabled(false);
        }
        return true;
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
            requestFocus(inputPassword);
            isValidPassword = false;
            return false;
        } else {
            isValidPassword = true;
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean confirmPassword() {
        String password1 = inputPassword.getText().toString().trim();
        String password2 = inputVerifyPassword.getText().toString().trim();
        if (!password1.equals(password2)) {
            inputLayoutVerifyPassword.setError(getString(R.string.err_msg_confirm_password));
            requestFocus(inputVerifyPassword);
            return false;
        } else {
            inputLayoutVerifyPassword.setErrorEnabled(false);
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

    private class SignupTextWatcher implements TextWatcher {
        private View view;

        private SignupTextWatcher(View view) {
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
                case R.id.input_firstname:
                    validateFirstName();
                    break;
                case R.id.input_lastname:
                    validateLastName();
                    break;
                case R.id.input_verify_password:
                    confirmPassword();
                    break;
            }
            if (isValidEmail && isValidPassword) {
                btnValidate.setEnabled(true);
            } else {
                btnValidate.setEnabled(false);
            }

        }
    }
}
