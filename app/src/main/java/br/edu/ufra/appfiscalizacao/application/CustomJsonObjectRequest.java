package br.edu.ufra.appfiscalizacao.application;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by geovane on 25/08/15.
 */
public class CustomJsonObjectRequest extends JsonObjectRequest {
    public CustomJsonObjectRequest(int method, String url, JSONObject jsonRequest,Response.Listener listener, Response.ErrorListener errorListener)
    {
        super(method, url, jsonRequest, listener, errorListener);
    }

    @Override
    public Map getHeaders() throws AuthFailureError {
        Map headers = new HashMap();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        return headers;
    }


}