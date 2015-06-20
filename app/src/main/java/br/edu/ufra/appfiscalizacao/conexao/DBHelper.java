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
public class DBHelper<E> extends OrmLiteSqliteOpenHelper {


    private static String dbName = "devisa.db";
    private static String droptable = "DROP TABLE estabelecimento";
    private static int dbVersion = 1;

    private static Context cont;


    public DBHelper(Context context) {
        super(context, dbName, null, dbVersion);
        cont = context;
    }



    private List<StringBuilder> lerScript(String arquivo) {
        List<StringBuilder> resposta = new ArrayList<StringBuilder>();
        try {
            //cont.getAssets().open(arquivo);
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
    private void realizarInserts(SQLiteDatabase db) {
        List<StringBuilder> inserts = lerScript("inserts.sql");
        for (StringBuilder sb : inserts) {
            db.execSQL(sb.toString());
        }
        Log.i("Inserts", "Inserts realizados " );
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource src) {
        try {
            TableUtils.createTable(src, Estabelecimento.class);
            realizarInserts(db);
            Log.w("Create", "Banco criado com sucesso");
        } catch (Exception e) {
            Log.e("Create", "Erro ao criar banco craido" + e.getMessage().toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try{
            db.execSQL(droptable);
            Log.w("Update", "Banco atualizado com sucesso");
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
