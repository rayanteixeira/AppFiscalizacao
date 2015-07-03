package br.edu.ufra.appfiscalizacao.application;

/**
 * Created by bpmlab on 29/06/15.
 */
public class StringURL {
    private static StringURL uniqueInstance = new StringURL();

    private static String urlEstabelecimento = "http://10.10.168.4:8084/AcaiPDE_Web/resources/estabelecimento/";
    private static String urlTecnico = "http://10.10.168.4:8084/AcaiPDE_Web/resources/tecnico/";

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
}
