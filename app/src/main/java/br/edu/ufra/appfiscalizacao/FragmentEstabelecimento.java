package br.edu.ufra.appfiscalizacao;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by Rayan on 16/06/2015.
 */
public class FragmentEstabelecimento extends Fragment  {


    View rootView;
    private TextView textoselo;


    public FragmentEstabelecimento() {
        // TODO Auto-generated constructor stub
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_estabelecimento, container, false);


        TabHost abas = (TabHost) rootView.findViewById(R.id.tabhost);
        abas.setup();

        TabHost.TabSpec descritor = abas.newTabSpec("aba1");
        descritor.setContent(R.id.vencidas);
        descritor.setIndicator("Vencidas");
        abas.addTab(descritor);

        descritor = abas.newTabSpec("aba2");
        descritor.setContent(R.id.detalhes);
        descritor.setIndicator("Em Vistoria");
        abas.addTab(descritor);


        listaPontosVistoriados();
        listaPontosLicVencida();


        return rootView;




}

    public  void listaPontosLicVencida(){

        ListView ponto_licVencida = (ListView) rootView.findViewById(R.id.list_pontos_licVencida);

        String[]  pontos = {"Acai do Tia", "Casa do Acai", "Acai do Anderson", "Acai do bom", "Acai do Veloso", "Acai do Iaca" };

        ArrayAdapter<String> licVencidasAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_list_item_1, pontos);
        ponto_licVencida.setAdapter(licVencidasAdapter);


    }


    public void listaPontosVistoriados(){
        ListView ponto_emVistoria = (ListView) rootView.findViewById(R.id.list_pontos_emVistoria);

        String[]  pontos = {"Acai do Mendara", "Acai do Heron", "Acai do GUGU","Acai do cowboy", "Acai du 9", "Acai pai degua", "Acai da 25", "Acai da tia" };

        ArrayAdapter<String> ponto_emVistoriaAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_list_item_1, pontos);
        ponto_emVistoria.setAdapter(ponto_emVistoriaAdapter);

    }

}
