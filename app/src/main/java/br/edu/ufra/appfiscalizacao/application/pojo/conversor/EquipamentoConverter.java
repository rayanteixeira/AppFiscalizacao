package br.edu.ufra.appfiscalizacao.application.pojo.conversor;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufra.appfiscalizacao.application.pojo.EquipamentoPOJO;
import br.edu.ufra.appfiscalizacao.entidade.Equipamento;

/**
 *
 * @author geovane
 */
public class EquipamentoConverter {

    private static final EquipamentoConverter uniqueInstance = new EquipamentoConverter();

    private EquipamentoConverter() {

    }

    public static EquipamentoConverter getInstance() {
        return uniqueInstance;
    }

    public static EquipamentoPOJO toEquipamentoPOJO(Equipamento equipamento) {
        if (equipamento != null) {

            EquipamentoPOJO resposta = new EquipamentoPOJO();

            resposta.setId(equipamento.getId());
            resposta.setDescricao(equipamento.getDescricao());
            resposta.setNome(equipamento.getNome());
            resposta.setStatus(equipamento.getStatus());
            resposta.setMaterial(equipamento.getMaterial());
            return resposta;

        } else {
            return null;
        }
    }

    public static Equipamento fromEquipamentoPOJO(EquipamentoPOJO equipamentoPOJO) {
        if (equipamentoPOJO != null) {
            Equipamento resposta = new Equipamento();

            resposta.setId(equipamentoPOJO.getId());
            resposta.setDescricao(equipamentoPOJO.getDescricao());
            resposta.setNome(equipamentoPOJO.getNome());
            resposta.setStatus(equipamentoPOJO.getStatus());
            resposta.setMaterial(equipamentoPOJO.getMaterial());
            return resposta;

        } else {
            return null;
        }
    }

    public static List<EquipamentoPOJO> toEquipamentosPOJO(List<Equipamento> equipamentos) {
        if (equipamentos != null) {

            ArrayList<EquipamentoPOJO> resposta = new ArrayList<>();

            for (Equipamento equipamento : equipamentos){

                resposta.add(toEquipamentoPOJO(equipamento));
            }

            return resposta;
        } else {
            return null;
        }

    }

    public static List<Equipamento> fromEquipamentosPOJO(List<EquipamentoPOJO> equipamentosPOJO) {
        if (equipamentosPOJO != null) {

            ArrayList<Equipamento> resposta = new ArrayList<>();

            for (EquipamentoPOJO equipamentoPOJO : equipamentosPOJO){

                resposta.add(fromEquipamentoPOJO(equipamentoPOJO));
            }

            return resposta;
        } else {
            return null;
        }
    }
}
