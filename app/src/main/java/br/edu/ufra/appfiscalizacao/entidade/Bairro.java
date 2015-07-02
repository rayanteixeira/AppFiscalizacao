package br.edu.ufra.appfiscalizacao.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "bairro")
public class Bairro {
    @DatabaseField(generatedId = false)
    private Integer id;
    @DatabaseField
    private String nome;

    public Bairro(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
