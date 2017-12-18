package com.example.usuario.inventoryfragment.ui.dependency.presenters;

import com.example.usuario.inventoryfragment.data.db.model.Dependency;
import com.example.usuario.inventoryfragment.ui.dependency.interactors.ListDependencyInteractor;
import com.example.usuario.inventoryfragment.ui.dependency.contracts.ListDependencyContract;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by usuario on 23/11/17.
 */

public class ListDependencyPresenter implements ListDependencyContract.Presenter,ListDependencyContract.Interactor.OnFinishLoadDependency {
    ListDependencyContract.View view;
    ListDependencyInteractor interactor;

    public ListDependencyPresenter(ListDependencyContract.View view) {
        this.view = view;
        interactor = new ListDependencyInteractor();
    }

    @Override
    public void loadDependency() {
        interactor.loadDependency(this);
    }


    @Override
    public void onSuccess(ArrayList<Dependency> dependencies) {
        view.loadDependency(dependencies);
    }

    @Override
    public void onDestroy(){
        view = null;
        interactor = null;
    }

    @Override
    public void deleteDependency(Dependency dependency) {
        interactor.deleteDependency(dependency);
        interactor.loadDependency(this);
    }

    //Métodos que gestionan la selección multiple de una lista
    @Override
    public void deleteSelection() {
        for(Integer position:selection.keySet()){
            interactor.deleteDependency(position);
        }
    }

    HashMap<Integer,Boolean> selection = new HashMap<>();
    @Override
    public void removeSelection(int position) {
        selection.remove(position);
    }

    @Override
    public void setNewSelection(int position) {
        selection.put(position,true);
    }

    @Override
    public void clearSelecion() {
        selection.clear();
    }

    /**
     * Comprobar si el elemento existe en el mapa
     * @param  position
     * @return
     **/
    @Override
    public boolean isPositionChecked(int position) {
        return selection.get(position) == null?false:true;
    }
}
