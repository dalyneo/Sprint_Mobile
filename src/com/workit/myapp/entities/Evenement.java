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
public class Evenement {
    
    int id;
    String nom;
    String date;
    String description;
    String email;
    int nbp;
   int nbvote;
   float nbetoile;

    public Evenement() {
    }

    public Evenement(int id, String nom, String date, String description, String email) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.description = description;
        this.email = email;
    }

    public Evenement(String nom, String date, String description, String email) {
        this.nom = nom;
        this.date = date;
        this.description = description;
        this.email = email;
        
    }

    public Evenement(String nom, String date, String description, String email, Cat idcat_id) {
        this.nom = nom;
        this.date = date;
        this.description = description;
        this.email = email;
        
       
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public int getNbp() {
        return nbp;
    }

    public int getNbvote() {
        return nbvote;
    }

    public float getNbetoile() {
        return nbetoile;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNbp(int nbp) {
        this.nbp = nbp;
    }

    public void setNbvote(int nbvote) {
        this.nbvote = nbvote;
    }

    public void setNbetoile(float nbetoile) {
        this.nbetoile = nbetoile;
    }
    
    

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nom=" + nom + ", date=" + date + ", description=" + description + ", email=" + email + '}';
    }
    
    
    
}
