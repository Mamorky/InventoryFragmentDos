package com.example.usuario.inventoryfragment.ui.dependency;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.usuario.inventoryfragment.R;
import com.example.usuario.inventoryfragment.adapters.DependencyAdapter;
import com.example.usuario.inventoryfragment.data.db.model.Dependency;
import com.example.usuario.inventoryfragment.ui.dependency.contracts.AddeditDependencyContract;
import com.example.usuario.inventoryfragment.ui.dependency.presenters.AddeditDependencyPresenter;
import com.example.usuario.inventoryfragment.ui.dependency.presenters.ListDependencyPresenter;
import com.example.usuario.inventoryfragment.ui.utils.AddEdit;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddeditDependencyFragment extends Fragment implements AddeditDependencyContract.View {
    public static final String TAG = "addeditdependency";
    private AddeditDependencyContract.Presenter presenter;
    FloatingActionButton fab;
    TextInputLayout tilname,tilshortname,tildescription;
    EditText edtname,edtshortname,edtdescription;
    AddeditDependencyListener callback;
    private AddEdit mode;

    interface  AddeditDependencyListener{
        void goBack();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new AddeditDependencyPresenter(this);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (AddeditDependencyListener) activity;
    }

    public static AddeditDependencyFragment newInstance(Bundle bundle) {
        AddeditDependencyFragment addeditDependencyFragment = new AddeditDependencyFragment();

        if (bundle != null)
            addeditDependencyFragment.setArguments(bundle);

        return addeditDependencyFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_addedit_dependency, container, false);

        // Como el fragment mantiene el estado (y solo se elimina la vista) se debe reinicializar el presenter cuando se cree la vista


        fab = (FloatingActionButton) root.findViewById(R.id.fab_dependencyAdd);
        edtname = (EditText) root.findViewById(R.id.edt_dependency_name);
        edtshortname = (EditText) root.findViewById(R.id.edt_dependency_sortname);
        edtdescription = (EditText) root.findViewById(R.id.edt_dependency_description);

        tilname = (TextInputLayout) root.findViewById(R.id.til_dependency_name);

        edtname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilname.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edtshortname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilshortname.setError(null);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtdescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tildescription.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tilshortname = (TextInputLayout) root.findViewById(R.id.til_dependency_sortname);
        tildescription = (TextInputLayout) root.findViewById(R.id.til_dependency_description);


        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            mode = new AddEdit(AddEdit.EDIT_MODE);
            Dependency dependency = getArguments().getParcelable("edit");
            edtname.setText(dependency.getName());
            edtname.setEnabled(false);
            edtshortname.setText(dependency.getShortname());
            edtshortname.setEnabled(false);
            edtdescription.setText(dependency.getDescription());
        }else{
            mode = new AddEdit();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mode.getMode() == AddEdit.ADD_MODE)
                    presenter.validateDependency(tilname.getEditText().getText().toString(),tilshortname.getEditText().getText().toString(),tildescription.getEditText().getText().toString());
                else if(mode.getMode() == AddEdit.EDIT_MODE)
                    presenter.editDependency(tilname.getEditText().getText().toString(),tilshortname.getEditText().getText().toString(),tildescription.getEditText().getText().toString());
            }
        });
    }

    @Override
    public void setPresenter(AddeditDependencyContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setOnEmptyName() {
        tilname.setError("Nombre vacio");
    }

    @Override
    public void setOnShortNameError() {
        tilshortname.setError("Nombre corto erroneo");
    }

    @Override
    public void setOnEmptyDescription() {
        tildescription.setError("Descripcion vacia");
    }

    @Override
    public void onSuccess() {
        callback.goBack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter = null;
    }
}
