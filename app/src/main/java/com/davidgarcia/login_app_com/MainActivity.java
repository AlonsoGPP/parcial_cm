package com.davidgarcia.login_app_com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_register;
    Button btn_login;
    EditText txtUsuario;
    EditText txtPassword;

    ;
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
        initComponents();
        initListeners();
        checkIntents();

    }
    private void checkIntents(){
        Intent intent = getIntent();
        if(intent!=null && intent.getExtras()!=null){
            String username = intent.getStringExtra("EXTRA_USERNAME");
            String password = intent.getStringExtra("EXTRA_PASSWORD");
            Usuario user = new Usuario(username,password);
            Usuario.usuarios.add(user);
        }
    }
   // @Override
   // protected  void onResume(){
    //    super.onResume();

   // }
    private void initComponents(){
        btn_register=findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_login);
        txtPassword=findViewById(R.id.txt_password);
        txtUsuario=findViewById(R.id.txt_username);
    }


    private void initListeners(){
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            gotoSecondActivity();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateData())
                {
                    doLogin();
                }
            }
        });
    }
    private void gotoSecondActivity(){
        Intent secondActivity = new Intent(this, Register.class);
        startActivity(secondActivity);
    }
    private boolean validateData(){
        String username = getUsername();
        String password = getPassword();
        if(username==null || password==null){
            showToast(getString((R.string.msg_not_valid_login)));
            return false;
        }
        return true;

    }
    private void doLogin(){
        String username = getUsername();
        String password = getPassword();
        boolean userFound=false;
        if(Usuario.usuarios.isEmpty()){
            showToast("not data available");
            return;
        }
        for (Usuario user : Usuario.usuarios) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                gotoHome(username);
                userFound = true;
                break;
            }}
       if(!userFound) showToast(getString(R.string.msg_not_valid_login));


    }
    private void gotoHome(String username){
        Intent home = new Intent(this, Home.class);
        home.putExtra("EXTRA_USERNAME", username);
        startActivity(home);
    }
    @Nullable
    private String getUsername(){
        String username;
        try {
            username = txtUsuario.getText().toString();
            if(username.isEmpty()) return null;
            return username;
        }catch(Exception e){
            return null;
        }
    }
    @Nullable
    private String getPassword(){
        String password;
        try {
            password = txtPassword.getText().toString();
            if(password.isEmpty()) return null;
            return password;
        }catch(Exception e){
            return null;
        }
    }
    private void showToast(String msg){
        Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
    }


}