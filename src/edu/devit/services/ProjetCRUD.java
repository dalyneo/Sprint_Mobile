package edu.devit.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import edu.devit.entities.Freelancer;
import edu.devit.entities.Projet;
import edu.devit.utils.Connection;

public class ProjetCRUD {

    public static ProjetCRUD instance = null ;

    //initialisation connection request
    private ConnectionRequest req;

    public static ProjetCRUD getInstance() {
        if(instance == null)
            instance = new ProjetCRUD();
        return instance;
    }

    public ProjetCRUD () {
        req = new ConnectionRequest();
    }

    public void AddProjet(Projet projet){

        String url = Connection.BASE_URL+"/addProjet?nom_projet="+projet.getNom_projet()+"&projet_description="+projet.getProjet_description()+"&job_type="+projet.getJob_type()+"&job_salary="+projet.getJob_salary()+"&job_time="+projet.getJob_time()+"&logo="+projet.getLogo();

        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData()); // hetha l response json lyrineha f navigateur bekri l affichage
            System.out.println("data == "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req); // execution taa request sinn yet3ada chy dima nal9awha
    }
}
