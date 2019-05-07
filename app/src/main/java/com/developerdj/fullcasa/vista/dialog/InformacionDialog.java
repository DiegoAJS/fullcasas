package com.developerdj.fullcasa.vista.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.developerdj.fullcasa.R;
import com.developerdj.fullcasa.util.Fuentes;


/**
 * Created by hp on 25/1/2017.
 */

public class InformacionDialog extends DialogFragment {
    private static final String TAG = InformacionDialog.class.getSimpleName();

    public InformacionDialog() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createLoginDialogo();
    }

    /**
     * Crea un diálogo con personalizado para comportarse
     * como formulario de login
     *
     * @return Diálogo
     */
    public AlertDialog createLoginDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dialog_fragment_info, null);

        Button ok = (Button) v.findViewById(R.id.bt_ok_info);
        TextView  nombre_empresa, descripcion, direc, direccion, telf, telefono, correo, email, web, version;

        nombre_empresa=(TextView) v.findViewById(R.id.tv_empresa_name);
        nombre_empresa.setTypeface(Fuentes.semi_bold(getContext()));
        descripcion=(TextView) v.findViewById(R.id.tv_empresa_info);
        descripcion.setTypeface(Fuentes.bold_italic(getContext()));
        direc=(TextView) v.findViewById(R.id.tv_direc);
        direc.setTypeface(Fuentes.medium(getContext()));
        direccion=(TextView)v.findViewById(R.id.tv_empresa_direc);
        direccion.setTypeface(Fuentes.regular(getContext()));
        telf=(TextView)v.findViewById(R.id.tv_telf);
        telf.setTypeface(Fuentes.medium(getContext()));
        telefono=(TextView)v.findViewById(R.id.tv_empresa_telf);
        telefono.setTypeface(Fuentes.regular(getContext()));
        correo=(TextView)v.findViewById(R.id.tv_correo);
        correo.setTypeface(Fuentes.medium(getContext()));
        email=(TextView)v.findViewById(R.id.tv_empresa_correo);
        email.setTypeface(Fuentes.regular(getContext()));
        web=(TextView)v.findViewById(R.id.tv_empresa_web);
        web.setTypeface(Fuentes.medium_italic(getContext()));
        version=(TextView)v.findViewById(R.id.tv_app_version);
        version.setTypeface(Fuentes.light(getContext()));

        ok.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }
        );

        builder.setView(v);

        return builder.create();
    }
}
