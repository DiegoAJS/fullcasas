package com.developerdj.fullcasa.vista.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.developerdj.fullcasa.R;
import com.developerdj.fullcasa.vista.adapter.MyAdapterFrgamentPager;
import com.developerdj.fullcasa.vista.fragment.ImagenFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hp on 22/5/2017.
 */

public class GaleriaActivity extends AppCompatActivity {

    //variables staticas
    public static String TAG_imagenes="GaleriaActivity.imagenes";

    //variables views
    private LinearLayout nav_imagenes;
    private ImageView next,previus;
    private ViewPager viewPager;

    //variables nativos
    private ArrayList<String> imagenes=null;

    private MyAdapterFrgamentPager myAdapterFrgamentPager;

    private List<ImageView> itemImageView= new ArrayList<ImageView>();

    public static void createInstance(Context context, ArrayList<String> imagenes) {
        Intent intent = getLaunchIntent(context, imagenes);
        context.startActivity(intent);
    }


    public static Intent getLaunchIntent(Context context, ArrayList<String> imagenes) {
        Intent intent = new Intent(context, GaleriaActivity.class);
        intent.putStringArrayListExtra(TAG_imagenes,imagenes);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        nav_imagenes=(LinearLayout)findViewById(R.id.layoutDots);
        next=(ImageView) findViewById(R.id.iv_next_galeria);
        previus=(ImageView) findViewById(R.id.iv_previous_galeria);

        viewPager=(ViewPager)findViewById(R.id.view_pager);

        getImagenes();

        inicializar();
        eventos();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList(TAG_imagenes, imagenes);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        imagenes = savedInstanceState.getStringArrayList(TAG_imagenes);

    }

    //seccion de metodos

    private void getImagenes(){
        Intent intent = getIntent();
        if (imagenes==null){
            imagenes=intent.getStringArrayListExtra(TAG_imagenes);
        }
    }

    private void inicializar(){

        myAdapterFrgamentPager=new MyAdapterFrgamentPager(getSupportFragmentManager());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(5,5,5,5);

        for(String s: imagenes){
            myAdapterFrgamentPager.addFragment(ImagenFragment.newInstance(s));
            ImageView iv=new ImageView(this);
            iv.setImageResource(R.drawable.ic_lens_off);
            itemImageView.add(iv);
            nav_imagenes.addView(iv,layoutParams);
        }
        itemImageView.get(0).setImageResource(R.drawable.ic_lens_on);
        viewPager.setAdapter(myAdapterFrgamentPager);
        previus.setVisibility(View.INVISIBLE);
    }

    private void eventos(){
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ImageView iv=itemImageView.get(position);

                next.setVisibility(View.VISIBLE);
                previus.setVisibility(View.VISIBLE);

                for (ImageView imageView:itemImageView)
                    imageView.setImageResource(R.drawable.ic_lens_off);
                iv.setImageResource(R.drawable.ic_lens_on);
                if (position==0)
                    previus.setVisibility(View.INVISIBLE);
                if (position==itemImageView.size()-1)
                    next.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemImageView.size()>viewPager.getCurrentItem())
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        });
        previus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem()>0)
                    viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
            }
        });


    }

}
