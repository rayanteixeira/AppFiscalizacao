package br.edu.ufra.appfiscalizacao.rn;

import android.content.Context;

import java.util.List;

import br.edu.ufra.appfiscalizacao.dao.InspecaoDAO;
import br.edu.ufra.appfiscalizacao.entidade.Inspecao;
import br.edu.ufra.appfiscalizacao.entidade.Vistoria;

/**
 * Created by geovane on 13/09/15.
 */
public class InspecaoRN extends GenericRN<Inspecao> {
    InspecaoDAO dao;

    public InspecaoRN(Context context) {
        super(Inspecao.class, context);

        dao = new InspecaoDAO(context);
    }

    public boolean salvarInspecaoApartirInspecoes (Vistoria vistoria, List<Inspecao> inspecoes){

        return dao.salvarInspecaoApartirInspecoes(vistoria, inspecoes);

    }



}
