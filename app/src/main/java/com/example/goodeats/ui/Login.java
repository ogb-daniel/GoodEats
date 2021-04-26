package com.example.goodeats.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goodeats.R;
import com.example.goodeats.data.User;
import com.example.goodeats.data.UserViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class Login extends Fragment {
    private List<User> users = new ArrayList<>();
    private UserViewModel userViewModel;
    private User user;
    private EditText email,password;
    private Button loginStaff,loginUser;
    private String registeredEmail,registeredPassword,emailUsername,emailPassword;
    private TextView emailValid,txtViewPassword,generalValid;
    private ProgressBar progress;
    private LinearLayout form,progressLayout;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        loginStaff = view.findViewById(R.id.loginStaff);
        loginUser = view.findViewById(R.id.loginUser);
        emailValid = view.findViewById(R.id.emailValid);
        generalValid = view.findViewById(R.id.generalValid);
        form = view.findViewById(R.id.form);
        progress = view.findViewById(R.id.progress);
         emailUsername = email.getText().toString().trim();
         emailPassword = password.getText().toString().trim();
         txtViewPassword = view.findViewById(R.id.txtViewPassword);
         progressLayout = view.findViewById(R.id.progressLayout);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                    setUsers(users);

            }
        });
        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailUsername = email.getText().toString().trim();
                emailPassword = password.getText().toString().trim();
                if(emailUsername.isEmpty()) {
                    emailValid.setText("Enter Email Address!");
                    emailValid.setTextColor(Color.RED);
                }else {
                    if (emailUsername.matches(emailPattern)) {
                        emailValid.setText("Valid Email");
                        emailValid.setTextColor(Color.WHITE);
                        for (User user:
                                users) {
                            registeredEmail = user.getEmailId();
                            registeredPassword = user.getPassword();

                            if((emailUsername.equals(registeredEmail)) && (emailPassword.equals(registeredPassword))){
                                progressLayout.setVisibility(View.VISIBLE);
                                form.setVisibility(View.GONE);
                                form.setClickable(false);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Navigation.findNavController(v).navigate(R.id.action_login2_to_userHomePage);
                                    }
                                },3000);
                            }else{
                                progressLayout.setVisibility(View.VISIBLE);
                                form.setVisibility(View.GONE);
                                form.setClickable(false);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        generalValid.setText("Invalid Credentials!!");
                                        generalValid.setTextColor(Color.RED);
                                        generalValid.setTextSize(20f);
                                        progressLayout.setVisibility(View.INVISIBLE);
                                        form.setVisibility(View.VISIBLE);
                                        form.setClickable(true);
                                    }
                                },3000);
                            }
//                            Log.d("TAG","Logged:"+registeredEmail);
//                            Log.d("TAG","Logged:"+registeredPassword);
//                            Log.d("TAG","Email:"+emailUsername);
//                            Log.d("TAG","Password:"+emailPassword);
                        }
                    } else {
                        emailValid.setText("Invalid Email Address!");
                        emailValid.setTextColor(Color.RED);
                    }
                }
            }
        });

        loginStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void setUsers(List<User> user){
        this.users = user;
    }
}