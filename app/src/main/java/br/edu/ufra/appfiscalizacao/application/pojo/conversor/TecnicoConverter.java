/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufra.appfiscalizacao.application.pojo.conversor;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufra.appfiscalizacao.application.pojo.TecnicoPOJO;
import br.edu.ufra.appfiscalizacao.entidade.Tecnico;

/**
 *
 * @author geovane
 */
public class TecnicoConverter {

    private static final TecnicoConverter uniqueInstance = new TecnicoConverter();

    private TecnicoConverter() {

    }

    public static TecnicoConverter getInstance() {
        return uniqueInstance;
    }

    public static TecnicoPOJO toTecnicoPOJO(Tecnico tecnico) {
        if (tecnico != null) {

            TecnicoPOJO resposta = new TecnicoPOJO();

            resposta.setId(tecnico.getId());
            resposta.setEmail(tecnico.getEmail());
            resposta.setMatricula(tecnico.getMatricula());
            resposta.setNome(tecnico.getNome());
            resposta.setSenha(tecnico.getNome());
            
            return resposta;

        } else {
            return null;
        }
    }

    public static Tecnico fromTecnicoPOJO(TecnicoPOJO tecnicoPOJO) {
        if (tecnicoPOJO != null) {
            Tecnico resposta = new Tecnico();

            resposta.setId(tecnicoPOJO.getId());
            resposta.setEmail(tecnicoPOJO.getEmail());
            resposta.setMatricula(tecnicoPOJO.getMatricula());
            resposta.setNome(tecnicoPOJO.getNome());
            resposta.setSenha(tecnicoPOJO.getNome());
            return resposta;

        } else {
            return null;
        }
    }

    public static List<TecnicoPOJO> toTecnicosPOJO(List<Tecnico> tecnicos) {
        if (tecnicos != null) {

            ArrayList<TecnicoPOJO> resposta = new ArrayList<>();

            for(Tecnico tecnico : tecnicos){
                resposta.add(toTecnicoPOJO(tecnico));

            }


            return resposta;
        } else {
            return null;
        }

    }

    public static List<Tecnico> fromTecnicosPOJO(List<TecnicoPOJO> tecnicosPOJO) {
        if (tecnicosPOJO != null) {

            ArrayList<Tecnico> resposta = new ArrayList<>();

            for(TecnicoPOJO tecnicoPOJO : tecnicosPOJO){

                resposta.add(fromTecnicoPOJO(tecnicoPOJO));
            }

            return resposta;
        } else {
            return null;
        }
    }

}
