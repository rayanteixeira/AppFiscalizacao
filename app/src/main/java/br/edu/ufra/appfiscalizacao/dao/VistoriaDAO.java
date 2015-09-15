package br.edu.ufra.appfiscalizacao.dao;

import android.content.Context;

import java.util.List;

import br.edu.ufra.appfiscalizacao.entidade.Inspecao;
import br.edu.ufra.appfiscalizacao.entidade.Vistoria;

/**
 * Created by Rayan on 01/07/2015.
 */
public class VistoriaDAO extends GenericDAO<Vistoria> {

    public VistoriaDAO(Context context) {
        super(context, Vistoria.class);
    }


}
