
package br.edu.ufra.appfiscalizacao.application.pojo;


import java.io.Serializable;

public class TecnicoPOJO implements Serializable {
    private Integer id;
    private String matricula;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    

    
}
