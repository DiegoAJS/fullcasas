package com.developerdj.fullcasa.vista.dialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.developerdj.fullcasa.R;
import com.developerdj.fullcasa.data.TCiudad;
import com.developerdj.fullcasa.data.TDepartamento;
import com.developerdj.fullcasa.data.TInmueble;
import com.developerdj.fullcasa.data.TOferta;
import com.developerdj.fullcasa.modelo.Buscar;
import com.developerdj.fullcasa.modelo.Ciudad;
import com.developerdj.fullcasa.modelo.Departamento;
import com.developerdj.fullcasa.modelo.TipoInmueble;
import com.developerdj.fullcasa.modelo.TipoOferta;
import com.developerdj.fullcasa.vista.control.ItemControl;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hp on 19/4/2017.
 */

public class BuscarDialog extends DialogFragment {

    private static final String TAG = BuscarDialog.class.getSimpleName();

    private String url="http://fullcasas.com/API/v1/propiedades?";

    private Context context;

    private EditText min,max;

    private Spinner departamento,ciudad;
    private Spinner cuartos,banios;
    private Spinner oferta,inmueble;

    private List<Departamento> departamentos = new ArrayList<Departamento>();
    private List<Ciudad> ciudades = new ArrayList<Ciudad>();
    private List<TipoOferta> ofertas = new ArrayList<TipoOferta>();
    private List<TipoInmueble> inmuebles = new ArrayList<TipoInmueble>();
    private ArrayAdapter<Ciudad> adapterCiudades;

    private String[] numeros ;

    private ImageView ok_buscar,close_cancelar;

    private ItemControl itemControl;


    private TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent event)
        {
            boolean action = false;
            if (actionId == EditorInfo.IME_ACTION_SEARCH)
            {
                if(!min.getText().toString().isEmpty())
                    itemControl.buscar.setPmin(min.getText().toString());
                if(!max.getText().toString().isEmpty())
                    itemControl.buscar.setPmax(max.getText().toString());
                itemControl.clear();

                action = true;
                dismiss();
            }
            return action;
        }
    };

    public BuscarDialog() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createDialogo();
    }

    /**
     * Crea un diálogo con personalizado para comportarse
     * como formulario de login
     *
     * @return Diálogo
     */
    public AlertDialog createDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        context=getActivity();

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_buscar, null);


        max=(EditText)view.findViewById(R.id.et_maxp_buscar);
        min=(EditText)view.findViewById(R.id.et_minp_buscar);


        departamento=(Spinner)view.findViewById(R.id.sp_departamento_buscar);
        ciudad=(Spinner)view.findViewById(R.id.sp_ciudad_buscar);

        banios=(Spinner)view.findViewById(R.id.sp_banios_buscar);
        cuartos=(Spinner)view.findViewById(R.id.sp_cuartos_buscar);

        oferta=(Spinner)view.findViewById(R.id.sp_oferta_buscar);
        inmueble=(Spinner)view.findViewById(R.id.sp_inmueble_buscar);

        numeros=context.getResources().getStringArray(R.array.array_numeros);

        ok_buscar=(ImageView) view.findViewById(R.id.iv_imagen_buscar);
        close_cancelar=(ImageView) view.findViewById(R.id.iv_cancelar_buscar);

        inicializar();
        eventos();

        builder.setView(view);

        return builder.create();
    }

    private void inicializar(){

        TDepartamento.getInstance(context).lista(departamentos);
        departamentos.add(0,new Departamento(0,"Todos los departamentos"));
        //TCiudad.getInstance(buscarDialog.getContext()).lista(ciudades);

        TOferta.getInstance(context).lista(ofertas);
        ofertas.add(0,new TipoOferta(0,"Todos"));

        TInmueble.getInstance(context).lista(inmuebles);
        inmuebles.add(0,new TipoInmueble(0,"Todos"));

        ArrayAdapter<Departamento> adapterDepartemento =
                new ArrayAdapter<Departamento>(context, android.R.layout.simple_spinner_item, departamentos);
        adapterDepartemento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departamento.setAdapter(adapterDepartemento);

        adapterCiudades =
                new ArrayAdapter<Ciudad>(context, android.R.layout.simple_spinner_item, ciudades);
        adapterCiudades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ciudad.setAdapter(adapterCiudades);

        ArrayAdapter<TipoOferta> adapterOfertas =
                new ArrayAdapter<TipoOferta>(context, android.R.layout.simple_spinner_item, ofertas);
        adapterOfertas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oferta.setAdapter(adapterOfertas);

        ArrayAdapter<TipoInmueble> adapterInmueble=
                new ArrayAdapter<TipoInmueble>(context, android.R.layout.simple_spinner_item, inmuebles);
        adapterInmueble.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inmueble.setAdapter(adapterInmueble);

        ArrayAdapter<String> adapterCuarto=
                new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, numeros);
        adapterCuarto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cuartos.setAdapter(adapterCuarto);

        ArrayAdapter<String> adapterbanio=
                new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, numeros);
        adapterbanio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        banios.setAdapter(adapterbanio);
    }

    private void eventos(){
        oferta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(ofertas.get(position).getId()>0)
                    itemControl.buscar.setTipos_ofertas(String.valueOf(ofertas.get(position).getId()));
                else
                    itemControl.buscar.setTipos_ofertas(null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        inmueble.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (inmuebles.get(position).getId()>0)
                    itemControl.buscar.setTipos_inmuebles(String.valueOf(inmuebles.get(position).getId()));
                else
                    itemControl.buscar.setTipos_inmuebles(null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        departamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (departamentos.get(position).getId()>0){
                    itemControl.buscar.setDepartamento(String.valueOf(departamentos.get(position).getId()));
                }else {
                    itemControl.buscar.setDepartamento(null);
                }
                getCiudades(departamentos.get(position).getId());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ciudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (ciudades.get(position).getId()>0)
                    itemControl.buscar.setCiudad(String.valueOf(ciudades.get(position).getId()));
                else
                    itemControl.buscar.setCiudad(null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        min.setOnEditorActionListener(onEditorActionListener);
        max.setOnEditorActionListener(onEditorActionListener);

        ok_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //itemControl.setUrl(url+getLinkBuscar());
                if(!min.getText().toString().isEmpty())
                    itemControl.buscar.setPmin(min.getText().toString());
                if(!max.getText().toString().isEmpty())
                    itemControl.buscar.setPmax(max.getText().toString());
                itemControl.clear();
                dismiss();
            }
        });

        close_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }


    public void getCiudades(int id){
        TCiudad.getInstance(context).lista(ciudades,id);
        if(id==0)
            ciudades.add(0,new Ciudad(0,"Todos",0));
        else if (ciudades.size()>0)
            ciudades.add(0,new Ciudad(0,"Todas las provincias",0));
        else
            ciudades.add(0,new Ciudad(0,"Sin registros",0));

        adapterCiudades.notifyDataSetChanged();
    }

    public void setup(ItemControl itemControl){
        this.itemControl=itemControl;
    }


}