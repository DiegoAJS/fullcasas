package com.developerdj.fullcasa.vista.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developerdj.fullcasa.R;
import com.developerdj.fullcasa.modelo.Noticia;
import com.developerdj.fullcasa.util.Fuentes;


/**
 * Created by Diego on 10/05/2017.
 */

public class NoticiaActivity extends AppCompatActivity {

    public static String TAG_id="id";
    public static String TAG_titulo="titulo";
    public static String TAG_contenido="contenido";
    public static String TAG_fecha="fecha";
    public static String TAG_imagen="imagen";
    public static String TAG_by="by";
    public static String TAG_id_empresa="idempresa";

    private TextView titulo, contenido, fecha, by;
    private ImageView imagen;

    private Noticia noticia=null;
    private Context context;

    public static void createInstance (Context context, Noticia noticia){
        Intent intent = getLaunchIntent(context,noticia);
        context.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context, Noticia noticia) {
        Intent intent = new Intent(context, NoticiaActivity.class);
        intent.putExtra(TAG_id,noticia.getId());
        intent.putExtra(TAG_titulo,noticia.getTitulo());
        intent.putExtra(TAG_contenido,noticia.getContenido());
        intent.putExtra(TAG_fecha,noticia.getFecha());
        intent.putExtra(TAG_imagen,noticia.getImagen());
        intent.putExtra(TAG_by,noticia.getBy());
        intent.putExtra(TAG_id_empresa,noticia.getId_empresa());
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_noticia);
        setSupportActionBar(toolbar);

        contenido = (TextView) findViewById(R.id.tv_contenido_noticia);
        contenido.setTypeface(Fuentes.regular(this));
        fecha = (TextView) findViewById(R.id.tv_fecha_noticia);
        fecha.setTypeface(Fuentes.light_italic(this));
        by = (TextView) findViewById(R.id.tv_by_noticia);
        by.setTypeface(Fuentes.medium_italic(this));
        titulo = (TextView) findViewById(R.id.tv_titulo_noticia);
        titulo.setTypeface(Fuentes.bold(this));

        imagen = (ImageView) findViewById(R.id.iv_image_noticia);

        actualizar();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noticia = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putInt(TAG_id, noticia.getId());
        outState.putString(TAG_titulo, noticia.getTitulo());
        outState.putString(TAG_contenido, noticia.getContenido());
        outState.putString(TAG_fecha, noticia.getFecha());
        outState.putString(TAG_by, noticia.getBy());
        outState.putString(TAG_imagen, noticia.getImagen());
        outState.putInt(TAG_id_empresa, noticia.getId_empresa());
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        if(noticia==null){
            noticia = new Noticia();
        }

        noticia.setId(savedInstanceState.getInt(TAG_id,0));
        noticia.setTitulo(savedInstanceState.getString(TAG_titulo));
        noticia.setContenido(savedInstanceState.getString(TAG_contenido));
        noticia.setFecha(savedInstanceState.getString(TAG_fecha));
        noticia.setBy(savedInstanceState.getString(TAG_by));
        noticia.setImagen(savedInstanceState.getString(TAG_imagen));
        noticia.setId_empresa(savedInstanceState.getInt(TAG_id_empresa,0));

    }

    public void actualizar(){

        getNoticia();

        setTitle("Noticias");

        titulo.setText(noticia.getTitulo());
        contenido.setText(noticia.getContenido());
        fecha.setText(noticia.getFecha());
        by.setText("Por: "+noticia.getBy());
        Glide.with(this).load(noticia.getImagen()).into(imagen);

    }

    public void getNoticia(){

        if(noticia==null){
            noticia=new Noticia();
        }

        Intent i = getIntent();

        noticia.setId(i.getIntExtra(TAG_id,0));
        noticia.setTitulo(i.getStringExtra(TAG_titulo));
        noticia.setContenido(i.getStringExtra(TAG_contenido));
        noticia.setFecha(i.getStringExtra(TAG_fecha));
        noticia.setBy(i.getStringExtra(TAG_by));
        noticia.setImagen(i.getStringExtra(TAG_imagen));
        noticia.setId_empresa(i.getIntExtra(TAG_id_empresa,0));

    }
}
