    
package br.edu.ufra.appfiscalizacao.application.pojo.lista;

import java.io.Serializable;
import java.util.List;

import br.edu.ufra.appfiscalizacao.application.pojo.InspecaoPOJO;

public class InspecaoListaPOJO implements Serializable{
    List<InspecaoPOJO> inspecoesPOJO;

    public List<InspecaoPOJO> getInspecoesPOJO() {
        return inspecoesPOJO;
    }

    public InspecaoListaPOJO(List<InspecaoPOJO> inspecoesPOJO) {
        this.inspecoesPOJO = inspecoesPOJO;
    }

    public InspecaoListaPOJO() {
    }

    public void setInspecoesPOJO(List<InspecaoPOJO> inspecoesPOJO) {
        this.inspecoesPOJO = inspecoesPOJO;
    }
    
    
}
