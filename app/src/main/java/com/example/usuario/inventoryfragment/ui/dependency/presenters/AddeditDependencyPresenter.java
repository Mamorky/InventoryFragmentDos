package com.example.usuario.inventoryfragment.ui.dependency.presenters;

import com.example.usuario.inventoryfragment.ui.dependency.interactors.AddeditDependecyInteractor;
import com.example.usuario.inventoryfragment.ui.dependency.contracts.AddeditDependencyContract;

/**
 * Created by usuario on 23/11/17.
 */

public class AddeditDependencyPresenter implements AddeditDependencyContract.Presenter, AddeditDependencyContract.Interactor.onValidateFinish {
    private AddeditDependencyContract.View view;
    private AddeditDependecyInteractor interactor;


    public AddeditDependencyPresenter (AddeditDependencyContract.View view) {
        this.view = view;
        interactor = new AddeditDependecyInteractor();
    }


    @Override
    public void validateDependency(String name, String shortname, String description) {
        interactor.validateDependency(name,shortname,description,this);
    }

    @Override
    public void editDependency(String name, String shortname, String description) {
        interactor.editDependency(name,shortname,description,this);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }

    @Override
    public void onEmptyName() {
        view.setOnEmptyName();
    }

    @Override
    public void onErrorShortName() {
        view.setOnShortNameError();
    }

    @Override
    public void onEmptyDescription() {
        view.setOnEmptyDescription();
    }

    @Override
    public void onSuccess() {
        view.onSuccess();
    }

}
