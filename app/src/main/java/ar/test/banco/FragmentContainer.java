package ar.test.banco;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentContainer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentContainer extends Fragment {

    ImageButton cuentaBoton;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "username";
    private static final String ARG_PARAM2 = "username";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentContainer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentContainer.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentContainer newInstance(String param1, String param2) {
        FragmentContainer fragment = new FragmentContainer();
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

      //  Bundle bundle = this.getArguments();
       // String username = bundle.getString("username");

        Toast.makeText(getContext(), "Nombre en fragment es:" +mParam1 + mParam2 , Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_container, container, false);



   /*
    cuentaBoton = (ImageButton) view.findViewById(R.id.cuentaBoton3);

        cuentaBoton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Navigation.findNavController(v).navigate(R.id.action_fragmentContainer_to_cuenta2);
        }
    });
*/
        return view;
    }
}