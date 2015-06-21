package br.edu.ufra.appfiscalizacao.conexao;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;

/**
 * Created by bpmlab on 19/06/2015.
 */
public class DataBaseHelper<E> extends OrmLiteSqliteOpenHelper {


    private static String databaseName = "devisa.bd";
    private static String droptable = "DROP TABLE estabelecimento";
    private static int databaseVersion = 1;

    private static Context cont;

    private List<StringBuilder> lerScript(String arquivo) {
        List<StringBuilder> resposta = new ArrayList<StringBuilder>();
        try {
            InputStream is = cont.getAssets().open(arquivo);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String linha = null;
            StringBuilder sb = null;

            while ((linha = br.readLine()) != null) {

                if (linha.trim().equals("--INICIO")) {
                    sb = new StringBuilder();

                } else if (linha.trim().equals("--FIM")) {
                    resposta.add(sb);

                } else {
                    if (!linha.trim().isEmpty()) {
                        sb.append(linha);

                    }
                }
            }
            br.close();
        } catch (IOException e) {
            Log.e("SCRIPT", "Erro ao ler script: " + e);
            e.printStackTrace();
        }
        return resposta;
    }
    public DataBaseHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
        cont = context;
        Log.e("Construtor", "Construtor");
    }
    private void realizarInserts(SQLiteDatabase db) {
        List<StringBuilder> inserts = lerScript("inserts.sql");
        for (StringBuilder sb : inserts) {
            db.execSQL(sb.toString());
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource src) {
        try {
            Log.e("Criando", "criando bd");
            TableUtils.createTable(src, Estabelecimento.class);
            realizarInserts(db);
            Log.e("Create", "Banco criado com sucesso");
        } catch (Exception e) {
            Log.e("Create", "Erro ao criar banco de dados" + e.getMessage().toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try{
            db.execSQL(droptable);
            Log.e("Update", "Banco atualizado com sucesso");
        }catch (Exception e){
            e.printStackTrace();
            String erro = e.getMessage().toString();
            Log.e("Update", "Erro ao atualizar banco " + erro );

        }

    }




    @Override
    public void close() {
        super.close();
    }

}
