/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufra.appfiscalizacao.application.pojo.conversor;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufra.appfiscalizacao.application.pojo.EstabelecimentoPOJO;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;

/**
 *
 * @author geovane
 */
public class EstabelecimentoConverter {

    private static final EstabelecimentoConverter uniqueInstance = new EstabelecimentoConverter();

    private EstabelecimentoConverter() {

    }

    public static EstabelecimentoConverter getInstance() {
        return uniqueInstance;
    }

    public static EstabelecimentoPOJO toEstabelecimentoPOJO(Estabelecimento estabelecimento) {
        if (estabelecimento != null) {

            EstabelecimentoPOJO resposta = new EstabelecimentoPOJO();

            resposta.setId(estabelecimento.getId());
            resposta.setNomeFantasia(estabelecimento.getNomeFantasia());
            resposta.setRg(estabelecimento.getRg());
            resposta.setCpf(estabelecimento.getCpf());
            resposta.setEmail(estabelecimento.getEmail());
            resposta.setTelefone(estabelecimento.getTelefone());
            resposta.setLogradouro(estabelecimento.getLogradouro());
            resposta.setNumero(estabelecimento.getNumero());
            resposta.setDataCadastro(estabelecimento.getDataCadastro());
            resposta.setDataLicenca(estabelecimento.getDataLicenca());
            resposta.setDataVencimento(estabelecimento.getDataVencimento());
            resposta.setStatus(estabelecimento.getStatus());
            resposta.setLatitude(null);
            resposta.setLongitude(null);

            return resposta;

        } else {
            return null;
        }
    }

    public static Estabelecimento fromEstabelecimentoPOJO(EstabelecimentoPOJO estabelecimentoPOJO) {
        if (estabelecimentoPOJO != null) {
            Estabelecimento resposta = new Estabelecimento();

            resposta.setId(estabelecimentoPOJO.getId());
            resposta.setNomeFantasia(estabelecimentoPOJO.getNomeFantasia());
            resposta.setRg(estabelecimentoPOJO.getRg());
            resposta.setCpf(estabelecimentoPOJO.getCpf());
            resposta.setEmail(estabelecimentoPOJO.getEmail());
            resposta.setTelefone(estabelecimentoPOJO.getTelefone());
            resposta.setLogradouro(estabelecimentoPOJO.getLogradouro());
            resposta.setNumero(estabelecimentoPOJO.getNumero());
            resposta.setDataCadastro(estabelecimentoPOJO.getDataCadastro());
            resposta.setDataLicenca(estabelecimentoPOJO.getDataLicenca());
            resposta.setDataVencimento(estabelecimentoPOJO.getDataVencimento());
            resposta.setStatus(estabelecimentoPOJO.getStatus());
            resposta.setLatitude(null);
            resposta.setLongitude(null);

            return resposta;

        } else {
            return null;
        }
    }

    public static List<EstabelecimentoPOJO> toEstabelecimentosPOJO(List<Estabelecimento> estabelecimentos) {
        if (estabelecimentos != null) {

            ArrayList<EstabelecimentoPOJO> resposta = new ArrayList<>();

            for(Estabelecimento estabelecimento : estabelecimentos){

                resposta.add(toEstabelecimentoPOJO(estabelecimento));
            }

            return resposta;
        } else {
            return null;
        }

    }

    public static List<Estabelecimento> fromEstabelecimentosPOJO(List<EstabelecimentoPOJO> estabelecimentosPOJO) {
        if (estabelecimentosPOJO != null) {

            ArrayList<Estabelecimento> resposta = new ArrayList<>();

            for(EstabelecimentoPOJO estabelecimentoPOJO : estabelecimentosPOJO){

                resposta.add(fromEstabelecimentoPOJO(estabelecimentoPOJO));
            }

            return resposta;
        } else {
            return null;
        }
    }

}
