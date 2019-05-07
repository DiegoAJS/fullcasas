package com.developerdj.fullcasa.vista.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.developerdj.fullcasa.R;
import com.developerdj.fullcasa.vista.activity.BuscarInmuebleActivity;
import com.developerdj.fullcasa.vista.control.ItemControl;

/**
 * Created by hp on 25/1/2017.
 */

public class InmueblesFragment extends Fragment {

    public static final String TAG=InmueblesFragment.class.getSimpleName();

    public ItemControl control=new ItemControl();

    public InmueblesFragment() {
        // Required empty public constructor
    }

    public static InmueblesFragment newInstance() {
        InmueblesFragment inmueblesFragment=new InmueblesFragment();
        return inmueblesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //control.setUrl("http://fullcasas.com/API/v1/propiedades?");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_lista, container, false);
        control.setupFragment(getActivity(),rootview,getFragmentManager(),ItemControl.HOLDER_INMUEBLE);
        return rootview;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.buscar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_buscar:
                BuscarInmuebleActivity.createInstance(getActivity());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
