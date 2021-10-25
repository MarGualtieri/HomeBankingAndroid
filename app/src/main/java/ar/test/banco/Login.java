package ar.test.banco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;


public class Login extends AppCompatActivity {

EditText login;
EditText password;
Button LogEnter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login = findViewById(R.id.username);
        password = findViewById(R.id.password);
        LogEnter = findViewById(R.id.logEnter);

        LogEnter.setOnClickListener(v -> {

            Fragment fragment = new Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("username",login.getText().toString());
            fragment.setArguments(bundle);


            Intent next = new Intent(Login.this, StartActivity.class);
             next.putExtras(bundle);
            startActivity(next);
        });

    }





}