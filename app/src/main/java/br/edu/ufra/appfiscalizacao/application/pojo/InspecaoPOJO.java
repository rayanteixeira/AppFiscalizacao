/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ufra.appfiscalizacao.application.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author geovane
 */
public class InspecaoPOJO implements Serializable {

    private Integer id;
    private Date dataInsp;
    private boolean apto;
    private String observacao;
    private EquipamentoPOJO equipamentoPOJO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataInsp() {
        return dataInsp;
    }

    public void setDataInsp(Date dataInsp) {
        this.dataInsp = dataInsp;
    }

    public boolean getApto() {
        return apto;
    }

    public void setApto(boolean apto) {
        this.apto = apto;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public EquipamentoPOJO getEquipamentoPOJO() {
        return equipamentoPOJO;
    }

    public void setEquipamentoPOJO(EquipamentoPOJO equipamentoPOJO) {
        this.equipamentoPOJO = equipamentoPOJO;
    }

        
    
      
    

}
