package br.edu.ufra.appfiscalizacao.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.entidade.Equipamento;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;
import br.edu.ufra.appfiscalizacao.rn.EquipamentoRN;
import br.edu.ufra.appfiscalizacao.rn.EstabelecimentoRN;

public class DetalhesEstabelecimentoActivity extends ActionBarActivity {
    private TextView statusTxt,nomeTxt,contatoTxt,logradouroTxt,bairroTxt,telefoneTxt;

    Spinner spinnerequip;
    EquipamentoRN equiprn;
    private TabHost abas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento_detalhes_vistoria);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Estabelecimento e = (Estabelecimento) getIntent().getSerializableExtra("estabelecimento");

        spinnerequip = (Spinner) findViewById(R.id.spinnerequip);

        nomeTxt = (TextView) findViewById(R.id.nomeEstabelecimento);
        contatoTxt = (TextView) findViewById(R.id.contatoEstabelecimento);
        logradouroTxt = (TextView) findViewById(R.id.logradouroEstabelecimento);
        //bairroTxt = (TextView) findViewById(R.id.bairroEstabelecimento);
        telefoneTxt = (TextView) findViewById(R.id.telefoneEstabelecimento);
        statusTxt = (TextView) findViewById(R.id.statusEstabelecimento);

        nomeTxt.setText(e.getNome());
        contatoTxt.setText(e.getContato());
        logradouroTxt.setText(e.getLogradouro());
        //bairroTxt.setText(e.getBairro().getNome());
        telefoneTxt.setText(e.getTelefone());
        statusTxt.setText(e.getStatus());



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
        abas.addTab(descritor);

        descritor = abas.newTabSpec("aba2");
        descritor.setContent(R.id.equipamentosobrigatorio);
        descritor.setIndicator("Equipamentos Obrigatorios");
        abas.addTab(descritor);

        descritor = abas.newTabSpec("aba3");
        descritor.setContent(R.id.demaisequipamentos);
        descritor.setIndicator("Demais Equipamentos");
        abas.addTab(descritor);

        //spinner();
    }



    private void spinner(){
        List<String> equipamentos = new ArrayList<>();

        equipamentos.add("Lampadas");
        equipamentos.add("Cesto");
        equipamentos.add("Freezer");
        equipamentos.add("Telhado");
        equipamentos.add("Porta Saco");
        equipamentos.add("Uniforme");
        equipamentos.add("Pia");
        equipamentos.add("Pisos");
        equipamentos.add("Paredes");
        equipamentos.add("Sistema Sanitário");




        ArrayAdapter<String> spinneradapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, equipamentos);

        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerequip.setAdapter(spinneradapter);

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
