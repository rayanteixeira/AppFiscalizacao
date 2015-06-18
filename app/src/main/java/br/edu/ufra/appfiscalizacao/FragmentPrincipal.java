package br.edu.ufra.appfiscalizacao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class FragmentPrincipal extends Fragment {



    public FragmentPrincipal() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_principal, container, false);


        ListView listaestabelecimentos = (ListView) rootView.findViewById(R.id.listaestabelecimentos);
        String[] pontos = {"Rayan","Jairo", "Geovane"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_list_item_1, pontos);
        listaestabelecimentos.setAdapter(adapter);

        return rootView;
    }



}
