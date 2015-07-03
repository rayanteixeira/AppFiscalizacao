package br.edu.ufra.appfiscalizacao.rn;

import android.content.Context;

import br.edu.ufra.appfiscalizacao.dao.TecnicoDAO;
import br.edu.ufra.appfiscalizacao.entidade.Tecnico;

/**
 * Created by Rayan on 30/06/2015.
 */
public class TecnicoRN extends GenericRN<Tecnico> {

   TecnicoDAO dao;


    public TecnicoRN( Context context) {
        super(Tecnico.class  , context);
        dao = new TecnicoDAO(context);

    }
}
