package com.developerdj.fullcasa.data.WebService;


import com.developerdj.fullcasa.modelo.Buscar;
import com.developerdj.fullcasa.modelo.BuscarEmpresa;
import com.developerdj.fullcasa.modelo.Ciudad;
import com.developerdj.fullcasa.modelo.Empresa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by sab99r
 */
public interface EmpresaApi {

    @POST("empresas")
    Call<List<Empresa>> getEmpresas(@Body Buscar buscar);

}
