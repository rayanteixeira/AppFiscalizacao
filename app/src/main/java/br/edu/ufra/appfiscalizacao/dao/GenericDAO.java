package br.edu.ufra.appfiscalizacao.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.edu.ufra.appfiscalizacao.conexao.DBHelper;

/**
 * Created by bpmlab on 19/06/2015.
 */
public class GenericDAO<E> extends DBHelper<E>{
    protected Dao<E, Integer> dao;
    private Class<E> type;


    public GenericDAO(Context context, Class<E> type) {
        super(context);
        this.type = type;
        setDao();
    }


    public void setDao() {
        try{
            dao = DaoManager.createDao(getConnectionSource(), type);
            Log.w("setDao","Realizado!");
        }catch (Exception e){
            e.printStackTrace();
            Log.e("setDao", "Erro setDao" + e.getMessage().toString());
        }
    }
    public List<E> getAll(){
        try{
            List<E> result = dao.queryForAll();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            Log.e("getAll", "Erro ao buscar por getAll" + e.getMessage().toString());
            return null;
        }
    }


    public E getId(int id){
        try {
            E obj = dao.queryForId(id);
            return obj;
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("getId", "Erro ao buscar por getId" + e.getMessage().toString());
            return null;
        }
    }

    public boolean insert(E obj){
        try{
            Log.w("Insert", "Sucesso ao inserir" + obj.getClass());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            Log.e("Insert", "Erro ao inserir" + e.getMessage().toString());
            return false;
        }
    }

    public boolean delete(E obj){
        try{
            dao.delete(obj);
            Log.w("Delete", "Sucesso ao deletar" + obj.getClass());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            Log.e("Delete", "Erro ao realizar delete" + e.getMessage().toString());
            return false;
        }
    }

    public boolean update(E obj){
        try {
            dao.update(obj);
            Log.w("update", "Sucesso ao atualizar" + obj.getClass());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            e.printStackTrace();
            Log.e("Updade", "Erro ao realizar Update" + e.getMessage().toString());
            return false;
        }
    }

}
