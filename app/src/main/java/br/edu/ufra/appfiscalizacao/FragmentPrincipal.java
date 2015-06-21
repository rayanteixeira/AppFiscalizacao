package br.edu.ufra.appfiscalizacao;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.edu.ufra.appfiscalizacao.adapter.EstabelecimentoAdapter;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;


public class FragmentPrincipal extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private EstabelecimentoAdapter adapter;
    private ArrayList<String> estabelecimentos;
    //private Estabelecimento estabelecimento;

    public FragmentPrincipal() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_principal, container, false);


        listView = (ListView) rootView.findViewById(R.id.listaestabelecimentos);
       // String[] pontos = {"Rayan","Jairo", "Geovane"};

        listView.setOnItemClickListener(this);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_list_item_1, pontos);
        //listaestabelecimentos.setAdapter(adapter);
        createListView();
        return rootView;
    }

    private void createListView(){
        /*
        estabelecimentos = new ArrayList<String>();

        Estabelecimento estabelecimento1 = new Estabelecimento("POnto do Geovane", "Licenciado", R.drawable.biff );
        Estabelecimento estabelecimento2 = new Estabelecimento("POnto do Rayan", "Licenciado", R.drawable.icon );
        Estabelecimento estabelecimento3 = new Estabelecimento("POnto do Jairo", "Licenciado", R.drawable.ic_launcher );
        Estabelecimento estabelecimento4 = new Estabelecimento("POnto do Fabio", "Interditado", R.drawable.ricoldi );
        Estabelecimento estabelecimento5 = new Estabelecimento("POnto do Zenaldo", "Interditado", R.drawable.iconic);
        estabelecimentos.add(estabelecimento1);
        estabelecimentos.add(estabelecimento2);
        estabelecimentos.add(estabelecimento3);
        estabelecimentos.add(estabelecimento4);
        estabelecimentos.add(estabelecimento5);


        adapter = new EstabelecimentoAdapter(getActivity().getApplicationContext(), estabelecimentos);
        listView.setAdapter(adapter);
        listView.setCacheColorHint(Color.TRANSPARENT);
        */
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       // Estabelecimento item = adapter.getItem(position);

    }
}
