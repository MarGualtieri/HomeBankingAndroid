package ar.test.banco;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tarjeta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tarjeta extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tarjeta() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tarjeta.
     */
    // TODO: Rename and change types and number of parameters
    public static Tarjeta newInstance(String param1, String param2) {
        Tarjeta fragment = new Tarjeta();
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
        View view =inflater.inflate(R.layout.tarjeta, container, false);


        List<ListElement> elements;
        elements = new ArrayList<>();
        elements.add(new ListElement("black","12/05/2021","$2345","Active"));
        elements.add(new ListElement("black","08/06/2021","$234","Active"));
        elements.add(new ListElement("black","23/23/2021","$105262","Active"));
        elements.add(new ListElement("black","18/04/2020","$236","Cancel"));
        elements.add(new ListElement("black","30/06/2019","$654","Cancel"));
        elements.add(new ListElement("black","08/06/2021","$234","Active"));
        elements.add(new ListElement("black","01/01/2015","$111","Cancel"));

        ListAdapter listAdapter = new ListAdapter(elements,getContext());
        RecyclerView recyclerView = view.findViewById(R.id.listRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);



      return view;
    }
}