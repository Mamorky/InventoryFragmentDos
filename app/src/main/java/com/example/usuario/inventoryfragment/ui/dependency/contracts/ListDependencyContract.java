package com.example.usuario.inventoryfragment.ui.dependency.contracts;

import com.example.usuario.inventoryfragment.data.db.model.Dependency;

import java.util.ArrayList;

/**
 * Created by usuario on 23/11/17.
 */

public interface ListDependencyContract {

    interface View {
        void setPresenter(ListDependencyContract.Presenter presenter);
        void loadDependency(ArrayList<Dependency> dependencies);
    }


    interface Presenter {
        void loadDependency();
        void onDestroy();
        void deleteDependency(Dependency dependency);
    }

    interface Interactor{
        void loadDependency(OnFinishLoadDependency onFinishLoadDependency);
        interface OnFinishLoadDependency{
            void onSuccess(ArrayList<Dependency> dependencies);
        }
        void deleteDependency(Dependency dependency);
    }
}
