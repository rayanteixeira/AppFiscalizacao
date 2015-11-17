package br.edu.ufra.appfiscalizacao.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ufra.appfiscalizacao.application.pojo.EquipamentoPOJO;
import br.edu.ufra.appfiscalizacao.entidade.Equipamento;

/**
 * Created by geovane on 10/08/15.
 */
public class EquipamentosSpinnerAdapter extends BaseAdapter{
    List<EquipamentoPOJO> equipamentos;
    Context context;

    public EquipamentosSpinnerAdapter(Context context, List<EquipamentoPOJO> equipamentos) {
    this.context = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        EquipamentoPOJO equipamento = (EquipamentoPOJO) getItem(position);

        TextView tv = new TextView(context);
        tv.setText(equipamento.getNome());
        return  tv;

    }
}
