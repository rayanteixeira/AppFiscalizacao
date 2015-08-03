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
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private String descricao;
    @DatabaseField
    private String status;
    @DatabaseField
    private String material;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Collection<Inspecao> getInspecoes() {
        return inspecoes;
    }

    public void setInspecoes(Collection<Inspecao> inspecoes) {
        this.inspecoes = inspecoes;
    }
}