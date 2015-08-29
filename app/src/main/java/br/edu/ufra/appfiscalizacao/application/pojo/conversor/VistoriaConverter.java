/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufra.appfiscalizacao.application.pojo.conversor;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufra.appfiscalizacao.application.pojo.VistoriaPOJO;
import br.edu.ufra.appfiscalizacao.entidade.Vistoria;

/**
 *
 * @author geovane
 */
public class VistoriaConverter {
    private static final VistoriaConverter uniqueInstance = new VistoriaConverter();

    private VistoriaConverter() {

    }

    public static VistoriaConverter getInstance() {
        return uniqueInstance;
    }

    public static VistoriaPOJO toVistoriaPOJO(Vistoria vistoria) {
        if (vistoria != null) {

            VistoriaPOJO resposta = new VistoriaPOJO();

            resposta.setId(vistoria.getId());
            resposta.setApto(vistoria.isApto());
            resposta.setDataSolicitacao(vistoria.getDataSolicitacao());
            resposta.setDataVistoria(vistoria.getDataVistoria());
            resposta.setEstabelecimento(EstabelecimentoConverter.toEstabelecimentoPOJO(vistoria.getEstabelecimento()));
            resposta.setObservacao(vistoria.getObservacao());
            resposta.setPrazo(vistoria.getPrazo());
            resposta.setTecnico1(TecnicoConverter.toTecnicoPOJO(vistoria.getTecnico1()));
            resposta.setTecnico2(TecnicoConverter.toTecnicoPOJO(vistoria.getTecnico2()));
            System.out.println("inspecoes"+vistoria.getInspecaoList().size());
            resposta.setInspecoes(InspecaoConverter.toInspecoesPOJO(vistoria.getInspecaoList()));
            return resposta;

        } else {
            return null;
        }
    }

    public static Vistoria fromVistoriaPOJO(VistoriaPOJO vistoriaPOJO) {
        if (vistoriaPOJO != null) {
            Vistoria resposta = new Vistoria();

            resposta.setId(vistoriaPOJO.getId());
            resposta.setApto(vistoriaPOJO.getApto());
            resposta.setDataSolicitacao(vistoriaPOJO.getDataSolicitacao());
            resposta.setDataVistoria(vistoriaPOJO.getDataVistoria());
            resposta.setEstabelecimento(EstabelecimentoConverter.fromEstabelecimentoPOJO(vistoriaPOJO.getEstabelecimento()));
            resposta.setObservacao(vistoriaPOJO.getObservacao());
            resposta.setPrazo(vistoriaPOJO.getPrazo());
            resposta.setTecnico1(TecnicoConverter.fromTecnicoPOJO(vistoriaPOJO.getTecnico1()));
            resposta.setTecnico2(TecnicoConverter.fromTecnicoPOJO(vistoriaPOJO.getTecnico2()));          
            resposta.setInspecaoList(InspecaoConverter.fromInspecoesPOJO(vistoriaPOJO.getInspecoes()));

            return resposta;

        } else {
            return null;
        }
    }

    public static List<VistoriaPOJO> toVistoriasPOJO(List<Vistoria> vistorias) {
        if (vistorias != null) {

            ArrayList<VistoriaPOJO> resposta = new ArrayList<>();

            for(Vistoria vistoria : vistorias){

                resposta.add(toVistoriaPOJO(vistoria));
            }

            return resposta;
        } else {
            return null;
        }

    }

    public static List<Vistoria> fromVistoriasPOJO(List<VistoriaPOJO> vistoriasPOJO) {
        if (vistoriasPOJO != null) {

            ArrayList<Vistoria> resposta = new ArrayList<>();

            for(VistoriaPOJO vistoriaPOJO : vistoriasPOJO){

                resposta.add(fromVistoriaPOJO(vistoriaPOJO));
            }

            return resposta;
        } else {
            return null;
        }
    }
}
