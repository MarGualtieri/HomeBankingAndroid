package ar.test.banco;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentContainer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentContainer extends Fragment {

    TabLayout table;
    ViewPager2 viewPager;
    FragmentAdapter adapter;


    TextView userInicio;
    TextView user;
    TextView dolarBlue;


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


        View view = inflater.inflate(R.layout.fragment_container, container, false);

        ViewFlipper simpleViewFlipper = view.findViewById(R.id.simpleViewFlipper); // get the reference of ViewFlipper
        //simpleViewFlipper.startFlipping();

        Animation in = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_out_right);
        // set the animation type's to ViewFlipper
        simpleViewFlipper.setInAnimation(in);
        simpleViewFlipper.setOutAnimation(out);
        // set interval time for flipping between views
        simpleViewFlipper.setFlipInterval(2000);
        // set auto start for flipping between views
        simpleViewFlipper.setAutoStart(true);




        SharedPreferences sh = this.getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        String nombre = sh.getString("name", "");
        String apellido = sh.getString("lastname", "");
        String email = sh.getString("email", "");
        int pesos = sh.getInt("pesos", 0);
        int dolares = sh.getInt("dolares", 0);


        userInicio = view.findViewById(R.id.userInicio);
        userInicio.setText(nombre);
        user = view.findViewById(R.id.user);
        user.setText(email);


        /*dolarBlue = view.findViewById(R.id.dolarBlue);
         dolarBlue.setText(blue);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        String postUrl = "https://www.dolarsi.com/api/api.php?type=valoresprincipales";
        JSONObject postData = new JSONObject();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, postData, postUrl, response -> {

            try {
                JSONArray jsonArray = response.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String email = jsonObject.getString("email");
                    jsonResponses.add(email);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> {

            //error.printStackTrace();
            //Toast.makeText(Login.this, " VERIFIQUE SUS DATOS", Toast.LENGTH_SHORT).show();
        });

        requestQueue.add(jsonObjectRequest);


    }
*/

// TAB ///////////////////////////////////////////////


/*
   FragmentActivity myContext = null;
        FragmentManager fragManager = getActivity().getSupportFragmentManager();
        adapter = new FragmentAdapter(fragManager, getLifecycle());
         table = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
        table.addTab(table.newTab().setText("first"));
        table.addTab(table.newTab().setText("second"));
        table.addTab(table.newTab().setText("third"));
        table.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab){

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                table.selectTab(table.getTabAt(position));
            }
        });*/

        return view;
}

/*
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText("OBJECT " + (position + 1))
        ).attach();
    }*/

}





