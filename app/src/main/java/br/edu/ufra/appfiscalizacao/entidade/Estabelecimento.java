package br.edu.ufra.appfiscalizacao.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;


@DatabaseTable(tableName = "estabelecimento")
public class Estabelecimento implements Serializable {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private String nomeFantasia;
    @DatabaseField
    private String nomeContato;
    @DatabaseField
    private String cep;
    @DatabaseField
    private String logradouro;
    @DatabaseField
    private String numero;
    @DatabaseField
    private String complemeto;
    @DatabaseField
    private String bairro;
    @DatabaseField
    private String telefone;
    @DatabaseField
    private long dataCadastro;
    @DatabaseField
    private long dataLicenca;
    @DatabaseField
    private long dataVencimento;
    @DatabaseField
    private String status;
    @DatabaseField
    private BigDecimal latitude;
    @DatabaseField
    private BigDecimal longitude;
    @ForeignCollectionField(eager = false)
    private Collection<Vistoria> vistorias;


    public Estabelecimento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemeto() {
        return complemeto;
    }

    public void setComplemeto(String complemeto) {
        this.complemeto = complemeto;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(long dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public long getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(long dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public long getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(long dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Collection<Vistoria> getVistorias() {
        return vistorias;
    }

    public void setVistorias(Collection<Vistoria> vistorias) {
        this.vistorias = vistorias;
    }
}