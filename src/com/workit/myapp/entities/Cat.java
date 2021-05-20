/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.workit.myapp.entities;

/**
 *
 * @author Nadia
 */
public class Cat {
    
    int id;
    String nom;

    public Cat() {
    }

    public Cat(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Cat(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom=" + nom + '}';
    }
    
    
    
}
