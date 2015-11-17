package br.edu.ufra.appfiscalizacao.util;

import java.io.Serializable;

/**
 * Created by geovane on 27/02/15.
 */
public class Mensagem implements Serializable{
    private static final Mensagem uniqueInstance= new Mensagem();
    private String erroAoLogar;
    private  String mensagemSalvar;
    private String mensagemServToClient;
    private static String mensagemInternet= "Conecte-se a internet para obter dados do servidor";
    private static String mensagemErroAoObter = "Erro ao obter dados do servidor";
    private static String mensagemErroAoSolicitar = "Erro ao solicitar dados do servidor";
    private static String mensagemZeroElementos = "Nem um elemento foi encontrado";
    private Mensagem(){

    }

    public static String getMensagemInternet() {
        return mensagemInternet;
    }

    public static Mensagem getInstace(){
        return uniqueInstance;
    }


    public String getErroAoLogar() {
        return erroAoLogar;
    }

    public void setErroAoLogar(String erroAoLogar) {
        this.erroAoLogar = erroAoLogar;
    }

    public String getMensagemServToClient() {
        return mensagemServToClient;
    }

    public void setMensagemServToClient(String mensagemServToClient) {
        this.mensagemServToClient = mensagemServToClient;
    }

    public  String getMensagemSalvar() {
        return mensagemSalvar;
    }

    public  void setMensagemSalvar(String mensagemSalvar) {
        mensagemSalvar = mensagemSalvar;
    }

    public static String getMensagemErroAoObter() {
        return mensagemErroAoObter;
    }

    public static String getMensagemErroAoSolicitar() {
        return mensagemErroAoSolicitar;
    }

    public static String getMensagemZeroElementos() {
        return mensagemZeroElementos;
    }
}
