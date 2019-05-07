package com.developerdj.fullcasa.vista.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.developerdj.fullcasa.R;
import com.developerdj.fullcasa.vista.activity.BuscarEmpresaActivity;
import com.developerdj.fullcasa.vista.control.ItemControl;

/**
 * Created by hp on 14/2/2017.
 */

public class EmpresasFragment  extends Fragment{

    public static final String TAG=EmpresasFragment.class.getSimpleName();

    private ItemControl itemControl=new ItemControl();


    public EmpresasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        //itemControl.setUrl("http://fullcasas.com/API/v1/empresas?");

    }

    public static EmpresasFragment newInstance() {
        return new EmpresasFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_lista, container, false);

        itemControl.setupFragment(getActivity(),root,getFragmentManager(),ItemControl.HOLDER_EMPRESA);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.buscar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_buscar:
                BuscarEmpresaActivity.createInstance(getActivity());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



   /* public void actualizarBuscador(){
        if(departamento==0&&texto.length()==0){
            itemControl.setUrl("http://fullcasas.com/API/v1/empresas?");
        }else if(texto.length()>0&&departamento==0){
            itemControl.setUrl("http://fullcasas.com/API/v1/empresas?s="+texto);
        }else if(texto.length()==0&&departamento>0){
            itemControl.setUrl("http://fullcasas.com/API/v1/empresas?departamento="+String.valueOf(departamento));
        }else {
            itemControl.setUrl("http://fullcasas.com/API/v1/empresas?s="+texto+"&departamento="+String.valueOf(departamento));
        }

        itemControl.clear();

    }*/

}
