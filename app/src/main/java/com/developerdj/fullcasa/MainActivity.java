package com.developerdj.fullcasa;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.developerdj.fullcasa.util.SharedPreference;
import com.developerdj.fullcasa.vista.adapter.MyAdapterFrgamentPager;
import com.developerdj.fullcasa.vista.dialog.InformacionDialog;
import com.developerdj.fullcasa.vista.fragment.EmpresasFragment;
import com.developerdj.fullcasa.vista.fragment.InmueblesFragment;
import com.developerdj.fullcasa.vista.fragment.NoticiasFragment;

public class MainActivity extends AppCompatActivity  {

    private static final String TAG = MainActivity.class.getSimpleName().toLowerCase();
    private Context context=this;
    private MyAdapterFrgamentPager myAdapterFrgamentPage;
    private ViewPager viewPagerMain;

    public static void createInstance(Context context) {
        Intent intent = getLaunchIntent(context);
        context.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPagerMain = (ViewPager) findViewById(R.id.view_pager_main);

        setSupportActionBar(toolbar);

        setTitle(getResources().getString(R.string.inicio));

        myAdapterFrgamentPage = new MyAdapterFrgamentPager(getSupportFragmentManager());

        myAdapterFrgamentPage.addFragment(InmueblesFragment.newInstance());
        myAdapterFrgamentPage.addFragment(EmpresasFragment.newInstance());
        myAdapterFrgamentPage.addFragment(NoticiasFragment.newInstance());

        viewPagerMain.setAdapter(myAdapterFrgamentPage);

        viewPagerMain.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                limpiarBotones();
                switch (position){
                    case 0:setTitle("Inmuebles");
                        ((ImageView)findViewById(R.id.iv_inicio_main)).setBackgroundResource(R.drawable.boton_background);break;
                    case 1:setTitle("Empresas");
                        ((ImageView)findViewById(R.id.iv_empresas_main)).setBackgroundResource(R.drawable.boton_background);break;
                    case 2:setTitle("Noticias");
                        ((ImageView)findViewById(R.id.iv_avisos_main)).setBackgroundResource(R.drawable.boton_background);break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onClickFragment(View view){
        getFragment(view.getId());
    }

    private void getFragment(int n){

        limpiarBotones();

        switch (n){
            case R.id.iv_inicio_main:
                viewPagerMain.setCurrentItem(0);setTitle("Inmuebles");
                ((ImageView)findViewById(R.id.iv_inicio_main)).setBackgroundResource(R.drawable.boton_background);break;
            case R.id.iv_empresas_main:
                viewPagerMain.setCurrentItem(1);setTitle("Empresas");
                ((ImageView)findViewById(R.id.iv_empresas_main)).setBackgroundResource(R.drawable.boton_background);break;
            case R.id.iv_avisos_main:
                viewPagerMain.setCurrentItem(2);setTitle("Noticias");
                ((ImageView)findViewById(R.id.iv_avisos_main)).setBackgroundResource(R.drawable.boton_background);break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.info_main:
                new InformacionDialog().show(getSupportFragmentManager(), getResources().getString(R.string.info));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Proyecta una {@link Snackbar} con el string usado
     *
     * @param msg Mensaje
     */
    private void showSnackBar(String msg) {
        Snackbar
                .make(findViewById(R.id.coordinator), msg, Snackbar.LENGTH_LONG)
                .show();
    }

    private void limpiarBotones(){
        ImageView informacion,inmuebles,avisos;
        informacion=(ImageView)findViewById(R.id.iv_inicio_main);
        inmuebles=(ImageView)findViewById(R.id.iv_empresas_main);
        avisos=(ImageView)findViewById(R.id.iv_avisos_main);

        informacion.setBackgroundResource(R.drawable.boton_background_dos);
        inmuebles.setBackgroundResource(R.drawable.boton_background_dos);
        avisos.setBackgroundResource(R.drawable.boton_background_dos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreference.setEmpresaActivity(this,false);
    }




}
