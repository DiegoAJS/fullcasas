package com.developerdj.fullcasa.vista.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developerdj.fullcasa.R;
import com.developerdj.fullcasa.modelo.Empresa;
import com.developerdj.fullcasa.util.Fuentes;
import com.developerdj.fullcasa.util.SharedPreference;
import com.developerdj.fullcasa.vista.control.ItemControl;


/**
 * Created by hp on 25/1/2017.
 */

public class EmpresaActivity extends AppCompatActivity {

    public static String TAG_id="id";
    public static String TAG_nombre="nombre";
    public static String TAG_direccion="direccion";
    public static String TAG_telefono="telefono";
    public static String TAG_celular="celular";
    public static String TAG_email="email";
    public static String TAG_logo="logo";

    private TextView direccion, telefono, celular, email;
    private ImageView avatar;

    private Empresa empresa=null;
    private ItemControl control = new ItemControl();

    public static void createInstance(Context context, Empresa empresa) {
        Intent intent = getLaunchIntent(context,empresa);
        context.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context, Empresa empresa) {
        Intent intent = new Intent(context, EmpresaActivity.class);
        intent.putExtra(TAG_id,empresa.getIdEmpresa());
        intent.putExtra(TAG_nombre,empresa.getRazon_social());
        intent.putExtra(TAG_direccion,empresa.getDireccion());
        intent.putExtra(TAG_telefono,empresa.getTelefono());
        intent.putExtra(TAG_celular,empresa.getCelular());
        intent.putExtra(TAG_email,empresa.getEmail());
        intent.putExtra(TAG_logo,empresa.getLogo());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_empresa);
        setSupportActionBar(toolbar);


        direccion = (TextView) findViewById(R.id.tv_direccion_empresa);
        direccion.setTypeface(Fuentes.regular(this));
        telefono = (TextView) findViewById(R.id.tv_telefono_empresa);
        telefono.setTypeface(Fuentes.regular(this));
        celular = (TextView) findViewById(R.id.tv_celular_empresa);
        celular.setTypeface(Fuentes.regular(this));
        email = (TextView) findViewById(R.id.tv_email_empresa);
        email.setTypeface(Fuentes.regular(this));

        avatar = (ImageView) findViewById(R.id.iv_avatar_empresa);

        SharedPreference.setEmpresaActivity(this,true);

        getEmpresa();
        eventos();

        control.setId(empresa.getIdEmpresa());
        control.setupActivityEmprersa(this,ItemControl.HOLDER_EMPRESA_INMUEBLES);

    }

    private void getEmpresa(){
        if(empresa==null)
            empresa= new Empresa();
        Intent intent = getIntent();
        empresa.setIdEmpresa(intent.getIntExtra(TAG_id,0));
        empresa.setRazon_social(intent.getStringExtra(TAG_nombre));
        empresa.setDireccion(intent.getStringExtra(TAG_direccion));
        empresa.setTelefono(intent.getStringExtra(TAG_telefono));
        empresa.setCelular(intent.getStringExtra(TAG_celular));
        empresa.setEmail(intent.getStringExtra(TAG_email));
        empresa.setLogo(intent.getStringExtra(TAG_logo));
        actualizar();
    }

    private void eventos(){
        telefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+telefono.getText().toString()));
                startActivity(intent);
            }
        });
        celular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+celular.getText().toString()));
                startActivity(intent);
            }
        });
    }

    private void actualizar(){
        setTitle(empresa.getRazon_social());
        direccion.setText(empresa.getDireccion());
        telefono.setText(empresa.getTelefono());
        celular.setText(empresa.getCelular());
        email.setText(empresa.getEmail());

        Glide.with(this)
                .load(empresa.getLogo())
                .into(avatar);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(TAG_id, empresa.getIdEmpresa());
        outState.putString(TAG_nombre,empresa.getRazon_social());
        outState.putString(TAG_direccion,empresa.getDireccion());
        outState.putString(TAG_telefono,empresa.getTelefono());
        outState.putString(TAG_celular, empresa.getCelular());
        outState.putString(TAG_email, empresa.getEmail());
        outState.putString(TAG_logo,empresa.getLogo());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(empresa==null)
            empresa=new Empresa();
        empresa.setIdEmpresa(savedInstanceState.getInt(TAG_id));
        empresa.setRazon_social(savedInstanceState.getString(TAG_nombre));
        empresa.setDireccion(savedInstanceState.getString(TAG_direccion));
        empresa.setTelefono(savedInstanceState.getString(TAG_telefono));
        empresa.setCelular(savedInstanceState.getString(TAG_celular));
        empresa.setEmail(savedInstanceState.getString(TAG_email));
        empresa.setLogo(savedInstanceState.getString(TAG_logo));
    }

}
