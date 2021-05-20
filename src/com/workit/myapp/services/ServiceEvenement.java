/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.workit.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.workit.myapp.entities.Cat;
import com.workit.myapp.entities.Evenement;
import com.workit.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nadia
 */
public class ServiceEvenement {
    
    public ArrayList<Evenement> listE;
    public static ServiceEvenement instance;
    public Boolean resultOK;
    private ConnectionRequest req;

    public ServiceEvenement() {
        
        req = new ConnectionRequest();
    }
    
    public static ServiceEvenement getInstance(){
        
        if(instance == null)
            instance = new ServiceEvenement();
        return instance;
    }
    
     public Boolean addEvenement(Evenement e){
         

        
        String url = Statics.BASE_URL+"/addE1?nom="+e.getNom() + "&"+"date="+e.getDate()+"&"+"description="+e.getDescription()+"&"+"email="+e.getEmail();        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
             resultOK = req.getResponseCode()==200;
             req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     public boolean DeleteEvenement(int id) {
        String url = Statics.BASE_URL + "/deleteE/" + id  ;
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
     
      public boolean EditEvenement(Evenement e ,int id) {

//        String url = Statics.BASE_URL + "/updateC/" + c.getId()+ "&nom=" +c.getNom();
//        req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;

 String url = Statics.BASE_URL+"/updateE/"+id+"?"+"nom="+e.getNom()+"&"+"date="+e.getDate()+"&"+"description="+e.getDescription()+"&"+"email="+e.getEmail();
        
        ConnectionRequest req  = new ConnectionRequest(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               resultOK=req.getResponseCode()==200;
               
            }                
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
       
    public ArrayList<Evenement> parseEvenement(String jsonText){

        
        try {
               listE= new ArrayList<>();
               
               JSONParser j = new JSONParser();
               Map<String,Object> eventListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
               List<Map<String, Object>> list = (List<Map<String,Object>>)eventListJson.get("root");
               
               
               for (Map<String, Object> obj : list) {
                   Evenement e = new Evenement();
                   float id = Float.parseFloat(obj.get("id").toString());
                  
                   e.setId((int)id);
                   e.setNom(obj.get("nom").toString());
                   e.setDate(obj.get("date").toString());
                   e.setDescription(obj.get("description").toString());
                   e.setEmail(obj.get("email").toString());
                   
                   e.setNbvote((int)Float.parseFloat(obj.get("nbvote").toString()));
                   e.setNbetoile(Float.parseFloat(obj.get("nbetoile").toString()));

                   
                   
                   listE.add(e);
                   
               }
             
               
           } catch (IOException ex) {
             //  Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
           }
          
    
    return listE;
}
    
    public ArrayList<Evenement> getAllEvent(){
        
//        String url = Statics.BASE_URL +"/listeC1/";
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                listC = parseCategorie(new String (req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);


String url = Statics.BASE_URL+"/listeE/";
          ConnectionRequest req  = new ConnectionRequest(url);
         req.setUrl(url);
         req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                listE = parseEvenement(new String(req.getResponseData()));
                req.removeResponseListener(this);
                
             }
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
         




return listE;

    }
    
    public boolean UpdateEventRate(Evenement e, int id){
        String url = Statics.BASE_URL+"/rateE/"+id+"?"+"nom="+e.getNom()+"&date="+e.getDate()+"&description="+e.getDescription()+"&email="+e.getEmail()+"&nbetoile="+e.getNbetoile()+"&nbvote="+e.getNbvote();
        
        ConnectionRequest req  = new ConnectionRequest(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               resultOK=req.getResponseCode()==200;
               
            }                
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
