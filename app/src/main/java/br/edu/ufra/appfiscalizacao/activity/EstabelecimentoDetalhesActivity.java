package br.edu.ufra.appfiscalizacao.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;

public class EstabelecimentoDetalhesActivity extends ActionBarActivity {
    TextView rgTxt;
    TextView cpfTxt;
    TextView numeroTxt;
    TextView dataLicencaTxt;
    TextView dataVencimentoTxt;
    TextView statusTxt;
    TextView autentificacaoTxt;
    TextView emailTxt;
    TextView nomeTxt;
    TextView contatoTxt;
    TextView logradouroTxt;
    TextView bairroTxt;
    TextView telefoneTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento_detalhes);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
            Estabelecimento e = (Estabelecimento) getIntent().getSerializableExtra("estabelecimento");

        nomeTxt = (TextView) findViewById(R.id.nomeEstabelecimento);
        contatoTxt = (TextView) findViewById(R.id.contatoEstabelecimento);
        logradouroTxt = (TextView) findViewById(R.id.logradouroEstabelecimento);
        bairroTxt = (TextView) findViewById(R.id.bairroEstabelecimento);
        telefoneTxt = (TextView) findViewById(R.id.telefoneEstabelecimento);

        nomeTxt.setText(e.getNome().toString());
        contatoTxt.setText(e.getContato().toString());
        logradouroTxt.setText(e.getLogradouro().toString());
        bairroTxt.setText(e.getBairro().toString());
        telefoneTxt.setText(e.getTelefone().toString());

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
