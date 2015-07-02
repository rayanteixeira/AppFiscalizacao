package br.edu.ufra.appfiscalizacao.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

/**
 * Created by geovane on 17/03/15.
 */
public class ConexaoInternet {

    public static boolean estaConectado(Context context){
        boolean conectado=false;
        ConnectivityManager connectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager.getActiveNetworkInfo() !=null && connectivityManager.getActiveNetworkInfo().isAvailable() &&
                connectivityManager.getActiveNetworkInfo().isConnected()
                ){


            conectado=true;

        }else {
            conectado=false;
        }

        return conectado;
    }
}
