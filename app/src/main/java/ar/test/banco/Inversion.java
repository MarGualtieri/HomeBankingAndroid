package ar.test.banco;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import static androidx.core.content.ContextCompat.getSystemService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Inversion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Inversion extends Fragment {

    EditText textoIngreso;
    TextView plazoFinal;

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


      View view= inflater.inflate(R.layout.inversion, container, false);
        textoIngreso=(EditText)view.findViewById(R.id.textoIngreso);
        plazoFinal=(TextView)view.findViewById(R.id.plazoFinal);



        Button invertirButton = (Button) view.findViewById(R.id.invertir);
        Button button = (Button) view.findViewById(R.id.calcular);

        invertirButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                    Snackbar snackBar = Snackbar .make(v, "INVERSION REALIZADA CON EXITO", Snackbar.LENGTH_LONG)
                            .setAction(" ENVIAR MAIL", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });

                    snackBar.show();

            }
        });

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                try {
                    String valor = textoIngreso.getText().toString();
                    int numero= Integer.parseInt(valor);
                    double plazo=  (numero*37/100/12) + (numero);
                    String plazoText = Double.toString(plazo);
                    plazoFinal.setText(plazoText);

                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

                }catch (Exception e){
                    plazoFinal.setText("0");
                }





            }
        });


        return view;
    }



}