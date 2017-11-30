package com.example.usuario.inventoryfragment.ui.dependency.interactors;

import com.example.usuario.inventoryfragment.data.db.model.Dependency;
import com.example.usuario.inventoryfragment.data.db.repository.DependencyRepository;
import com.example.usuario.inventoryfragment.ui.dependency.contracts.AddeditDependencyContract;

/**
 * Created by usuario on 24/11/17.
 */

public class AddeditDependecyInteractor implements AddeditDependencyContract.Interactor {


    @Override
    public void validateDependency(String name, String shortname, String description, onValidateFinish onValidateFinish) {
        if(name.isEmpty())
            onValidateFinish.onEmptyName();
        else if(description.isEmpty())
            onValidateFinish.onEmptyDescription();
        else if(shortname.isEmpty() || shortname.length() >= 5)
            onValidateFinish.onErrorShortName();
        else if(!DependencyRepository.getInstance().validate(name,shortname))
            onValidateFinish.onErrorShortName();
        else {
            DependencyRepository.getInstance().addDependency(new Dependency(6,name,shortname,description));
            onValidateFinish.onSuccess();
        }
    }

    @Override
    public void editDependency(String name, String shorname, String description, onValidateFinish onValidateFinish) {
        if(description.isEmpty())
            onValidateFinish.onEmptyDescription();
        else {
            DependencyRepository.getInstance().editDependency(name, shorname, description);
            onValidateFinish.onSuccess();
        }
    }
}
