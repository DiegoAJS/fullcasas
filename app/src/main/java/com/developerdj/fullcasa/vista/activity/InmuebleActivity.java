package com.developerdj.fullcasa.vista.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developerdj.fullcasa.R;
import com.developerdj.fullcasa.modelo.Empresa;
import com.developerdj.fullcasa.modelo.Inmueble;
import com.developerdj.fullcasa.modelo.Propiedad;
import com.developerdj.fullcasa.util.Fuentes;
import com.developerdj.fullcasa.util.SharedPreference;



/**
 * Created by hp on 25/1/2017.
 */

public class InmuebleActivity extends AppCompatActivity {

    public static String TAG_id = "inmueble.id";
    public static String TAG_titulo = "inmueble.titulo";
    public static String TAG_descripcion = "inmueble.descripcion";
    public static String TAG_cuartos= "inmueble.cuartos";
    public static String TAG_nbanios = "inmueble.nbanios";
    public static String TAG_salas= "inmueble.salas";
    public static String TAG_npatios = "inmueble.npatios";
    public static String TAG_ngaraje = "inmueble.ngaraje";
    public static String TAG_agua = "inmueble.agua";
    public static String TAG_luz = "inmueble.luz";
    public static String TAG_gas = "inmueble.gas";
    public static String TAG_ofrecen= "inmueble.ofrecen";
    public static String TAG_ubicacion = "inmueble.ubicacion";
    public static String TAG_areaTerreno= "inmueble.areaTerreno";
    public static String TAG_superficieConstruccion = "inmueble.superficieConstruccion";
    public static String TAG_precio = "inmueble.precio";
    public static String TAG_modoEntrega = "inmueble.modoEntrega";
    public static String TAG_fecha = "inmueble.fecha";
    public static String TAG_codigo = "inmueble.codigo";

    public static String TAG_imagenes = "inmueble.imagenes";

    public static String TAG_idEmpresa = "inmueble.empresa.id";
    public static String TAG_nombreEmpresa = "inmueble.empresa.nombre";
    public static String TAG_direccionEmpresa = "inmueble.empresa.direccion";
    public static String TAG_telefonoEmpresa = "inmueble.empresa.telefono";
    public static String TAG_celularEmpresa = "inmueble.empresa.celular";
    public static String TAG_emailEmpresa = "inmueble.empresa.email";
    public static String TAG_logoEmpresa = "inmueble.empresa.logo";

    //inmueble view
    private ImageView parallax;
    private TextView galeria,descripcion, titulo, cuartos,banios,salas,patios,garajes,agua,luz,gas,ubicacion,area,suferficie,precio,entrega,fecha;
    private TextView cuarto,banio,sala,patio,garaje,agu,lu,ga,are,superfici,entreg;

    //empresa view
    private TextView empresa, direccion, telefonos;
    private ImageView logo_empresa, masInformacion;
    private LinearLayout ll_empresa;
    private FloatingActionButton fab_llamar;

    private Propiedad propiedad = null;
    private Context context;

    public static void createInstance(Context context, Propiedad propiedad) {
        Intent intent = getLaunchIntent(context, propiedad);
        context.startActivity(intent);
    }

   public static Intent getLaunchIntent(Context context, Propiedad propiedad) {
        Intent intent = new Intent(context, InmuebleActivity.class);

       intent.putExtra(TAG_id,propiedad.getPropiedad().getIdPropiedad());
       intent.putExtra(TAG_titulo,propiedad.getPropiedad().getTitulo());
       intent.putExtra(TAG_descripcion,propiedad.getPropiedad().getDescripcion());
       intent.putExtra(TAG_cuartos,propiedad.getPropiedad().getCuartos());
       intent.putExtra(TAG_nbanios,propiedad.getPropiedad().getBanios());
       intent.putExtra(TAG_salas,propiedad.getPropiedad().getSalas());
       intent.putExtra(TAG_npatios,propiedad.getPropiedad().getPatios());
       intent.putExtra(TAG_ngaraje,propiedad.getPropiedad().getGarajes());
       intent.putExtra(TAG_agua,propiedad.getPropiedad().getAgua());
       intent.putExtra(TAG_luz,propiedad.getPropiedad().getLuz());
       intent.putExtra(TAG_gas,propiedad.getPropiedad().getGas());
       intent.putExtra(TAG_ofrecen,propiedad.getPropiedad().getQue_ofrece());
       intent.putExtra(TAG_ubicacion,propiedad.getPropiedad().getUbicacion());
       intent.putExtra(TAG_areaTerreno,propiedad.getPropiedad().getArea_terreno());
       intent.putExtra(TAG_superficieConstruccion,propiedad.getPropiedad().getSuperficie_construccion());
       intent.putExtra(TAG_precio,propiedad.getPropiedad().getPrecio());
       intent.putExtra(TAG_modoEntrega,propiedad.getPropiedad().getModo_entrega());
       intent.putExtra(TAG_fecha,propiedad.getPropiedad().getFecha());
       intent.putExtra(TAG_codigo,propiedad.getPropiedad().getCodigo());

       intent.putStringArrayListExtra(TAG_imagenes,propiedad.getImagenes());

       intent.putExtra(TAG_idEmpresa,propiedad.getEmpresa().getIdEmpresa());
       intent.putExtra(TAG_nombreEmpresa,propiedad.getEmpresa().getRazon_social());
       intent.putExtra(TAG_direccionEmpresa,propiedad.getEmpresa().getDireccion());
       intent.putExtra(TAG_telefonoEmpresa,propiedad.getEmpresa().getTelefono());
       intent.putExtra(TAG_celularEmpresa,propiedad.getEmpresa().getCelular());
       intent.putExtra(TAG_emailEmpresa,propiedad.getEmpresa().getEmail());
       intent.putExtra(TAG_logoEmpresa,propiedad.getEmpresa().getLogo());

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inmueble);

