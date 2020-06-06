package com.vrodriguez.cinesaragon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton loginbtn = findViewById(R.id.loginbtn);
        ImageButton registerbtn = findViewById(R.id.registerbtn);
        ImageButton cinesbtn = findViewById(R.id.cinesbtn);
        loginbtn.setOnClickListener(this);
        registerbtn.setOnClickListener(this);
        cinesbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.loginbtn:
                Intent loginIntent = new Intent(MainActivity.this,Login.class);
                startActivityForResult(loginIntent,0);
                break;

            case R.id.registerbtn:
                Intent registerIntent = new Intent (MainActivity.this,Registro.class);
                startActivityForResult(registerIntent,0);
                break;

            case R.id.cinesbtn:
                Intent cinesIntent = new Intent(MainActivity.this, Mapa.class);
                startActivityForResult(cinesIntent,0);
                break;
        }
    }
}
