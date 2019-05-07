package com.developerdj.fullcasa.vista.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.developerdj.fullcasa.R;
import com.developerdj.fullcasa.data.TDepartamento;
import com.developerdj.fullcasa.modelo.Departamento;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragmento con diálogo de lista simple
 */
public class DepartamentosDialog extends DialogFragment {

    private List<Departamento> itemsDepartamento=new ArrayList<Departamento>();


    public DepartamentosDialog() {

    }

    public interface OnDialogListener {
        void onSelectItemClick(int id);
    }

    // Interfaz de comunicación
    OnDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        TDepartamento.getInstance(getContext()).lista(itemsDepartamento);
        itemsDepartamento.add(0,new Departamento(0,"Todos"));

        return createSingleListDialog();
    }

    /**
     * Crea un Diálogo con una lista de selección simple
     *
     * @return La instancia del diálogo
     */
    public AlertDialog createSingleListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setTitle(getString(R.string.departamentos))
                .setItems(getDepartementos(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onSelectItemClick(itemsDepartamento.get(which).getId());

                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            listener = (OnDialogListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(
                    activity.toString() +
                            " no implementó OnSimpleDialogListener");

        }
    }


    public CharSequence[] getDepartementos(){
        CharSequence[] items = new CharSequence[itemsDepartamento.size()];
        for (int i=0; i<itemsDepartamento.size();i++)
            items[i]=itemsDepartamento.get(i).getNombre();
        return items;

    }

}

