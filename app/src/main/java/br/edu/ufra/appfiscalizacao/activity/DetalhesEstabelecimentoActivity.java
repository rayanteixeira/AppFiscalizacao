package br.edu.ufra.appfiscalizacao.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;

/**
 * Created by geovane on 03/08/15.
 */
public class DetalhesEstabelecimentoActivity extends Activity {

    private TextView statusTxt,nomeTxt,contatoTxt,logradouroTxt,telefoneTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estabelecimento_detalhes);


        Estabelecimento e = (Estabelecimento) getIntent().getSerializableExtra("estabelecimento");

        /*
        nomeTxt = (TextView) findViewById(R.id.nomeEstabelecimento);
        contatoTxt = (TextView) findViewById(R.id.contatoEstabelecimento);
        logradouroTxt = (TextView) findViewById(R.id.logradouroEstabelecimento);
        //bairroTxt = (TextView) findViewById(R.id.bairroEstabelecimento);
        telefoneTxt = (TextView) findViewById(R.id.telefoneEstabelecimento);
        statusTxt = (TextView) findViewById(R.id.statusEstabelecimento);


        nomeTxt.setText(e.getNomeFantasia());
        contatoTxt.setText(e.getContato());
        logradouroTxt.setText(e.getLogradouro());
        //bairroTxt.setText(e.getBairro().getNomeFantasia());
        telefoneTxt.setText(e.getTelefone());
        statusTxt.setText(e.getStatus());
    */
    }

}
