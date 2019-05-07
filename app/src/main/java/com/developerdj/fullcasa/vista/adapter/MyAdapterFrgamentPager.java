package com.developerdj.fullcasa.vista.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by hp on 22/5/2017.
 */

public class MyAdapterFrgamentPager extends FragmentPagerAdapter {


    private List<Fragment> fragments;

    /** Constructor of the class */
    public MyAdapterFrgamentPager(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<Fragment>();    }

    public void addFragment(Fragment fragmento) {
        fragments.add(fragmento);
    }

    /** This method will be invoked when a page is requested to create */
    @Override
    public Fragment getItem(int arg0) {

        return fragments.get(arg0);
    }

    /** Returns the number of pages */
    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

}
