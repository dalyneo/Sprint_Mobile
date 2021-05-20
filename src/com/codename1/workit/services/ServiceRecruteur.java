/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.workit.entities.Recruteur;
import com.codename1.workit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Wael Belhadj
 */
public class ServiceRecruteur {
      public ArrayList<Recruteur> comptes;
    public Recruteur recruteur;
    public static ServiceRecruteur instance;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceRecruteur() {
        req = new ConnectionRequest();
        recruteur = new Recruteur();
    }

    public static ServiceRecruteur getInstance() {
        if (instance == null) {
            instance = new ServiceRecruteur();
        }
        return instance;

    }
    
     public Recruteur login(String mail, String mdp) {
        String url = "http://127.0.0.1:8000/json_loginjob/" + mail + "/" + mdp;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                recruteur = findCompte(new String(req.getResponseData()));
                System.out.println(recruteur);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return recruteur;

    }
     public Recruteur findCompte(String jsonText) {

        try {
            
            JSONParser j = new JSONParser();
            j.setIncludeNulls(true);
            if(!jsonText.equals("null")){
                Map<String, Object> compteListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                if(compteListJson.containsKey("id"))
                   recruteur.setId((int) Float.parseFloat(compteListJson.get("id").toString()));
                if(compteListJson.containsKey("nom"))
                    recruteur.setNom(compteListJson.get("nom").toString());
                 if(compteListJson.containsKey("prenom"))
                    recruteur.setPrenom(compteListJson.get("prenom").toString());
                if(compteListJson.containsKey("nomsociete"))
                    recruteur.setNomsociete(compteListJson.get("nomsociete").toString());
                if(compteListJson.containsKey("adresse"))
                    recruteur.setAdresse(compteListJson.get("adresse").toString());
                if(compteListJson.containsKey("mail"))
                    recruteur.setMail(compteListJson.get("mail").toString());
                 if(compteListJson.containsKey("numsociete"))
                   recruteur.setNumsociete((int) Float.parseFloat(compteListJson.get("numsociete").toString()));
                if(compteListJson.containsKey("mdp"))
                    recruteur.setMdp(compteListJson.get("mdp").toString());
                if(compteListJson.containsKey("type"))
                    recruteur.setType(compteListJson.get("type").toString());
                if(compteListJson.containsKey("photo"))
                    recruteur.setPhoto(compteListJson.get("photo").toString());
                if(compteListJson.containsKey("competence"))
                    recruteur.setCompetence(compteListJson.get("competence").toString());
                

            }  
        } catch (IOException ex) {
            return recruteur;
        }
        return recruteur;
    }
       public void addCompte(Recruteur c) {
           MultipartRequest con = new MultipartRequest();
        String url = "http://127.0.0.1:8000/rec/add?nom="+c.getNom()+"&prenom=" + c.getPrenom()+ "&nomsociete=" + c.getNomsociete()+"&mail="+c.getMail()+"&adresse="+c.getAdresse()+"&numsociete="+c.getNumsociete()+"&mdp="+c.getMdp()+"&type="+c.getType()+"&photo="+c.getPhoto()+"&competence=" + c.getCompetence();
        con.setUrl(url);
      con.addResponseListener((ee)->{
      String str = new String(con.getResponseData());
          System.out.println(str);
      });
        NetworkManager.getInstance().addToQueueAndWait(con);
     
    }
     public boolean deleteCompte(Recruteur c) {
        String url = Statics.BASE_URL + "/recruteur/deleteCompte/" + c.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
       public boolean updateCompte(Recruteur c) {
        String url = Statics.BASE_URL + "/recruteur/updateCompte/" + c.getId() +"?nom="+c.getNom()+"&prenom=" + c.getPrenom()+ "&nomsociete=" + c.getNomsociete()+ "&adresse="+c.getAdresse()+"&mail="+c.getMail()+"&numsociete="+c.getNumsociete()+"&mdp="+c.getMdp()+"&type="+c.getType()+"&photo="+c.getPhoto()+"&competence="+c.getCompetence();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
}
