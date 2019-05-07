package com.developerdj.fullcasa.vista.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.developerdj.fullcasa.R;
import com.github.chrisbanes.photoview.PhotoView;


/**
 * Created by hp on 22/5/2017.
 */

public class ImagenFragment extends Fragment {

    public static String TAG_imagen="fragment,imagen";
    private PhotoView photoView;
    private String urlImagen=null;

    public static ImagenFragment newInstance(String urlImagen) {
        ImagenFragment fragment = new ImagenFragment();
        Bundle bundle= new Bundle();
        bundle.putString(TAG_imagen,urlImagen);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_imagen, container, false);
        photoView = (PhotoView) view.findViewById(R.id.ivZoom);
        getUrlImengen();
        inicializar();
        return view;
    }

    private void getUrlImengen()
    {
        if(urlImagen==null)
            urlImagen=getArguments().getString(TAG_imagen);

    }

    private void inicializar(){
        if (urlImagen!=null)
            Glide.with(getActivity()).load(urlImagen).into(photoView);
    }
}
