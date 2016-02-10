package br.edu.ufra.appfiscalizacao.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.application.pojo.EquipamentoPOJO;

/**
 * Created by geovane on 10/08/15.
 */
public class EquipamentoAdapter extends BaseAdapter{
    List<EquipamentoPOJO> equipamentos;
    Context context;
    private LayoutInflater mInflate;
    private View convertView;
    private int selecionado = -1;
    private boolean apto;
    private boolean inspecionado;
    public EquipamentoAdapter(Context context, List<EquipamentoPOJO> equipamentos) {
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
        this.convertView = convertView;
        EquipamentoPOJO equipamento = (EquipamentoPOJO) getItem(position);
        convertView = mInflate.inflate(R.layout.equipamento_model, null);
        TextView nome_equipamento = (TextView) convertView.findViewById(R.id.nome_equipamento);
        TextView tv_equip_situacao = (TextView) convertView.findViewById(R.id.txt_equipamento_situacao);
        ImageView iv__checked = (ImageView) convertView.findViewById(R.id.img_checked);
        iv__checked.setVisibility(View.GONE);
        ImageView iv_no_checked = (ImageView) convertView.findViewById(R.id.img_no_checked);

        if (equipamento.isEquipApto() == true){
            tv_equip_situacao.setTextColor(Color.GREEN);
            tv_equip_situacao.setText("Apto");
        }else {
            tv_equip_situacao.setTextColor(Color.RED);
            tv_equip_situacao.setText("Não está apto");
        }

        if (equipamento.isEquipInspecionado() == true){
            iv__checked.setVisibility(View.VISIBLE);
            iv_no_checked.setVisibility(View.GONE);
        }

        nome_equipamento.setText(equipamento.getNome());

        System.out.println("getview");
/*
            if (selecionado != -1 && position == selecionado){

            if(apto == true){
                convertView.setBackgroundResource(R.color.nliveo_green_colorAccent);
            }else {
                convertView.setBackgroundResource(R.color.nliveo_red_colorPrimary);
            }

            }
*/
        convertView.setTag(equipamento);

        //TextView tv = new TextView(context);
        //tv.setText(equipamento.getNome());
        //return  tv;

        return convertView;
    }

    public void mudarSituacaoEquipamento() {
        notifyDataSetChanged();
    }

/*
    public void alterarBackground(boolean apto, int position) {
            this.selecionado = position;
            this.apto = apto;
            notifyDataSetChanged();
        /*
        if (inspecao.isAptoPOJO()==true){

            System.out.println("verde");

            view.setBackgroundResource(R.color.nliveo_green_colorAccent);
            notifyDataSetChanged();
        }

    }
        */

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
