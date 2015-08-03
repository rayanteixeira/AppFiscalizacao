package br.edu.ufra.appfiscalizacao.entidade;

import java.util.Date;
import java.util.List;

/**
 * Created by Rayan on 16/06/2015.
 */
public class Inspecao {

    private int id;
    private Vistoria vistoria;
    private Equipamento equipamento;
    private Date dataInsp;
    private boolean apto;
    private String observacao;
    private List<Equipamento> equipamentos;

    public Inspecao() {
    }

    public Inspecao(Vistoria vistoria, Equipamento equipamento, Date dataInsp, boolean apto, String observacao, List<Equipamento> equipamentos, int id) {
        this.vistoria = vistoria;
        this.equipamento = equipamento;
        this.dataInsp = dataInsp;
        this.apto = apto;
        this.observacao = observacao;
        this.equipamentos = equipamentos;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vistoria getVistoria() {
        return vistoria;
    }

    public void setVistoria(Vistoria vistoria) {
        this.vistoria = vistoria;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Date getDataInsp() {
        return dataInsp;
    }

    public void setDataInsp(Date dataInsp) {
        this.dataInsp = dataInsp;
    }

    public boolean isApto() {
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

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
}
