package br.edu.ufra.appfiscalizacao.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.adapter.EquipamentoAdapter;
import br.edu.ufra.appfiscalizacao.adapter.InspecaoAdapter;
import br.edu.ufra.appfiscalizacao.adapter.TecnicoAdapter;
import br.edu.ufra.appfiscalizacao.adapter.VistoriaAdapter;
import br.edu.ufra.appfiscalizacao.application.pojo.EquipamentoPOJO;
import br.edu.ufra.appfiscalizacao.application.pojo.EstabelecimentoPOJO;
import br.edu.ufra.appfiscalizacao.application.pojo.InspecaoPOJO;
import br.edu.ufra.appfiscalizacao.application.pojo.TecnicoPOJO;
import br.edu.ufra.appfiscalizacao.application.pojo.VistoriaPOJO;
import br.edu.ufra.appfiscalizacao.application.pojo.conversor.InspecaoConverter;
import br.edu.ufra.appfiscalizacao.application.pojo.conversor.TecnicoConverter;
import br.edu.ufra.appfiscalizacao.application.pojo.conversor.VistoriaConverter;
import br.edu.ufra.appfiscalizacao.application.pojo.lista.InspecaoListaPOJO;
import br.edu.ufra.appfiscalizacao.entidade.Inspecao;
import br.edu.ufra.appfiscalizacao.entidade.Tecnico;
import br.edu.ufra.appfiscalizacao.entidade.Vistoria;
import br.edu.ufra.appfiscalizacao.rn.InspecaoRN;
import br.edu.ufra.appfiscalizacao.rn.TecnicoRN;
import br.edu.ufra.appfiscalizacao.rn.VistoriaRN;
import br.edu.ufra.appfiscalizacao.util.ConexaoInternet;
import br.edu.ufra.appfiscalizacao.util.Mensagem;
import br.edu.ufra.appfiscalizacao.util.StringURL;