        context=this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_inmueble);
        setSupportActionBar(toolbar);

        precio =( TextView) findViewById(R.id.tv_precio_inmueble);
        precio.setTypeface(Fuentes.semi_bold_italic(context));
        galeria=( TextView) findViewById(R.id.tv_galeria_inmueble);
        galeria.setTypeface(Fuentes.light(context));
        descripcion=( TextView) findViewById(R.id.tv_descripcion_inmueble);
        titulo=( TextView) findViewById(R.id.tv_titulo_inmueble);
        titulo.setTypeface(Fuentes.bold(context));
        cuartos=( TextView) findViewById(R.id.tv_cuartos_inmueble);
        cuartos.setTypeface(Fuentes.regular(context));
        cuarto=( TextView) findViewById(R.id.tv_cuartos);
        cuarto.setTypeface(Fuentes.regular(context));
        banios=( TextView) findViewById(R.id.tv_banios_inmueble);
        banios.setTypeface(Fuentes.regular(context));
        banio=( TextView) findViewById(R.id.tv_banios);
        banio.setTypeface(Fuentes.regular(context));
        salas=( TextView) findViewById(R.id.tv_salas_inmueble);
        salas.setTypeface(Fuentes.regular(context));
        sala=( TextView) findViewById(R.id.tv_salas);
        sala.setTypeface(Fuentes.regular(context));
        patios=( TextView) findViewById(R.id.tv_patios_inmueble);
        patios.setTypeface(Fuentes.regular(context));
        patio=( TextView) findViewById(R.id.tv_patios);
        patio.setTypeface(Fuentes.regular(context));
        garajes=( TextView) findViewById(R.id.tv_garajes_inmueble);
        garajes.setTypeface(Fuentes.regular(context));
        garaje=( TextView) findViewById(R.id.tv_garajes);
        garaje.setTypeface(Fuentes.regular(context));
        agua=( TextView) findViewById(R.id.tv_agua_inmueble);
        agua.setTypeface(Fuentes.regular(context));
        agu=( TextView) findViewById(R.id.tv_agua);
        agu.setTypeface(Fuentes.regular(context));
        luz=( TextView) findViewById(R.id.tv_luz_inmueble);
        luz.setTypeface(Fuentes.regular(context));
        lu=( TextView) findViewById(R.id.tv_luz);
        lu.setTypeface(Fuentes.regular(context));
        gas=( TextView) findViewById(R.id.tv_gas_inmueble);
        gas.setTypeface(Fuentes.regular(context));
        ga=( TextView) findViewById(R.id.tv_gas);
        ga.setTypeface(Fuentes.regular(context));
        ubicacion=( TextView) findViewById(R.id.tv_ubicacion_inmueble);
        ubicacion.setTypeface(Fuentes.regular(context));
        area=( TextView) findViewById(R.id.tv_area_inmueble);
        area.setTypeface(Fuentes.regular(context));
        are=( TextView) findViewById(R.id.tv_area);
        are.setTypeface(Fuentes.regular(context));
        suferficie=( TextView) findViewById(R.id.tv_superficie_inmueble);
        suferficie.setTypeface(Fuentes.regular(context));
        superfici=( TextView) findViewById(R.id.tv_superficie);
        superfici.setTypeface(Fuentes.regular(context));
        entrega=( TextView) findViewById(R.id.tv_modoentrega_inmueble);
        entrega.setTypeface(Fuentes.regular(context));
        entreg=( TextView) findViewById(R.id.tv_modoentrega);
        entreg.setTypeface(Fuentes.regular(context));
        fecha=( TextView) findViewById(R.id.tv_fecha_inmueble);
        fecha.setTypeface(Fuentes.extra_light_italic(context));

        parallax= (ImageView)findViewById(R.id.iv_image_paralax_inmueble);

        empresa=( TextView) findViewById(R.id.tv_empresa_inmueble);
        empresa.setTypeface(Fuentes.bold(context));
        direccion=( TextView) findViewById(R.id.tv_direccion_inmueble);
        direccion.setTypeface(Fuentes.regular(context));
        telefonos=( TextView) findViewById(R.id.tv_telefono_inmueble);
        telefonos.setTypeface(Fuentes.regular(context));

        logo_empresa=(ImageView)findViewById(R.id.iv_logo_inmueble);
        masInformacion = (ImageView) findViewById(R.id.iv_empresa_inmueble);
        ll_empresa=(LinearLayout)findViewById(R.id.ll_empresa_inmueble);
        fab_llamar=(FloatingActionButton) findViewById(R.id.fab_inmueble);

        actualizar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        propiedad=null;
    }


    public void actualizar()
    {
        getInmueble();

        setTitle(propiedad.getPropiedad().getQue_ofrece());

        descripcion.setText(propiedad.getPropiedad().getDescripcion());
        titulo.setText(propiedad.getPropiedad().getTitulo());
        cuartos.setText(String.valueOf(propiedad.getPropiedad().getCuartos()));
        banios.setText(String.valueOf(propiedad.getPropiedad().getBanios()));
        salas.setText(String.valueOf(propiedad.getPropiedad().getSalas()));
        patios.setText(String.valueOf(propiedad.getPropiedad().getPatios()));
        garajes.setText(String.valueOf(propiedad.getPropiedad().getGarajes()));
        agua.setText(propiedad.getPropiedad().getAgua());
        luz.setText(propiedad.getPropiedad().getLuz());
        gas.setText(propiedad.getPropiedad().getGas());
        ubicacion.setText(propiedad.getPropiedad().getUbicacion());
        area.setText(String.valueOf(propiedad.getPropiedad().getArea_terreno()));
        suferficie.setText(String.valueOf(propiedad.getPropiedad().getSuperficie_construccion()));
        precio.setText(propiedad.getPropiedad().getPrecio());
        entrega.setText(propiedad.getPropiedad().getModo_entrega());
        fecha.setText(propiedad.getPropiedad().getFecha());

        empresa.setText(propiedad.getEmpresa().getRazon_social());
        direccion.setText(propiedad.getEmpresa().getDireccion());
        telefonos.setText(propiedad.getEmpresa().getTelefonos());

        Glide.with(this)
                .load(propiedad.getEmpresa().getLogo())
                .into(logo_empresa);

        if(propiedad.getImagenes().size()>0)
            Glide.with(this)
                    .load(propiedad.getImagenes().get(0))
                    .into(parallax);

        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GaleriaActivity.createInstance(context,propiedad.getImagenes());

            }
        });

        fab_llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ propiedad.getEmpresa().getCelular()));
                startActivity(intent);
            }
        });

        if(!SharedPreference.isEmpresaActivity(this)){
            masInformacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EmpresaActivity.createInstance(context,propiedad.getEmpresa());
                }
            });
            SharedPreference.setEmpresaActivity(this,true);
        }else {
            masInformacion.setVisibility(View.INVISIBLE);
        }

    }

    public void getInmueble(){

        if(propiedad==null) {
            propiedad = new Propiedad();
        }
        Intent i = getIntent();

        Inmueble inmueble =new Inmueble();
        inmueble.setIdPropiedad(i.getIntExtra(TAG_id,0));
        inmueble.setTitulo(i.getStringExtra(TAG_titulo));
        inmueble.setDescripcion(i.getStringExtra(TAG_descripcion));
        inmueble.setCuartos(i.getIntExtra(TAG_cuartos,0));
        inmueble.setBanios(i.getIntExtra(TAG_nbanios,0));
        inmueble.setSalas(i.getIntExtra(TAG_salas,0));
        inmueble.setPatios(i.getIntExtra(TAG_npatios,0));
        inmueble.setGarajes(i.getIntExtra(TAG_ngaraje,0));
        inmueble.setAgua(i.getStringExtra(TAG_agua));
        inmueble.setLuz(i.getStringExtra(TAG_luz));
        inmueble.setGas(i.getStringExtra(TAG_gas));
        inmueble.setQue_ofrece(i.getStringExtra(TAG_ofrecen));
        inmueble.setUbicacion(i.getStringExtra(TAG_ubicacion));
        inmueble.setArea_terreno(i.getDoubleExtra(TAG_areaTerreno,0));
        inmueble.setSuperficie_construccion(i.getDoubleExtra(TAG_superficieConstruccion,0));
        inmueble.setPrecio(i.getStringExtra(TAG_precio));
        inmueble.setModo_entrega(i.getStringExtra(TAG_modoEntrega));
        inmueble.setFecha(i.getStringExtra(TAG_fecha));
        inmueble.setCodigo(i.getStringExtra(TAG_codigo));

        propiedad.setPropiedad(inmueble);

        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(i.getIntExtra(TAG_idEmpresa,0));
        empresa.setRazon_social(i.getStringExtra(TAG_nombreEmpresa));
        empresa.setDireccion(i.getStringExtra(TAG_direccionEmpresa));
        empresa.setTelefono(i.getStringExtra(TAG_telefonoEmpresa));
        empresa.setCelular(i.getStringExtra(TAG_celularEmpresa));
        empresa.setEmail(i.getStringExtra(TAG_emailEmpresa));
        empresa.setLogo(i.getStringExtra(TAG_logoEmpresa));

        propiedad.setEmpresa(empresa);

        propiedad.setImagenes(i.getStringArrayListExtra(TAG_imagenes));

    }


}

