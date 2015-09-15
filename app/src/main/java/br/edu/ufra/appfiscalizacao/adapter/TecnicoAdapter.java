package br.edu.ufra.appfiscalizacao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;
import br.edu.ufra.appfiscalizacao.entidade.Tecnico;

/**
 * Created by Rayan on 28/06/2015.
 */
public class TecnicoAdapter extends BaseAdapter{

    private LayoutInflater mInflate;
    private List<Tecnico> tecnicos;

    public TecnicoAdapter(Context context, List<Tecnico> tecnicos) {
        this.mInflate = LayoutInflater.from(context);
        this.tecnicos = tecnicos;
    }

    @Override
    public int getCount() {
        return tecnicos.size();
    }

    @Override
    public Object getItem(int position) {
        return tecnicos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        Tecnico tecnico = (Tecnico) getItem(position);

        view = mInflate.inflate(R.layout.item_model, null);
        TextView nome = (TextView) view.findViewById(R.id.namepoint);
        TextView email = (TextView) view.findViewById(R.id.situacaopoint);
        //ImageView img = (ImageView) view.findViewById(R.id.photopoint);

        //matricula.setText(String.valueOf(tecnico.getId()));
        //img.setImageResource(estabelecimento.getDataVenc());


        return view;
    }



}
