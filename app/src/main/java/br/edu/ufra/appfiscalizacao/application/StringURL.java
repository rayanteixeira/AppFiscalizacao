package br.edu.ufra.appfiscalizacao.application;

/**
 * Created by bpmlab on 29/06/15.
 */
public class StringURL {
    private static StringURL uniqueInstance = new StringURL();
    private static String ip="10.10.164.20";
    private static String porta="8080";
    private static String urlEstabelecimento = "http://"+ip+":"+porta+"/AcaiInspecao-mvn/resources/estabelecimento/";
    private static String urlTecnico = "http://"+ip+":"+porta+"/AcaiInspecao-mvn/resources/tecnico/";
    private static String urlEquipamento = "http://"+ip+":"+porta+"/AcaiInspecao-mvn/resources/equipamento/";
    private static String urlVistoria = "http://"+ip+":"+porta+"/AcaiInspecao-mvn/resources/vistoria/";

    public static String getUrlTecnico() {
        return urlTecnico;
    }

    public static void setUrlTecnico(String urlTecnico) {
        StringURL.urlTecnico = urlTecnico;
    }

    private static StringURL getInstance() {
        return uniqueInstance;
    }

    public static String getUrlEstabelecimento() {
        return urlEstabelecimento;
    }

    public static String getUrlEquipamento() {
        return urlEquipamento;
    }

    public static String getUrlVistoria() {
        return urlVistoria;
    }
}
