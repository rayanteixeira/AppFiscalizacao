package br.edu.ufra.appfiscalizacao.application.pojo.conversor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ufra.appfiscalizacao.application.pojo.VistoriaPOJO;
import br.edu.ufra.appfiscalizacao.entidade.Vistoria;

/**
 *
 * @author geovane
 */
public class VistoriaConverter {
    private static final VistoriaConverter uniqueInstance = new VistoriaConverter();

    private VistoriaConverter() {

    }

    public static VistoriaConverter getInstance() {
        return uniqueInstance;
    }

    public static VistoriaPOJO toVistoriaPOJO(Vistoria vistoria) {
        if (vistoria != null) {

            VistoriaPOJO resposta = new VistoriaPOJO();

            resposta.setId(vistoria.getId());
            resposta.setApto(vistoria.isApto());
            resposta.setDataSolicitacao(vistoria.getDataSolicitacao());
            resposta.setDataVistoria(vistoria.getDataVistoria());
            resposta.setEstabelecimentoPOJO(EstabelecimentoConverter.toEstabelecimentoPOJO(vistoria.getEstabelecimento()));
            resposta.setObservacao(vistoria.getObservacao());
            resposta.setPrazo(vistoria.getPrazo());
            resposta.setTecnicoPOJO1(TecnicoConverter.toTecnicoPOJO(vistoria.getTecnico1()));
            resposta.setTecnicoPOJO2(TecnicoConverter.toTecnicoPOJO(vistoria.getTecnico2()));
            return resposta;

        } else {
            return null;
        }
    }

    public static Vistoria fromVistoriaPOJO(VistoriaPOJO vistoriaPOJO) {
        if (vistoriaPOJO != null) {
            Vistoria resposta = new Vistoria();

            resposta.setId(vistoriaPOJO.getId());
            resposta.setApto(vistoriaPOJO.getApto());
            resposta.setDataSolicitacao(vistoriaPOJO.getDataSolicitacao());
            resposta.setDataVistoria(vistoriaPOJO.getDataVistoria());
            resposta.setEstabelecimento(EstabelecimentoConverter.fromEstabelecimentoPOJO(vistoriaPOJO.getEstabelecimentoPOJO()));
            resposta.setObservacao(vistoriaPOJO.getObservacao());
            resposta.setPrazo(vistoriaPOJO.getPrazo());
            resposta.setTecnico1(TecnicoConverter.fromTecnicoPOJO(vistoriaPOJO.getTecnicoPOJO1()));
            resposta.setTecnico2(TecnicoConverter.fromTecnicoPOJO(vistoriaPOJO.getTecnicoPOJO2()));          
            
            return resposta;

        } else {
            return null;
        }
    }

    public static List<VistoriaPOJO> toVistoriasPOJO(List<Vistoria> vistorias) {
        if (vistorias != null) {

            ArrayList<VistoriaPOJO> resposta = new ArrayList<>();

            for (Vistoria vistoria : vistorias){

                resposta.add(toVistoriaPOJO(vistoria));
            }

            return resposta;
        } else {
            return null;
        }

    }

    public static List<Vistoria> fromVistoriasPOJO(List<VistoriaPOJO> vistoriasPOJO) {
        if (vistoriasPOJO != null) {

            ArrayList<Vistoria> resposta = new ArrayList<>();

            for (VistoriaPOJO vistoriaPOJO : vistoriasPOJO){

                resposta.add(fromVistoriaPOJO(vistoriaPOJO));
            }

            return resposta;
        } else {
            return null;
        }
    }
}
