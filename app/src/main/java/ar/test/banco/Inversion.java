package ar.test.banco;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import static androidx.core.content.ContextCompat.getSystemService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Inversion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Inversion extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Inversion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Inversion.
     */
    // TODO: Rename and change types and number of parameters
    public static Inversion newInstance(String param1, String param2) {
        Inversion fragment = new Inversion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.inversion, container, false);

        TextView plazoFinal;

        EditText textoIngresoValor = (EditText) view.findViewById(R.id.textoIngreso);
        plazoFinal = (TextView) view.findViewById(R.id.plazoFinal);


        SharedPreferences sh = this.getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        String nombre = sh.getString("name", "");
        String apellido = sh.getString("lastname", "");
        String email = sh.getString("email", "");
        String token = sh.getString("token", "");
        int pesos = sh.getInt("pesos", 0);
        int dolares = sh.getInt("dolares", 0);

        Button invertirButton = view.findViewById(R.id.invertir);
        Button btnCalcular = view.findViewById(R.id.calcular);
        TextView totalCuentaValor = view.findViewById(R.id.totalCuentaValor);

        totalCuentaValor.setText("$" + Integer.toString(pesos));


        invertirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (textoIngresoValor.getText().toString().equals("") || textoIngresoValor.getText().toString().equals("0")) {
                    Snackbar snackBar = Snackbar.make(v, "INGRESE UN VALOR", Snackbar.LENGTH_LONG);

                    snackBar.show();
                    return;
                }


                int textoIngresoInt = Integer.parseInt(textoIngresoValor.getText().toString());


                if (textoIngresoInt > pesos) {
                    Snackbar snackBar = Snackbar.make(v, "NO POSEE DINERO SUFICIENTE EN LA CUENTA", Snackbar.LENGTH_LONG);
                          /*  .setAction(" ENVIAR MAIL", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });*/

                    snackBar.show();

                } else {

                    Snackbar snackBar = Snackbar.make(v, "INVERSION REALIZADA!", Snackbar.LENGTH_LONG);
                    snackBar.show();

                    SharedPreferences sh = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
                    String nombre = sh.getString("name", "");
                    String apellido = sh.getString("lastname", "");
                    String email = sh.getString("email", "");
                    int pesos = sh.getInt("pesos", 0);
                    int dolares = sh.getInt("dolares", 0);

                    getData(email, pesos, textoIngresoInt,token);

                }

            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {


                    Double numero = Double.valueOf(textoIngresoValor.getText().toString());

                    Double number = (numero * 37 / 100 / 12) + numero;

                    //Toast.makeText(getContext(),""+number,Toast.LENGTH_SHORT).show();
                    double plazo = Math.round(number * 100.0) / 100.0;
                    String plazoText = Double.toString(plazo);
                    plazoFinal.setText("$" + plazoText);

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

                } catch (Exception e) {
                    plazoFinal.setText("0");
                }

            }
        });


        return view;
    }

    private void getData(String emailData, int pesosData, int textoIngresoValor,String token) {


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        String postUrl = "https://homebancking.herokuapp.com/users/login";
        JSONObject postData = new JSONObject();

        try {

            postData.put("email", emailData);
            Double pesosFinales = Double.valueOf(textoIngresoValor - pesosData);
            postData.put("pesos", pesosFinales);


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
                           // String token = response.getString("token");

                            Intent i = new Intent(getContext(), StartActivity.class);
                            startActivity(i);
                            //Toast.makeText(Login.this," Bienvenido " + token, Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();
                        Toast.makeText(getContext(), " VERIFIQUE SUS DATOS", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getContext(), StartActivity.class);
                        startActivity(i);
                    }

                    // Providing Request Headers

                    public Map getHeaders() throws AuthFailureError{
                        HashMap headers = new HashMap();
                        headers.put("Content-Type", "application/json; charset=UTF-8");
                        headers.put("Authorization",token);
                        return headers;
                    }
                    /*
                    public Map<String, String> getParams() throws
                            com.android.volley.AuthFailureError {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "aplication/json");
                        headers.put("Authorization",token);
                        return headers;

                    };*/


                });

        requestQueue.add(jsonObjectRequest);


    }

}