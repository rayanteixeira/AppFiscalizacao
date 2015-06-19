package br.edu.ufra.appfiscalizacao.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Rayan on 16/06/2015.
 */
@DatabaseTable (tableName = "estabelecimento")
public class Estabelecimento {
    @DatabaseField (generatedId = true)
    private int id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private int data_lic;
    @DatabaseField
    private int data_venc;
    @DatabaseField
    private String situacao;


    public Estabelecimento() {
    }

    public Estabelecimento(String nome, String situacao , int data_venc) {
        this.nome = nome;
        this.situacao = situacao;
        this.data_venc =  data_venc;
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
