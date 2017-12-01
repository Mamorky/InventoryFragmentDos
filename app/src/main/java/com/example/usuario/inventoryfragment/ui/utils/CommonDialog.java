package com.example.usuario.inventoryfragment.ui.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.usuario.inventoryfragment.R;
import com.example.usuario.inventoryfragment.data.db.model.Dependency;
import com.example.usuario.inventoryfragment.ui.dependency.contracts.ListDependencyContract;

/**
 * Created by mamorky on 30/11/17.
 */

public class CommonDialog {

    public static final String MESSAGE = "message";
    public static final String TITLE = "titulo";
    public static final String DEPSELECT = "depselect";

    public static Dialog showConfirmDialog(Bundle bundle, final Context context, final ListDependencyContract.Presenter presenter){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final Dependency dependency = (Dependency) bundle.getParcelable(CommonDialog.DEPSELECT);
        builder.setMessage(bundle.getString(CommonDialog.MESSAGE)).
                setTitle(bundle.getString(CommonDialog.TITLE)).setPositiveButton(R.string.btnOk, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.deleteDependency(dependency);
            }
        }).setNegativeButton(R.string.btnCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }
}
