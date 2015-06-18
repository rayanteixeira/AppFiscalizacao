package br.edu.ufra.appfiscalizacao.entidade;

/**
 * Created by Rayan on 16/06/2015.
 */
public class Vistoria {

    private int id;
    private String data_vistoria;
    private int data_entrada_lic;
    private String observacao;
    private Boolean apto;


    public Vistoria() {
    }

    public Vistoria(int id, String data_vistoria, int data_entrada_lic, String observacao, Boolean apto) {
        this.id = id;
        this.data_vistoria = data_vistoria;
        this.data_entrada_lic = data_entrada_lic;
        this.observacao = observacao;
        this.apto = apto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData_vistoria() {
        return data_vistoria;
    }

    public void setData_vistoria(String data_vistoria) {
        this.data_vistoria = data_vistoria;
    }

    public int getData_entrada_lic() {
        return data_entrada_lic;
    }

    public void setData_entrada_lic(int data_entrada_lic) {
        this.data_entrada_lic = data_entrada_lic;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Boolean getApto() {
        return apto;
    }

    public void setApto(Boolean apto) {
        this.apto = apto;
    }
}