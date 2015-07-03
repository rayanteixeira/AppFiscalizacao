package br.edu.ufra.appfiscalizacao.util;

import java.io.Serializable;

/**
 * Created by geovane on 27/02/15.
 */
public class Mensagem implements Serializable{
    private static final Mensagem uniqueInstance= new Mensagem();
    private String erroAoLogar;
    private  String mensagemSalvar;
    private String mensagemDeEnvio;
    private static String mensagemInternet= "Conecte-se a internet para obter dados do servidor";

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

    public String getMensagemDeEnvio() {
        return mensagemDeEnvio;
    }

    public void setMensagemDeEnvio(String mensagemDeEnvio) {
        this.mensagemDeEnvio = mensagemDeEnvio;
    }

    public  String getMensagemSalvar() {
        return mensagemSalvar;
    }

    public  void setMensagemSalvar(String mensagemSalvar) {
        mensagemSalvar = mensagemSalvar;
    }
}
