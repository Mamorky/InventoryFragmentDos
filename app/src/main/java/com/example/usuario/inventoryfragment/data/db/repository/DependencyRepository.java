package com.example.usuario.inventoryfragment.data.db.repository;

import android.support.annotation.NonNull;

import com.example.usuario.inventoryfragment.data.db.model.Dependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * Repositorio con los datos de Dependency.
 */
public class DependencyRepository{
    private ArrayList<Dependency> dependencies;
    private static DependencyRepository instance;


    private DependencyRepository() {
        this.dependencies = new ArrayList<>();
        initialize();
    }


    public static DependencyRepository getInstance() {
        if (instance == null)
            instance = new DependencyRepository();

        return instance;
    }


    private void initialize() {
        addDependency(new Dependency(1, "1º Ciclo Formativo Grado Superior",
                "ZZZ", "1CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(2, "2º Ciclo Formativo Grado Superior",
                "GGG", "2CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(3, "1º Ciclo Formativo Grado Superior",
                "BBB", "1CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(4, "2º Ciclo Formativo Grado Superior",
                "BBB", "2CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(5, "1º Ciclo Formativo Grado Superior",
                "HHH", "1CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(6, "2º Ciclo Formativo Grado Superior",
                "HHH", "2CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(7, "1º Ciclo Formativo Grado Superior",
                "EEE", "1CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(8, "2º Ciclo Formativo Grado Superior",
                "EEE", "2CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(9, "1º Ciclo Formativo Grado Superior",
                "KKK", "1CFGS Desarrollo de Aplicaciones Multiplataformma"));
        addDependency(new Dependency(10, "2º Ciclo Formativo Grado Superior",
                "KKK", "2CFGS Desarrollo de Aplicaciones Multiplataformma"));
    }


    public void addDependency(Dependency dependency) {
        dependencies.add(dependency);
    }


    public ArrayList<Dependency> getDependencies() {
        Collections.sort(dependencies, new Dependency.DependencyOrderByShortName());
        return dependencies;
    }


    public int getLastId() {
        return dependencies.get(dependencies.size() - 1).get_ID();
    }
    public boolean validate(String name, String shortname){
        boolean res = true;

        for (int i = 0; i < dependencies.size();i++){
            if(dependencies.get(i).getName().equals(name) || dependencies.get(i).getShortname().equals(shortname))
                res =false;
        }

        return  res;
    }

    public void editDependency(String name, String shortname, String description){
        for(int i =0; i < dependencies.size();i++){
            if(dependencies.get(i).getName().equals(name)&& dependencies.get(i).getShortname().equals(shortname))
                dependencies.get(i).setDescription(description);
        }
    }

    //Alternativa a editDependency
    public void saveDependency(Dependency d){
        for(Dependency dependency:dependencies)
            if(dependency.getName().equals(d.getName()))
                dependency.setDescription(d.getDescription());
    }

    public void deleteDependency(Dependency dependency){
        dependencies.remove(dependency);
    }

    //Guiño Guiño
    public void deleteDependency(int id){
        Iterator<Dependency> iterator = dependencies.iterator();
        Dependency dependency;
        while (iterator.hasNext()){
            dependency = iterator.next();
            if(dependency.get_ID() == id){
                iterator.remove();
                return;
            }
        }
    }
}

class CompararPorID implements Comparator<Dependency>{
    @Override
    public int compare(Dependency o1, Dependency o2) {
        if(o1.get_ID() > o2.get_ID())
            return 1;
        else if(o1.get_ID() < o2.get_ID())
            return -1;
        else
            return 0;
    }
}

class CompararPorName implements Comparator<Dependency>{
    @Override
    public int compare(Dependency o1, Dependency o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
