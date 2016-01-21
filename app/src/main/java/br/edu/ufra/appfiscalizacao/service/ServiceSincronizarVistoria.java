package br.edu.ufra.appfiscalizacao.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufra.appfiscalizacao.application.pojo.VistoriaComInspecoesPOJO;
import br.edu.ufra.appfiscalizacao.application.pojo.conversor.InspecaoConverter;
import br.edu.ufra.appfiscalizacao.application.pojo.conversor.VistoriaConverter;
import br.edu.ufra.appfiscalizacao.application.pojo.lista.InspecaoListaPOJO;
import br.edu.ufra.appfiscalizacao.entidade.Vistoria;
import br.edu.ufra.appfiscalizacao.rn.InspecaoRN;
import br.edu.ufra.appfiscalizacao.rn.VistoriaRN;
import br.edu.ufra.appfiscalizacao.util.ConexaoInternet;
import br.edu.ufra.appfiscalizacao.util.Mensagem;
import br.edu.ufra.appfiscalizacao.util.StringURL;

public class ServiceSincronizarVistoria extends Service {
    VistoriaRN rnVistoria;
    InspecaoRN rnInspecao;
    List<VistoriaComInspecoesPOJO> vistoriasComInspecoesPOJO;
    Gson gson;
    RequestQueue requestQueue;
    InspecaoListaPOJO inspecaoListaPOJO;
    Mensagem mensagemServidor;
    private String urlSalvarInspecaoListaPOJO = StringURL.getUrlVistoria()+"salvar";


    public ServiceSincronizarVistoria() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        rnVistoria = new VistoriaRN(getBaseContext());
        rnInspecao = new InspecaoRN(getBaseContext());
        vistoriasComInspecoesPOJO = new ArrayList<>();
        gson = new Gson();
        enviarServidor();

        return super.onStartCommand(intent, flags, startId);

    }

    public void enviarServidor(){
        try{

            if (ConexaoInternet.estaConectado(getBaseContext())==true && !rnVistoria.obterTodos().isEmpty()){
                obterVistoriasDoBD();

                requestQueue = Volley.newRequestQueue(this.getApplicationContext());
                System.out.println("cont "+vistoriasComInspecoesPOJO.size() );
                String converteToJson=gson.toJson(vistoriasComInspecoesPOJO);
                System.out.println("json "+converteToJson);
                JSONObject convertingToJsonObject= new JSONObject(converteToJson);

                System.out.println("object:"+convertingToJsonObject);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlSalvarInspecaoListaPOJO,convertingToJsonObject , new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        System.out.println("mgs json "+response);
                        mensagemServidor =gson.fromJson(response.toString(), Mensagem.class);
                        System.out.println("resposta: "+mensagemServidor.getMensagemServToClient());
                        Toast.makeText(getBaseContext(), "Mensagem server: " + mensagemServidor.getMensagemServToClient(), Toast.LENGTH_LONG).show();


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(2000,3,2));
                System.out.println("policy 1 "+((DefaultRetryPolicy) jsonObjectRequest.getRetryPolicy()).getCurrentTimeout());
                System.out.println("policy 2 "+((DefaultRetryPolicy) jsonObjectRequest.getRetryPolicy()).getCurrentRetryCount());
                System.out.println("policy 3 "+((DefaultRetryPolicy) jsonObjectRequest.getRetryPolicy()).getBackoffMultiplier());
                requestQueue.add(jsonObjectRequest);
            }
        }catch (JSONException e){

            Toast.makeText(getBaseContext(),"Erro ao tentar enviar os dados para o servidor: "+e.getCause(),Toast.LENGTH_LONG).show();
        }
    }

    public void obterVistoriasDoBD(){
        List<Vistoria> vistorias = new ArrayList<>();
        vistoriasComInspecoesPOJO = new ArrayList<>();

        VistoriaComInspecoesPOJO vistoriaComInspecoesPOJO;
        System.out.println("Antes for "+rnVistoria.obterTodos().size());
        System.out.println("Antes for "+rnInspecao.obterTodos().size());
        for (Vistoria v : rnVistoria.obterTodos()){
            vistoriaComInspecoesPOJO = new VistoriaComInspecoesPOJO(VistoriaConverter.toVistoriaPOJO(v), InspecaoConverter.toInspecoesPOJO(v.getInspecaoList()));
            vistoriasComInspecoesPOJO.add(vistoriaComInspecoesPOJO);
        }

    }

    public void removerVistorias(){
        for (Vistoria v : rnVistoria.obterTodos()){

            rnVistoria.remover(v);
        }
    }
}
