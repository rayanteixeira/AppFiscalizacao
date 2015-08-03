package br.edu.ufra.appfiscalizacao.dao;

import android.content.Context;

import br.edu.ufra.appfiscalizacao.entidade.Equipamento;

/**
 * Created by Rayan on 31/07/2015.
 */
public class EquipamentoDAO extends  GenericDAO<Equipamento> {

    public EquipamentoDAO(Context context) {
        super(context, Equipamento.class);
    }
}
