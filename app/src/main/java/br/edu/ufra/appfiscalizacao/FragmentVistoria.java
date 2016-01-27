package br.edu.ufra.appfiscalizacao;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ufra.appfiscalizacao.activity.DetalhesVistoriaActivity;
import br.edu.ufra.appfiscalizacao.adapter.EstabelecimentoAdapter;
import br.edu.ufra.appfiscalizacao.application.VolleyApplication;
import br.edu.ufra.appfiscalizacao.application.pojo.EstabelecimentoPOJO;
import br.edu.ufra.appfiscalizacao.util.ConexaoInternet;
import br.edu.ufra.appfiscalizacao.util.Mensagem;
import br.edu.ufra.appfiscalizacao.util.StringURL;


public class FragmentVistoria extends Fragment {

    View rootView;
    private TextView textoselo;
    //EstabelecimentoRN rn;

    List<EstabelecimentoPOJO> estabelecimentos;
    List<EstabelecimentoPOJO> listAguardandoVistoria;
    List<EstabelecimentoPOJO> listPendente;
    private TabHost abas;
    RequestQueue resquestQueue;
    String urlEstabelecimentos = StringURL.getUrlEstabelecimento() + "all";
    Gson gson;
    String mensagemInternet = Mensagem.getMensagemInternet();
    Context contexto;
    private GsonBuilder builder;
    private EstabelecimentoAdapter adapterAguardando;
    private EstabelecimentoAdapter adapterPendente;
    private ProgressDialog mProgressDialog;
    EstabelecimentoPOJO estabelecimento;
    public FragmentVistoria() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        contexto = getActivity().getBaseContext();
        rootView = inflater.inflate(R.layout.fragment_estabelecimento, container, false);
        //rn = new EstabelecimentoRN(getActivity());
        mProgressDialog = ProgressDialog.show(getActivity(),"Download","Atualizando lista, por favor espere.", false, true);
        TabHost.TabSpec descritor;
        abas = (TabHost) rootView.findViewById(R.id.tabhost);
        abas.setup();

        descritor = abas.newTabSpec("aba1");
        descritor.setContent(R.id.aguardandoVistoria);
        descritor.setIndicator("Aguardando");
        abas.addTab(descritor);

        descritor = abas.newTabSpec("aba2");
        descritor.setContent(R.id.pendente);
        descritor.setIndicator("Pendente");
        abas.addTab(descritor);

        obterEstabelecimentosWS();


        return rootView;


    }

    public void obterEstabelecimentosWS(){
        try {

            if (ConexaoInternet.estaConectado(contexto) == true) {
                builder = new GsonBuilder();
                builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                    @Override
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        try {
                            return df.parse(json.getAsString());
                        } catch (ParseException e) {
                            return null;
                        }
                    }
                });
                gson = builder.create();
                JsonArrayRequest request = new JsonArrayRequest(urlEstabelecimentos, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int i;
                        estabelecimentos = new ArrayList<>();
                        for (i = 0; i < response.length(); i++) {
                            try {
                                JSONObject estabelecimentoItem = response.getJSONObject(i);
                                estabelecimento = gson.fromJson(estabelecimentoItem.toString(), EstabelecimentoPOJO.class);
                                System.out.println("estabelecimento" + estabelecimento.getNomeFantasia());
                                estabelecimentos.add(estabelecimento);
                            } catch (JSONException e) {
                                mProgressDialog.dismiss();
                                Toast.makeText(contexto, "Erro ao obter dados do servidor:", Toast.LENGTH_LONG).show();

                                System.out.println("Erro: "+e.getMessage());
                                e.printStackTrace();
                            }
                        }

                        definirSituacaoPonto();
                        listPontosAguardandoVistoria();
                        listPontosPendente();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mProgressDialog.dismiss();
                        Toast.makeText(contexto, "Erro ao obter dados do servidor:"+error.getMessage() , Toast.LENGTH_LONG).show();
                        System.out.println("Erro volley: "+error.getMessage());
                    }
                });

                request.setRetryPolicy(new DefaultRetryPolicy(2000,3,2));
                System.out.println("policy 1 "+((DefaultRetryPolicy) request.getRetryPolicy()).getCurrentTimeout());
                System.out.println("policy 2 "+((DefaultRetryPolicy) request.getRetryPolicy()).getCurrentRetryCount());
                System.out.println("policy 3 "+((DefaultRetryPolicy) request.getRetryPolicy()).getBackoffMultiplier());
                VolleyApplication.getsInstance().getmRequestQueue().add(request);
            } else {
                mProgressDialog.dismiss();
                Toast.makeText(contexto, mensagemInternet, Toast.LENGTH_LONG).show();
                System.out.println("sem internet"+mensagemInternet);
            }
        } catch (Exception e) {
            Toast.makeText(contexto, "Erro ao solicitar dados: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            System.out.println("erro " + e.getMessage());
        }
    }

    public void listPontosAguardandoVistoria() {
        final ListView pontoAguardando = (ListView) rootView.findViewById(R.id.listPontosAguardando);
         adapterAguardando = new EstabelecimentoAdapter(contexto, listAguardandoVistoria);
        pontoAguardando.setAdapter(adapterAguardando);
        pontoAguardando.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EstabelecimentoPOJO estabelecimento = (EstabelecimentoPOJO) adapterAguardando.getItem(position);

                int idEstabelecimento = estabelecimento.getId();
                Intent it = new Intent(getActivity().getBaseContext(), DetalhesVistoriaActivity.class);
                it.putExtra("estabelecimento", estabelecimento);
                startActivity(it);
            }
        });

    }

    public void listPontosPendente() {

        final ListView pontoPendente = (ListView) rootView.findViewById(R.id.listPontosPendente);
        adapterPendente = new EstabelecimentoAdapter(contexto, listPendente);
        pontoPendente.setAdapter(adapterPendente);
        pontoPendente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 estabelecimento = (EstabelecimentoPOJO) adapterPendente.getItem(position);

                int idEstabelecimento = estabelecimento.getId();
                Intent it = new Intent(getActivity().getBaseContext(), DetalhesVistoriaActivity.class);
                it.putExtra("estabelecimento", estabelecimento);
                startActivity(it);
            }
        });


    }


    public void definirSituacaoPonto() {
        listAguardandoVistoria = new ArrayList<>();
        listPendente = new ArrayList<>();
        for (EstabelecimentoPOJO e : estabelecimentos) {

            if (e.getStatus().equals("Aguardando vistoria")) {
                listAguardandoVistoria.add(e);
            } else if (e.getStatus().equals("Pendente")) {
                listPendente.add(e);
            }
        }
        mProgressDialog.dismiss();
    }

}
