package br.edu.ufra.appfiscalizacao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;

/**
 * Created by bpmlab on 19/06/2015.
 */
public class EstabelecimentoAdapter extends BaseAdapter {

    private Context context;
    private List<HashMap<String, String>> listaestabelecimentos;
    private HashMap<String, String>  estabelecimento;
    public EstabelecimentoAdapter(Context context, List<HashMap<String, String>> listaestabelecimentos) {
        this.context = context;
        this.listaestabelecimentos = listaestabelecimentos;
    }

    @Override
    public int getCount() {
        return listaestabelecimentos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaestabelecimentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

    estabelecimento = (HashMap<String, String>) getItem(position);


        String nome = (String) estabelecimento.get("nome");
        String situacao = (String) estabelecimento.get("situacao");

        if(view == null){
            LayoutInflater layoutI = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutI.inflate(R.layout.item_model, null);
        }


    TextView txvnome = (TextView) view.findViewById(R.id.namepoint);
    TextView txvsituacao = (TextView) view.findViewById(R.id.situacaopoint);
    //ImageView img = (ImageView) view.findViewById(R.id.photopoint);

        txvnome.setText(nome);
        txvsituacao.setText(situacao);
        //img.setImageResource(estabelecimento.getData_venc());



        return view;
    }
}
