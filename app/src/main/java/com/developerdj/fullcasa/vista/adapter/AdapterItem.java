package com.developerdj.fullcasa.vista.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.developerdj.fullcasa.R;
import com.developerdj.fullcasa.modelo.Empresa;
import com.developerdj.fullcasa.modelo.Inmueble;
import com.developerdj.fullcasa.modelo.Noticia;
import com.developerdj.fullcasa.modelo.Propiedad;
import com.developerdj.fullcasa.util.Fuentes;
import com.developerdj.fullcasa.vista.activity.EmpresaActivity;
import com.developerdj.fullcasa.vista.activity.InmuebleActivity;
import com.developerdj.fullcasa.vista.activity.NoticiaActivity;
import com.developerdj.fullcasa.vista.adapter.Interfaces.ItemClickListener;
import com.developerdj.fullcasa.vista.adapter.Interfaces.OnLoadMoreListener;
import com.developerdj.fullcasa.modelo.Footer;
import com.developerdj.fullcasa.modelo.Item;


import java.util.List;

/**
 * Created by hp on 15/8/2017.
 */

public class AdapterItem extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemClickListener {

    private Context context;
    private List<Item> items;

    OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;

    private static final int TYPE_INMUEBLE = 0;
    private static final int TYPE_EMPRESA = 1;
    private static final int TYPE_NOTICIA = 2;
    private static final int TYPE_FOOTER = 3;

    public AdapterItem(@NonNull List<Item> items, Context context) {
        this.items = items;
        this.context=context;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Propiedad) {
            return TYPE_INMUEBLE;
        } else if (items.get(position) instanceof Empresa) {
            return TYPE_EMPRESA;
        } else if (items.get(position) instanceof Noticia) {
            return TYPE_NOTICIA;
        }else if(items.get(position) instanceof Footer){
            return TYPE_FOOTER;
        }else
            throw new RuntimeException("ItemViewType unknown");
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        RecyclerView.ViewHolder viewHolder=null;
        switch (viewType){
            case TYPE_INMUEBLE:
                viewHolder = new InmuebleHolder(inflater.inflate(R.layout.item_inmueble,parent,false),this);break;
            case TYPE_EMPRESA:
                viewHolder = new EmpresaHolder(inflater.inflate(R.layout.item_empresa,parent,false),this);break;
            case TYPE_NOTICIA:
                viewHolder =  new NoticiaHolder(inflater.inflate(R.layout.item_noticia,parent,false),this);break;
            case TYPE_FOOTER:
                viewHolder =  new LoadHolder(inflater.inflate(R.layout.item_load,parent,false));break;

        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(position>=getItemCount()-1 && isMoreDataAvailable && !isLoading && loadMoreListener!=null){
            isLoading = true;
            loadMoreListener.onLoadMore();
        }

        if(getItemViewType(position)==TYPE_INMUEBLE){
            ((InmuebleHolder)holder).bindData(context,(Propiedad)items.get(position));
        }else if(getItemViewType(position)==TYPE_EMPRESA){
            ((EmpresaHolder)holder).bindData(context,(Empresa)items.get(position));
        }else if(getItemViewType(position)==TYPE_NOTICIA){
            ((NoticiaHolder)holder).bindData(context,(Noticia)items.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (getItemViewType(position))
        {
            case TYPE_INMUEBLE:
                InmuebleActivity.createInstance(context,(Propiedad) items.get(position));break;
            case TYPE_EMPRESA:
                Empresa empresa = (Empresa) items.get(position);
                empresa.updateId();
                EmpresaActivity.createInstance(context,empresa);break;
            case TYPE_NOTICIA:
                NoticiaActivity.createInstance(context,(Noticia) items.get(position));break;
        }
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    /* notifyDataSetChanged is final method so we can't override it
         call adapter.notifyDataChanged(); after update the list
         */
    public void notifyDataChanged(){
        notifyDataSetChanged();
        isLoading = false;
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    //viewHolder inmueble
    public static class InmuebleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Campos respectivos de un item
        public TextView empresa,titulo, subTitulo;
        public ImageView imagen,share;
        public ItemClickListener listener;

        public InmuebleHolder(View v, ItemClickListener listener) {
            super(v);

            empresa = (TextView) v.findViewById(R.id.tv_empresa_item_inmueble);
            empresa.setTypeface(Fuentes.semi_bold(v.getContext()));
            titulo = (TextView) v.findViewById(R.id.tv_titulo_item_inmueble);
            titulo.setTypeface(Fuentes.medium(v.getContext()));
            subTitulo = (TextView) v.findViewById(R.id.tv_descripcion_item_inmueble);
            subTitulo.setTypeface(Fuentes.italic(v.getContext()));

            imagen = (ImageView) v.findViewById(R.id.iv_imagen_item_inmueble);
            share = (ImageView) v.findViewById(R.id.iv_share_item_inmueble);

            this.listener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }

        public void bindData(final Context context, Propiedad propiedad){

            empresa.setText(propiedad.getEmpresa().getRazon_social());
            titulo.setText(propiedad.getPropiedad().getTitulo());

            if(propiedad.getPropiedad().getEstado()==1){
                subTitulo.setText(propiedad.getPropiedad().getPrecio());

                final String share = propiedad.getEmpresa().getRazon_social()
                        + " anuncia "+ propiedad.getPropiedad().getQue_ofrece()
                        + " a " + propiedad.getPropiedad().getPrecio()
                        + " anunciate aqui con " + context.getResources().getString(R.string.app_name)+" http://fullcasas.com";

                this.share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT,share);
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT,R.string.app_name);
                        context.startActivity(Intent.createChooser(sharingIntent, "Compartir"));

                    }
                });
                this.share.setImageResource(R.drawable.compartir);
            }else{
                subTitulo.setText("No disponible");
                share.setImageResource(R.drawable.ic_emoticon_sad);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            }

