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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufra.appfiscalizacao.activity.DetalhesTecnicoActivity;
import br.edu.ufra.appfiscalizacao.adapter.TecnicoAdapter;
import br.edu.ufra.appfiscalizacao.application.StringURL;
import br.edu.ufra.appfiscalizacao.application.VolleyApplication;
import br.edu.ufra.appfiscalizacao.entidade.Tecnico;
import br.edu.ufra.appfiscalizacao.util.ConexaoInternet;
import br.edu.ufra.appfiscalizacao.util.Mensagem;


public class FragmentTecnico extends Fragment {

    Gson gson;
    StringURL url;
    List<Tecnico> tecnicos = null;
    TecnicoAdapter adapter = null;
    ListView listtecnicos;
    ProgressDialog mProgressDialog = null;
    private Context contexto;
    String mensagemInternet = Mensagem.getMensagemInternet();

    public FragmentTecnico() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contexto = getActivity().getBaseContext();
        View rootview = inflater.inflate(R.layout.fragment_tecnicos, container, false);

        listtecnicos = (ListView) rootview.findViewById(R.id.listtecnicos);

        gson = new Gson();
        mProgressDialog = ProgressDialog.show(getActivity(), "Download", "Atualizando lista, por favor espere.", false, true);
        getjsonArray();
        return rootview;
    }


    public void getjsonArray() {

        try {
            if (ConexaoInternet.estaConectado(contexto) == true) {

                JsonArrayRequest arrayRequest = new JsonArrayRequest(
                        url.getUrlTecnico() + "all",
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(final JSONArray response) {

                                int i;
                                tecnicos = new ArrayList<>();

                                for (i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject mTecnicoItem = response.getJSONObject(i);
                                        System.out.println("Estabelecimento Item -> " + mTecnicoItem);
                                        Tecnico tecnico = gson.fromJson(String.valueOf(mTecnicoItem), Tecnico.class);
                                        tecnicos.add(tecnico);
                                        mProgressDialog.dismiss();
                                        //tecnico.setNome(mTecnicoItem.getString("nome"));
                                        //System.out.println(tecnico.getNome());

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }

                                for (Tecnico t : tecnicos) {
                                    System.out.println(t.getId());
                                }
                                Toast.makeText(getActivity().getBaseContext(), "Lista atualizada com sucesso", Toast.LENGTH_SHORT).show();


                                listadeTecnicos();

                            }


                        }

                        ,
                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                mProgressDialog.dismiss();
                                Toast.makeText(getActivity().getBaseContext(), "Erro ao atualizar Lista ", Toast.LENGTH_SHORT).show();
                            }
                        });
                VolleyApplication.getsInstance().getmRequestQueue().add(arrayRequest);
            } else {
                mProgressDialog.dismiss();
                Toast.makeText(contexto, mensagemInternet, Toast.LENGTH_LONG).show();
                System.out.println("sem internet" + mensagemInternet);
            }

        } catch (Exception e) {
            Toast.makeText(contexto, "Erro ao solicitar dados: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            System.out.println("erro " + e.getMessage());
        }


        //int socketTimeout = 30000;//30 seconds - change to what you want
        // RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        // arrayRequest.setRetryPolicy(policy);


    }


    public void listadeTecnicos() {
        adapter = new TecnicoAdapter(getActivity().getBaseContext(), tecnicos);
        listtecnicos.setAdapter(adapter);
        listtecnicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tecnico tecnico = (Tecnico) adapter.getItem(position);
                int idtecnico = tecnico.getId();
                System.out.println("ITEM--=>" + tecnico.getId());
                System.out.println("NOME--=>" + tecnico.getNome());

                Intent it = new Intent(getActivity().getBaseContext(), DetalhesTecnicoActivity.class);
                it.putExtra("tecnico", tecnico);
                startActivity(it);



                                /* Fragment chamando Fragment e passango argumento entre eles
                                FragmentMain mFragmentMain = new FragmentMain().newInstance(((Tecnico) adapter.getItem(position)).getNome());
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.container, mFragmentMain);
                                ft.addToBackStack(null);
                                ft.commit();
                                */

            }
        });
    }


}
