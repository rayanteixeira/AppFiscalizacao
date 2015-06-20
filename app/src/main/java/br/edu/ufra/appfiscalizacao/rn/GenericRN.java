package br.edu.ufra.appfiscalizacao.rn;

import android.content.Context;

import java.util.List;

import br.edu.ufra.appfiscalizacao.dao.GenericDAO;

/**
 * Created by Rayan on 20/06/2015.
 */
public class GenericRN<T> {

    private Context context;
    private Class<T> tipo;

    GenericDAO<T> dao;

  public GenericRN(Class<T> tipo, Context context){
      this.tipo = tipo;
      this.context = context;
      dao = new GenericDAO(context,tipo);
  }


     public boolean save(T e){
      return dao.insert(e);
    }

    public boolean remove(T e){
      return dao.delete(e);
    }

    public boolean refresh(T e){
        return dao.update(e);
    }

    public List<T> getAll(){
        return dao.getAll();
    }





}
