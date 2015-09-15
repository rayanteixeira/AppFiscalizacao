package br.edu.ufra.appfiscalizacao.dao;

import android.content.Context;

import java.util.List;

import br.edu.ufra.appfiscalizacao.entidade.Inspecao;
import br.edu.ufra.appfiscalizacao.entidade.Vistoria;

/**
 * Created by geovane on 13/09/15.
 */
public class InspecaoDAO extends GenericDAO<Inspecao>{

    public InspecaoDAO(Context context) {
        super(context, Inspecao.class);
    }

    public boolean salvarInspecaoApartirInspecoes(Vistoria vistoria, List<Inspecao> inspecoes) {
        try {
            for (Inspecao inspecao : inspecoes){
                inspecao.setVistoria(vistoria);
                dao.create(inspecao);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Erro: "+e.toString());
            return false;
        }
    }
}
