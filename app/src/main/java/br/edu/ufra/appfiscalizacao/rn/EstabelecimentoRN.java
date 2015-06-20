package br.edu.ufra.appfiscalizacao.rn;

import android.content.Context;

import br.edu.ufra.appfiscalizacao.dao.EstabelecimentoDAO;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;

/**
 * Created by Rayan on 20/06/2015.
 */
public class EstabelecimentoRN extends GenericRN<Estabelecimento> {

    public EstabelecimentoRN(Class<Estabelecimento> tipo, Context context) {
        super(tipo, context);
        dao = new EstabelecimentoDAO(context);
    }
}
