package br.edu.ufra.appfiscalizacao.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.adapter.EquipamentoAdapter;
import br.edu.ufra.appfiscalizacao.application.pojo.EquipamentoPOJO;
import br.edu.ufra.appfiscalizacao.application.pojo.InspecaoPOJO;
import br.edu.ufra.appfiscalizacao.application.pojo.VistoriaPOJO;
import br.edu.ufra.appfiscalizacao.application.pojo.lista.InspecaoListaPOJO;
import br.edu.ufra.appfiscalizacao.util.Mensagem;
import br.edu.ufra.appfiscalizacao.util.StringURL;

public class DadosVistoriaActivity extends AppCompatActivity {

    private VistoriaPOJO vistoria;
    private InspecaoListaPOJO inspecaoListaPOJO;
    private ListView lv_historico_equipamentos_obrigatorios, lv_historico_equipamentos_nao_obrigatorios;
    private TextView historico_nomeEstabelecimento, historico_dataVistoria, historico_vistoria_tecnico1, historico_vistoria_tecnico2, historico_vistoria_prazo, historico_vistoria_observacao;
    private DateFormat sdf;
    private Button btn_confirmar;
    private ProgressDialog progressDialog;
    private Gson gson;
    private String urlSalvarInspecaoListaPOJO = StringURL.getUrlInspecao()+"salvar";
    private Mensagem mensagemServidor;
    private  Context contexto;
    private RequestQueue requestQueue;
    private List<EquipamentoPOJO> equipamentos_nao_obg, equipamentos_obg;
    private EquipamentoAdapter equipamento_obg_adapter, equipamento_nao_obg_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_vistoria);

        contexto =this;
        gson = new Gson();

        requestQueue = Volley.newRequestQueue(this.getApplicationContext());


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sdf= new SimpleDateFormat("dd/MM/yyyy");

        inspecaoListaPOJO = (InspecaoListaPOJO) getIntent().getSerializableExtra("confirmar_detalhes_vistoria");

        if (inspecaoListaPOJO != null){
            vistoria = inspecaoListaPOJO.getInspecoesPOJO().get(0).getVistoriaPOJO();
        }else {
        vistoria = (VistoriaPOJO) getIntent().getSerializableExtra("dados_vistoria");
        }

        if ( vistoria.getId() != null){
        btn_confirmar = (Button) findViewById(R.id.btn_confirmar_vistoria);
            btn_confirmar.setVisibility(View.INVISIBLE);
        }


        lv_historico_equipamentos_obrigatorios = (ListView) findViewById(R.id.list_historico_vistoria_equipObrigatorio);
        lv_historico_equipamentos_nao_obrigatorios = (ListView) findViewById(R.id.list_historico_vistoria_equipNAOObrigatorio);
        historico_nomeEstabelecimento = (TextView) findViewById(R.id.txt_historico_nomeEstabelecimento);
        historico_dataVistoria = (TextView) findViewById(R.id.txt_historico_dataVistoria);
        historico_vistoria_tecnico1 = (TextView) findViewById(R.id.txt_historico_vistoria_tecnico1);
        historico_vistoria_tecnico2 = (TextView) findViewById(R.id.txt_historico_vistoria_tecnico2);
        historico_vistoria_prazo = (TextView) findViewById(R.id.txt_historico_vistoria_prazo);
        historico_vistoria_observacao = (TextView) findViewById(R.id.txt_historico_vistoria_observacao);
        btn_confirmar = (Button) findViewById(R.id.btn_confirmar_vistoria);

        if(vistoria.getTecnicoPOJO1() == null) {
            historico_vistoria_tecnico1.setText("TECNICO 1 ausente");
        }else {

            historico_vistoria_tecnico1.setText(vistoria.getTecnicoPOJO1().getNome());
        }
        if(vistoria.getTecnicoPOJO2() == null) {
            historico_vistoria_tecnico2.setText("TECNICO 2 ausente");
        }else {

            historico_vistoria_tecnico2.setText(vistoria.getTecnicoPOJO2().getNome());
        }


        historico_nomeEstabelecimento.setText(vistoria.getEstabelecimentoPOJO().getNomeFantasia());
        historico_dataVistoria.setText(sdf.format(vistoria.getDataVistoria()));
        historico_vistoria_tecnico1.setText(vistoria.getTecnicoPOJO1().getNome());
        historico_vistoria_tecnico2.setText(vistoria.getTecnicoPOJO2().getNome());
        historico_vistoria_prazo.setText(String.valueOf(vistoria.getPrazo()));
        historico_vistoria_observacao.setText(vistoria.getObservacao());


        definirTipoEquipamento();

        btn_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarInspecaoListaPOJOWS();
            }
        });


    }

    public void definirTipoEquipamento(){

        equipamentos_obg = new ArrayList<>();
        equipamentos_nao_obg = new ArrayList<>();



        for(InspecaoPOJO i : inspecaoListaPOJO.getInspecoesPOJO()){
            if (i.getEquipamentoPOJO().getStatus().equals("Obrigatorio")){

                equipamentos_obg.add(i.getEquipamentoPOJO());

            }else if (i.getEquipamentoPOJO().getStatus().equals("Não obrigatorio")){

                equipamentos_nao_obg.add(i.getEquipamentoPOJO());

            }


        }

        criarLVEquipamentosObgInspecionados();
        criarLVEquipamentosNaoObgInspecionados();

    }

    public void criarLVEquipamentosObgInspecionados(){
        equipamento_obg_adapter = new EquipamentoAdapter(contexto, equipamentos_obg);
        lv_historico_equipamentos_obrigatorios.setAdapter(equipamento_obg_adapter);


    }

    public void criarLVEquipamentosNaoObgInspecionados(){
    equipamento_nao_obg_adapter = new EquipamentoAdapter(contexto, equipamentos_nao_obg);
        lv_historico_equipamentos_nao_obrigatorios.setAdapter(equipamento_nao_obg_adapter);

    }

    private void salvarInspecaoListaPOJOWS(){

        try{
            progressDialog = ProgressDialog.show(this, "Salvando os Dados no Servidor", "Aguarde...");

            String converteToJson=gson.toJson(inspecaoListaPOJO);
            System.out.println("json "+converteToJson);
            JSONObject convertingToJsonObject= new JSONObject(converteToJson);

            System.out.println("object:"+convertingToJsonObject);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    urlSalvarInspecaoListaPOJO,
                    convertingToJsonObject ,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            progressDialog.dismiss();
                            System.out.println("mgs json "+response);
                            mensagemServidor =gson.fromJson(response.toString(), Mensagem.class);
                            System.out.println("resposta: "+mensagemServidor.getMensagemServToClient());
                            Toast.makeText(contexto, mensagemServidor.getMensagemServToClient(), Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(getBaseContext(),"Erro ao enviar os dados para o servidor: "+error,Toast.LENGTH_LONG).show();
                }
            });

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(2000,3,2));

            requestQueue.add(jsonObjectRequest);
        }catch (JSONException e){
            progressDialog.dismiss();
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }

    }


    /*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vistoria, menu);
        return true;
    }
    */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
