package br.edu.ufra.appfiscalizacao.activity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.adapter.VistoriaAdapter;
import br.edu.ufra.appfiscalizacao.application.pojo.EstabelecimentoPOJO;
import br.edu.ufra.appfiscalizacao.application.pojo.VistoriaPOJO;
import br.edu.ufra.appfiscalizacao.util.ConexaoInternet;
import br.edu.ufra.appfiscalizacao.util.Mensagem;
import br.edu.ufra.appfiscalizacao.util.StringURL;

public class VistoriasActivity extends ListActivity {

    private TextView dataVistoriaTxt;
    private ListView listView;
    private ProgressDialog progressDialog;
    private DateFormat sdf;
    private String date;
    private VistoriaAdapter vistoriaAdapter;
    private VistoriaPOJO vistoria;
    private List<VistoriaPOJO> vistorias;
    private Integer id;
    private RequestQueue requestQueue;
    private Gson gson;

    public VistoriasActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent it = getIntent();
        EstabelecimentoPOJO estabelecimento = (EstabelecimentoPOJO) getIntent().getSerializableExtra("estabelecimento");
        id = estabelecimento.getId();

        requestQueue = Volley.newRequestQueue(this.getApplicationContext());

        sdf = new SimpleDateFormat("dd/MM/yyyy");
        //listView = (ListView) findViewById(R.id.listVistoriaFragment);
        progressDialog = ProgressDialog.show(this, "Aguarde", "Atualizando lista", false, true);

        obterVistoriasEstabelecimento(id);

    }

    private void obterVistoriasEstabelecimento(Integer id){
        try {


            if (ConexaoInternet.estaConectado(getBaseContext()) == true) {
                System.out.println("conectado");
                gson = new Gson();
                String urlVistoriasEstabelecimento = StringURL.getUrlVistoria()+id;

                System.out.println(urlVistoriasEstabelecimento);
                //Recebe a lista de estabelecimentos do servidor
                JsonArrayRequest request = new JsonArrayRequest(urlVistoriasEstabelecimento, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            System.out.println("resposta" + response.isNull(0));
                            if (response.length() != 0){
                                int i;
                                vistorias = new ArrayList<>();
                                for (i = 0; i < response.length(); i++) {
                                    try {
                                        System.out.println("objects server "+response.length());
                                        JSONObject vistoriaItem = response.getJSONObject(i);
                                        vistoria = gson.fromJson(vistoriaItem.toString(), VistoriaPOJO.class);
                                        System.out.println("vistoria" + vistoria.getDataVistoria());
                                        vistorias.add(vistoria);
                                    } catch (JSONException e) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getBaseContext(), Mensagem.getMensagemErroAoObter(), Toast.LENGTH_LONG).show();

                                        e.printStackTrace();
                                    }
                                }

                                criarListView();

                            }else {
                                progressDialog.dismiss();
                                Toast.makeText(getBaseContext(), Mensagem.getMensagemZeroElementos(), Toast.LENGTH_LONG).show();
                                System.out.println("zero elementos retornados");
                            }



                        } catch (Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getBaseContext(), Mensagem.getMensagemErroAoObter(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                        System.out.println("erro volley: " + error.toString());
                        Toast.makeText(getBaseContext(), Mensagem.getMensagemErroAoObter(), Toast.LENGTH_LONG).show();
                    }
                });
                request.setRetryPolicy(new DefaultRetryPolicy(2000,3,2));
                System.out.println("policy 1 "+((DefaultRetryPolicy) request.getRetryPolicy()).getCurrentTimeout());
                System.out.println("policy 2 "+((DefaultRetryPolicy) request.getRetryPolicy()).getCurrentRetryCount());
                System.out.println("policy 3 "+((DefaultRetryPolicy) request.getRetryPolicy()).getBackoffMultiplier());
                requestQueue.add(request);
                //VolleyApplication.getsInstance().getmRequestQueue().add(request);
            } else {
                progressDialog.dismiss();
                Toast.makeText(getBaseContext(), Mensagem.getMensagemInternet(), Toast.LENGTH_LONG).show();
                System.out.println("sem internet");
            }
        } catch (Exception e) {
            progressDialog.dismiss();
            System.out.println("erro " + e.toString());
            Toast.makeText(getBaseContext(), Mensagem.getMensagemErroAoSolicitar() , Toast.LENGTH_LONG).show();
        }
    }

    private void criarListView(){
        vistoriaAdapter = new VistoriaAdapter(this, vistorias);
        //listView.setAdapter();
        setListAdapter(vistoriaAdapter);
        progressDialog.dismiss();
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {

        VistoriaPOJO vistoria = (VistoriaPOJO) l.getAdapter().getItem(position);

        startActivity(new Intent(getBaseContext(), DadosVistoriaActivity.class)
                .putExtra("dados_vistoria", vistoria));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vistorias, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
