package com.example.usuario.inventoryfragment.ui.dependency.contracts;

import com.example.usuario.inventoryfragment.data.db.model.Dependency;

/**
 * Created by usuario on 23/11/17.
 */

public interface AddeditDependencyContract {

    interface View {
        void setPresenter(AddeditDependencyContract.Presenter  presenter);
        void setOnEmptyName();
        void setOnShortNameError();
        void setOnEmptyDescription();
        void onSuccess();
    }


    interface Presenter {
        void validateDependency(String name,String shortname,String description);
        void editDependency(String name,String shortname, String description);
        void onDestroy();

    }

    interface Interactor{
        void validateDependency(String name, String shortname, String description, onValidateFinish onValidateFinish);
        void editDependency(String name, String shorname,String description, onValidateFinish onValidateFinish);
        interface onValidateFinish{
             void onEmptyName();
             void onErrorShortName();
             void onEmptyDescription();
             void onSuccess();
        }
    }
}
