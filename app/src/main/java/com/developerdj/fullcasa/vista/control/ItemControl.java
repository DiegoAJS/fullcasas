package com.developerdj.fullcasa.vista.control;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.developerdj.fullcasa.R;
import com.developerdj.fullcasa.data.WebService.EmpresaApi;
import com.developerdj.fullcasa.data.WebService.PropiedadApi;
import com.developerdj.fullcasa.data.WebService.NoticiaApi;
import com.developerdj.fullcasa.data.WebService.ServiceGenerator;
import com.developerdj.fullcasa.modelo.Buscar;
import com.developerdj.fullcasa.modelo.BuscarEmpresa;
import com.developerdj.fullcasa.modelo.Empresa;
import com.developerdj.fullcasa.modelo.Inmueble;
import com.developerdj.fullcasa.modelo.Noticia;
import com.developerdj.fullcasa.modelo.Propiedad;
import com.developerdj.fullcasa.vista.adapter.AdapterItem;
import com.developerdj.fullcasa.vista.adapter.Interfaces.OnLoadMoreListener;
import com.developerdj.fullcasa.vista.dialog.NoresultDialog;
import com.developerdj.fullcasa.modelo.Footer;
import com.developerdj.fullcasa.modelo.Item;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hp on 15/8/2017.
 */

public class ItemControl implements SwipeRefreshLayout.OnRefreshListener {

    public static String TAG = ItemControl.class.getSimpleName();

    public static final int HOLDER_INMUEBLE=1;
    public static final int HOLDER_EMPRESA=2;
    public static final int HOLDER_NOTICIA=3;

    public static final int HOLDER_EMPRESA_INMUEBLES=4;

    private Context context;
    private RecyclerView recycler;
    private FragmentManager fragmentManager;
    private int type=0;
    public Buscar buscar = new Buscar();

    private SwipeRefreshLayout refreshLayout;

    private EmpresaApi empresaApi       = ServiceGenerator.createService(EmpresaApi.class);
    private PropiedadApi propiedadApi   = ServiceGenerator.createService(PropiedadApi.class);
    private NoticiaApi noticiaApi       = ServiceGenerator.createService(NoticiaApi.class);

    private NoresultDialog noResultadoDialog = new NoresultDialog();

    private AdapterItem adapter;
    private List<Item> items = new ArrayList<Item>();
    private int id;

    public ItemControl() {


    }

    public void setupFragment(Context c, ViewGroup v, FragmentManager f, int type){

        this.context=c;
        this.fragmentManager=f;

        recycler=(RecyclerView)v.findViewById(R.id.reciclador);
        refreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.sr_cargando);

        this.type=type;

