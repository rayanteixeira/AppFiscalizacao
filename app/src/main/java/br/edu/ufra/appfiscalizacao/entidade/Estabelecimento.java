package br.edu.ufra.appfiscalizacao.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "estabelecimento")
public class Estabelecimento {
    @DatabaseField (generatedId=true)
    private int id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private int dataLic;
    @DatabaseField
    private int dataVenc;
    @DatabaseField
    private String situacao;


    public Estabelecimento() {
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

    public int getDataLic() {
        return dataLic;
    }

    public void setDataLic(int dataLic) {
        this.dataLic = dataLic;
    }

    public int getDataVenc() {
        return dataVenc;
    }

    public void setDataVenc(int dataVenc) {
        this.dataVenc = dataVenc;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
