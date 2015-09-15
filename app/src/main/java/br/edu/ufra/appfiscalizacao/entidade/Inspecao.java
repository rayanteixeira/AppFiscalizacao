package br.edu.ufra.appfiscalizacao.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Rayan on 16/06/2015.
 */
@DatabaseTable(tableName = "inspecao")
public class Inspecao implements Serializable {

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private long dataInsp;
    @DatabaseField
    private boolean apto;
    @DatabaseField
    private String observacao;
    @DatabaseField(foreign = true)
    private Vistoria vistoria;
    @DatabaseField(foreign = true)
    private Equipamento equipamento;


    public Inspecao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getDataInsp() {
        return dataInsp;
    }

    public void setDataInsp(long dataInsp) {
        this.dataInsp = dataInsp;
    }

    public boolean isApto() {
        return apto;
    }

    public void setApto(boolean apto) {
        this.apto = apto;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Vistoria getVistoria() {
        return vistoria;
    }

    public void setVistoria(Vistoria vistoria) {
        this.vistoria = vistoria;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

}