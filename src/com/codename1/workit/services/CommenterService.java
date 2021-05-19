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
import com.codename1.workit.entites.Commenter;

//import entities.Forum;
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
public class CommenterService {
     private static CommenterService instance= null;
    private ConnectionRequest req;
    private boolean resultOK;
    ArrayList<Commenter> events;

    private CommenterService() {
        req = new ConnectionRequest();
    }

    public static CommenterService getInstance() {
        if (instance == null) {
            instance = new CommenterService();
        }
        return instance;
    }

    public ArrayList<Commenter> findAll(){
        String url = Statics.BASE_URL + "api/mobile/comm";
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

    public boolean save(Commenter event) {
        String url = Statics.BASE_URL + "api/new/comm/forum/" + event.getForum_id();
        req.setUrl(url);
        req.setPost(true);
        req.setContentType("application/json");
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
             hashMap.put("ref", event.getRef());
              // hashMap.put("forum_id", event.forum_id);
            hashMap.put("commentaire", event.getCommentaire());
            hashMap.put("rating", event.getRating());
         
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

      public boolean update(Commenter event) {
        String url = Statics.BASE_URL + "apiC/" + event.getRef() + "/updateComm?commentaire="+event.getCommentaire()+"&rating="+ event.getRating();
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
        String url = Statics.BASE_URL + "api/" + id + "/deleteComm";
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





    public ArrayList<Commenter> parseEvents(String jsonText){
        try {
            events =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");


            for(Map<String,Object> obj : list){

                Commenter t = new Commenter();
                float id = Float.parseFloat(obj.get("ref").toString());
                 float rating = Float.parseFloat(obj.get("rating").toString());
                t.setRef((int)id);
                 t.setRating((int)rating);
                t.setCommentaire((obj.get("commentaire").toString()));
                float forumId = Float.parseFloat(obj.get("forumId").toString());
                t.setForum_id(ForumService.getInstance().findById((int) forumId).getId());
               

                events.add(t);
            }

        } catch (IOException ex) {

        }

        return events;
    }

    
}
