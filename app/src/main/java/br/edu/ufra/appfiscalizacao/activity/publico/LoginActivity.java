package br.edu.ufra.appfiscalizacao.activity.publico;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.activity.MainActivity;
import br.edu.ufra.appfiscalizacao.application.pojo.TecnicoPOJO;
import br.edu.ufra.appfiscalizacao.application.pojo.conversor.TecnicoConverter;
import br.edu.ufra.appfiscalizacao.rn.TecnicoRN;
import br.edu.ufra.appfiscalizacao.util.ConexaoInternet;
import br.edu.ufra.appfiscalizacao.util.Mensagem;
import br.edu.ufra.appfiscalizacao.util.StringURL;


public class LoginActivity extends ActionBarActivity {

    private String matriculaTec1,senha;
    //private Integer preferencesIdTec1, preferencesIdTec2;
    private Button btnentrar;
    private EditText txtMatriculaTec1, txtSenha;
    //private static final String PREFERENCE_NAME="LoginActivityPreferences";
    //private SharedPreferences sharedP;
    private TecnicoPOJO tecnicoPOJO;
    private RequestQueue requestQueue;
    private Gson gson;
    private ProgressDialog progressD;
    private Mensagem mensagem= Mensagem.getInstace();
    private StringURL stringsURL = StringURL.getInstance();
    private TecnicoRN rnTecnico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       rnTecnico = new TecnicoRN(getBaseContext());
/*
        sharedP=getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
        preferencesIdTec1 = sharedP.getInt("idTec1",0);
        preferencesIdTec2 = sharedP.getInt("idTec2",0);
*/
        if (!rnTecnico.obterTodos().isEmpty()){
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();
        }

        txtMatriculaTec1 = (EditText) findViewById(R.id.matriculaTec1);
        txtSenha = (EditText) findViewById(R.id.senha);
        btnentrar = (Button) findViewById(R.id.btnLogin);

        requestQueue= Volley.newRequestQueue(this.getApplicationContext());

        ActionBar actionBar = getActionBar();

        if (actionBar != null){
            actionBar.hide();
        }

        btnentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matriculaTec1 = txtMatriculaTec1.getText().toString();
                senha = txtSenha.getText().toString();
                if (!matriculaTec1.equals("") && !senha.equals("")){
                verificarEntradaUsuario();
                } else {
                    Toast.makeText(getBaseContext(), "Insira matrícula e senha!", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    private void verificarEntradaUsuario(){

        try{

            System.out.println("mat1 "+matriculaTec1+"senha "+senha);
            if (ConexaoInternet.estaConectado(getBaseContext())==true ){

                progressD=ProgressDialog.show(this, "Aguarde", "...");


                stringsURL.setUrlLogarTec(matriculaTec1,senha);
                String url=stringsURL.getUrlLogarTec();
                System.out.println("url: "+url);

                final JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,url,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try{

                                    if (response.isNull("id")==false){
                                        gson = new Gson();
                                        tecnicoPOJO = new TecnicoPOJO();
                                        tecnicoPOJO = gson.fromJson(response.toString(), TecnicoPOJO.class);
                                        System.out.println("pos reponse");


                                if (rnTecnico.salvar(TecnicoConverter.fromTecnicoPOJO(tecnicoPOJO))){

                                    progressD.dismiss();
                                    Toast.makeText(getBaseContext(), "Sucesso, seja bem vindo "+tecnicoPOJO.getNome(), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                                    finish();

                                } else {
                                    Toast.makeText(getBaseContext(), "Erro ao logar, tente novamente!", Toast.LENGTH_LONG).show();

                                }


/*
                                        SharedPreferences getSharedP = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                                        SharedPreferences.Editor editor = getSharedP.edit();
                                        editor.putInt("idTec1", tecnicoPOJO1.getId());
                                        editor.putInt("idTec2", tecnicoPOJO2.getId());
                                        editor.commit();
*/



                                    } else {
                                        progressD.dismiss();
                                        Toast.makeText(getBaseContext(),"Matrícula ou senha incorretos ! ",Toast.LENGTH_LONG).show();
                                    }
                                }catch(Exception e){
                                    progressD.dismiss();
                                    Toast.makeText(getBaseContext(),"Erro ao fazer login: "+e,Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                try{
                                    progressD.dismiss();
                                    Toast.makeText(getBaseContext(),"Erro ao obter resposta do servidor: "+ error.getCause(),Toast.LENGTH_LONG).show();

                                }catch (Exception e){
                                    progressD.dismiss();
                                    Toast.makeText(getBaseContext(),"Erro: "+e.getMessage(),Toast.LENGTH_LONG).show();

                                }
                            }
                        }
                );
                requestQueue.add(request);
            } else {
                Toast.makeText(this,"Conecte-se a internet para tentar entrar no sistema",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e ){
            progressD.dismiss();
            Toast.makeText(getBaseContext(),"Erro: "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
