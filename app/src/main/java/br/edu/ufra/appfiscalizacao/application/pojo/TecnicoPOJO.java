
package br.edu.ufra.appfiscalizacao.application.pojo;


import java.io.Serializable;

public class TecnicoPOJO implements Serializable {
    private Integer id;
    private String nome;

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
