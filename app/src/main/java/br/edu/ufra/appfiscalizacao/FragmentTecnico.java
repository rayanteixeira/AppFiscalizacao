package br.edu.ufra.appfiscalizacao;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufra.appfiscalizacao.activity.DetalheActivity;
import br.edu.ufra.appfiscalizacao.adapter.TecnicoAdapter;
import br.edu.ufra.appfiscalizacao.application.StringURL;
import br.edu.ufra.appfiscalizacao.application.VolleyApplication;
import br.edu.ufra.appfiscalizacao.entidade.Tecnico;


public class FragmentTecnico extends Fragment {

    Gson gson;
    StringURL url;
    List<Tecnico> tecnicos = null;
    TextView mText;
    TecnicoAdapter adapter = null;
    ListView listtecnicos;
    ProgressDialog mProgressDialog = null;


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
        View rootview = inflater.inflate(R.layout.fragment_tecnicos, container, false);

        listtecnicos = (ListView) rootview.findViewById(R.id.listtecnicos);

        gson = new Gson();
        mProgressDialog = ProgressDialog.show(getActivity(),"Download","Atualizando lista, por favor espere.", false, true);
        mText = (TextView) rootview.findViewById(R.id.txtjson);
        getjsonArray();
        return rootview;
    }


    public void getjsonArray() {



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


                        for( Tecnico t : tecnicos){
                            System.out.println(t.getId());
                        }
                        Toast.makeText(getActivity().getBaseContext(),"Lista atualizada com sucesso",Toast.LENGTH_SHORT).show();

                        adapter = new TecnicoAdapter(getActivity().getBaseContext(), tecnicos);
                        listtecnicos.setAdapter(adapter);
                        listtecnicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Tecnico tecnico = (Tecnico) adapter.getItem(position);
                                int idtecnico = tecnico.getId();
                                System.out.println("ITEM--=>" +tecnico.getId());
                                System.out.println("NOME--=>" +tecnico.getNome());

                                Intent it = new Intent(getActivity().getBaseContext(), DetalheActivity.class);
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

                ,
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        mText.setText(error.toString());
                        Toast.makeText(getActivity().getBaseContext(),"Erro ao atualizar Lista ",Toast.LENGTH_SHORT).show();
                    }
                });


        //int socketTimeout = 30000;//30 seconds - change to what you want
       // RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
       // arrayRequest.setRetryPolicy(policy);



        VolleyApplication.getsInstance().getmRequestQueue().add(arrayRequest);

    }
}
