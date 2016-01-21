package br.edu.ufra.appfiscalizacao.dao;

import android.content.Context;

import com.j256.ormlite.misc.TransactionManager;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import br.edu.ufra.appfiscalizacao.entidade.Inspecao;
import br.edu.ufra.appfiscalizacao.entidade.Vistoria;

/**
 * Created by Rayan on 01/07/2015.
 */
public class VistoriaDAO extends GenericDAO<Vistoria> {
    Context context;
    InspecaoDAO daoInspecao;
    EquipamentoDAO daoEquip;
    EstabelecimentoDAO daoEstabelecimento;
    public VistoriaDAO(Context context) {
        super(context, Vistoria.class);
        this.context= context;
    }


    public boolean salvarVistoriaEInspecoes(final Vistoria vistoria, final List<Inspecao> inspecoes) {
        try{

            TransactionManager.callInTransaction(getConnectionSource(), new Callable<Object>() {
                @Override
                public Boolean call() throws SQLException {

                    daoInspecao = new InspecaoDAO(context);
                    daoEquip = new EquipamentoDAO(context);
                    daoEstabelecimento = new EstabelecimentoDAO(context);

                    if (daoEstabelecimento.obterId(vistoria.getEstabelecimento().getId())==null){
                    daoEstabelecimento.inserir(vistoria.getEstabelecimento());
                    }

                    dao.create(vistoria);



                    for (Inspecao inspecao : inspecoes) {
                        //inspecao.setVistoria(vistoria);
                        System.out.println("id vist antes"+inspecao.getVistoria().getId()+"id insp "+inspecao.getId());

                        if (daoEquip.obterId(inspecao.getEquipamento().getId())==null){
                        daoEquip.inserir(inspecao.getEquipamento());
                        }

                        daoInspecao.inserir(inspecao);

                        System.out.println("id vist depos" + inspecao.getVistoria().getId() + "id insp " + inspecao.getId());

                    }
                    return true;
                }
            });
            return true;
        } catch (SQLException e){
            System.out.println("Erro ao salvar ou atualizar as inspecões dessa vistoria: "+e.toString());
            return false;
        }

    }


}
