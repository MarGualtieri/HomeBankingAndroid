package ar.test.banco;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cuenta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cuenta extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Cuenta() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cuenta.
     */
    // TODO: Rename and change types and number of parameters
    public static Cuenta newInstance(String param1, String param2) {
        Cuenta fragment = new Cuenta();
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
        SharedPreferences sh = this.getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        String email = sh.getString("email", "");
        String nombre = sh.getString("name", "");
        String apellido = sh.getString("lastname", "");
        String token = sh.getString("token", "");
        int pesos = sh.getInt("pesos",0);
        int dolares = sh.getInt("dolares",0);



      //  getData(email);

    }

    private void getData(String email) {

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        String postUrl = "https://homebancking.herokuapp.com/users/cuenta"; //
        JSONObject postData = new JSONObject();

        try {

            postData.put("email", email);



        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, postUrl, postData, response -> {

            try {
                JSONObject user = response.getJSONObject("user");
                String name = user.getString("name");
                String lastname = user.getString("lastname");
                String emailNuevo = user.getString("email");
                int pesos = user.getInt("pesos");
                int dolares = user.getInt("dolares");
                String token = response.getString("token");

                SharedPreferences sh = this.getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sh.edit();
                myEdit.putString("name", name);
                myEdit.putString("lastname", lastname);
                myEdit.putString("email", emailNuevo);
                myEdit.putString("token", token);
                myEdit.putInt("pesos", pesos);
                myEdit.putInt("dolares", dolares);
                myEdit.commit();
                //Toast.makeText(Login.this," Bienvenido " + token, Toast.LENGTH_SHORT).show();

              /*  SharedPreferences sh = this.getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("name", name);
                myEdit.putString("lastname", lastname);
                myEdit.putString("email", emialNuevo);
                myEdit.putString("token", token);
                myEdit.putInt("pesos", Integer.parseInt(pesos));
                myEdit.putInt("dolares", Integer.parseInt(dolares));

                myEdit.commit();*/



            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> {

            //error.printStackTrace();
            //Toast.makeText(Login.this, " VERIFIQUE SUS DATOS", Toast.LENGTH_SHORT).show();
        });

        requestQueue.add(jsonObjectRequest);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view=  inflater.inflate(R.layout.cuenta, container, false);

        SharedPreferences sh = this.getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        String nombre = sh.getString("name", "");
        String apellido = sh.getString("lastname", "");
        String email = sh.getString("email", "");
        String token = sh.getString("token", "");
        int pesos = sh.getInt("pesos",0);
        int dolares = sh.getInt("dolares",0);

        TextView nameCuenta=view.findViewById(R.id.nameCuenta);
        TextView lastnameCuenta=view.findViewById(R.id.lastnameCuenta);
        TextView pesosCuenta=view.findViewById(R.id.pesosCuenta);
        TextView dolaresCuenta=view.findViewById(R.id.dolaresCuenta);

        nameCuenta.setText(nombre);
        lastnameCuenta.setText(apellido);
        pesosCuenta.setText("$ "+Integer.toString(pesos));
        dolaresCuenta.setText("u$s "+Integer.toString(dolares));



        return view;
    }
}