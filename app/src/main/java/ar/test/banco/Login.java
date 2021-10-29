package ar.test.banco;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;

import android.text.TextUtils;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;




public class Login extends AppCompatActivity {

    EditText email;
    EditText password;
    Button btnLogin;
    Button btnRegister;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.logEnter);
        btnRegister = findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(v -> {

            Intent next = new Intent(Login.this, Register.class);
            startActivity(next);

        });

        btnLogin.setOnClickListener(v -> {

            loginUser(email.getText().toString(), password.getText().toString());

        });

    }

    private void loginUser(String email, String password) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "password is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

       if(TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            if (email.trim().matches(emailPattern)) {

                getData(email,password);

            } else {
                Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                return;
            }
        }

    }
    private void getData(String emailData, String pass) {


        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);

        String postUrl =  "https://homebancking.herokuapp.com/users/login";
        JSONObject postData = new JSONObject();

        try {

            postData.put("email", emailData);
            postData.put("password", pass);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                 Toast.makeText(Login.this," Bienvenido ", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Login.this,StartActivity.class);
                startActivity(i);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //error.printStackTrace();
                Toast.makeText(Login.this, " VERIFIQUE SUS DATOS" , Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);


    }

}