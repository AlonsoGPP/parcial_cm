package com.davidgarcia.login_app_com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {
    Button btnRegistrar;
    Button btnVolver;

    EditText txtUsername;
    EditText txtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initComponents();
        initListeners();

    }
    private void initComponents(){
        btnRegistrar=findViewById(R.id.btn_reg_register);
        btnVolver = findViewById(R.id.btn_reg_volver);
        txtUsername = findViewById(R.id.txt_reg_username);
        txtPassword=findViewById(R.id.txt_reg_password);
    }

    private void initListeners(){
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    public void validateData(){
        String username=getUsername();
        String password=getPassword();
        if(username==null ){
            showToast(getString(R.string.msg_not_valid_user));
            return;
        }if(password==null ){
            showToast(getString(R.string.msg_not_valid_password));
            return;
        }

        goToLogin(username,password);

    }
    private void goToLogin(String username,String password){
        Intent login = new Intent(this, MainActivity.class);
        login.putExtra("EXTRA_USERNAME",username);
        login.putExtra("EXTRA_PASSWORD",password);
        startActivity(login);
        finish();
    }
    private String getUsername(){
        String username;
        try{
            username= txtUsername.getText().toString().trim();
            if(username.isEmpty())return null;
        }catch (Exception e){

            return null;
        }
        return username;
    }
    private String getPassword(){
        String password;
        try{
            password= txtPassword.getText().toString();
            if(password.isEmpty()) return null;
        }catch (Exception e){

            return null;
        }
     // if(password.length()<8) return null;
        return password;
    }
    private void showToast(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
    }



}