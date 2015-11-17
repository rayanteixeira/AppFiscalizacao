package br.edu.ufra.appfiscalizacao;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.edu.ufra.appfiscalizacao.activity.MainActivity;
import br.edu.ufra.appfiscalizacao.adapter.VistoriaAdapter;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;
import br.edu.ufra.appfiscalizacao.rn.VistoriaRN;


public class FragmentVistoriaList extends Fragment {
    TextView dataVistoriaTxt;
    ListView listView;
    ProgressDialog progressDialog;
    DateFormat sdf;
    String date;
    VistoriaRN rnVistoria;
    VistoriaAdapter vistoriaAdapter;
    Estabelecimento estabelecimento ;
    MainActivity mainActivity ;

    public FragmentVistoriaList() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        rnVistoria = new VistoriaRN(getActivity());

        estabelecimento = mainActivity.estabelecimento;

        View rootView = inflater.inflate(R.layout.fragment_fragment_vistorias, container, false);
        listView = (ListView) rootView.findViewById(R.id.listVistoriaFragment);
        progressDialog = ProgressDialog.show(getActivity(), "Aguarde", "Atualizando lista", false, true);




        return rootView;
    }

    private void createListView(){
        System.out.println("vistorias: "+estabelecimento.getVistorias().size());
        //vistoriaAdapter = new VistoriaAdapter(getActivity(), (java.util.List<br.edu.ufra.appfiscalizacao.entidade.Vistoria>) estabelecimento.getVistorias());
        listView.setAdapter(vistoriaAdapter);

    }




}
