package ar.test.banco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class Login extends AppCompatActivity {

EditText login;
EditText password;
Button LogEnter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        LogEnter = (Button)findViewById(R.id.logEnter);

        LogEnter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Bundle bundle = new Bundle();
                bundle.putString("username", login.getText().toString());

                Intent next = new Intent(Login.this, MainActivity.class);
                next.putExtras(bundle);
                startActivity(next);
            }
        });

    }





}