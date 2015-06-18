package br.edu.ufra.appfiscalizacao.entidade;

/**
 * Created by Rayan on 16/06/2015.
 */
public class Equipamento {
    private int id;
    private String nome;
    private String descricao;
    private String condicoes;

    public Equipamento() {
    }

    public Equipamento(int id, String nome, String descricao, String condicoes) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.condicoes = condicoes;
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
}
