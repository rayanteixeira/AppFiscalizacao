package br.edu.ufra.appfiscalizacao.dao;

import android.content.Context;

import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;

/**
 * Created by geovane on 20/06/15.
 */
public class EstabelecimentoDAO extends GenericDAO<Estabelecimento> {

    public EstabelecimentoDAO(Context context) {
        super(context, Estabelecimento.class);
    }


}
