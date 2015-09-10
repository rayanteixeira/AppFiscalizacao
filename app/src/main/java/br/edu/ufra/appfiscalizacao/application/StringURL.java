package br.edu.ufra.appfiscalizacao.application;

public class StringURL {
    private static StringURL uniqueInstance = new StringURL();
    private static String ip="192.168.0.160";
    private static String porta="8080";
    private String urlLogarTec;
    private static String urlEstabelecimento = "http://"+ip+":"+porta+"/AcaiInspecao-mvn/resources/estabelecimento/";
    private static String urlInspecao = "http://"+ip+":"+porta+"/AcaiInspecao-mvn/resources/inspecao/";
    private static String urlTecnico = "http://"+ip+":"+porta+"/AcaiInspecao-mvn/resources/tecnico/";
    private static String urlEquipamento = "http://"+ip+":"+porta+"/AcaiInspecao-mvn/resources/equipamento/";
    private static String urlVistoria = "http://"+ip+":"+porta+"/AcaiInspecao-mvn/resources/vistoria/";

    private StringURL (){

    }


    public static StringURL getInstance() {
        return uniqueInstance;
    }

    public String getUrlLogarTec() {
        return urlLogarTec;
    }

    public void setUrlLogarTec(String mat1, String mat2) {
        this.urlLogarTec = urlTecnico+"logar?m1="+mat1+"&"+"m2="+mat2;
    }

    public static String getUrlTecnico() {
        return urlTecnico;
    }

    public static void setUrlTecnico(String urlTecnico) {
        StringURL.urlTecnico = urlTecnico;

    }

    public static String getUrlInspecao() {
        return urlInspecao;
    }

    public static void setUrlInspecao(String urlInspecao) {
        StringURL.urlInspecao = urlInspecao;
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
