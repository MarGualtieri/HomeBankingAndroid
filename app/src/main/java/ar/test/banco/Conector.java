package ar.test.banco;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Conector extends AppCompatActivity {

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

  public void conector(String email, String password) {



        //Toast.makeText(this, "entro en conector" + email + password, Toast.LENGTH_SHORT).show();

        if(TextUtils.isEmpty(password)){
           // Toast.makeText(getApplicationContext(), "password is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(email)) {
           // Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            if (email.trim().matches(emailPattern)) {

                getData(email,password);

            } else {
              //  Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                return;
            }
        }

    }

    private void getData(String emailData, String pass) {


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

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

                try {
                    JSONObject user = response.getJSONObject("user");
                    String name = user.getString("name");
                    String lastname = user.getString("lastname");
                    String email = user.getString("email");
                    String pesos = user.getString("pesos");
                    String dolares = user.getString("dolares");
                    String token = response.getString("token");

                    //Toast.makeText(Login.this," Bienvenido " + token, Toast.LENGTH_SHORT).show();

                    SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("name", name);
                    myEdit.putString("lastname", lastname);
                    myEdit.putString("email", email);
                    myEdit.putString("token", token);
                    myEdit.putInt("pesos", Integer.parseInt(pesos));
                    myEdit.putInt("dolares", Integer.parseInt(dolares));

                    myEdit.commit();

                    Intent i = new Intent(Conector.this,StartActivity.class);
                    findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
                    startActivity(i);
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //error.printStackTrace();
                Toast.makeText(Conector.this, " VERIFIQUE SUS DATOS" , Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);


    }

}

