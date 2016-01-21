package br.edu.ufra.appfiscalizacao.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import br.edu.ufra.appfiscalizacao.rn.VistoriaRN;
import br.edu.ufra.appfiscalizacao.util.ConexaoInternet;

public class NetworkChangeReceiver extends BroadcastReceiver {
    VistoriaRN rnVistoria;
    public NetworkChangeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        rnVistoria = new VistoriaRN(context);
        //verificar se ha algo no bd tbm
        if (ConexaoInternet.estaConectado(context)== true && !rnVistoria.obterTodos().isEmpty()){
            Toast.makeText(context,"Enviando",Toast.LENGTH_LONG).show();

            context.startService(new Intent("SERVICEVISTORIA"));
        }else {
            System.out.println("Broadcast- não está conectado");
        }
    }
}
