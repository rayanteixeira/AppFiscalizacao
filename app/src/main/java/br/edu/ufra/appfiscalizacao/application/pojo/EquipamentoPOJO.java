package br.edu.ufra.appfiscalizacao.application.pojo;

import java.io.Serializable;

public class EquipamentoPOJO implements Serializable {
    private Integer id;
    private String nome;
    private String descricao;
    private String status;
    private String material;

    private boolean equipInspecionado;

    private boolean equipApto;

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

    public boolean isEquipInspecionado() {
        return equipInspecionado;
    }

    public void setEquipInspecionado(boolean equipInspecionado) {
        this.equipInspecionado = equipInspecionado;
    }

    public boolean isEquipApto() {
        return equipApto;
    }

    public void setEquipApto(boolean equipApto) {
        this.equipApto = equipApto;
    }
}
