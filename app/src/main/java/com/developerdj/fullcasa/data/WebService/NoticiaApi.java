package com.developerdj.fullcasa.data.WebService;


import com.developerdj.fullcasa.modelo.Empresa;
import com.developerdj.fullcasa.modelo.Noticia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sab99r
 */
public interface NoticiaApi {

    @GET("noticias")
    Call<List<Noticia>> getNoticias(@Query("page") int page);

}
