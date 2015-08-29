package br.edu.ufra.appfiscalizacao.application.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.edu.ufra.appfiscalizacao.entidade.Inspecao;


public class VistoriaPOJO implements Serializable {
    private Integer id; 
    private TecnicoPOJO tecnico1;
    private TecnicoPOJO tecnico2;
    private EstabelecimentoPOJO estabelecimento;
    private Date dataSolicitacao;
    private Date dataVistoria;
    private int prazo;
    private Boolean apto;
    private String observacao;
    private List<InspecaoPOJO> inspecoes;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TecnicoPOJO getTecnico1() {
        return tecnico1;
    }

    public void setTecnico1(TecnicoPOJO tecnico1) {
        this.tecnico1 = tecnico1;
    }

    public TecnicoPOJO getTecnico2() {
        return tecnico2;
    }

    public void setTecnico2(TecnicoPOJO tecnico2) {
        this.tecnico2 = tecnico2;
    }

    public EstabelecimentoPOJO getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(EstabelecimentoPOJO estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Date getDataVistoria() {
        return dataVistoria;
    }

    public void setDataVistoria(Date dataVistoria) {
        this.dataVistoria = dataVistoria;
    }

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public Boolean getApto() {
        return apto;
    }

    public void setApto(Boolean apto) {
        this.apto = apto;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<InspecaoPOJO> getInspecoes() {
        return inspecoes;
    }

    public void setInspecoes(List<InspecaoPOJO> inspecoes) {
        this.inspecoes = inspecoes;
    }
}
