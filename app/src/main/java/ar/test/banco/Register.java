package ar.test.banco;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Register extends AppCompatActivity {

    //------libreria volley API REST
    //private RequestQueue queue; // es una cola donde van los request que se van haciendo, diferentes peticiones de diferentes urls


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);




       sendData();


    }


    private void sendData() {

        EditText nombreRegister =findViewById(R.id.nombreRegister);
        EditText apellidoRegister =  findViewById(R.id.apellidoRegister);
        EditText mailRegister = findViewById(R.id.mailRegister);
        EditText passwordRegister = findViewById(R.id.passwordRegister);
        Button registerSend = findViewById(R.id.btnRegisterSend);


        registerSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String postUrl = "https://homebancking.herokuapp.com/users";
                RequestQueue requestQueue = Volley.newRequestQueue(Register.this);

                JSONObject postData = new JSONObject();


                try {
                    postData.put("name", nombreRegister.getText());
                    postData.put("lastname", apellidoRegister.getText());
                    postData.put("email", mailRegister.getText());
                    postData.put("password", passwordRegister.getText());
                    postData.put("pesos", 0);
                    postData.put("dolares", 0);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                       // Toast.makeText(Register.this,"Registracion exitosa! Bienvenido "+nombreRegister.getText(), Toast.LENGTH_SHORT).show();


                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("REGISTRACION EXITOSA!");
                        builder.setMessage("Bienvenido "+nombreRegister.getText()+" "+apellidoRegister.getText());
                        builder.setCancelable(true);

                        final AlertDialog closedialog= builder.create();

                        closedialog.show();

                        final Timer timer2 = new Timer();
                        timer2.schedule(new TimerTask() {
                            public void run() {
                                closedialog.dismiss();
                                timer2.cancel(); //this will cancel the timer of the system
                                Intent i = new Intent(Register.this,Login.class);
                                startActivity(i);
                            }
                        }, 2000);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();
                        Toast.makeText(Register.this, "ERROR EN LA REGISTRACION - email already exist", Toast.LENGTH_SHORT).show();
                    }
                });

                requestQueue.add(jsonObjectRequest);


            }
        });
    }

    private void getData() {


        //String myUrl = "https://serverfutbol.herokuapp.com/users/";
        //String myUrl = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1";
        String myUrl =  "https://homebancking.herokuapp.com/users";

        StringRequest myRequest = new StringRequest(Request.Method.GET, myUrl,
                response -> {
                    Toast.makeText(Register.this, response.toString(), Toast.LENGTH_SHORT).show();


                    try {

                        JSONArray array = new JSONArray(response);
                       // Toast.makeText(Register.this, array.getJSONObject(0).toString(), Toast.LENGTH_SHORT).show();
                        //JSONObject object = new JSONObject(response);


                       // JSONObject myJsonObject = array.getJSONObject(0);

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object1 = array.getJSONObject(i);
                            String name = object1.getString("nombre");
                            Toast.makeText(Register.this, name.toString(), Toast.LENGTH_SHORT).show();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                volleyError -> Toast.makeText(Register.this, "error", Toast.LENGTH_SHORT).show()
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(myRequest);
    }

}
