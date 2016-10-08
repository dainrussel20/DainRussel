package com.example.androidstudio.firstandroid_morningclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    EditText UsernameReg, PasswordReg, PasswordConfirm, EmailReg;
    Button Register;
    DataAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        final DataAdapter db = new DataAdapter(getApplicationContext());
        final EditText EmailSignUp = (EditText) findViewById(R.id.EmailReg);
        final EditText PwSignUp = (EditText) findViewById(R.id.PasswordReg);
        final EditText ConPwSignUp = (EditText) findViewById(R.id.PasswordConfirm);
        final EditText Firstname = (EditText) findViewById(R.id.FirstName);
        final EditText Lastname = (EditText) findViewById(R.id.LastName);
        final EditText Username = (EditText) findViewById(R.id.UsernameReg);
        final Button btnRegister = (Button) findViewById(R.id.Register);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAdd = EmailSignUp.getText().toString();
                String pword = PwSignUp.getText().toString();
                String confPassword = ConPwSignUp.getText().toString();
                String uname = Username.getText().toString();
                String fname = Firstname.getText().toString();
                String lname = Lastname.getText().toString();

                if (!emailAdd.isEmpty() && !pword.isEmpty() && !confPassword.isEmpty() && !uname.isEmpty() && !fname.isEmpty() && !lname.isEmpty()) {
                    if (validateName(fname) == true && validateName(lname) == true) {
                        if (validateEmail(emailAdd) == true && validatePassword(pword) == true)
                            if (db.validateEmail(emailAdd) == true) {
                                if (db.validateUserName(uname) == true) {
                                    if (pword.equals(confPassword)) {
                                        db.registerUser(emailAdd, pword, uname, fname, lname);
                                        Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(Register.this, login.class);
                                        startActivity(intent);
                                    } else{
                                        PwSignUp.setError("Password did not match");
                                        Toast.makeText(getApplicationContext(), "Password did not match", Toast.LENGTH_SHORT).show();
                                    }
                                } else{
                                    Username.setError("Username already exists");
                                    Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                EmailSignUp.setError("Email Address already exists");
                                Toast.makeText(getApplicationContext(), "Email Address already exists", Toast.LENGTH_SHORT).show();
                            }
                        else if (validateEmail(emailAdd) == false && validatePassword(pword) == true) {
                            EmailSignUp.setError("Invalid email address");
                            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                        } else if (validateEmail(emailAdd) == true && validatePassword(pword) == false) {
                            PwSignUp.setError("Password must be more than 8 characters");
                            Toast.makeText(getApplicationContext(), "Password must be more than 8 characters", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Firstname.setError("Names must not contain numbers");
                        Lastname.setError("Names must not contain numbers");
                        Toast.makeText(getApplicationContext(), "Names must not contain numbers", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill up required fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public boolean validateEmail(String emailAdd) {
        String regexEmail = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";

        Pattern p = Pattern.compile(regexEmail);
        Matcher m = p.matcher(emailAdd);

        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validatePassword(String pword) {
        if (pword.length() >= 8) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateName(String name) {
        String regexEmail = "^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$";
        Pattern p = Pattern.compile(regexEmail);
        Matcher m = p.matcher(name);

        if (m.matches()) {
            return true;
        } else {
            return false;
        }

    }


}
