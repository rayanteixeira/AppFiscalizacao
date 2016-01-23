/*
 * Copyright 2015 Rudson Lima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.edu.ufra.appfiscalizacao.activity;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import br.edu.ufra.appfiscalizacao.R;

public class SobreActivity extends AppCompatActivity {




    String[] nomes = {"Rayan","Rayan","Rayan","Rayan","Rayan","Rayan","Rayan","Rayan","Rayan","Rayan","Rayan","Rayan","Rayan","Rayan","Rayan","Rayan"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView mTxtTitle = (TextView) findViewById(R.id.txtTitle);
       // mTxtTitle.setText(getString(R.string.settings));


        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1, nomes);
        //ListView list = (ListView) findViewById(R.id.listviewteste);
        //list.setAdapter(adapter);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.attachToListView(list);


        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
