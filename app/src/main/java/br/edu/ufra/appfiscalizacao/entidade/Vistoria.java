package br.edu.ufra.appfiscalizacao.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.List;

/**
 * Created by Rayan on 16/06/2015.
 */
@DatabaseTable (tableName = "vistoria")
public class Vistoria {

    @DatabaseField (generatedId = true)
    private int id;
    @DatabaseField
    private String dataSolicitacao;
    @DatabaseField
    private String dataVistoria;
    @DatabaseField
    private Integer prazo;
    @DatabaseField
    private boolean apto;
    @DatabaseField
    private String observacao;

    private List<Inspecao> inspecaoList;

    private Estabelecimento estabelecimento;

    private Tecnico tecnico1;

    private Tecnico tecnico2;


    public Vistoria() {
    }

    public Vistoria(int id, String dataSolicitacao, String dataVistoria, Integer prazo, boolean apto, String observacao, List<Inspecao> inspecaoList, Estabelecimento estabelecimento, Tecnico tecnico1, Tecnico tecnico2) {
        this.id = id;
        this.dataSolicitacao = dataSolicitacao;
        this.dataVistoria = dataVistoria;
        this.prazo = prazo;
        this.apto = apto;
        this.observacao = observacao;
        this.inspecaoList = inspecaoList;
        this.estabelecimento = estabelecimento;
        this.tecnico1 = tecnico1;
        this.tecnico2 = tecnico2;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(String dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getDataVistoria() {
        return dataVistoria;
    }

    public void setDataVistoria(String dataVistoria) {
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

    public List<Inspecao> getInspecaoList() {
        return inspecaoList;
    }

    public void setInspecaoList(List<Inspecao> inspecaoList) {
        this.inspecaoList = inspecaoList;
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
}