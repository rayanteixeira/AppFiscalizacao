package br.edu.ufra.appfiscalizacao.entidade;

/**
 * Created by Rayan on 16/06/2015.
 */
public class Inspecao {

    private int id;
    private int data_insp;
    private boolean apto;
    private String observacao;

    public Inspecao() {
    }

    public Inspecao(int id, int data_insp, boolean apto, String observacao) {
        this.id = id;
        this.data_insp = data_insp;
        this.apto = apto;
        this.observacao = observacao;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getData_insp() {
        return data_insp;
    }

    public void setData_insp(int data_insp) {
        this.data_insp = data_insp;
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
}
