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
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
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

import br.edu.ufra.appfiscalizacao.activity.MainActivity;
import br.edu.ufra.appfiscalizacao.activity.VistoriasActivity;
import br.edu.ufra.appfiscalizacao.adapter.EstabelecimentoAdapter;
import br.edu.ufra.appfiscalizacao.application.pojo.EstabelecimentoPOJO;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;
import br.edu.ufra.appfiscalizacao.rn.EstabelecimentoRN;
import br.edu.ufra.appfiscalizacao.util.ConexaoInternet;
import br.edu.ufra.appfiscalizacao.util.Mensagem;
import br.edu.ufra.appfiscalizacao.util.StringURL;


public class FragmentEstabelecimento extends Fragment {

    private boolean mSearchCheck;
    private ListView listView;
    private EstabelecimentoAdapter adapter;
    private ArrayList<EstabelecimentoPOJO> estabelecimentosPOJO;
    private ArrayList<Estabelecimento> estabelecimentos;
    private EstabelecimentoPOJO estabelecimentoPOJO;
    private EstabelecimentoRN rn;
    private Gson gson;
    private GsonBuilder builder;
    private String urlEstabelecimentos = StringURL.getUrlEstabelecimento() + "all";
    private Context contexto;
    private String mensagemInternet = Mensagem.getMensagemInternet();
    private ProgressDialog mProgressDialog;
    private RequestQueue requestQueue;
    MainActivity mainActivity;
    public FragmentEstabelecimento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        contexto = getActivity().getBaseContext();
        requestQueue = Volley.newRequestQueue(getActivity().getApplication());
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_principal, container, false);
        listView = (ListView) rootView.findViewById(R.id.listaestabelecimentos);
        mProgressDialog = ProgressDialog.show(getActivity(), "Download", "Atualizando lista, por favor espere.", false, true);
        obterEstabelecimentos();
        //listaBancoLocal();
        return rootView;
    }

    //Busca no servidor os estabelecimentos
    public void obterEstabelecimentos() {
        try {
            //verifica conexao com internet
            if (ConexaoInternet.estaConectado(contexto) == true) {
                System.out.println("conectado");
                builder = new GsonBuilder();
                //converte data(pois esta em formato DATE)
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

                //Recebe a lista de estabelecimentos do servidor
                JsonArrayRequest request = new JsonArrayRequest(urlEstabelecimentos, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int i;
                        estabelecimentosPOJO = new ArrayList<>();
                        for (i = 0; i < response.length(); i++) {
                            try {
                                System.out.println("objects server "+response.length());
                                JSONObject estabelecimentoItem = response.getJSONObject(i);
                                estabelecimentoPOJO = gson.fromJson(estabelecimentoItem.toString(), EstabelecimentoPOJO.class);
                                System.out.println("estabelecimento" + estabelecimentoPOJO.getDataLicenca());
                                estabelecimentosPOJO.add(estabelecimentoPOJO);
                                mProgressDialog.dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        createListView();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mProgressDialog.dismiss();
                        System.out.println("Erro ao obter dados do servidor:" + error.toString());
                        Toast.makeText(contexto, "Erro ao obter dados do servidor:" + error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
                request.setRetryPolicy(new DefaultRetryPolicy(2000,3,2));
                System.out.println("policy 1 "+((DefaultRetryPolicy) request.getRetryPolicy()).getCurrentTimeout());
                System.out.println("policy 2 "+((DefaultRetryPolicy) request.getRetryPolicy()).getCurrentRetryCount());
                System.out.println("policy 3 "+((DefaultRetryPolicy) request.getRetryPolicy()).getBackoffMultiplier());
                requestQueue.add(request);
                //VolleyApplication.getsInstance().getmRequestQueue().add(request);
            } else {
                mProgressDialog.dismiss();
                Toast.makeText(contexto, mensagemInternet, Toast.LENGTH_LONG).show();
                System.out.println("sem internet" + mensagemInternet);
            }
        } catch (Exception e) {
            Toast.makeText(contexto, "Erro ao solicitar dados: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            System.out.println("erro " + e.getMessage());
        }
    }

    //passagem dos dados da lista para o listview utilizando adapterpersonalizado.
    private void createListView() {

        adapter = new EstabelecimentoAdapter(contexto, estabelecimentosPOJO);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EstabelecimentoPOJO estabelecimento = (EstabelecimentoPOJO) adapter.getItem(position);

                //Intent it = new Intent(getActivity().getBaseContext(), DetalhesVistoriaActivity.class);
                Intent it = new Intent(getActivity().getBaseContext(), VistoriasActivity.class);

                //it.putExtra("estabelecimento", estabelecimento);
                it.putExtra("estabelecimento", estabelecimento);
                //mainActivity.estabelecimento = estabelecimento;
                startActivity(it);

            }
        });


    }

/*
    //utilizando busca em banco de dados local
    public void listaBancoLocal(){

        estabelecimentos = new ArrayList<>();
        rn = new EstabelecimentoRN(getActivity().getBaseContext());
        System.out.println("Quantidade e "+rn.obterTodos().size());
        for (Estabelecimento e : rn.obterTodos()){
            estabelecimentos.add(e);

        }
        createListView();
    }

*/
}
