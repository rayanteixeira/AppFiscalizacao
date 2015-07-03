package br.edu.ufra.appfiscalizacao;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufra.appfiscalizacao.activity.CadastroEstabelecimento;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;
import br.edu.ufra.appfiscalizacao.rn.EstabelecimentoRN;

/**
 * Created by Rayan on 16/06/2015.
 */
public class FragmentEstabelecimento extends Fragment {


    View rootView;
    private TextView textoselo;
    EstabelecimentoRN rn;
    Estabelecimento estabelecimento;
    List<String> pontosRegular;
    List<String> pontosEmVistoria;
    List<String> pontosSemVistoria;
    List<String> pontosVencidos;
    private TabHost abas;
    Button btNovo;

    public FragmentEstabelecimento() {
        // TODO Auto-generated constructor stub
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_estabelecimento, container, false);
        btNovo = (Button) rootView.findViewById(R.id.btnNovoPonto);
        rn = new EstabelecimentoRN(getActivity());

        TabHost.TabSpec descritor;
        abas = (TabHost) rootView.findViewById(R.id.tabhost);
        abas.setup();

        descritor = abas.newTabSpec("aba1");
        descritor.setContent(R.id.semVistoria);
        descritor.setIndicator("Sem Vistoria");
        abas.addTab(descritor);

        descritor = abas.newTabSpec("aba2");
        descritor.setContent(R.id.emVistoria);
        descritor.setIndicator("Em Vistoria");
        abas.addTab(descritor);

        descritor = abas.newTabSpec("aba3");
        descritor.setContent(R.id.vencidas);
        descritor.setIndicator("Vencidas");
        abas.addTab(descritor);

        descritor = abas.newTabSpec("aba4");
        descritor.setContent(R.id.regular);
        descritor.setIndicator("Regular");
        abas.addTab(descritor);

        //definirSituacaoPonto();
        //listPontosRegular();
        //listPontosLicVencida();
        //listPontosEmVistoria();
        //listPontosSemVistoria();

        btNovo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CadastroEstabelecimento.class));
            }
        });


        return rootView;


    }

    public void listPontosLicVencida() {

        ListView ponto_licVencida = (ListView) rootView.findViewById(R.id.list_pontos_licVencida);


        ArrayAdapter<String> licVencidasAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, pontosVencidos);
        ponto_licVencida.setAdapter(licVencidasAdapter);


    }

    public void listPontosEmVistoria() {
        ListView ponto_emVistoria = (ListView) rootView.findViewById(R.id.list_pontos_emVistoria);

        ArrayAdapter<String> ponto_emVistoriaAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, pontosEmVistoria);
        ponto_emVistoria.setAdapter(ponto_emVistoriaAdapter);

    }

    public void listPontosSemVistoria() {

        ListView pontoSemVistoria = (ListView) rootView.findViewById(R.id.list_pontos_semVistoria);


        ArrayAdapter<String> semVistoriaAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, pontosSemVistoria);
        pontoSemVistoria.setAdapter(semVistoriaAdapter);


    }

    public void listPontosRegular() {
        ListView pontoRegular = (ListView) rootView.findViewById(R.id.list_pontos_regular);

        ArrayAdapter<String> pontoRegularAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1, pontosRegular);
        pontoRegular.setAdapter(pontoRegularAdapter);

    }
 /*
    public void definirSituacaoPonto() {
        pontosEmVistoria = new ArrayList<String>();
        pontosSemVistoria = new ArrayList<String>();
        pontosRegular = new ArrayList<String>();
        pontosVencidos = new ArrayList<String>();

       for (Estabelecimento e : rn.obterTodos()) {

            if (e.getSituacao().equals("vencida")) {
                pontosVencidos.add(e.getNome());
            } else if (e.getSituacao().equals("semVistoria")) {
                pontosSemVistoria.add(e.getNome());
            } else if (e.getSituacao().equals("emVistoria")) {
                pontosEmVistoria.add(e.getNome());
            } else if (e.getSituacao().equals("regular")) {
                pontosRegular.add(e.getNome());
            }
        }

    }
*/
}
