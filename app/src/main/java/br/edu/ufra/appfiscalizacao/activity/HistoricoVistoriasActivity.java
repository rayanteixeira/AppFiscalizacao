package br.edu.ufra.appfiscalizacao.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.application.pojo.VistoriaPOJO;

public class HistoricoVistoriasActivity extends AppCompatActivity {

    private VistoriaPOJO vistoria;

    ListView lv_historicoEquipamentosObrigatorios, lv_historicoEquipamentosNAOObrigatorios;
    TextView historico_nomeEstabelecimento, historico_dataVistoria, historico_vistoria_tecnico1, historico_vistoria_tecnico2, historico_vistoria_prazo, historico_vistoria_observacao;
    DateFormat sdf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_vistoria);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sdf= new SimpleDateFormat("dd/MM/yyyy");
        vistoria = (VistoriaPOJO) getIntent().getSerializableExtra("historico_vistoria");

        lv_historicoEquipamentosObrigatorios = (ListView) findViewById(R.id.list_historico_vistoria_equipObrigatorio);
        lv_historicoEquipamentosNAOObrigatorios = (ListView) findViewById(R.id.list_historico_vistoria_equipNAOObrigatorio);
        historico_nomeEstabelecimento = (TextView) findViewById(R.id.txt_historico_nomeEstabelecimento);
        historico_dataVistoria = (TextView) findViewById(R.id.txt_historico_dataVistoria);
        historico_vistoria_tecnico1 = (TextView) findViewById(R.id.txt_historico_vistoria_tecnico1);
        historico_vistoria_tecnico2 = (TextView) findViewById(R.id.txt_historico_vistoria_tecnico2);
        historico_vistoria_prazo = (TextView) findViewById(R.id.txt_historico_vistoria_prazo);
        historico_vistoria_observacao = (TextView) findViewById(R.id.txt_historico_vistoria_observacao);


        historico_nomeEstabelecimento.setText(vistoria.getEstabelecimentoPOJO().getNomeFantasia());
        historico_dataVistoria.setText(sdf.format(vistoria.getDataVistoria()));
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

        historico_vistoria_prazo.setText(String.valueOf(vistoria.getPrazo()));
        historico_vistoria_prazo.setText(vistoria.getObservacao());

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
