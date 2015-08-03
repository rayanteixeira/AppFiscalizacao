package br.edu.ufra.appfiscalizacao.rn;

import android.content.Context;

import br.edu.ufra.appfiscalizacao.dao.EquipamentoDAO;
import br.edu.ufra.appfiscalizacao.entidade.Equipamento;

/**
 * Created by Rayan on 31/07/2015.
 */
public class EquipamentoRN extends GenericRN<Equipamento> {


    EquipamentoDAO dao;

    public EquipamentoRN( Context context) {
        super(Equipamento.class, context);
        dao = new EquipamentoDAO(context);
    }
}
