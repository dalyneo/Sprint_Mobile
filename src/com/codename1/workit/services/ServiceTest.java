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
//import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.codename1.workit.entities.Test;
import com.codename1.workit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

/**
 *
 * @author walid belhadj
 */
public class ServiceTest {
    public ArrayList<Test> tests;
    public  static ServiceTest instance;
    public boolean resultOK;
    private ConnectionRequest req;
    public ServiceTest() {
      req = new ConnectionRequest();
    }

    public static ServiceTest getInstance(){
        if(instance == null){
            instance = new ServiceTest(); 
        }
         return instance;
    }
    
    
    public boolean addTest(Test t){
        String url = "http://127.0.0.1:8000/test/test/add2?nom="+t.getNom()+"&q1="+t.getQ1()+"&r1="+t.getR1()+"&q2="+t.getQ2()+"&r2="+t.getR2()+"&q3="+t.getQ3()+"&r3="+t.getR3()+"&q4="+t.getQ4()+"&r4="+t.getR4()+"&q5="+t.getQ5()+"&r5="+t.getR5();
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
   public ArrayList<Test> parseTest(String jsonText){
        try {
            tests = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> TestsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) TestsListJson.get("root");
            for (Map<String, Object> obj : list){
                Test t= new Test();
                t.setNom(obj.get("nom").toString());
                t.setQ1(obj.get("q1").toString());
                t.setR1(obj.get("r1").toString());
                t.setQ2(obj.get("q2").toString());
                t.setR2(obj.get("r2").toString());
                t.setQ3(obj.get("q3").toString());
                t.setR3(obj.get("r3").toString());
                t.setQ4(obj.get("q4").toString());
                t.setR4(obj.get("r4").toString());
                t.setQ5(obj.get("q5").toString());
                t.setR5(obj.get("r5").toString());
                tests.add(t);
                
                
            }
        } catch (Exception e) {
        }
        return tests;
    }
    
   public ArrayList<Test> getAllTests() {
        String url = Statics.BASE_URL + "/test/liste1";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(req.getResponseData()));
                tests = parseTest(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tests;
    }
   public boolean deleteTest(Test t) {
        String url = Statics.BASE_URL + "/test/deletet/" + t.getId();
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
