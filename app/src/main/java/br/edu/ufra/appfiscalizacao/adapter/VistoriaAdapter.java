package br.edu.ufra.appfiscalizacao.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.application.pojo.VistoriaPOJO;
import br.edu.ufra.appfiscalizacao.entidade.Vistoria;

/**
 * Created by geovane on 26/08/15.
 */
public class VistoriaAdapter extends BaseAdapter {

    private LayoutInflater mInflate;
    private List<VistoriaPOJO> vistorias;
    DateFormat sdf;

    public VistoriaAdapter(Context context, List<VistoriaPOJO> vistorias) {
        this.mInflate = LayoutInflater.from(context);
        this.vistorias = vistorias;
        sdf= new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public int getCount() {
        return vistorias.size();
    }

    @Override
    public Object getItem(int position) {
        return vistorias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        VistoriaPOJO vistoria = vistorias.get(position);
        view = mInflate.inflate(R.layout.vistoria_model, null);
        TextView data = (TextView) view.findViewById(R.id.dataVistoria);
        TextView status = (TextView) view.findViewById(R.id.txt_status_estabelecimento);



        data.setText(sdf.format(vistoria.getDataVistoria()));
        if (vistoria.getApto() == true){
            view.setBackgroundResource(R.color.nliveo_green_colorSoft);
            status.setText("Apto");
        }else {
            view.setBackgroundResource(R.color.nliveo_red_colorSoft);
            status.setText("Inapto");
        }
        return view;
    }
}