        inicializar();
    }

    public void setupActivity(AppCompatActivity context, int type){

        this.context=context;
        this.fragmentManager=context.getSupportFragmentManager();

        recycler=(RecyclerView)context.findViewById(R.id.reciclador_buscar);
        refreshLayout = (SwipeRefreshLayout)context.findViewById(R.id.sr_cargando_buscar);

        this.type=type;

        inicializar();
    }

    public void setupActivityEmprersa(AppCompatActivity context, int type){

        this.context=context;
        this.fragmentManager=context.getSupportFragmentManager();

        recycler=(RecyclerView)context.findViewById(R.id.reciclador_empresa);
        refreshLayout = (SwipeRefreshLayout)context.findViewById(R.id.sr_cargando_empresa);

        this.type=type;

        inicializar();
    }


    public void inicializar(){

        adapter = new AdapterItem(items,context);

        adapter.setLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                recycler.post(new Runnable() {
                    @Override
                    public void run() {
                        buscar.incrementPage();
                        cargar();
                    }
                });
            }
        });

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(context));
        recycler.setAdapter(adapter);

        // Obtener el refreshLayout
        // Seteamos los colores que se usarán a lo largo de la animación
        refreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorAccent);
        refreshLayout.setOnRefreshListener(this);

        if(items.isEmpty())
            cargar();
    }



    public void cargar(){

       if(buscar.getPage()==1)
            showCargando();
        else{
            items.add(new Footer());
            adapter.notifyItemInserted(items.size()-1);
        }

        switch (type){
            case HOLDER_INMUEBLE:   getInmueble();break;
            case HOLDER_EMPRESA:    getEmpresa();break;
            case HOLDER_NOTICIA:    getNoticia();break;
            case HOLDER_EMPRESA_INMUEBLES:    getEmpresaInmuebles();break;
        }

    }

    public void clear(){
        items.clear();
        buscar.resetPager();
        inicializar();
    }

    private void cancelar(){
        if (buscar.getPage()==1){
            dismissCargando();
        }else {
            //remove loading view
            items.remove(items.size()-1);
        }
    }

    @Override
    public void onRefresh() {
        clear();
    }

    public void showCargando(){
        refreshLayout.setRefreshing(true);
    }

    public void dismissCargando(){
        refreshLayout.setRefreshing(false);
    }

    public void getInmueble(){

        Log.d("TAG_Control",buscar.toString());

        Call<List<Propiedad>> callInmueble= propiedadApi.filterInmuebles(buscar);
        callInmueble.enqueue(new Callback<List<Propiedad>>() {
            @Override
            public void onResponse(Call<List<Propiedad>> call, Response<List<Propiedad>> response) {
                cancelar();
                Log.d("TAG_Control",response.body().toString());
                if(response.isSuccessful()){
                    if(((List<Propiedad>)response.body()).isEmpty())
                        adapter.setMoreDataAvailable(false);
                    else {
                        items.addAll(response.body());
                    }

                }else{
                    Log.e(TAG," Response Error "+String.valueOf(response.code()));
                }
                adapter.notifyDataChanged();
            }

            @Override
            public void onFailure(Call<List<Propiedad>> call, Throwable t) {
                cancelar();
                Log.e(TAG," Response Error "+t.getMessage());
            }
        });
    }

    public void getEmpresa(){

        Log.d("TAG_Control",buscar.toString());
        Call<List<Empresa>> callEmpresa= empresaApi.getEmpresas(buscar);
        callEmpresa.enqueue(new Callback<List<Empresa>>() {
            @Override
            public void onResponse(Call<List<Empresa>> call, Response<List<Empresa>> response) {
                cancelar();
                if(response.isSuccessful()){
                    if(((List<Empresa>)response.body()).isEmpty())
                        adapter.setMoreDataAvailable(false);
                    else {
                        items.addAll(response.body());
                    }

                }else{
                    Log.e(TAG," Response Error "+String.valueOf(response.code()));
                }
                adapter.notifyDataChanged();
            }

            @Override
            public void onFailure(Call<List<Empresa>> call, Throwable t) {
                cancelar();
                Log.e(TAG," Response Error "+t.getMessage());
            }
        });

    }

    public void getNoticia(){

        Call<List<Noticia>> callNoticia= noticiaApi.getNoticias(buscar.getPage());
        callNoticia.enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Call<List<Noticia>> call, Response<List<Noticia>> response) {
                cancelar();
                if(response.isSuccessful()){
                    if(((List<Noticia>)response.body()).isEmpty())
                        adapter.setMoreDataAvailable(false);
                    else {
                        items.addAll(response.body());
                    }

                }else{
                    Log.e(TAG," Response Error "+String.valueOf(response.code()));
                }
                adapter.notifyDataChanged();
            }

            @Override
            public void onFailure(Call<List<Noticia>> call, Throwable t) {
                cancelar();
                Log.e(TAG," Response Error "+t.getMessage());
            }
        });

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void getEmpresaInmuebles(){

        Log.d("TAG_Control","id:"+id+" "+buscar.toString());

        Call<List<Propiedad>> callInmueble= propiedadApi.getPropiedades(id,buscar.getPage());
        callInmueble.enqueue(new Callback<List<Propiedad>>() {
            @Override
            public void onResponse(Call<List<Propiedad>> call, Response<List<Propiedad>> response) {
                cancelar();
                Log.d("TAG_Control",response.body().toString());
                if(response.isSuccessful()){
                    if(((List<Propiedad>)response.body()).isEmpty())
                        adapter.setMoreDataAvailable(false);
                    else {
                        items.addAll(response.body());
                    }

                }else{
                    Log.e(TAG," Response Error "+String.valueOf(response.code()));
                }
                adapter.notifyDataChanged();
            }

            @Override
            public void onFailure(Call<List<Propiedad>> call, Throwable t) {
                cancelar();
                Log.e(TAG," Response Error "+t.getMessage());
            }
        });

    }

}
