package edu.devit.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import edu.devit.entities.Freelancer;
import edu.devit.utils.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FreelancerCRUD {

    public static FreelancerCRUD instance = null ;
    public static boolean resultOK = true;


    //initialisation connection request
    private ConnectionRequest req;

    public static FreelancerCRUD getInstance() {
        if(instance == null)
            instance = new FreelancerCRUD();
        return instance;
    }

    public FreelancerCRUD () {
 req = new ConnectionRequest();
    }


    //ajouter freelancer
    public void AddFreelancer(Freelancer freelancer){

        String url = Connection.BASE_URL+"/addFreelancer?email="+freelancer.getEmail()+"&name="+freelancer.getName()+"&password="+freelancer.getPassword()+"&photo="+freelancer.getPhoto()+"&title="+freelancer.getTitle()+"&skills="+freelancer.getSkills()+"&country="+freelancer.getCountry()+"&prix="+freelancer.getPrix()+"&experience="+freelancer.getExperience();

        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData()); // hetha l response json lyrineha f navigateur bekri l affichage
            System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req); // execution taa request sinn yet3ada chy dima nal9awha
    }

    public ArrayList<Freelancer>affichageFreelancer(){
        ArrayList<Freelancer> result = new ArrayList<>();

        String url = Connection.BASE_URL+"/displayFreelancer";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                try {
                    Map<String, Object>mapFreelancers = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String,Object>> listofMaps = (List<Map<String, Object>>)mapFreelancers.get("root");

                    for(Map<String, Object> obj : listofMaps) {
                        Freelancer fc = new Freelancer();

                        float id = Float.parseFloat(obj.get("id").toString());

                        String email = obj.get("email").toString();
                        String name = obj.get("name").toString();
                        String password = obj.get("password").toString();
                        String photo = obj.get("photo").toString();
                        String title = obj.get("title").toString();
                        String skills = obj.get("skills").toString();
                        String country = obj.get("country").toString();
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        String experience = obj.get("experience").toString();

                        fc.setId((int)id);
                        fc.setEmail(email);
                        fc.setName(name);
                        fc.setPassword(password);
                        fc.setPhoto(photo);
                        fc.setTitle(title);
                        fc.setSkills(skills);
                        fc.setCountry(country);
                        fc.setPrix((int)prix);
                        fc.setExperience(experience);

                        //insert data into arrayList
                        result.add(fc);


                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req); //execution taa request sinn yetaada chy dima nalgouha

        return result;

    }



    //Detal Freelancer

    public Freelancer DetailFreelancer ( int id , Freelancer freelancer) {
        String url = Connection.BASE_URL+"/detailFreelancer"+id;
        req.setUrl(url);

        String str = new String (req.getResponseData());


        req.addResponseListener((evt -> {
            JSONParser jsonp = new JSONParser();
            try{
                Map<String, Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));

                freelancer.setEmail(obj.get("email").toString());
                freelancer.setName(obj.get("name").toString());
                freelancer.setPassword(obj.get("password").toString());
                freelancer.setPhoto(obj.get("photo").toString());
                freelancer.setTitle(obj.get("title").toString());
                freelancer.setSkills(obj.get("skills").toString());
                freelancer.setCountry(obj.get("country").toString());
                freelancer.setPrix(Integer.parseInt(obj.get("prix").toString()));
                freelancer.setExperience(obj.get("experience").toString());


            }catch(IOException ex){
                System.out.println("error related to qsl :( " +ex.getMessage());
            }

            System.out.println("data === " +str);
        }));
        NetworkManager.getInstance().addToQueueAndWait(req);

        return freelancer;
    }

    public boolean deleteFreelancer(int id) {
        String url = Connection.BASE_URL +"/delete?id="+id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean modifierFreelancer(Freelancer freelancer){
        String url = Connection.BASE_URL+"/updateFreelancer?email="+freelancer.getEmail()+"&name="+freelancer.getName()+"&password="+freelancer.getPassword()+"&photo="+freelancer.getPhoto()+"&title="+freelancer.getTitle()+"&skills="+freelancer.getSkills()+"&country="+freelancer.getCountry()+"&prix="+freelancer.getPrix()+"&experience="+freelancer.getExperience();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }


}




















