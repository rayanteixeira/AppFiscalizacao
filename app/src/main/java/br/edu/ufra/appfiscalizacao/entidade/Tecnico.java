package br.edu.ufra.appfiscalizacao.entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;





@DatabaseTable(tableName = "tecnico")
public class Tecnico implements Serializable {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private String matricula;
    @ForeignCollectionField
    private Collection<Vistoria> vistoriaList1;
    @ForeignCollectionField
    private Collection<Vistoria> vistoriaList2;

    
    public Tecnico() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Collection<Vistoria> getVistoriaList1() {
        return vistoriaList1;
    }

    public void setVistoriaList1(Collection<Vistoria> vistoriaList1) {
        this.vistoriaList1 = vistoriaList1;
    }

    public Collection<Vistoria> getVistoriaList2() {
        return vistoriaList2;
    }

    public void setVistoriaList2(Collection<Vistoria> vistoriaList2) {
        this.vistoriaList2 = vistoriaList2;
    }
}