            if(propiedad.getImagenes().size()>0){
                imagen.setMinimumHeight(260);
                Glide.with(context)
                        .load(propiedad.getImagenes().get(0))
                        .into(imagen);
            }

        }
    }

    //viewHolder Empresa
    public static class EmpresaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Campos respectivos de un item
        public TextView empresa, descripcion;
        public ImageView logo;
        public ItemClickListener listener;

        public EmpresaHolder(View v, ItemClickListener listener) {
            super(v);

            logo=(ImageView) v.findViewById(R.id.iv_logo_item_empresa);
            empresa = (TextView) v.findViewById(R.id.tv_empresa_item_empresa);
            empresa.setTypeface(Fuentes.semi_bold(v.getContext()));
            descripcion = (TextView) v.findViewById(R.id.tv_direccion_item_empresa);
            descripcion.setTypeface(Fuentes.regular(v.getContext()));

            this.listener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }

        public void bindData(Context context,Empresa empresa){

            this.empresa.setText(empresa.getRazon_social());
            this.descripcion.setText(empresa.getDireccion());

            Glide.with(context)
                    .load(empresa.getLogo())
                    .into(this.logo);

        }
    }

    //viewHolder Noticia
    public static class NoticiaHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imagen;
        public TextView titulo;
        public TextView fecha;
        public TextView contenido;
        public TextView by,por;
        public ItemClickListener listener;

        public NoticiaHolder(View v, ItemClickListener listener){
            super(v);

            imagen =(ImageView) v.findViewById(R.id.imagen_noticia);
            titulo =(TextView) v.findViewById(R.id.texto_titulo);
            titulo.setTypeface(Fuentes.semi_bold(v.getContext()));
            fecha =(TextView) v.findViewById(R.id.texto_fecha);
            fecha.setTypeface(Fuentes.light_italic(v.getContext()));
            contenido =(TextView) v.findViewById(R.id.texto_contenido);
            contenido.setTypeface(Fuentes.regular(v.getContext()));
            by =(TextView) v.findViewById(R.id.texto_autor);
            by.setTypeface(Fuentes.italic(v.getContext()));
            por = (TextView) v.findViewById(R.id.texto_by);
            por.setTypeface(Fuentes.italic(v.getContext()));

            this.listener = listener;
            v.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }

        public void bindData(Context context, Noticia noticia){

            titulo.setText(noticia.getTitulo());
            fecha.setText(noticia.getFecha());
            contenido.setText(noticia.getContenido());
            by.setText(noticia.getBy());
            Glide.with(context).load(noticia.getImagen()).centerCrop().into(imagen);
        }
    }

    //viewHolder Load
    static class LoadHolder extends RecyclerView.ViewHolder{
        public LoadHolder(View itemView) {
            super(itemView);
        }
    }



}
