package br.edu.ufra.appfiscalizacao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.application.pojo.TecnicoPOJO;

/**
 * Created by Rayan on 28/06/2015.
 */
public class TecnicoAdapter extends BaseAdapter{

    private LayoutInflater mInflate;
    private List<TecnicoPOJO> tecnicos;
    private int elementoClicado = -1;
    private View view;
    public TecnicoAdapter(Context context, List<TecnicoPOJO> tecnicos) {
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
        this.view = view;
        TecnicoPOJO tecnico = (TecnicoPOJO) getItem(position);

        if (elementoClicado != -1 & elementoClicado == position){
            System.out.println("clicado");
            this.view.setBackgroundResource(R.color.nliveo_green_colorAccent);
        }


        view = mInflate.inflate(R.layout.tecnico_model, null);
        TextView nome = (TextView) view.findViewById(R.id.tecnico_model_nome);
        nome.setText(tecnico.getNome());


        return view;
    }

    public void alterarCorElemento(int elementoClicado){
        this.elementoClicado = elementoClicado;
        notifyDataSetChanged();
    }


}
