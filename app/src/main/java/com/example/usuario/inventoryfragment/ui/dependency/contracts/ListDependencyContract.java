package com.example.usuario.inventoryfragment.ui.dependency.contracts;

import com.example.usuario.inventoryfragment.data.db.model.Dependency;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by usuario on 23/11/17.
 */

public interface ListDependencyContract {

    interface View {
        void setPresenter(ListDependencyContract.Presenter presenter);
        void loadDependency(ArrayList<Dependency> dependencies);
        Dependency getDependency(int position);
        void closeActionMode();
    }


    interface Presenter {
        void loadDependency();
        void onDestroy();
        void deleteDependency(Dependency dependency);

        void removeSelection(int position);
        void setNewSelection(int position);
        void deleteSelection();

        void clearSelecion();

        boolean isPositionChecked(int position);

        Dependency getDependency(int position);

        void checkedActionMode();
    }

    interface Interactor{
        void loadDependency(OnFinishLoadDependency onFinishLoadDependency);
        interface OnFinishLoadDependency{
            void onSuccess(ArrayList<Dependency> dependencies);
        }
        void deleteDependency(Dependency dependency);
        void deleteDependency(Integer position);
    }
}
