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
public class Encapsulation {
    protected static int id;
    protected static String nom;
    protected static String prenom;
    protected static String nomsociete;
    protected static String adresse ;
    protected static String mail;
    protected static int numsociete;
    protected static String mdp;
    protected static String type;
    protected static String photo;
    protected static String competence;

 
    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Encapsulation.id = id;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        Encapsulation.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        Encapsulation.prenom = prenom;
    }

    public static String getNomsociete() {
        return nomsociete;
    }

    public static void setNomsociete(String nomsociete) {
        Encapsulation.nomsociete = nomsociete;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static void setAdresse(String adresse) {
        Encapsulation.adresse = adresse;
    }

    public static String getMail() {
        return mail;
    }

    public static void setMail(String mail) {
        Encapsulation.mail = mail;
    }

    public static int getNumsociete() {
        return numsociete;
    }

    public static void setNumsociete(int numsociete) {
        Encapsulation.numsociete = numsociete;
    }

    public static String getMdp() {
        return mdp;
    }

    public static void setMdp(String mdp) {
        Encapsulation.mdp = mdp;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        Encapsulation.type = type;
    }

    public static String getPhoto() {
        return photo;
    }

    public static void setPhoto(String photo) {
        Encapsulation.photo = photo;
    }

    public static String getCompetence() {
        return competence;
    }

    public static void setCompetence(String competence) {
        Encapsulation.competence = competence;
    }

    public Encapsulation() {
    }
     
       public Encapsulation(int id,String nom, String prenom, String nomsociete, String adresse, String mail,int numsociete, String mdp, String type, String photo ,String competence ) {
        this.id = id;
        this.nom=nom;
        this.prenom=prenom;
        this.nomsociete = nomsociete;
        this.adresse = adresse;
        this.mail = mail;
        this.numsociete = numsociete;
        this.mdp = mdp;
        this.type = type;
        this.photo=photo;
        this.competence=competence;
    }
        
       public static void setCompte(Recruteur recruteur )
    {
        Encapsulation.id = recruteur.getId();
        Encapsulation.nom= recruteur.getNom();
        Encapsulation.prenom = recruteur.getPrenom();
        Encapsulation.nomsociete = recruteur.getNomsociete();
        Encapsulation.adresse = recruteur.getAdresse();
        Encapsulation.mail = recruteur.getMail();
        Encapsulation.numsociete = recruteur.getNumsociete();
        Encapsulation.mdp = recruteur.getMdp();
        Encapsulation.type = recruteur.getType();
        Encapsulation.photo = recruteur.getPhoto();
        Encapsulation.competence = recruteur.getCompetence();
    }
    
    
}
