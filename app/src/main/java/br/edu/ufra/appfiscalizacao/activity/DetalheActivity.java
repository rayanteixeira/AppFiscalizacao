package br.edu.ufra.appfiscalizacao.activity;


import android.support.v7.app.ActionBar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;

import br.edu.ufra.appfiscalizacao.R;
import br.edu.ufra.appfiscalizacao.dao.TecnicoDAO;
import br.edu.ufra.appfiscalizacao.rn.TecnicoRN;


public class DetalheActivity extends ActionBarActivity {

    private ActionBar actionBar;
    TecnicoRN rn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        rn = new TecnicoRN(getBaseContext());

        //ActionBar actionBar = getSupportActionBar();

        //if (actionBar != null){
          //  actionBar.setDisplayHomeAsUpEnabled(true);
           // actionBar.setHomeButtonEnabled(true);
        //}
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("id");




        TextView txt = (TextView) findViewById(R.id.txtdetalhes);
        txt.setText(String.valueOf(id));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       // int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
           // return true;
       // }

        finish();
        return super.onOptionsItemSelected(item);
    }
}
