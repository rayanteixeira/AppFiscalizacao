package br.edu.ufra.appfiscalizacao.dao;

import android.content.Context;

import br.edu.ufra.appfiscalizacao.entidade.Tecnico;

/**
 * Created by Rayan on 30/06/2015.
 */
public class TecnicoDAO extends GenericDAO<Tecnico> {
    public TecnicoDAO(Context context) {
        super(context, Tecnico.class);
    }
}
