package com.example.nutritionapplication.activity;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.nutritionapplication.R;
import com.example.nutritionapplication.dao.UserDao;
import com.example.nutritionapplication.database.UserDatabase;
import com.example.nutritionapplication.databinding.ActivityLoginBinding;
import com.example.nutritionapplication.model.User;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding loginBinding;
    private UserDatabase database;

    private UserDao userDao;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.
                setContentView(LoginActivity.this,
                R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Login Page");

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Check User...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);


        database = Room.databaseBuilder(this, UserDatabase.class, "mi-database.db")
                .allowMainThreadQueries()
                .build();

        userDao = database.getUserDao();

        loginBinding. tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,
                        SignUpActivity.class));
            }
        });

        loginBinding. btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyValidation()) {
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            User user = userDao.getUser(loginBinding.etUsername.getEditText()
                                            .getText().toString().trim(),
                                    loginBinding.etPassword.getEditText().getText().toString()
                                            .trim());
                            if(user!=null){
                                startActivity(new Intent(LoginActivity.this,
                                        BMIActivity.class));
                                finish();
                                loginBinding.etUsername.getEditText().setText("");
                                loginBinding.etUsername.getEditText().setText("");
                            }else{
                                Toast.makeText(LoginActivity.this, "Unregistered user, " +
                                                "or incorrect",
                                        Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    }, 1000);

                }else{
                    Toast.makeText(LoginActivity.this, "Empty Fields",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean emptyValidation() {
        if (TextUtils.isEmpty(loginBinding.etUsername.getEditText().getText().toString().trim()) ||
                TextUtils.isEmpty(loginBinding.etPassword.getEditText().getText().toString().trim())) {
            return true;
        }else {
            return false;
        }
    }
}
