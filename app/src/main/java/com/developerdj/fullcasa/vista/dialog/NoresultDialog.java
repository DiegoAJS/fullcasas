package com.developerdj.fullcasa.vista.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.developerdj.fullcasa.R;


/**
 * Created by hp on 29/1/2017.
 */

public class NoresultDialog extends DialogFragment {

    private static final String TAG = NoresultDialog.class.getSimpleName();
    private SwipeRefreshLayout refreshLayout;

    public NoresultDialog() {
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

        View v = inflater.inflate(R.layout.dialog_noresult, null);

        Button ok = (Button) v.findViewById(R.id.bt_cancelar_dialog_cargando);

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
