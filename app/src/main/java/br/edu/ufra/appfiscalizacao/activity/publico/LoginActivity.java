package br.edu.ufra.appfiscalizacao.activity.publico;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.ActionBar;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.activity.MainActivity;
import br.edu.ufra.appfiscalizacao.util.StringURL;
import br.edu.ufra.appfiscalizacao.application.pojo.TecnicoPOJO;
import br.edu.ufra.appfiscalizacao.application.pojo.conversor.TecnicoConverter;
import br.edu.ufra.appfiscalizacao.entidade.Tecnico;
import br.edu.ufra.appfiscalizacao.rn.TecnicoRN;
import br.edu.ufra.appfiscalizacao.util.ConexaoInternet;
import br.edu.ufra.appfiscalizacao.util.Mensagem;


public class LoginActivity extends ActionBarActivity {

    private String matriculaTec1,matriculaTec2;
    //private Integer preferencesIdTec1, preferencesIdTec2;
    private Button btnentrar;
    private EditText txtMatriculaTec1, txtMatriculaTec2;
    //private static final String PREFERENCE_NAME="LoginActivityPreferences";
    //private SharedPreferences sharedP;
    private TecnicoPOJO tecnicoPOJO,tecnicoPOJO1, tecnicoPOJO2;
    private RequestQueue requestQueue;
    private Gson gson = new Gson();
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

        tecnicoPOJO1 = new TecnicoPOJO();
        tecnicoPOJO2 = new TecnicoPOJO();

        txtMatriculaTec1 = (EditText) findViewById(R.id.matriculaTec1);
        txtMatriculaTec2 = (EditText) findViewById(R.id.matriculaTec2);
        btnentrar = (Button) findViewById(R.id.btnLogin);

        requestQueue= Volley.newRequestQueue(this.getApplicationContext());

        ActionBar actionBar = getActionBar();

        if (actionBar != null){
            actionBar.hide();
        }

        btnentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarEntradaUsuario();

            }
        });

    }

    private void verificarEntradaUsuario(){

        try{

                matriculaTec1 = txtMatriculaTec1.getText().toString();
                matriculaTec2 = txtMatriculaTec2.getText().toString();
            System.out.println("mat1 "+matriculaTec1+"mat2 "+matriculaTec2);
            if (ConexaoInternet.estaConectado(getBaseContext())==true ){

            if (!matriculaTec1.equals(matriculaTec2)){


                progressD=ProgressDialog.show(this, "Aguarde", "...");


                stringsURL.setUrlLogarTec(matriculaTec1,matriculaTec2);
                String url=stringsURL.getUrlLogarTec();
                System.out.println("url: "+url);

                final JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET,url,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                try{
                                    if (!response.isNull(0) & !response.isNull(1)){

                                    int i;
                                    for (i = 0; i < response.length(); i++) {
                                        JSONObject tecnicoItem = response.getJSONObject(i);
                                        tecnicoPOJO = gson.fromJson(tecnicoItem.toString(), TecnicoPOJO.class);

                                        if (tecnicoPOJO.getMatricula().equals(matriculaTec1)) {
                                            tecnicoPOJO1 = tecnicoPOJO;
                                        } else if (tecnicoPOJO.getMatricula().equals(matriculaTec2)) {
                                            tecnicoPOJO2 = tecnicoPOJO;
                                        }

                                    }

                                    if  (tecnicoPOJO1.getMatricula().equals(matriculaTec1) & tecnicoPOJO2.getMatricula().equals(matriculaTec2) ) {
                                if (rnTecnico.salvar(TecnicoConverter.fromTecnicoPOJO(tecnicoPOJO1)) &&  rnTecnico.salvar(TecnicoConverter.fromTecnicoPOJO(tecnicoPOJO2))){


                                    for (Tecnico t : rnTecnico.obterTodos()){
                                        System.out.println("id "+t.getId());
                                    }

                                    progressD.dismiss();
                                    Toast.makeText(getBaseContext(), "Sucesso, sejam bem vindos !", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                                    finish();

                                }


/*
                                        SharedPreferences getSharedP = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                                        SharedPreferences.Editor editor = getSharedP.edit();
                                        editor.putInt("idTec1", tecnicoPOJO1.getId());
                                        editor.putInt("idTec2", tecnicoPOJO2.getId());
                                        editor.commit();
*/


                                        }
                                    } else {
                                        progressD.dismiss();
                                        Toast.makeText(getBaseContext(),"As matrículas não foram encontradas no servidor ! ",Toast.LENGTH_LONG).show();
                                    }
                                }catch(Exception e){
                                    progressD.dismiss();
                                    Toast.makeText(getBaseContext(),"Erro na Resposta: "+e.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                try{
                                    progressD.dismiss();
                                    Toast.makeText(getBaseContext(),"Erro ao obter resposta: "+ error.getCause(),Toast.LENGTH_LONG).show();

                                }catch (Exception e){
                                    progressD.dismiss();
                                    Toast.makeText(getBaseContext(),"Erro: "+e.getMessage(),Toast.LENGTH_LONG).show();

                                }
                            }
                        }
                );
                requestQueue.add(request);
            }else {
                Toast.makeText(this,"As matrículas precisam ser diferentes, digite novamente !",Toast.LENGTH_LONG).show();
            }
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
