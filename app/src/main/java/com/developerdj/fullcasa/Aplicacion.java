package com.developerdj.fullcasa;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.developerdj.fullcasa.data.TCiudad;
import com.developerdj.fullcasa.data.TDepartamento;
import com.developerdj.fullcasa.data.TInmueble;
import com.developerdj.fullcasa.data.TOferta;
import com.developerdj.fullcasa.data.WebService.CiudadApi;
import com.developerdj.fullcasa.data.WebService.DepartamentoApi;
import com.developerdj.fullcasa.data.WebService.ServiceGenerator;
import com.developerdj.fullcasa.data.WebService.TipoInmuebleApi;
import com.developerdj.fullcasa.data.WebService.TipoOfertaApi;
import com.developerdj.fullcasa.modelo.Ciudad;
import com.developerdj.fullcasa.modelo.Departamento;
import com.developerdj.fullcasa.modelo.TipoInmueble;
import com.developerdj.fullcasa.modelo.TipoOferta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hp on 29/09/2016.
 */
public class Aplicacion extends  android.app.Application {

    public static final String TAG = Aplicacion.class.getSimpleName();

    private static Aplicacion mInstance;

    private Context context=this;

    private DepartamentoApi departamentoApi         = ServiceGenerator.createService(DepartamentoApi.class);
    private CiudadApi ciudadApi                     = ServiceGenerator.createService(CiudadApi.class);
    private TipoInmuebleApi tipoInmuebleApi         = ServiceGenerator.createService(TipoInmuebleApi.class);
    private TipoOfertaApi tipoOfertaApi             = ServiceGenerator.createService(TipoOfertaApi.class);

    @Override
    public void onCreate() {
        super.onCreate();
        inicializarBD();
        cargarDatos();
        mInstance = this;
    }

    public static synchronized Aplicacion getInstance() {
        return mInstance;
    }


    public void inicializarBD(){
        TDepartamento.getInstance(getBaseContext());
        TCiudad.getInstance(getBaseContext());
        TInmueble.getInstance(getBaseContext());
        TOferta.getInstance(getBaseContext());
    }

    public void cargarDatos(){

        Call<List<Departamento>> callDepartamento = departamentoApi.getDepartamentos();
        callDepartamento.enqueue(new Callback<List<Departamento>>() {
            @Override
            public void onResponse(Call<List<Departamento>> call, Response<List<Departamento>> response) {
                if(response.isSuccessful()){
                    TDepartamento.getInstance(context).deleteAll();
                    for (Departamento departamento : response.body()){
                        Log.d(TAG, departamento.toString());
                        TDepartamento.getInstance(context).insertar(departamento);
                    }

                }else{
                    Log.e(TAG," Response Error "+String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Departamento>> call, Throwable t) {
                Log.e(TAG," Response Error "+t.getMessage());
            }
        });

        Call<List<Ciudad>> callCiudad = ciudadApi.getCiudades();
        callCiudad.enqueue(new Callback<List<Ciudad>>() {
            @Override
            public void onResponse(Call<List<Ciudad>> call, Response<List<Ciudad>> response) {
                if(response.isSuccessful()){
                    TCiudad.getInstance(context).deleteAll();
                    for (Ciudad ciudad : response.body()){
                        Log.d(TAG, ciudad.toString());
                        TCiudad.getInstance(context).insertar(ciudad);
                    }

                }else{
                    Log.e(TAG," Response Error "+String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Ciudad>> call, Throwable t) {
                Log.e(TAG," Response Error "+t.getMessage());
            }
        });

        Call<List<TipoInmueble>> callTipoInmueble = tipoInmuebleApi.getTiposInmuebles();
        callTipoInmueble.enqueue(new Callback<List<TipoInmueble>>() {
            @Override
            public void onResponse(Call<List<TipoInmueble>> call, Response<List<TipoInmueble>> response) {
                if(response.isSuccessful()){
                    TInmueble.getInstance(context).deleteAll();
                    for (TipoInmueble tipoInmueble : response.body()){
                        Log.d(TAG, tipoInmueble.toString());
                        TInmueble.getInstance(context).insertar(tipoInmueble);
                    }

                }else{
                    Log.e(TAG," Response Error "+String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<TipoInmueble>> call, Throwable t) {
                Log.e(TAG," Response Error "+t.getMessage());
            }
        });

        Call<List<TipoOferta>> callTipoOferta = tipoOfertaApi.getTiposOfertas();
        callTipoOferta .enqueue(new Callback<List<TipoOferta>>() {
            @Override
            public void onResponse(Call<List<TipoOferta>> call, Response<List<TipoOferta>> response) {
                if(response.isSuccessful()){
                    TOferta.getInstance(context).deleteAll();
                    for (TipoOferta tipoOferta : response.body()){
                        Log.d(TAG, tipoOferta.toString());
                        TOferta.getInstance(context).insertar(tipoOferta);
                    }

                }else{
                    Log.e(TAG," Response Error "+String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<TipoOferta>> call, Throwable t) {
                Log.e(TAG," Response Error "+t.getMessage());
            }
        });
    }


}
