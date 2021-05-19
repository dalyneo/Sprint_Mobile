/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;

import com.codename1.ui.events.ActionListener;

import com.codename1.workit.entites.Forum;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.codename1.workit.utils.Statics;

/**
 *
 * @author HP-PC
 */
public class ForumService {
     private static ForumService instance= null;
    private ConnectionRequest req;
    private boolean resultOK;
    ArrayList<Forum> events;
    Forum forum;

    private ForumService() {
        req = new ConnectionRequest();
    }

    public static ForumService getInstance() {
        if (instance == null) {
            instance = new ForumService();
        }
        return instance;
    }

    public ArrayList<Forum> findAll(){
        String url = Statics.BASE_URL + "api/mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
    
        public Forum findById(int id){
        String url = Statics.BASE_URL + "api/mobile/forum/"+ id + "/find";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                forum = parseEvents(new String(req.getResponseData())).get(0);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return forum;
    }


    public boolean save(Forum event) {
        String url = Statics.BASE_URL + "api/new";
        req.setUrl(url);
        req.setPost(true);
        req.setContentType("application/json");
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
             hashMap.put("id", event.getId());
            hashMap.put("sujet", event.getSujet());
            hashMap.put("probleme", event.getProbleme());
            hashMap.put("theme", event.getTheme());
          //  hashMap.put("date", event.getDate());
            req.setRequestBody(Result.fromContent(hashMap).toString());
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                    req.removeResponseListener(this);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
            return resultOK;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(Forum event) {
        String url = Statics.BASE_URL + "api/" + event.getId() + "/update?sujet="+event.getSujet()+"&probleme="+ event.getProbleme() + "&theme="+event.getTheme();
        req.setUrl(url);
        req.setContentType("application/json");
        try {
      
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                    req.removeResponseListener(this);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
            return resultOK;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(int id) {
        String url = Statics.BASE_URL + "api/" + id + "/delete";
        req.setUrl(url);
        req.setHttpMethod("DELETE");
        req.setContentType("application/json");
        try {
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                    req.removeResponseListener(this);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
            return resultOK;
        } catch (Exception e) {
            return false;
        }

    }





    public ArrayList<Forum> parseEvents(String jsonText){
        try {
            events =new ArrayList<Forum>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");


            for(Map<String,Object> obj : list){

                Forum t = new Forum();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setSujet((obj.get("sujet").toString()));
                t.setProbleme(obj.get("probleme").toString());
                 t.setTheme(obj.get("theme").toString());
                
                 
         

                events.add(t);
            }

        } catch (IOException ex) {

        }

        return events;
    }

}
