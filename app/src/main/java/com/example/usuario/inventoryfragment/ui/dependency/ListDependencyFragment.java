package com.example.usuario.inventoryfragment.ui.dependency;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.usuario.inventoryfragment.R;
import com.example.usuario.inventoryfragment.adapters.DependencyAdapter;
import com.example.usuario.inventoryfragment.data.db.model.Dependency;
import com.example.usuario.inventoryfragment.data.db.repository.DependencyRepository;
import com.example.usuario.inventoryfragment.ui.dependency.contracts.ListDependencyContract;
import com.example.usuario.inventoryfragment.ui.dependency.presenters.ListDependencyPresenter;
import com.example.usuario.inventoryfragment.ui.utils.CommonDialog;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListDependencyFragment extends ListFragment implements ListDependencyContract.View {
    public static final String TAG = "listdependency";
    private ListDependencyContract.Presenter presenter;
    private ListDependencyListener mCallback;
    private DependencyAdapter adapter;
    private FloatingActionButton fabAdd;

    private DependencyMultiChoice dependencyMultiChoice;

    interface ListDependencyListener {
        void addNewDependency();
        void editDependency(Bundle bundle);
    }


    public static ListDependencyFragment newInstance(Bundle bundle) {
        ListDependencyFragment listDependencyFragment = new ListDependencyFragment();

        if (bundle != null)
            listDependencyFragment.setArguments(bundle);

        return listDependencyFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new DependencyAdapter(getActivity());
        this.presenter = new ListDependencyPresenter(this);
        setRetainInstance(true);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (ListDependencyListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().getLocalClassName() + " must implements ListDepedencyListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_dependency, container, false);

        fabAdd = (FloatingActionButton) root.findViewById(R.id.fab_dependencyAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActionMode();
                mCallback.addNewDependency();
            }
        });
        presenter.loadDependency();

        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("edit", (Dependency) adapterView.getItemAtPosition(i));
                mCallback.editDependency(bundle);
            }
        });
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        dependencyMultiChoice = new DependencyMultiChoice((ListDependencyPresenter) presenter);
        getListView().setMultiChoiceModeListener(dependencyMultiChoice);


        //Pulsación Larga del elemento
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                getListView().setItemChecked(position,!presenter.isPositionChecked(position));
                return false;
            }
        });

        //Registro de menu contexual
        //registerForContextMenu(getListView());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_fragment_list_dependency_order,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case  R.id.action_list_depen_order_name:
                break;
            case R.id.action_list_depen_order_id:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Título");
        getActivity().getMenuInflater().inflate(R.menu.menu_fragment_list_depenendency,menu);
    }

    /**
     * Implementar las diferentes acciones del menu contextual
     * @param item
     * @return
     * */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_list_depen_delete:
                presenter.deleteSelection();
                presenter.loadDependency();
                break;
            default:
                super.onContextItemSelected(item);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void setPresenter(ListDependencyContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadDependency(ArrayList<Dependency> dependencies) {
        adapter.clear();
        adapter.addAll(dependencies);
    }

    @Override
    public Dependency getDependency(int position) {
        return adapter.getItem(position);
    }

    @Override
    public void closeActionMode() {
        dependencyMultiChoice.removeActionMode();
    };
}
