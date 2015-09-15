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

import br.edu.ufra.appfiscalizacao.entidade.Equipamento;
import br.edu.ufra.appfiscalizacao.entidade.Estabelecimento;
import br.edu.ufra.appfiscalizacao.entidade.Inspecao;
import br.edu.ufra.appfiscalizacao.entidade.Tecnico;
import br.edu.ufra.appfiscalizacao.entidade.Vistoria;

/**
 * Created by bpmlab on 19/06/2015.
 */
public class DataBaseHelper<E> extends OrmLiteSqliteOpenHelper {


    private static String databaseName = "devisa.bd";
    private static int databaseVersion = 1;

    private static Context cont;
    public DataBaseHelper(Context context) {
        super(context, databaseName, null, databaseVersion);
        cont = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource src) {
        try {
            Log.i("Tabelas", "Criando tabelas");
            TableUtils.createTable(src, Estabelecimento.class);
            TableUtils.createTable(src, Tecnico.class);
            TableUtils.createTable(src, Vistoria.class);
            TableUtils.createTable(src, Inspecao.class);
            TableUtils.createTable(src, Equipamento.class);

            Log.i("Tabelas", "Tabelas criadas com sucesso");
        } catch (Exception e) {
            Log.e("Tabelas", "Erro ao criar tablelas " + e.getMessage().toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource src, int oldVersion, int newVersion) {

        try{
            TableUtils.dropTable(src, Estabelecimento.class, true);
            TableUtils.dropTable(src, Tecnico.class,true);
            TableUtils.dropTable(src, Vistoria.class,true);
            TableUtils.dropTable(src, Inspecao.class,true);
            TableUtils.dropTable(src, Equipamento.class,true);

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
