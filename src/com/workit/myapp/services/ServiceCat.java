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
//import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.workit.myapp.entities.Cat;
import com.workit.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;


/**
 *
 * @author Nadia
 */
public class ServiceCat {
    
    public ArrayList<Cat> listC;
    public static ServiceCat instance;
    public Boolean resultOK;
    private ConnectionRequest req;

    public ServiceCat() {
        
        req = new ConnectionRequest();
    }
    
    public static ServiceCat getInstance(){
        
        if(instance == null)
            instance = new ServiceCat();
        return instance;
    }
    
    public Boolean addCategorie(Cat c){
        
        String url = Statics.BASE_URL+"/addC1?nom="+c.getNom();
        req.setUrl(url);
        req.setHttpMethod("POST");
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
    
    
     public boolean DeleteCategorie(int id) {
        String url = Statics.BASE_URL + "/deleteC/" +id  ;
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
     
     public boolean EditCategorie(Cat c ,int id) {

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

 String url = Statics.BASE_URL+"/updateC/"+id+"?"+"nom="+c.getNom();
        
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
     
     
    public ArrayList<Cat> parseCategorie(String jsonText){

        
        try {
               listC= new ArrayList<>();
               
               JSONParser j = new JSONParser();
               Map<String,Object> eventListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
               List<Map<String, Object>> list = (List<Map<String,Object>>)eventListJson.get("root");
               
               
               for (Map<String, Object> obj : list) {
                   Cat c = new Cat();
                   float id = Float.parseFloat(obj.get("id").toString());
                  c.setId((int)id);
                   c.setNom(obj.get("nom").toString());
                   
                   listC.add(c);
                   
               }
             
               
           } catch (IOException ex) {
             //  Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
           }
          
    
    return listC;
}
    
    public ArrayList<Cat> getAllCat(){
        
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


String url = Statics.BASE_URL+"/listeC1/";
          ConnectionRequest req  = new ConnectionRequest(url);
         req.setUrl(url);
         req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                listC = parseCategorie(new String(req.getResponseData()));
                req.removeResponseListener(this);
                
             }
         });
         NetworkManager.getInstance().addToQueueAndWait(req);
         




return listC;

    }
    
}
