package com.developerdj.fullcasa.data.WebService;


import com.developerdj.fullcasa.modelo.Ciudad;
import com.developerdj.fullcasa.modelo.TipoOferta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sab99r
 */
public interface TipoOfertaApi {

    @GET("start/tipos_ofertas")
    Call<List<TipoOferta>> getTiposOfertas();

}
