package com.developerdj.fullcasa.data.WebService;


import com.developerdj.fullcasa.modelo.Ciudad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sab99r
 */
public interface CiudadApi {

    @GET("start/ciudades")
    Call<List<Ciudad>> getCiudades();

}
