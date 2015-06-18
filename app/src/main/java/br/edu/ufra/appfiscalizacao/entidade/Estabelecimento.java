package br.edu.ufra.appfiscalizacao.entidade;

/**
 * Created by Rayan on 16/06/2015.
 */
public class Estabelecimento {
    private int id;
    private String nome;
    private int data_lic;
    private int data_venc;
    private String situacao;


    public Estabelecimento() {
    }

    public Estabelecimento(int id, String nome, int data_lic, int data_venc, String situacao) {
        this.id = id;
        this.nome = nome;
        this.data_lic = data_lic;
        this.data_venc = data_venc;
        this.situacao = situacao;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getData_lic() {
        return data_lic;
    }

    public void setData_lic(int data_lic) {
        this.data_lic = data_lic;
    }

    public int getData_venc() {
        return data_venc;
    }

    public void setData_venc(int data_venc) {
        this.data_venc = data_venc;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
