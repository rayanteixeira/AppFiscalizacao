package br.edu.ufra.appfiscalizacao.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.application.pojo.VistoriaPOJO;

public class HistoricoVistoriasActivity extends Activity {

    private VistoriaPOJO vistoria;

    ListView lv_historicoEquipamentosObrigatorios, lv_historicoEquipamentosNAOObrigatorios;
    TextView historico_nomeEstabelecimento;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_vistoria);


        vistoria = (VistoriaPOJO) getIntent().getSerializableExtra("historico_vistoria");

        lv_historicoEquipamentosObrigatorios = (ListView) findViewById(R.id.list_historico_vistoria_equipObrigatorio);
        lv_historicoEquipamentosNAOObrigatorios = (ListView) findViewById(R.id.list_historico_vistoria_equipNAOObrigatorio);
        historico_nomeEstabelecimento = (TextView) findViewById(R.id.txt_historico_nomeEstabelecimento);

        historico_nomeEstabelecimento.setText(vistoria.getEstabelecimentoPOJO().getNomeFantasia());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vistoria, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }


}
