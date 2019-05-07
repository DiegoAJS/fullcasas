package com.developerdj.fullcasa.data.WebService;


import com.developerdj.fullcasa.modelo.Buscar;
import com.developerdj.fullcasa.modelo.Empresa;
import com.developerdj.fullcasa.modelo.Inmueble;
import com.developerdj.fullcasa.modelo.Propiedad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sab99r
 */
public interface PropiedadApi {

    @GET("{id}/propiedades")
    Call<List<Propiedad>> getPropiedades(@Path("id") int id, @Query("page") int page);

    @POST("propiedades")
    Call<List<Propiedad>> filterInmuebles(@Body Buscar buscar);

}
