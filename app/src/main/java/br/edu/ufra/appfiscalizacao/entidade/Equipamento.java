package br.edu.ufra.appfiscalizacao.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Rayan on 16/06/2015.
 */

@DatabaseTable(tableName = "equipamento")
public class Equipamento implements Serializable {
    @DatabaseField(generatedId = false)
    private int id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private String descricao;
    @DatabaseField
    private String condicoes;
    @ForeignCollectionField(eager = false)
    private Collection<Inspecao> inspecoes;

    public Equipamento() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCondicoes() {
        return condicoes;
    }

    public void setCondicoes(String condicoes) {
        this.condicoes = condicoes;
    }

    public Collection<Inspecao> getInspecoes() {
        return inspecoes;
    }

    public void setInspecoes(Collection<Inspecao> inspecoes) {
        this.inspecoes = inspecoes;
    }
}