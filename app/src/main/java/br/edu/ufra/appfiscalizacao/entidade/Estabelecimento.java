package br.edu.ufra.appfiscalizacao.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@DatabaseTable(tableName = "estabelecimento")
public class Estabelecimento {
    @DatabaseField (generatedId=true)
    private int id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private String contato;
    @DatabaseField
    private String rg;
    @DatabaseField
    private String cpf;
    @DatabaseField
    private String logradouro ;
    @DatabaseField
    private String numero;
    @DatabaseField
    private String email;
    @DatabaseField
    private String telefone;
    @DatabaseField
    private Date dataCadastro;
    @DatabaseField
    private String dataLicenca;
    @DatabaseField
    private String dataVencimento;
    @DatabaseField
    private String status;
    @DatabaseField
    private double latitude;
    @DatabaseField
    private double longitude;
    @DatabaseField
    private String autentificacao;

    private List<Vistoria> vistoriaList;

    private Bairro bairro;




    public Estabelecimento() {
    }

    public Estabelecimento(int id, String nome, String contato, String rg, String cpf, String logradouro, String numero, String email, String telefone, Date dataCadastro, String dataLicenca, String dataVencimento, String status, double latitude, double longitude, String autentificacao, List<Vistoria> vistoriaList, Bairro bairro) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
        this.rg = rg;
        this.cpf = cpf;
        this.logradouro = logradouro;
        this.numero = numero;
        this.email = email;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
        this.dataLicenca = dataLicenca;
        this.dataVencimento = dataVencimento;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.autentificacao = autentificacao;
        this.vistoriaList = vistoriaList;
        this.bairro = bairro;
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

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(String dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAutentificacao() {
        return autentificacao;
    }

    public void setAutentificacao(String autentificacao) {
        this.autentificacao = autentificacao;
    }

    public List<Vistoria> getVistoriaList() {
        return vistoriaList;
    }

    public void setVistoriaList(List<Vistoria> vistoriaList) {
        this.vistoriaList = vistoriaList;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
}
