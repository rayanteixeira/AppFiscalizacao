package br.edu.ufra.appfiscalizacao.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Rayan on 16/06/2015.
 */




@DatabaseTable(tableName = "tecnico")
public class Tecnico implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String matricula;
    @DatabaseField
    private String nome;
    @DatabaseField
    private String email;
    @DatabaseField
    private String senha;
    @ForeignCollectionField(eager = false)
    Collection<Vistoria> vistorias;

    public Tecnico() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Collection<Vistoria> getVistorias() {
        return vistorias;
    }

    public void setVistorias(Collection<Vistoria> vistorias) {
        this.vistorias = vistorias;
    }
}