package br.edu.ufra.appfiscalizacao.application;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Rayan on 24/06/2015.
 */
public class VolleyApplication extends Application {

    private  static  VolleyApplication sInstance;

    private RequestQueue mRequestQueue;


    @Override
    public void onCreate() {
        super.onCreate();

        mRequestQueue = Volley.newRequestQueue(this);

        sInstance = this;
    }

    public synchronized static VolleyApplication getsInstance(){
        return sInstance;
    }

    public RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }
}
