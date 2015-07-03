package br.edu.ufra.appfiscalizacao.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;
import br.edu.ufra.appfiscalizacao.rn.EstabelecimentoRN;

public class CadastroEstabelecimento extends Activity {
    Button btnSalvar;
    Estabelecimento estabelecimento;
    EstabelecimentoRN rn;
    EditText nome;
    EditText dataLic;
    EditText dataVenc;
    Spinner escolhaSituacao;
    String situacao = "Regular";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_estabelecimento);
        btnSalvar = (Button) findViewById(R.id.btnSalvarPonto);
        nome = (EditText) findViewById(R.id.nomePonto);
        dataLic = (EditText) findViewById(R.id.dataLicenca);
        dataVenc = (EditText) findViewById(R.id.dataVencLicenca);
        escolhaSituacao = (Spinner)  findViewById(R.id.spinnerSituacaoPonto);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.arraySituacao,android.R.layout.simple_spinner_item);
    escolhaSituacao.setAdapter(adapter);

        escolhaSituacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                atribuirValorVariavel();
                System.out.println("Situacao: "+situacao);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

               // salvar();
                finish();
            }
        });
    }

    public void atribuirValorVariavel(){

        if (escolhaSituacao.getSelectedItem().equals("Vencida")){
            situacao = "vencida";
        } else if (escolhaSituacao.getSelectedItem().equals("Sem vistoria")){
            situacao = "semVistoria";
        } else if (escolhaSituacao.getSelectedItem().equals("Em vistoria")){
            situacao = "emVistoria";
        } else if (escolhaSituacao.getSelectedItem().equals("Regular")){
            situacao = "regular";
        }
    }
/*
    public void salvar(){
       try {
           estabelecimento = new Estabelecimento();
           rn = new EstabelecimentoRN(getBaseContext());
           estabelecimento.setNome(nome.getText().toString());
           int dtLic= Integer.parseInt(dataLic.getText().toString());

           estabelecimento.setDataLic(Integer.parseInt(dataLic.getText().toString()));
           estabelecimento.setDataVenc(Integer.parseInt(dataVenc.getText().toString()));
           estabelecimento.setSituacao(situacao);
           rn.salvar(estabelecimento);
       } catch (Exception e){
           Log.e("Salvar", "Erro ao salvar "+e.getMessage());
       }


    }  */


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro_estabelecimento, menu);
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
