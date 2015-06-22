package br.edu.ufra.appfiscalizacao.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ufra.appfiscalizacao.R;


public class LoginActivity extends Activity {

    String name = "devisa", password = "12345";
    String uName, uPassword;
    Button btnentrar;
    EditText username, userpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.loginname);
        userpassword = (EditText) findViewById(R.id.loginPassword);
        btnentrar = (Button) findViewById(R.id.btnLogin);




        btnentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uName = username.getText().toString();
                uPassword = userpassword.getText().toString();
                if( uName.equals(name) &&  uPassword.equals(password)){

                    Intent it = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(it);

                    Toast.makeText(getBaseContext(),"Bem vindo(A)!",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getBaseContext(),"Dados incorretos!",Toast.LENGTH_SHORT).show();
                }


            }
        });

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
