package br.edu.ufra.appfiscalizacao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.entidade.Equipamento;

/**
 * Created by Rayan on 01/07/2015.
 */
public class EquipamentoAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    List<Equipamento> equipamentos;

    public EquipamentoAdapter(Context context, List<Equipamento> equipamentos) {
        this.mInflater = LayoutInflater.from(context);
        this.equipamentos = equipamentos;
    }

    @Override
    public int getCount() {
        return equipamentos.size();
    }

    @Override
    public Object getItem(int position) {
        return equipamentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        Equipamento equipamento  = equipamentos.get(position);
        view = mInflater.inflate(R.layout.item_model, null);
        TextView nome = (TextView) view.findViewById(R.id.namepoint);


        nome.setText(equipamento.getNome());
        return null;
    }
}
