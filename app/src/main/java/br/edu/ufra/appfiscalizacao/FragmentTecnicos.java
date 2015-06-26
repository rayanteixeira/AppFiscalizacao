package br.edu.ufra.appfiscalizacao;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONObject;

import br.edu.ufra.appfiscalizacao.application.VolleyApplication;


public class FragmentTecnicos extends Fragment {

    public FragmentTecnicos() {

    }
    String url = "http://192.168.0.5:8080/Contato3/webresources/contato/buscarTodos";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_tecnicos, container, false);

        final TextView mText = (TextView) rootview.findViewById(R.id.txtjson);

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                mText.setText(response.toString());
            }
        },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mText.setText(error.toString());
                    }
                });

        VolleyApplication.getsInstance().getmRequestQueue().add(arrayRequest);



        return rootview;
    }


}
