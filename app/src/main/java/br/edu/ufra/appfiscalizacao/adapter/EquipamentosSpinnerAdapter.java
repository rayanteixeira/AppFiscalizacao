package br.edu.ufra.appfiscalizacao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.application.pojo.EquipamentoPOJO;
import br.edu.ufra.appfiscalizacao.entidade.Equipamento;

/**
 * Created by geovane on 10/08/15.
 */
public class EquipamentosSpinnerAdapter extends BaseAdapter{
    List<EquipamentoPOJO> equipamentos;
    Context context;
    private LayoutInflater mInflate;

    public EquipamentosSpinnerAdapter(Context context, List<EquipamentoPOJO> equipamentos) {
        this.context = context;
        this.equipamentos = equipamentos;
        this.mInflate = LayoutInflater.from(context);

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

            convertView = mInflate.inflate(R.layout.equipamento_model, null);
            TextView item_equipamento = (TextView) convertView.findViewById(R.id.item_equipamento);
            item_equipamento.setText(equipamento.getNome());


        //TextView tv = new TextView(context);
        //tv.setText(equipamento.getNome());
        //return  tv;

        return convertView;
    }


    /* ItemModel itemHolder;
        EquipamentoPOJO equipamento = (EquipamentoPOJO) getItem(position);

        if(view == null) {


            view = mInflate.inflate(R.layout.equipamento_model, null);
            itemHolder = new ItemModel();

            itemHolder.item_equipamento = ((TextView) view.findViewById(R.id.item_equipamento));
            view.setTag(itemHolder);

        }else{
            itemHolder = (ItemModel) view.getTag();
        }

        itemHolder.item_equipamento.setText(equipamento.getNome());



        //TextView tv = new TextView(context);
        //tv.setText(equipamento.getNome());
        //return  tv;

        return view;
    }

    private class ItemModel{
        TextView item_equipamento;

    }
*/
}
