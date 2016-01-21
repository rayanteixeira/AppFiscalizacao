package br.edu.ufra.appfiscalizacao.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Rayan on 16/06/2015.
 */
@DatabaseTable (tableName = "vistoria")
public class Vistoria implements Serializable{

    @DatabaseField (generatedId = true)
    private int id;
    @DatabaseField
    private long dataSolicitacao;
    @DatabaseField
    private long dataVistoria;
    @DatabaseField
    private Integer prazo;
    @DatabaseField
    private boolean apto;
    @DatabaseField
    private String observacao;
    @DatabaseField(foreign = true)
    private Estabelecimento estabelecimento;
    @DatabaseField(foreign = true)
    private Tecnico tecnico1;
    @DatabaseField(foreign = true)
    private Tecnico tecnico2;
    @ForeignCollectionField(eager = false)
    private Collection<Inspecao> inspecaoList;



    public Vistoria() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(long dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public long getDataVistoria() {
        return dataVistoria;
    }

    public void setDataVistoria(long dataVistoria) {
        this.dataVistoria = dataVistoria;
    }

    public Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
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

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public Tecnico getTecnico1() {
        return tecnico1;
    }

    public void setTecnico1(Tecnico tecnico1) {
        this.tecnico1 = tecnico1;
    }

    public Tecnico getTecnico2() {
        return tecnico2;
    }

    public void setTecnico2(Tecnico tecnico2) {
        this.tecnico2 = tecnico2;
    }

    public Collection<Inspecao> getInspecaoList() {
        return inspecaoList;
    }

    public void setInspecaoList(Collection<Inspecao> inspecaoList) {
        this.inspecaoList = inspecaoList;
    }
}