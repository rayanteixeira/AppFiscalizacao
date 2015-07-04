package br.edu.ufra.appfiscalizacao;

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

import br.edu.ufra.appfiscalizacao.activity.EstabelecimentoDetalhesActivity;
import br.edu.ufra.appfiscalizacao.adapter.EstabelecimentoAdapter;
import br.edu.ufra.appfiscalizacao.application.StringURL;
import br.edu.ufra.appfiscalizacao.application.VolleyApplication;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;
import br.edu.ufra.appfiscalizacao.rn.EstabelecimentoRN;
import br.edu.ufra.appfiscalizacao.util.ConexaoInternet;
import br.edu.ufra.appfiscalizacao.util.Mensagem;


public class FragmentPrincipal extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private EstabelecimentoAdapter adapter;
    private ArrayList<Estabelecimento> estabelecimentos;
    private Estabelecimento estabelecimento;
    private EstabelecimentoRN rn;
    private Gson gson;
    private GsonBuilder builder;
    private String urlEstabelecimentos = StringURL.getUrlEstabelecimento()+"all";
    private Context contexto ;
    private String mensagemInternet= Mensagem.getMensagemInternet();

    public FragmentPrincipal() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        contexto = getActivity().getBaseContext();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_principal, container, false);
        listView = (ListView) rootView.findViewById(R.id.listaestabelecimentos);
        getJsonArray();

        return rootView;
    }

    public void getJsonArray(){
        try {
            if (ConexaoInternet.estaConectado(contexto) == true) {
                System.out.println("conectado");
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
                                estabelecimento = gson.fromJson(estabelecimentoItem.toString(), Estabelecimento.class);
                                System.out.println("estabelecimento"+estabelecimento.getDataLicenca());
                                estabelecimentos.add(estabelecimento);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        for (Estabelecimento e : estabelecimentos){
                            System.out.println("lista estabelecimento "+e.getNome());
                        }
                        createListView();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(contexto, "Erro ao obter dados do servidor:"+error.getMessage() , Toast.LENGTH_LONG).show();
                    }
                });
                VolleyApplication.getsInstance().getmRequestQueue().add(request);
            } else {
                Toast.makeText(contexto, mensagemInternet, Toast.LENGTH_LONG).show();
                System.out.println("sem internet"+mensagemInternet);
            }
        } catch (Exception e) {
            Toast.makeText(contexto, "Erro ao solicitar dados: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            System.out.println("erro " + e.getMessage());
        }
    }

    private void createListView(){



        adapter = new EstabelecimentoAdapter(contexto, estabelecimentos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Estabelecimento estabelecimento = (Estabelecimento) adapter.getItem(position);

                Intent it = new Intent(getActivity().getBaseContext(), EstabelecimentoDetalhesActivity.class);
                it.putExtra("estabelecimento",estabelecimento);
                startActivity(it);

            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       // Estabelecimento item = adapter.getItem(position);

    }
}
