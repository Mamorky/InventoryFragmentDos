package com.example.usuario.inventoryfragment.ui.dependency.interactors;

import com.example.usuario.inventoryfragment.data.db.model.Dependency;
import com.example.usuario.inventoryfragment.data.db.repository.DependencyRepository;
import com.example.usuario.inventoryfragment.ui.dependency.contracts.ListDependencyContract;

/**
 * Created by usuario on 27/11/17.
 */

public class ListDependencyInteractor implements ListDependencyContract.Interactor {
    @Override
    public void loadDependency(OnFinishLoadDependency onFinishLoadDependency) {
        onFinishLoadDependency.onSuccess(DependencyRepository.getInstance().getDependencies());
    }

    @Override
    public void deleteDependency(Dependency dependency) {
        DependencyRepository.getInstance().deleteDependency(dependency);
    }
}
