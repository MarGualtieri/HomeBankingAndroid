package ar.test.banco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements ComunicaMenu{


     //------libreria volley API REST
    private RequestQueue queue; // es una cola donde van los request que se van haciendo, diferentes peticiones de diferentes urls

    //-------------------
    private Toolbar toolbar;

    public Fragment [] misFragments;

    Inicio init;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init = new Inicio();

        getSupportFragmentManager().beginTransaction().add(R.id.containerFragment,init).commit();

       toolbar=(Toolbar)findViewById(R.id.toolbar);

       //api call request
        //

        //-----------------------------

        queue = Volley.newRequestQueue(this);
        obtenerDatosVolley();

                }
private void obtenerDatosVolley(){

   // String URL="https://serverfutbol.herokuapp.com/";
    String url="https://api.androidhive.info/contacts/";

    JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

        @Override
        public void onResponse(JSONObject response) {
            Toast.makeText(MainActivity.this, "Nombre", Toast.LENGTH_LONG).show();
            try {
                JSONArray myJsonArray = response.getJSONArray("title");
               // JSONObject myJsonObject = myJsonArray.getJSONObject(0) // indice del numero del objeto dentro del array que quiero

                for (int i=0; i<myJsonArray.length(); i++) {
                    JSONObject myJsonObject = myJsonArray.getJSONObject(i);
                    String nombre= myJsonObject.getString("title"); // el nombre de cmillas verde toene que ser exacto al de la base de datos debe existir
                    Toast.makeText(MainActivity.this, "Nombre es:" + nombre, Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){

                e.printStackTrace();
            }




        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });
    queue.add(request);
}



    @Override
    public void menu(int boton) {


        misFragments = new Fragment[]{new Cuenta(), new Tarjeta(), new Inversion()};

        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragment,misFragments[boton]).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dots, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.config) {
            return true;
        }
        if (id == R.id.info) {
informacion();

        }
        if (id == R.id.contacto) {
            return true;
        }
        if (id == R.id.salir) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void informacion(){
        Intent i= new Intent(this,Informacion.class );
        startActivity(i);
    }


}