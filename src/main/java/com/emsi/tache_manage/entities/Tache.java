package com.emsi.tache_manage.entities;

import com.emsi.tache_manage.enums.Priority;
import com.emsi.tache_manage.enums.Statut;

import java.io.Serializable;
import java.time.LocalDate;

public class Tache implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nom;
    private String description;
    private Statut statut;
    private Priority priority;



    public Tache() {

    }

    public Tache(String nom, String description, Statut statut, Priority priority) {
        super();
        this.nom = nom;
        this.description = description;
        this.statut = statut;
        this.priority = priority;
    }
    public Tache(Integer id, String nom, String description, Statut statut, Priority priority) {
        super();
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.statut = statut;
        this.priority = priority;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Statut getStatut() {
        return statut;
    }
    public void setStatut(Statut statut) {
        this.statut = statut;
    }
    public Priority getPriority() {
        return priority;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }


    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
