package com.developerdj.fullcasa.data.WebService;


import com.developerdj.fullcasa.modelo.Departamento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by sab99r
 */
public interface DepartamentoApi {

    @GET("start/departamentos")
    Call<List<Departamento>> getDepartamentos();

}
