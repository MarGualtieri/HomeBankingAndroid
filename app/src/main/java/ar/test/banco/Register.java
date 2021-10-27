package ar.test.banco;

import static android.app.ProgressDialog.show;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
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
import java.util.List;

public class Register extends AppCompatActivity {

    //------libreria volley API REST
    //private RequestQueue queue; // es una cola donde van los request que se van haciendo, diferentes peticiones de diferentes urls


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        TextView usernameSend = (TextView) findViewById(R.id.usernameSend);
        TextView passwordSend = (TextView) findViewById(R.id.passwordSend);
        TextView tv = (TextView) findViewById(R.id.tv);
        Button registerSend = (Button) findViewById(R.id.btnRegisterSend);

        getData();

    }

    private void getData() {

        TextView totalCasesWorld, totalDeathsWorld, totalRecoveredWorld;
        totalCasesWorld = findViewById(R.id.newCasesWorld);
        totalDeathsWorld = findViewById(R.id.newDeathsWorld);
        totalRecoveredWorld = findViewById(R.id.newRecoveredWorld);


        String myUrl = "https://serverfutbol.herokuapp.com/users/";
        //String myUrl = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1";

        StringRequest myRequest = new StringRequest(Request.Method.GET, myUrl,
                response -> {
                    //Toast.makeText(Register.this, response.toString(), Toast.LENGTH_SHORT).show();


                    try {
                        JSONObject object = new JSONObject(response);
                        JSONArray array = object.getJSONArray("jugadores");
                        JSONObject myJsonObject = array.getJSONObject(0);


                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object1 = array.getJSONObject(i);
                            String name = object1.getString("nombre");
                            Toast.makeText(Register.this, name.toString(), Toast.LENGTH_SHORT).show();
                        }
                    /*
                    try {
                        JSONObject object = new JSONObject(response);
                        JSONArray array = object.getJSONArray("users");
                        JSONObject myJsonObject = array.getJSONObject(0);


                        for(int i=0;i<array.length();i++) {
                            JSONObject object1=array.getJSONObject(i);
                            String name =object1.getString("name");
                            Toast.makeText(Register.this, name.toString(), Toast.LENGTH_SHORT).show();
                        }


                       totalCasesWorld.setText(myJsonObject.getString("name"));
                        totalRecoveredWorld.setText(myJsonObject.getString("email"));
                        totalDeathsWorld.setText(myJsonObject.getString("gender"));*/


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
