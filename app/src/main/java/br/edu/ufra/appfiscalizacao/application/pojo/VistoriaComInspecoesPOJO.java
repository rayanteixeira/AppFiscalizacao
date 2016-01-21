package br.edu.ufra.appfiscalizacao.application.pojo;

import java.util.Collection;
import java.util.List;

/**
 * Created by geovane on 18/01/16.
 */
public class VistoriaComInspecoesPOJO {

    private VistoriaPOJO vistoriaPOJO;
    private Collection<InspecaoPOJO> inspecoesPOJO;

    public VistoriaComInspecoesPOJO(VistoriaPOJO vistoriaPOJO, Collection<InspecaoPOJO> inspecoesPOJO) {
        this.vistoriaPOJO = vistoriaPOJO;
        this.inspecoesPOJO = inspecoesPOJO;
    }

    public VistoriaPOJO getVistoriaPOJO() {
        return vistoriaPOJO;
    }

    public void setVistoriaPOJO(VistoriaPOJO vistoriaPOJO) {
        this.vistoriaPOJO = vistoriaPOJO;
    }

    public Collection<InspecaoPOJO> getInspecoesPOJO() {
        return inspecoesPOJO;
    }

    public void setInspecoesPOJO(List<InspecaoPOJO> inspecoesPOJO) {
        this.inspecoesPOJO = inspecoesPOJO;
    }
}
