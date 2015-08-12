package br.edu.ufra.appfiscalizacao.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.melnykov.fab.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.adapter.EquipamentosSpinnerAdapter;
import br.edu.ufra.appfiscalizacao.application.StringURL;
import br.edu.ufra.appfiscalizacao.application.VolleyApplication;
import br.edu.ufra.appfiscalizacao.entidade.Equipamento;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;
import br.edu.ufra.appfiscalizacao.entidade.Inspecao;
import br.edu.ufra.appfiscalizacao.entidade.Tecnico;
import br.edu.ufra.appfiscalizacao.entidade.Vistoria;
import br.edu.ufra.appfiscalizacao.util.ConexaoInternet;
import br.edu.ufra.appfiscalizacao.util.Mensagem;

public class DetalhesVistoriaActivity extends ActionBarActivity {
    private Spinner spinnerDemaisEquip, spinnerEquipObrigatorios;
    private ProgressDialog progressD;
    private TabHost abas;
    private Mensagem mensagemServidor;
    private List<Equipamento> equipamentos, equipamentosObrigatorios,demaisEquipamentos;
    private List<Inspecao> inspecoes;
    private Context contexto;
    private GsonBuilder builder;
    private Gson gson;
    private String urlEquipamentos = StringURL.getUrlEquipamento()+"all";
    private String urlSalvarVistoria= StringURL.getUrlVistoria()+"salvar";
    private String mensagemInternet = Mensagem.getMensagemInternet();
    private Tecnico tecnico1, tecnico2;
    private Vistoria vistoria;
    Estabelecimento estabelecimento;
    private Equipamento equipamento, demaisEquipamentoSpinner, equipamentoObrigatorioSpinner;
    private Inspecao inspecao;
    private TextView dataSolicitacaoTxt;
    private EditText prazoEdt,  observacaoEdt, demaisEquipamentoObsEdt,equipamentoObrigatorioObsEdt;
    private RadioButton vistoriaAptRadio, demaisEquipamentoAptRadio, demaisEquipamentoNaoAptRadio, equipamentoObrigatorioAptRadio, equipamentoObrigatorioNaoAptRadio;
    private Button concluirBtn, inspecaoDemaisEquipamentosBtn, inspecaoEquipamentosObrigatoriosBtn;
    DateFormat sdf ;
    private EquipamentosSpinnerAdapter equipamentoSpAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento_detalhes_vistoria);
        contexto= getBaseContext();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
        inspecoes = new ArrayList<>();
        vistoria = new Vistoria();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        estabelecimento = (Estabelecimento) getIntent().getSerializableExtra("estabelecimento");
        //TabVistoria
        dataSolicitacaoTxt = (TextView) findViewById(R.id.dataSolicitacao);
        prazoEdt = (EditText) findViewById(R.id.prazo);
        observacaoEdt = (EditText) findViewById(R.id.observacao);
        vistoriaAptRadio = (RadioButton) findViewById(R.id.radioVistoriaAp);
        concluirBtn = (Button) findViewById(R.id.btnConcluirVistoria);

        //Tab2 EquipamentosObrigatorios

        equipamentoObrigatorioObsEdt = (EditText) findViewById(R.id.txtObsEquipamentoObrigatorio);
        equipamentoObrigatorioAptRadio = (RadioButton) findViewById(R.id.radioBtnEquipamentoObrigatorioApt);
        equipamentoObrigatorioNaoAptRadio = (RadioButton) findViewById(R.id.radioBtnEquipamentoObrigatorioNaoApt);
        inspecaoEquipamentosObrigatoriosBtn = (Button) findViewById(R.id.btnInspecaoEquipamentoObrigatorio);

        //Tab3 Demais Equipamento

        demaisEquipamentoObsEdt = (EditText) findViewById(R.id.txtObsDemaisEquipamento);
        demaisEquipamentoAptRadio = (RadioButton) findViewById(R.id.radioBtnDemaisEquipamentoApt);
        demaisEquipamentoNaoAptRadio = (RadioButton) findViewById(R.id.radioBtnDemaisEquipamentoNaoApt);
        inspecaoDemaisEquipamentosBtn = (Button) findViewById(R.id.btnInspecaoDemaisEquipamento);

        /*
        System.out.println("Data: "+sdf.format(estabelecimento.getDataCadastro()));
        dataSolicitacaoTxt.setText(sdf.format(estabelecimento.getDataCadastro()));
        */
        tecnico1 = new Tecnico();
        tecnico1.setMatricula("1234");
        tecnico2 = new Tecnico();
        tecnico2.setMatricula("4321");

        spinnerDemaisEquip = (Spinner) findViewById(R.id.spinnerDemaisEquip);
        spinnerEquipObrigatorios = (Spinner) findViewById(R.id.spinnerEquipObrigatorios);


        //FloatingButton
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(getBaseContext(), InspecaoActivity.class);
                startActivity(it);

            }
        });


        //TabHost3Colunas
        TabHost.TabSpec descritor;
        abas = (TabHost) findViewById(R.id.tabhostestab);
        abas.setup();

        descritor = abas.newTabSpec("aba1");
        descritor.setContent(R.id.dadosvistoria);
        descritor.setIndicator("Dados Vistoria");
        abas.setCurrentTab(0);
        abas.addTab(descritor);



        descritor = abas.newTabSpec("aba2");
        descritor.setContent(R.id.equipamentosObrigatorios);
        descritor.setIndicator("Equipamentos Obrigatorios");
        abas.setCurrentTab(1);

        abas.addTab(descritor);

        descritor = abas.newTabSpec("aba3");
        descritor.setContent(R.id.demaisequipamentos);
        descritor.setIndicator("Demais Equipamentos");
        abas.addTab(descritor);

        obterEquipamentosWS();


        concluirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarVistoria();
            }
        });

        inspecaoDemaisEquipamentosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    private void obterEquipamentosWS(){
        try {
            //verifica conexao com internet
            if (ConexaoInternet.estaConectado(contexto)) {
                System.out.println("conectado");
                gson = new Gson();
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
                                equipamento = gson.fromJson(equipamentoItem.toString(), Equipamento.class);
                                System.out.println("Equipamento: "+equipamento.getNome());
                                equipamentos.add(equipamento);
                            } catch (JSONException e) {
                                System.out.println("Erro em response "+e.getCause());
                            }
                        }
                        definirStatusEquipamento();
                        spinnerEquipamentosObrigatorios();
                        spinnerDemaisEquipamentos();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(contexto, "Erro ao tentar obter dados do servidor:" + error.getCause(), Toast.LENGTH_LONG).show();
                    }
                });


                VolleyApplication.getsInstance().getmRequestQueue().add(request);
            } else {

                Toast.makeText(contexto, mensagemInternet, Toast.LENGTH_LONG).show();
                System.out.println("sem internet" + mensagemInternet);
            }
        } catch (Exception e) {

            System.out.println("erro " + e.getCause());
            Toast.makeText(contexto, "Erro ao solicitar dados: " + e.getCause(), Toast.LENGTH_SHORT).show();
        }
    }

    private void salvarVistoriaWS(){

        try{
            progressD=ProgressDialog.show(this,"Salvando os Dados no Servidor","Aguarde...");



            String converteToJson=gson.toJson(vistoria);

            JSONObject convertingToJsonObject= new JSONObject(converteToJson);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, urlSalvarVistoria,convertingToJsonObject , new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    progressD.dismiss();
                    mensagemServidor =gson.fromJson(response.toString(), Mensagem.class);
                    System.out.println("mgs json "+response);
                    System.out.println("resposta: "+mensagemServidor.getMensagemServToClient());
                    Toast.makeText(contexto,mensagemServidor.getMensagemServToClient(),Toast.LENGTH_SHORT).show();
                    finish();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressD.dismiss();
                    Toast.makeText(getBaseContext(),"Erro ao tentar obter dados do servidor: "+error.getMessage(),Toast.LENGTH_LONG).show();
                }
            });

            VolleyApplication.getsInstance().getmRequestQueue().add(jsonObjectRequest);
        }catch (Exception e){
            progressD.dismiss();
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    private void spinnerDemaisEquipamentos(){

      //  final ArrayAdapter<String> spinneradapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, equipamentosNome);
       // spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerDemaisEquip.setAdapter(new EquipamentosSpinnerAdapter(contexto, demaisEquipamentos));

        spinnerDemaisEquip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                demaisEquipamentoSpinner = (Equipamento) parent.getAdapter().getItem(position);
                System.out.println("demais equip selected" + demaisEquipamentoSpinner.getNome());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    private void spinnerEquipamentosObrigatorios(){


        spinnerEquipObrigatorios.setAdapter(new EquipamentosSpinnerAdapter(contexto, equipamentosObrigatorios));

        spinnerEquipObrigatorios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                equipamentoObrigatorioSpinner = (Equipamento) parent.getAdapter().getItem(position);
                System.out.println("equip obrigatorio selected" + equipamentoObrigatorioSpinner.getNome());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    private void realizarVistoria(){




        definirVistoriaApto();
        vistoria.setTecnico1(tecnico1);
        vistoria.setTecnico2(tecnico2);
        vistoria.setEstabelecimento(estabelecimento);
        vistoria.setDataVistoria(obterDataHoje());
        vistoria.setPrazo(Integer.parseInt(prazoEdt.getText().toString()));
        vistoria.setApto(definirVistoriaApto());
        vistoria.setObservacao(observacaoEdt.getText().toString());

        System.out.println("inspecoes "+inspecoes.size());

        vistoria.setInspecaoList(inspecoes);

        if (ConexaoInternet.estaConectado(this) ){
            salvarVistoriaWS();
        }else {
            Toast.makeText(contexto,"Conecte-se a internet",Toast.LENGTH_LONG).show();
   //Salvar no celular
        }



    }

    private void realizarInspecaoDemaisEquipamentos(){
        inspecao = new Inspecao();
        inspecao.setVistoria(vistoria);
        inspecao.setEquipamento(demaisEquipamentoSpinner);
        inspecao.setDataInsp(obterDataHoje());
        inspecao.setApto(definirDemaisEquipamentoApto());
        inspecao.setObservacao(demaisEquipamentoObsEdt.getText().toString());
        inspecoes.add(inspecao);



    }

    private void realizarInspecaoEquipamentosObrigatorios(){

        inspecao = new Inspecao();
        inspecao.setVistoria(vistoria);
        inspecao.setEquipamento(equipamentoObrigatorioSpinner);
        inspecao.setDataInsp(obterDataHoje());
        inspecao.setApto(definirEquipamentoObrigatorioApto());
        inspecao.setObservacao(equipamentoObrigatorioObsEdt.getText().toString());
        inspecoes.add(inspecao);
    }

    private boolean definirDemaisEquipamentoApto(){
        if (demaisEquipamentoAptRadio.isChecked()){
            System.out.println("demais equipamento apt");
            return true;
        }else {
            System.out.println("demais equipamento nao apt");
            return false;
        }
    }

    private boolean definirEquipamentoObrigatorioApto(){
        if (equipamentoObrigatorioAptRadio.isChecked()){
            System.out.println("Equipamento obrigatorio apt");
            return true;
        }else {
            System.out.println("Equipamento obrigatorio nao apt");
            return false;
        }
    }

    private void definirStatusEquipamento(){
        equipamentosObrigatorios = new ArrayList<>();
        demaisEquipamentos = new ArrayList<>();

        for(Equipamento e : equipamentos){
            if (e.getStatus().equals("Obrigatorio")){
                System.out.println("obrigatorio " + e.getNome());
                equipamentosObrigatorios.add(e);
            } else if (e.getStatus().equals("Não obrigatorio")){
                System.out.println("Nao obrigatorio "+e.getNome());
                    demaisEquipamentos.add(e);
            }
        }
    }



    private boolean definirVistoriaApto(){
        if (vistoriaAptRadio.isChecked()){
            System.out.println("vistoria apto");
            return true;
        }else {
            System.out.println("vistoria nao apto");
            return false;
        }
    }

    private String obterDataHoje(){
        Date data = Calendar.getInstance().getTime();
        String data2 = sdf.format(data);
        System.out.println("Data de hoje:"+data2);
        return data2;
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
        }


        return super.onOptionsItemSelected(item);
    }
}
