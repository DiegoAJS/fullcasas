package com.developerdj.fullcasa.vista.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import com.developerdj.fullcasa.R;
import com.developerdj.fullcasa.vista.control.ItemControl;


/**
 * Created by hp on 25/1/2017.
 */

public class NoticiasFragment extends Fragment {

    private ItemControl control=new ItemControl();

    public NoticiasFragment() {
        // Required empty public constructor
        //control.setUrl("http://fullcasas.com/API/v1/noticias?");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public static NoticiasFragment newInstance() {
        return new NoticiasFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_lista, container, false);

        control.setupFragment(getContext(),root,getFragmentManager(), ItemControl.HOLDER_NOTICIA);

        return root;
    }




}
