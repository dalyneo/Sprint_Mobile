/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.entities;

/**
 *
 * @author Wael Belhadj
 */
public class Recruteur {
       private int id;
    private String nom;
    private String prenom;
    private String nomsociete;
    private String adresse;
    private String mail;
    private int numsociete;
    private String mdp;
    private String type;
    private String photo;
    private String competence;

    public Recruteur() {
    }

    public Recruteur(int id, String nom, String prenom, String nomsociete, String adresse, String mail, int numsociete, String mdp, String type, String photo, String competence) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.nomsociete = nomsociete;
        this.adresse = adresse;
        this.mail = mail;
        this.numsociete = numsociete;
        this.mdp = mdp;
        this.type = type;
        this.photo = photo;
        this.competence = competence;
    }

    public Recruteur(String nom, String prenom, String nomsociete, String adresse, String mail, int numsociete, String mdp, String type, String photo, String competence) {
        this.nom = nom;
        this.prenom = prenom;
        this.nomsociete = nomsociete;
        this.adresse = adresse;
        this.mail = mail;
        this.numsociete = numsociete;
        this.mdp = mdp;
        this.type = type;
        this.photo = photo;
        this.competence = competence;
    }

    public Recruteur(int id) {
        this.id = id;
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

    public String getNomsociete() {
        return nomsociete;
    }

    public void setNomsociete(String nomsociete) {
        this.nomsociete = nomsociete;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getNumsociete() {
        return numsociete;
    }

    public void setNumsociete(int numsociete) {
        this.numsociete = numsociete;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    @Override
    public String toString() {
        return "Recruteur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", nomsociete=" + nomsociete + ", adresse=" + adresse + ", mail=" + mail + ", numsociete=" + numsociete + ", mdp=" + mdp + ", type=" + type + ", photo=" + photo + ", competence=" + competence + '}';
    }
    
    
    
}
