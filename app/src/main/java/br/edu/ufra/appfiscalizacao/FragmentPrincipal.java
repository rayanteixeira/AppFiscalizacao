package br.edu.ufra.appfiscalizacao;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.edu.ufra.appfiscalizacao.adapter.EstabelecimentoAdapter;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;


public class FragmentPrincipal extends Fragment {

    private ListView listView;
    private EstabelecimentoAdapter adapter;

    private Estabelecimento estabelecimento;

    public FragmentPrincipal() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_principal, container, false);
        listView = (ListView) rootView.findViewById(R.id.listaestabelecimentos);

        createListView();

        return rootView;
    }

    private List<HashMap<String, String>> createListView() {

    List<HashMap<String, String>> estabelecimentos = new ArrayList<HashMap<String, String>>();

    for (int i = 1; i < 50; i++) {


        HashMap<String, String> ponto = new HashMap<String, String>();
        ponto.put("nome", "Rayan");
        ponto.put("situacao", "Licenciado");

        estabelecimentos.add(ponto);

/*
        O problema é que nao consigo fazer uma lista personalizada. Não quero estânciar um objeto para cada
        estabelecimento, quero fazer como tu fez no açai pai degua. Porém, tentei fazer igual e da erro quando trabalho com HashMap<String, Objet>
        entrão mudo todos os HashMap para HashMap<String, String> e funciona. Me ajuda ai.

        Esse era o hashmap que tava usando.

        HashMap<String, Object> ponto = new HashMap<String, Object>();
        ponto.put("nome", estabelecimento.getNome());
        ponto.put("situacao", estabelecimento.getSituacao());

        estabelecimentos.add(ponto);

    */


    }

    /*
        ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();

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
        */

    //define adapter
    adapter = new EstabelecimentoAdapter(getActivity().getApplicationContext(), estabelecimentos);
    listView.setAdapter(adapter);
    //cor quando a lista é selecionada para ralagem
    listView.setCacheColorHint(Color.TRANSPARENT);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    });



        return estabelecimentos;
    }
}



