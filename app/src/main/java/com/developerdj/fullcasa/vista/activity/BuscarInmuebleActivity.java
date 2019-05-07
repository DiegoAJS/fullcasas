package com.developerdj.fullcasa.vista.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.developerdj.fullcasa.R;
import com.developerdj.fullcasa.modelo.Buscar;
import com.developerdj.fullcasa.vista.control.ItemControl;
import com.developerdj.fullcasa.vista.dialog.BuscarDialog;

/**
 * Created by hp on 19/9/2017.
 */

public class BuscarInmuebleActivity extends AppCompatActivity {

    public static final String TAG = BuscarInmuebleActivity.class.getSimpleName().toLowerCase();

    private String url="http://fullcasas.com/API/v1/propiedades?";

    private SearchView mSearchView=null;
    private ItemControl itemControl = new ItemControl();
    private BuscarDialog buscarDialog= new BuscarDialog();


    public static void createInstance(Context context) {
        Intent intent = getLaunchIntent(context);
        context.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context) {
        Intent intent = new Intent(context, BuscarInmuebleActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_buscar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //itemControl.setUrl("http://fullcasas.com/API/v1/propiedades?");
        itemControl.setupActivity(this,ItemControl.HOLDER_INMUEBLE);
        setTitle(getString(R.string.buscar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_buscar, menu);

        if(mSearchView==null){
            MenuItem item = menu.findItem(R.id.menu_buscar_activity);
            mSearchView = (SearchView)item.getActionView();
            menuEvento();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_buscar_activity:
                return true;
            case R.id.menu_filtrar_activity:
                buscarDialog.setup(itemControl);
                buscarDialog.show(getSupportFragmentManager(),TAG);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void menuEvento() {

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //actualizarBuscador();
                setTitle(query);
                mSearchView.setIconified(true);
                mSearchView.onActionViewCollapsed();
                buscarVehiculo();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                itemControl.buscar.setTitulo(newText);

                return false;
            }
        });
    }

    public void buscarVehiculo(){

        itemControl.buscar.setTitulo(getTitle().toString());

        //itemControl.setUrl(url+buscar.getLink());
        itemControl.clear();

    }
}
