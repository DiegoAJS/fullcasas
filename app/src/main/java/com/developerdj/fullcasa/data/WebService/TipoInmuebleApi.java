package com.developerdj.fullcasa.data.WebService;


import com.developerdj.fullcasa.modelo.TipoInmueble;
import com.developerdj.fullcasa.modelo.TipoOferta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sab99r
 */
public interface TipoInmuebleApi {

    @GET("start/tipos_inmuebles")
    Call<List<TipoInmueble>> getTiposInmuebles();

}
