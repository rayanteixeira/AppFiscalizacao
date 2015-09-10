
package br.edu.ufra.appfiscalizacao.application.pojo;

import java.io.Serializable;

public class VistoriaPOJO implements Serializable {
    private Integer id; 
    private TecnicoPOJO tecnicoPOJO1;
    private TecnicoPOJO tecnicoPOJO2;
    private EstabelecimentoPOJO estabelecimentoPOJO;
    private long dataSolicitacao;
    private long dataVistoria;
    private int prazo;
    private Boolean apto;
    private String observacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TecnicoPOJO getTecnicoPOJO1() {
        return tecnicoPOJO1;
    }

    public void setTecnicoPOJO1(TecnicoPOJO tecnicoPOJO1) {
        this.tecnicoPOJO1 = tecnicoPOJO1;
    }

    public TecnicoPOJO getTecnicoPOJO2() {
        return tecnicoPOJO2;
    }

    public void setTecnicoPOJO2(TecnicoPOJO tecnicoPOJO2) {
        this.tecnicoPOJO2 = tecnicoPOJO2;
    }

    public EstabelecimentoPOJO getEstabelecimentoPOJO() {
        return estabelecimentoPOJO;
    }

    public void setEstabelecimentoPOJO(EstabelecimentoPOJO estabelecimentoPOJO) {
        this.estabelecimentoPOJO = estabelecimentoPOJO;
    }

    public long getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(long dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public long getDataVistoria() {
        return dataVistoria;
    }

    public void setDataVistoria(long dataVistoria) {
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
    
    
         

}
