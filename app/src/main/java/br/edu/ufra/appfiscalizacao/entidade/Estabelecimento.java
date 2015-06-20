package br.edu.ufra.appfiscalizacao.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Rayan on 16/06/2015.
 */
@DatabaseTable (tableName = "estabelecimento")
public class Estabelecimento {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private String dataLic;
    @DatabaseField
    private String dataVenc;
    @DatabaseField
    private String situacao;


    public Estabelecimento() {
    }

    public Estabelecimento(String nome, String situacao, String dataLic) {
        this.nome = nome;
        this.situacao = situacao;
        this.dataLic = dataLic;
    }

    public Estabelecimento(String nome, String dataLic, String dataVenc, String situacao) {
        this.nome = nome;
        this.dataLic = dataLic;
        this.dataVenc = dataVenc;
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

    public String getDataLic() {
        return dataLic;
    }

    public void setDataLic(String dataLic) {
        this.dataLic = dataLic;
    }

    public String getDataVenc() {
        return dataVenc;
    }

    public void setDataVenc(String dataVenc) {
        this.dataVenc = dataVenc;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}