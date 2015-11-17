package br.edu.ufra.appfiscalizacao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.application.pojo.EstabelecimentoPOJO;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;

public class EstabelecimentoAdapter extends BaseAdapter {

    private LayoutInflater mInflate;
    private List<EstabelecimentoPOJO> estabelecimentos;

    public EstabelecimentoAdapter(Context context, List<EstabelecimentoPOJO> estabelecimentos) {
        this.mInflate = LayoutInflater.from(context);
        this.estabelecimentos = estabelecimentos;
    }

    @Override
    public int getCount() {
        return estabelecimentos.size();
    }

    @Override
    public Object getItem(int position) {
        return estabelecimentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

    EstabelecimentoPOJO estabelecimento = estabelecimentos.get(position);
    view = mInflate.inflate(R.layout.item_model, null);
    TextView nome = (TextView) view.findViewById(R.id.namepoint);
    TextView situacao = (TextView) view.findViewById(R.id.situacaopoint);
    //ImageView img = (ImageView) view.findViewById(R.id.photopoint);

        nome.setText(estabelecimento.getNomeFantasia());
        situacao.setText(estabelecimento.getNomeContato());
        //img.setImageResource(estabelecimento.getDataVenc());



        return view;
    }
}
