/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.entites;

//import java.time.LocalDateTime;





/**
 *
 * @author HP-PC
 */
public class Forum {
        private int id;
    private String sujet;
    private String probleme;
   // private LocalDateTime date;
    private String theme;
    private String views;
    private int nviews;
 

//    public int getNviews() {
//        if(views == null)
//            return 0;
//        return views.split(",").length;
//    }

    public int getNviews() {
        return nviews;
    }

    public void setNviews(int nviews) {
        this.nviews = nviews;
    }



    public Forum() {
     //   date=LocalDateTime.now();
       
    }

    public Forum(int id, String sujet, String probleme, String theme) {
        this.id = id;
        this.sujet = sujet;
        this.probleme = probleme;
     //date=LocalDateTime.now();
 
     this.theme = theme;
    }

    public Forum(String sujet, String probleme, String theme) {
        this.sujet = sujet;
        this.probleme = probleme;
        this.theme = theme;
       // date=LocalDateTime.now();
      
    }

    public String getViews() {
        return views;
    }

    //public void setViews(String views) {
      //  this.views = views;
    //}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    //public LocalDateTime getDate() {
      //  return date;
  //  }

   // public void setDate(LocalDateTime date) {
      //  this.date = date;
  //  }
   

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getProbleme() {
        return probleme;
    }

    public void setProbleme(String probleme) {
        this.probleme = probleme;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "Forum{" + "id=" + id + ", sujet=" + sujet + ", probleme=" + probleme + ", theme=" + theme + ", views=" + views + ", nviews=" + nviews + '}';
    }
    
    

   
    
    

  

   
    
    

    

  
   
}
