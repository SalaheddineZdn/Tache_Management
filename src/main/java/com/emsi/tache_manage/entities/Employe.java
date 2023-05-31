package com.emsi.tache_manage.entities;

import java.io.Serializable;
import java.util.Collection;

public class Employe implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String password;


    public Employe() {

    }

    public Employe(int id, String nom, String prenom, String email, String password) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }
    public Employe(String nom, String prenom, String email, String password) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
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
