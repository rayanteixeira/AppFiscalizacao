package br.edu.ufra.appfiscalizacao.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "bairro")
public class Bairro implements Serializable {
    @DatabaseField(id = true)
    private Integer id;
    @DatabaseField
    private String nome;
    @DatabaseField
    String enviado = "naoEnviado";


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
