package br.edu.ufra.appfiscalizacao.entidade;

/**
 * Created by Rayan on 16/06/2015.
 */
public class Tecnico {

    private int id_matricula;
    private String nome;

    public Tecnico() {
    }

    public Tecnico(int id_matricula, String nome) {
        this.id_matricula = id_matricula;
        this.nome = nome;
    }


    public int getId_matricula() {
        return id_matricula;
    }

    public void setId_matricula(int id_matricula) {
        this.id_matricula = id_matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
