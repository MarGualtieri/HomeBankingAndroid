package ar.test.banco;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

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



        Toast.makeText(getContext(), "Nombre en fragment es:" + mParam1 + mParam2, Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_container, container, false);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        String value = sharedPreferences.getString("email","");
        userInicio = view.findViewById(R.id.userInicio);
        userInicio.setText(value);




/*
        FragmentActivity myContext = null;
        table = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.pager);
        FragmentManager fragManager = myContext.getSupportFragmentManager();
        adapter = new FragmentAdapter(fragManager, getLifecycle());
        viewPager.setAdapter(adapter);
        table.addTab(table.newTab().setText("first"));
        table.addTab(table.newTab().setText("second"));
        table.addTab(table.newTab().setText("third"));

        table.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()

*/
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





