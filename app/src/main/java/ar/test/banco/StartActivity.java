package ar.test.banco;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StartActivity extends AppCompatActivity {

    ImageButton home;
    ImageButton close;
    TextView titleInfo;



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        SharedPreferences sh = getSharedPreferences("data", MODE_PRIVATE);
        String nombre = sh.getString("name", "");
        String apellido = sh.getString("lastname", "");
        String email = sh.getString("email", "");
        int pesos = sh.getInt("pesos", 0);
        int dolares = sh.getInt("dolares", 0);

        getData(email);



        // conectar boton home
        home = findViewById(R.id.home);
        close = findViewById(R.id.close);
        titleInfo=findViewById(R.id.titleInfo);

        Button cuenta = findViewById(R.id.btnCuenta);
        Button tarjeta = findViewById(R.id.btnTarjeta);
        Button inversion = findViewById(R.id.btnInversion);
        TextView userName = findViewById(R.id.userName);

        titleInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                Information miDialogo = Information.newInstance("Some Title");
                miDialogo.show(fm, "fragment_edit_name");

            }
        });

        cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment cuentaFragment = new Cuenta();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, cuentaFragment).commit();
            }
        });
        tarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment tarjetaFragment = new Tarjeta();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, tarjetaFragment).commit();


            }
        });
        inversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment inversionFragment = new Inversion();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, inversionFragment).commit();
            }
        });


        // assigning ID of the toolbar to a variable
        Toolbar toolbar = findViewById(R.id.toolbar);

        // using toolbar as ActionBar
        setSupportActionBar(toolbar);


        // recuperar el dato desde login


        userName.setText(nombre);


        // poner a la escucha al boton home
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentContainer init = new FragmentContainer();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, init).commit();

            }

        });


        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog alertDialog = new AlertDialog.Builder(StartActivity.this).create();
                alertDialog.setTitle(nombre);
                alertDialog.setMessage("Desea cerrar la sesion?");

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                myEdit.putString("name", "");
                                myEdit.putString("lastname", "");
                                myEdit.putString("email", "");
                                myEdit.putString("token", "");
                                myEdit.putInt("pesos", 0);
                                myEdit.putInt("dolares", 0);
                                myEdit.commit();

                                Intent i = new Intent(StartActivity.this, Login.class);
                                startActivity(i);
                                finish();
                            }
                        });

                alertDialog.show();

                // Navigation.findNavController(v).navigate(R.id.action_fragmentContainer_to_cuenta2);


            }
        });


    }

    private void getData(String emailData) {

        RequestQueue requestQueue = Volley.newRequestQueue(StartActivity.this);


        String postUrl =  "https://homebancking.herokuapp.com/users/login";
        JSONObject postData = new JSONObject();

        try {

            postData.put("email", emailData);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                postUrl,
                postData,
                new Response.Listener<JSONObject>() {


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


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
           /* @Override
            public void onErrorResponse(VolleyError error) {

                //error.printStackTrace();
                Toast.makeText(StartActivity.this, " VERIFIQUE SUS DATOS en StartActivity" , Toast.LENGTH_SHORT).show();

            }*/

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), " VERIFIQUE SUS DATOS", Toast.LENGTH_SHORT).show();
                        //  Intent i = new Intent(getApplicationContext(), StartActivity.class);
                        // startActivity(i);
                    }

                    // Providing Request Headers


                  /*  public Map<String, String> getParams() throws
                            com.android.volley.AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "aplication/json");
                        headers.put("Authorization",token);
                        return headers;

                    };*/

                })

        {  @Override
        public Map<String,String> getHeaders() throws AuthFailureError{
            Toast.makeText(getApplicationContext(), " entro al header", Toast.LENGTH_SHORT).show();
            HashMap headers = new HashMap();
            headers.put("Content-Type", "application/json; charset=UTF-8");
            headers.put("Authorization","");
            return headers;
        }}


                ;



        requestQueue.add(jsonObjectRequest);


    }

    }
