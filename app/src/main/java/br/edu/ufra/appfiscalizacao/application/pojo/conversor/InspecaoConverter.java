
package br.edu.ufra.appfiscalizacao.application.pojo.conversor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.edu.ufra.appfiscalizacao.application.pojo.InspecaoPOJO;
import br.edu.ufra.appfiscalizacao.entidade.Inspecao;


public class InspecaoConverter {
      private static final InspecaoConverter uniqueInstance = new InspecaoConverter();

    private InspecaoConverter() {

    }

    public static InspecaoConverter getInstance() {
        return uniqueInstance;
    }

    public static InspecaoPOJO toInspecaoPOJO(Inspecao inspecao) {
        if (inspecao != null) {

            InspecaoPOJO resposta = new InspecaoPOJO();

            resposta.setId(null);
            resposta.setVistoriaPOJO(VistoriaConverter.toVistoriaPOJO(inspecao.getVistoria()));
            resposta.setAptoPOJO(inspecao.isApto());
            resposta.setDataInspPOJO(inspecao.getDataInsp());
            resposta.setEquipamentoPOJO(EquipamentoConverter.toEquipamentoPOJO(inspecao.getEquipamento()));
            resposta.setObservacaoPOJO(inspecao.getObservacao());
                
            return resposta;

        } else {
            return null;
        }
    }

    public static Inspecao fromInspecaoPOJO(InspecaoPOJO inspecaoPOJO) {
        if (inspecaoPOJO != null) {
            Inspecao resposta = new Inspecao();

            resposta.setId(inspecaoPOJO.getId());
            resposta.setVistoria(VistoriaConverter.fromVistoriaPOJO(inspecaoPOJO.getVistoriaPOJO()));
            resposta.setApto(inspecaoPOJO.isAptoPOJO());
            resposta.setDataInsp(inspecaoPOJO.getDataInspPOJO());
            resposta.setEquipamento(EquipamentoConverter.fromEquipamentoPOJO(inspecaoPOJO.getEquipamentoPOJO()));
            resposta.setObservacao(inspecaoPOJO.getObservacaoPOJO());

            return resposta;

        } else {
            return null;
        }
    }

    public static Collection<InspecaoPOJO> toInspecoesPOJO(Collection<Inspecao> inspecoes) {
        if (inspecoes != null) {

            ArrayList<InspecaoPOJO> resposta = new ArrayList<>();

            for (Inspecao inspecao : inspecoes){

                resposta.add(toInspecaoPOJO(inspecao));
            }

            return resposta;
        } else {
            return null;
        }

    }

    public static List<Inspecao> fromInspecoesPOJO(List<InspecaoPOJO> inspecoesPOJO) {
        if (inspecoesPOJO != null) {

            ArrayList<Inspecao> resposta = new ArrayList<>();

            for (InspecaoPOJO inspecaoPOJO : inspecoesPOJO){

                resposta.add(fromInspecaoPOJO(inspecaoPOJO));
            }

            return resposta;
        } else {
            return null;
        }
    }
}
