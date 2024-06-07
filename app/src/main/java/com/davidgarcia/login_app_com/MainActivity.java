package com.davidgarcia.login_app_com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_register;
    ArrayList<Usuario> usuarios;
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

    }
    private void initComponents(){
        btn_register=findViewById(R.id.btn_register);
    }
    private void initListeners(){
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            gotoSecondActivity();
            }
        });
    }
    private void gotoSecondActivity(){
        Intent secondActivity = new Intent(this, Register.class);
        startActivity(secondActivity);
    }

}