public class DetalhesVistoriaActivity  extends AppCompatActivity {
    private Spinner sp_dilog_tecnicos;
    private ProgressDialog progressDialog;
    private TabHost abas;
    private Mensagem mensagemServidor;
    private List<EquipamentoPOJO> equipamentos, equipamentosObrigatoriosSpinner, demaisEquipamentosSpinner;
    private List<InspecaoPOJO> inspecoes, demaisEquipInspecionados, equipObrigatoriosInspecionados, inspecoesWS, inspecoesVistoriaWS;
    private List<TecnicoPOJO> tecnicosPOJO;
    private Context contexto;
    private Gson gson;
    private GsonBuilder builder;
    private String urlEquipamentos = StringURL.getUrlEquipamento()+"all";
    private String urlTecnicos = StringURL.getUrlTecnico()+"all";
    private String urlSalvarVistoria= StringURL.getUrlVistoria()+"salvar";
    private String urlSalvarInspecaoListaPOJO = StringURL.getUrlInspecao()+"salvar";
    private String mensagemInternet = Mensagem.getMensagemInternet();
    private TecnicoPOJO tecnicoWSPOJO, tecnico2POJO;
    private Tecnico tecnico1, tecnico2;
    private VistoriaPOJO vistoria;
    private EstabelecimentoPOJO estabelecimento;
    private EquipamentoPOJO equipamento, demaisEquipamentoSpinner, equipamentoObrigatorioSpinner;
    private InspecaoPOJO inspecao, inspecaoWS;
    private TextView dataSolicitacaoTxt;
    private EditText prazoEdt,  observacaoEdt, demaisEquipamentoObsEdt,equipamentoObrigatorioObsEdt;
    private RadioButton demaisEquipamentoAptRadio, demaisEquipamentoNaoAptRadio, equipamentoObrigatorioAptRadio, equipamentoObrigatorioNaoAptRadio;
    private Button concluirBtn, inspecaoDemaisEquipamentosBtn, inspecaoEquipamentosObrigatoriosBtn;
    private DateFormat sdf ;
    private EquipamentoAdapter equipamento_obg_spinner_adapter, demais_equipamento_spinner_adapter;
    private InspecaoAdapter inspecoesDemaisEquipAdapter, inspecoesEquipObrigatoriosAdapter;
    private ListView lvInspecaoDemaisEquip, lvInspecaoEquipObrigatorios, lvVistorias;
    private RequestQueue requestQueue;
    private VistoriaRN rnVistoria;
    private InspecaoRN rnInspecao;
    private TecnicoRN rnTecnico;
    private InspecaoListaPOJO inspecaoListaPOJO;
    private  TecnicoAdapter tecnicoAdapter;
    private EditText edt_observacao;
    //private static final String PREFERENCE_NAME="LoginActivityPreferences";
    private int selecionado;
    private boolean apto;
    private TextView dataVistoriaTxt;
    private String date;
    private VistoriaAdapter vistoriaAdapter;
    private VistoriaPOJO vistoriaWS;
    private List<VistoriaPOJO> vistoriasWS;
    private Integer id;
    AlertDialog alertDialog;
    Context appContext;
    FloatingActionButton fbVistoriar;
    private RadioButton rb_apto, rb_Inapto;
    private EditText edt_observacao_inspecao, edt_observacao_vistoria, edt_prazo_vistoria, edt_matricula_tec2;
    private ListView lv_tecnicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_vistoria);
        fbVistoriar = (FloatingActionButton) findViewById(R.id.float_vistoriar);



        //lvInspecaoDemaisEquip = (ListView) findViewById(R.id.listVDemaisEquipamentosInspecionados);
        //lvInspecaoEquipObrigatorios = (ListView) findViewById(R.id.listVEquipamentosObrigatoriosInspecionados);
        //SharedPreferences sharedP = getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);


        progressDialog = ProgressDialog.show(this, "Aguarde", "...", false, true);

        sdf = new SimpleDateFormat("dd/MM/yyyy");

        estabelecimento = (EstabelecimentoPOJO) getIntent().getSerializableExtra("estabelecimento");
        id = estabelecimento.getId();

        requestQueue = Volley.newRequestQueue(this.getApplicationContext());

        //obterVistoriasEstabelecimento(id);

        contexto = this;
        rnVistoria = new VistoriaRN(getApplicationContext());
        rnInspecao = new InspecaoRN(getApplicationContext());
        rnTecnico = new TecnicoRN(getApplicationContext());
        inspecoes = new ArrayList<>();
        inspecaoListaPOJO = new InspecaoListaPOJO();

        vistoria = new VistoriaPOJO();
        tecnico2POJO = new TecnicoPOJO();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TabVistoria
        // dataSolicitacaoTxt = (TextView) findViewById(R.id.dataSolicitacao);
        //prazoEdt = (EditText) findViewById(R.id.prazo);
        //observacaoEdt = (EditText) findViewById(R.id.observacao);
        //concluirBtn = (Button) findViewById(R.id.btnConcluirVistoria);

        //Tab2 EquipamentosObrigatorios

        //equipamentoObrigatorioObsEdt = (EditText) findViewById(R.id.txtObsEquipamentoObrigatorio);
        //equipamentoObrigatorioAptRadio = (RadioButton) findViewById(R.id.radioBtnEquipamentoObrigatorioApt);
        //equipamentoObrigatorioNaoAptRadio = (RadioButton) findViewById(R.id.radioBtnEquipamentoObrigatorioNaoApt);
        //inspecaoEquipamentosObrigatoriosBtn = (Button) findViewById(R.id.btnInspecaoEquipamentoObrigatorio);

        //Tab3 Demais Equipamento

        //demaisEquipamentoObsEdt = (EditText) findViewById(R.id.txtObsDemaisEquipamento);
        //demaisEquipamentoAptRadio = (RadioButton) findViewById(R.id.radioBtnDemaisEquipamentoApt);
        //demaisEquipamentoNaoAptRadio = (RadioButton) findViewById(R.id.radioBtnDemaisEquipamentoNaoApt);
        //inspecaoDemaisEquipamentosBtn = (Button) findViewById(R.id.btnInspecaoDemaisEquipamento);



                                    /*
                                    System.out.println("Data: "+sdf.format(estabelecimento.getDataCadastro()));
                                    dataSolicitacaoTxt.setText(sdf.format(estabelecimento.getDataCadastro()));
                                    */
                                    /*
                                    int idTec1= sharedP.getInt("idTec1",0);
                                    int idTec2= sharedP.getInt("idTec2",0);
                                    tecnico1.setId(idTec1);
                                    tecnico2.setId(idTec2);
                                    */

        tecnico1 = rnTecnico.obterTodos().get(0);
        //tecnico2 = rnTecnico.obterTodos().get(1);

        //spinnerDemaisEquip = (Spinner) findViewById(R.id.spinnerDemaisEquip);
        //spinnerEquipObrigatorios = (Spinner) findViewById(R.id.spinnerEquipObrigatorios);



        //TabHost3Colunas
        TabHost.TabSpec descritor;
        abas = (TabHost) findViewById(R.id.tabhostestab);
        abas.setup();

        /*descritor = abas.newTabSpec("aba1");
        descritor.setContent(R.id.vistorias);
        descritor.setIndicator("Vistorias");
        abas.setCurrentTab(0);
        abas.addTab(descritor);
*/


        descritor = abas.newTabSpec("aba2");
        descritor.setContent(R.id.equipamentosObrigatorios);
        descritor.setIndicator("Obrigatórios");
        abas.setCurrentTab(1);

        abas.addTab(descritor);

        descritor = abas.newTabSpec("aba3");
        descritor.setContent(R.id.demaisequipamentos);
        descritor.setIndicator("Demais");
        abas.addTab(descritor);





        builder = new GsonBuilder();
        //converte data(pois esta em formato DATE)
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

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

        obterEquipamentosWS();
        obterTecnicosWS();

                                    /*
                                    CONCLUIR VISTORIA
                                    concluirBtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            realizarVistoria();
                                        }
                                    });
                            */

        if (savedInstanceState != null) {
            selecionado = savedInstanceState.getInt("selecionado");
            apto = savedInstanceState.getBoolean("apto");

            equipamento_obg_spinner_adapter.alterarBackground(apto, selecionado);
        }

        fbVistoriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogRealizarVistoria();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onresume");
        // criarLVDemaisEquipamentosInspecionados();
        //criarLVEquipamentosObrigatoriosInspecionados();




    }

    private void obterVistoriasEstabelecimento(Integer id){
        try {


            if (ConexaoInternet.estaConectado(getBaseContext()) == true) {
                System.out.println("conectado");
                gson = new Gson();
                String urlVistoriasEstabelecimento = StringURL.getUrlVistoria()+id;

                System.out.println(urlVistoriasEstabelecimento);
                JsonArrayRequest request = new JsonArrayRequest(urlVistoriasEstabelecimento, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            System.out.println("resposta" + response.isNull(0));
                            if (response.length() != 0){
                                int i;
                                vistoriasWS = new ArrayList<>();
                                for (i = 0; i < response.length(); i++) {
                                    try {
                                        System.out.println("objects server "+response.length());
                                        JSONObject vistoriaItem = response.getJSONObject(i);
                                        vistoriaWS = gson.fromJson(vistoriaItem.toString(), VistoriaPOJO.class);
                                        System.out.println("vistoria" + vistoriaWS.getDataVistoria());
                                        vistoriasWS.add(vistoriaWS);
                                    } catch (JSONException e) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getBaseContext(), Mensagem.getMensagemErroAoObter(), Toast.LENGTH_LONG).show();

                                        e.printStackTrace();
                                    }
                                }

                                //criarLVVistoria();

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


    /*private void criarLVVistoria(){
        lvVistorias = (ListView) findViewById(R.id.listVVistorias);
        vistoriaAdapter = new VistoriaAdapter(this, vistoriasWS);
        lvVistorias.setAdapter(vistoriaAdapter);
        lvVistorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VistoriaPOJO vistoria = (VistoriaPOJO) vistoriaAdapter.getItem(position);
                //dialogVistoria(vistoria.getId());
                Intent it = new Intent(contexto, DadosVistoriaActivity.class);
                it.putExtra("dados_vistoria", vistoria);
                startActivity(it);

            }
        });
        progressDialog.dismiss();
    }
*/
    private void dialogVistoria(final Integer id){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Operações");
        alerta.setMessage("Escolha a operação que deseja realizar para essa vistoria");

        alerta.setPositiveButton("Excluir",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        alerta.setNegativeButton("Editar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        setarDadosVistoria(id);
                    }
                });

        alerta.show();
    }


    /*
        private void criarLVEquipamentosObrigatoriosInspecionados(){
            equipObrigatoriosInspecionados = new ArrayList<>();
            inspecaoEquipamentosObrigatoriosBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    equipObrigatoriosInspecionados.add(realizarInspecaoEquipamentosObrigatorios());
                    inspecoesEquipObrigatoriosAdapter.notifyDataSetChanged();
                }
            });
           // lvInspecaoEquipObrigatorios = (ListView) findViewById(R.id.listVEquipamentosObrigatoriosInspecionados);
            inspecoesEquipObrigatoriosAdapter = new InspecaoAdapter(contexto, equipObrigatoriosInspecionados);
            lvInspecaoEquipObrigatorios.setAdapter(inspecoesEquipObrigatoriosAdapter);
            lvInspecaoEquipObrigatorios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    InspecaoPOJO inspecao = (InspecaoPOJO) inspecoesDemaisEquipAdapter.getItem(position);
                    System.out.println("clicou");
                }
            });
        }
    */
    private List<InspecaoPOJO> obterInspecoesPorVistoria(Integer idVistoria){
        try {


            if (ConexaoInternet.estaConectado(getBaseContext()) == true) {
                gson = new Gson();
                String urlInspecoesPorVistoria = StringURL.getUrlInspecao()+"inspecoesPorVistoria?idv="+idVistoria;

                System.out.println(urlInspecoesPorVistoria);

                JsonArrayRequest request = new JsonArrayRequest(urlInspecoesPorVistoria, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            System.out.println("resposta" + response.isNull(0));
                            if (response.length() != 0){
                                int i;
                                inspecoesWS = new ArrayList<>();
                                for (i = 0; i < response.length(); i++) {
                                    try {
                                        System.out.println("objects server "+response.length());
                                        JSONObject inspecaoItem = response.getJSONObject(i);
                                        inspecaoWS = gson.fromJson(inspecaoItem.toString(), InspecaoPOJO.class);
                                        System.out.println("inspecao" + inspecaoWS.getEquipamentoPOJO());
                                        inspecoesWS.add(inspecaoWS);
                                    } catch (JSONException e) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getBaseContext(), Mensagem.getMensagemErroAoObter(), Toast.LENGTH_LONG).show();

                                        e.printStackTrace();
                                    }
                                }
                                tipoEquipamentoInspecionadoWS(inspecoesWS);
                                inspecoesDemaisEquipAdapter.notifyDataSetChanged();

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

        return inspecoesWS;
    }

    private void obterTecnicosWS(){
        try {
            //verifica conexao com internet
            if (ConexaoInternet.estaConectado(contexto)) {

                //Recebe a lista de equipamentos do servidor
                JsonArrayRequest request = new JsonArrayRequest(urlTecnicos, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int i;
                        tecnicosPOJO = new ArrayList<>();
                        for (i = 0; i < response.length(); i++) {
                            try {

                                JSONObject tecnicoItem = response.getJSONObject(i);
                                tecnicoWSPOJO = gson.fromJson(tecnicoItem.toString(), TecnicoPOJO.class);
                                System.out.println("tec " + tecnicoWSPOJO.getNome());
                                tecnicosPOJO.add(tecnicoWSPOJO);
                            } catch (JSONException e) {
                                Toast.makeText(contexto, "Erro ao obter dados do servidor !", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(contexto, "Erro ao tentar obter dados do servidor !", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

                requestQueue.add(request);
                // VolleyApplication.getsInstance().getmRequestQueue().add(request);
            } else {

                Toast.makeText(contexto, mensagemInternet, Toast.LENGTH_LONG).show();
                System.out.println("sem internet" + mensagemInternet);
            }
        } catch (Exception e) {

            System.out.println("erro " + e.getCause());
            Toast.makeText(contexto, "Erro ao solicitar dados do servidor " + e.getCause(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void obterEquipamentosWS(){
        try {
            //verifica conexao com internet
            if (ConexaoInternet.estaConectado(contexto)) {
                System.out.println("conectado");

                //Recebe a lista de equipamentos do servidor
                JsonArrayRequest request = new JsonArrayRequest(urlEquipamentos, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int i;
                        equipamentos = new ArrayList<>();
                        for (i = 0; i < response.length(); i++) {
                            try {
                                System.out.println("quantidade equip: "+response.length());
                                JSONObject equipamentoItem = response.getJSONObject(i);
                                equipamento = gson.fromJson(equipamentoItem.toString(), EquipamentoPOJO.class);
                                System.out.println("Equipamento: "+equipamento.getNome());
                                equipamentos.add(equipamento);
                            } catch (JSONException e) {
                                System.out.println("Erro em response " + e.getCause());
                            }
                        }
                        definirSpinnerParaEquipamento();
                        spinnerEquipamentosObrigatorios();
                        spinnerDemaisEquipamentos();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(contexto, "Erro ao tentar obter dados do servidor:", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

                requestQueue.add(request);
                // VolleyApplication.getsInstance().getmRequestQueue().add(request);
            } else {

                Toast.makeText(contexto, mensagemInternet, Toast.LENGTH_LONG).show();
                System.out.println("sem internet" + mensagemInternet);
            }
        } catch (Exception e) {

            System.out.println("erro " + e.getCause());
            Toast.makeText(contexto, "Erro ao solicitar dados: " + e.getCause(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void salvarInspecaoListaPOJOBD(){
        try {
            Toast.makeText(getBaseContext(), "Tentando salvar os dados no banco de dados no celular.",
                    Toast.LENGTH_LONG).show();
            // progressDialog = ProgressDialog.show(getApplicationContext(),"Salvando dados no celular","aguarde...");
            Vistoria vistoriaEntidade = VistoriaConverter.fromVistoriaPOJO(vistoria);
            List<Inspecao> inspecoes = InspecaoConverter.fromInspecoesPOJO(inspecaoListaPOJO.getInspecoesPOJO());
            if (rnVistoria.salvarVistoriaEInspecoes(vistoriaEntidade, inspecoes)){
                //   progressDialog.dismiss();
                Toast.makeText(getBaseContext(), "Sucesso, vistoria  salva no banco de dados do celular. "+
                                "Conecte-se a internet para sincronizar os dados.",
                        Toast.LENGTH_LONG).show();
                finish();
            }else {
                Toast.makeText(getBaseContext(), "Erro ao salvar a vistoria no banco de dados do celular!", Toast.LENGTH_SHORT).show();
                finish();

            }
        } catch (Exception e){
            System.out.println("exception "+e.toString());
            Toast.makeText(getBaseContext(), "Exception: "+e.toString(),
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void spinnerDemaisEquipamentos(){

        ListView list_equip_NAOobrigatorios = (ListView) findViewById(R.id.listV_equipamentos_NAOobrigatorios);
        demais_equipamento_spinner_adapter = new EquipamentoAdapter(contexto, demaisEquipamentosSpinner);

        list_equip_NAOobrigatorios.setAdapter(demais_equipamento_spinner_adapter);
        progressDialog.dismiss();
        list_equip_NAOobrigatorios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                demaisEquipamentoSpinner = (EquipamentoPOJO) adapterView.getAdapter().getItem(i);
                System.out.println("equip não obrigatorio selecionado" + demaisEquipamentoSpinner.getNome());
                String nomeEquipamentoInspecionado = demaisEquipamentoSpinner.getNome();
                mostrarDialogEquipamentosNAOObrigatorios(nomeEquipamentoInspecionado, i);


            }
        });



        //  final ArrayAdapter<String> spinneradapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, equipamentosNome);
        // spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                                   /* spinnerDemaisEquip.setAdapter(new EquipamentoAdapter(contexto, demaisEquipamentosSpinner));

                                    spinnerDemaisEquip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            demaisEquipamentoSpinner = (EquipamentoPOJO) parent.getAdapter().getItem(position);
                                            System.out.println("demais equip selected" + demaisEquipamentoSpinner.getNome());

                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });

                            */

    }

    private void spinnerEquipamentosObrigatorios(){


        ListView list_equip_obrigatorios = (ListView) findViewById(R.id.listV_equipamentos_obrigatorios);
        equipamento_obg_spinner_adapter = new EquipamentoAdapter(contexto, equipamentosObrigatoriosSpinner);

        list_equip_obrigatorios.setAdapter(equipamento_obg_spinner_adapter);
        progressDialog.dismiss();
        list_equip_obrigatorios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                equipamentoObrigatorioSpinner = (EquipamentoPOJO) adapterView.getAdapter().getItem(i);
                System.out.println("equip obrigatorio selected" + equipamentoObrigatorioSpinner.getNome());
                String nomeEquipamentoInspecionado = equipamentoObrigatorioSpinner.getNome();
                mostrarDialogEquipamentosObrigatorios(nomeEquipamentoInspecionado, i);
            }
        });
                                      /*spinnerEquipObrigatorios.setAdapter(new EquipamentoAdapter(contexto, equipamentosObrigatoriosSpinner));

                                    spinnerEquipObrigatorios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            equipamentoObrigatorioSpinner = (EquipamentoPOJO) parent.getAdapter().getItem(position);
                                            System.out.println("equip obrigatorio selected" + equipamentoObrigatorioSpinner.getNome());

                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                    */


    }



    private void mostrarDialogEquipamentosObrigatorios(String nomeEquipamanetoInpecionado, final int position){


        AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
        alert.setTitle("Inspeção de equipamento"); //Set Alert dialog title here

        LayoutInflater li = getLayoutInflater();

        View view = li.inflate(R.layout.alertdialog_equip_model, null);
        TextView nomeEquipamentoInspecao = (TextView) view.findViewById(R.id.nome_equipamento_model);
        nomeEquipamentoInspecao.setText(nomeEquipamanetoInpecionado);
        //equipObrigatoriosInspecionados = new ArrayList<>();
        rb_apto = (RadioButton) view.findViewById(R.id.rb_model_apto);
        rb_Inapto = (RadioButton) view.findViewById(R.id.rb_model_inapto);
        edt_observacao = (EditText) view.findViewById(R.id.edit_model_oberservacao);

        alert.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                realizarInspecaoEquipamentosObrigatorios(position);
                definirEquipamentoObrigatorioApto();

            }
        });
        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
                                    /* view.findViewById(R.id.btn_model_salvar).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {


                                            alertDialog.dismiss();
                                        }
                                    });

                                    */

        alert.setView(view);

        alertDialog = alert.create();
        alert.show();


    }
    private void mostrarDialogEquipamentosNAOObrigatorios(String nomeEquipamanetoInpecionado, final int selecionado){


        AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
        alert.setTitle("Inspeção de equipamento"); //Set Alert dialog title here

        LayoutInflater li = getLayoutInflater();

        View view = li.inflate(R.layout.alertdialog_equip_model, null);
        TextView nomeEquipamentoInspecao = (TextView) view.findViewById(R.id.nome_equipamento_model);
        nomeEquipamentoInspecao.setText(nomeEquipamanetoInpecionado);
        //equipObrigatoriosInspecionados = new ArrayList<>();
        rb_apto = (RadioButton) view.findViewById(R.id.rb_model_apto);
        rb_Inapto = (RadioButton) view.findViewById(R.id.rb_model_inapto);
        edt_observacao = (EditText) view.findViewById(R.id.edit_model_oberservacao);

        alert.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                realizarInspecaoDemaisEquipamentos(selecionado);
                definirDemaisEquipamentoApto();

            }
        });
        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
                                    /* view.findViewById(R.id.btn_model_salvar).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {


                                            alertDialog.dismiss();
                                        }
                                    });

                                    */

        alert.setView(view);

        alertDialog = alert.create();
        alert.show();


    }





    //System.out.println("status antes "+estabelecimento.getStatus());

    private void mostrarDialogRealizarVistoria(){


        AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
        alert.setTitle("Vistoria do estabelecimento"); //Set Alert dialog title here

        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.alertdialog_vistoria, null);


        tecnicoAdapter = new TecnicoAdapter(contexto, tecnicosPOJO);

        edt_observacao_vistoria = (EditText) view.findViewById(R.id.edit_dialog_vistoria_obs);
        edt_prazo_vistoria = (EditText) view.findViewById(R.id.edit_dialog_vistoria_prazo);
        //lv_tecnicos = (ListView) view.findViewById(R.id.lv_tecnico_dialog_vistoria);
        sp_dilog_tecnicos = (Spinner) view.findViewById(R.id.spinner_dialog_tec_vistoria);

        if (estabelecimento.getStatus() == "Regular") {
            edt_prazo_vistoria.setVisibility(View.INVISIBLE);
        }


        //lv_tecnicos.setAdapter(tecnicoAdapter);


        sp_dilog_tecnicos.setAdapter(tecnicoAdapter);
        sp_dilog_tecnicos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tecnico2POJO = (TecnicoPOJO) tecnicoAdapter.getItem(position);

                //tecnicoAdapter.alterarCorElemento(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //edt_matricula_tec2 =(EditText) findViewById(R.id.edit_dialogVistoria_matTec2);

        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            realizarVistoria();

            }
        });



        alert.setView(view);
        alert.show();


    }


    private void realizarVistoria(){
        vistoria.setTecnicoPOJO1(TecnicoConverter.toTecnicoPOJO(tecnico1));
        System.out.println("tec 2" + tecnico2POJO.getNome());
        vistoria.setTecnicoPOJO2(tecnico2POJO);
        vistoria.setDataVistoria(obterDataHoje());
        vistoria.setApto(definirStatusEstabelecimentoEVistoriaApt(inspecoes));
        vistoria.setEstabelecimentoPOJO(estabelecimento);
        vistoria.setPrazo(Integer.parseInt(edt_prazo_vistoria.getText().toString()));
        vistoria.setObservacao(edt_observacao_vistoria.getText().toString());
        inspecaoListaPOJO.setInspecoesPOJO(inspecoes);

        startActivity( new Intent(this, DadosVistoriaActivity.class).putExtra("confirmar_detalhes_vistoria", inspecaoListaPOJO));

    /*
                if (ConexaoInternet.estaConectado(contexto) ){
                    salvarInspecaoListaPOJOWS();
                }else {
                    salvarInspecaoListaPOJOBD();
                }


        if(vistoria.getEstabelecimentoPOJO().getStatus() == "Pendente") {
            mostrarDialogRealizarVistoria();

        }
    */


    }

    private InspecaoPOJO realizarInspecaoDemaisEquipamentos(int selecionado){
        inspecao = new InspecaoPOJO();
        inspecao.setVistoriaPOJO(vistoria);
        inspecao.setEquipamentoPOJO(demaisEquipamentoSpinner);
        inspecao.setDataInspPOJO(obterDataHoje());
        inspecao.setAptoPOJO(definirDemaisEquipamentoApto());
        inspecao.setObservacaoPOJO(edt_observacao.getText().toString());
        inspecoes.add(inspecao);

        this.selecionado = selecionado;
        this.apto = inspecao.isAptoPOJO();

        equipamento_obg_spinner_adapter.alterarBackground(inspecao.isAptoPOJO(), selecionado);
        return inspecao;
    }

    private InspecaoPOJO realizarInspecaoEquipamentosObrigatorios(int position){

        inspecao = new InspecaoPOJO();
        inspecao.setVistoriaPOJO(vistoria);
        inspecao.setEquipamentoPOJO(equipamentoObrigatorioSpinner);
        inspecao.setDataInspPOJO(obterDataHoje());
        inspecao.setAptoPOJO(definirEquipamentoObrigatorioApto());
        inspecao.setObservacaoPOJO(edt_observacao.getText().toString());
        inspecoes.add(inspecao);
        equipamento_obg_spinner_adapter.alterarBackground(inspecao.isAptoPOJO(), position);

        return inspecao;
    }

    private boolean definirDemaisEquipamentoApto(){


        if (rb_apto.isChecked()) {
            System.out.println("Equipamento Apto");
            return true;
        } else {
            System.out.println("Equipamento inapto");
            return false;
        }
                                    /* if (demaisEquipamentoAptRadio.isChecked()){
                                        System.out.println("demais equipamento apt");
                                        return true;
                                    }else {
                                        System.out.println("demais equipamento nao apt");
                                        return false;
                                    }
                                    */
    }

    private boolean definirEquipamentoObrigatorioApto(){


        if (rb_apto.isChecked()) {
            System.out.println("Equipamento Apto");
            return true;
        } else {
            System.out.println("Equipamento inapto");
            return false;
        }


                                    /*if (equipamentoObrigatorioAptRadio.isChecked()){
                                        System.out.println("Equipamento obrigatorio apt");
                                        return true;
                                    }else {
                                        System.out.println("Equipamento obrigatorio nao apt");
                                        return false;
                                    }*/
    }

    private void definirSpinnerParaEquipamento(){
        equipamentosObrigatoriosSpinner = new ArrayList<>();
        demaisEquipamentosSpinner = new ArrayList<>();

        for(EquipamentoPOJO e : equipamentos){
            if (e.getStatus().equals("Obrigatorio")){
                System.out.println("obrigatorio " + e.getNome());
                equipamentosObrigatoriosSpinner.add(e);
            } else if (e.getStatus().equals("Não obrigatorio")){
                System.out.println("Nao obrigatorio "+e.getNome());
                demaisEquipamentosSpinner.add(e);
            }
        }
    }

    private boolean definirStatusEstabelecimentoEVistoriaApt(List<InspecaoPOJO> inspecoesRealizadas){
        Inspecao inspecaoRealizada;
        Iterator<InspecaoPOJO> iterator = inspecoesRealizadas.iterator();
        int contInspecaoApt = 0;
        while (iterator.hasNext() && iterator.next().isAptoPOJO() == true){
            System.out.println("aa");
            contInspecaoApt ++;
        }
        System.out.println("context inspecao apt: "+contInspecaoApt);
        if (contInspecaoApt == inspecoesRealizadas.size()){
            estabelecimento.setStatus("Regular");
            return true;
        }else{
            estabelecimento.setStatus("Pendente");
            return false;
        }
    }

    private void setarDadosVistoria(Integer id){
        progressDialog.getProgress();
        obterInspecoesPorVistoria(id);




    }

    private Long obterDataHoje(){
        Date data = Calendar.getInstance().getTime();
        System.out.println("Data de hoje:" + data);
        Long dataLong = data.getTime();
        return dataLong;
    }

    private void tipoEquipamentoInspecionadoWS(List<InspecaoPOJO> inspecoesWS){
        System.out.println("inspecoes"+inspecoesWS.size());

        for (InspecaoPOJO i : inspecoesWS){
            if (i.getEquipamentoPOJO().getStatus().equals("Obrigatorio")){
                equipObrigatoriosInspecionados.add(i);
            } else if (i.getEquipamentoPOJO().getStatus().equals("Não obrigatorio")){
                demaisEquipInspecionados.add(i);
            }
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_estabelecimento_detalhes, menu);
        return true;
    }

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
        }else if (id == R.id.action_barInspecao){
            Intent it = new Intent(contexto, VistoriasActivity.class);
            it.putExtra("estabelecimento", estabelecimento);
            startActivity(it);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selecionado", this.selecionado);
        outState.putBoolean("apto",this.apto);
    }
}