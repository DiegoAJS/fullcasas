package com.developerdj.fullcasa.control;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.developerdj.fullcasa.R;
import com.developerdj.fullcasa.vista.fragment.EmpresasFragment;
import com.developerdj.fullcasa.vista.fragment.InmueblesFragment;
import com.developerdj.fullcasa.vista.fragment.NoticiasFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 25/1/2017.
 */

public class ControlFragment {

    private int max =  10;

    private FragmentManager fm;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    public static Fragment fragmentAnteriror;

    public ControlFragment(FragmentManager fm) {
        this.fm=fm;

        fragmentAnteriror=InmueblesFragment.newInstance();

        fragments.add(fragmentAnteriror);
        fragments.add(EmpresasFragment.newInstance());
        fragments.add(NoticiasFragment.newInstance());
    }


    public void insertar(Fragment f)
    {
        if(fragments.size()>max)
            borrar();
        fragments.add(f);
    }

    public Fragment quitar(){
        fragments.remove(fragments.size());
        return fragments.get(fragments.size());
    }

    public void borrar(){
        fragments.clear();
    }

    public void runFragment(int i)
    {
        fm.beginTransaction()
            .replace(R.id.content_main, fragments.get(i))
            .commit();
        fragmentAnteriror=fragments.get(i);
    }


}
