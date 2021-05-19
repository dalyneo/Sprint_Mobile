/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.workit.entites.Commenter;
import com.codename1.workit.services.CommenterService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;


/**
 *
 * @author HP-PC
 */
public class AddCommenter extends Form {
    private Resources theme;
     public AddCommenter(int forum_id) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Ajouter un commentaire");
        setLayout(BoxLayout.y());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        TextField tfComm = new TextField("","Commentaire");
         tfComm.getAllStyles().setFgColor(ColorUtil.BLACK);
        TextField tfRating= new TextField("", "Rating");
         tfRating.getAllStyles().setFgColor(ColorUtil.BLACK);
       
        Button btnValider = new Button("Ajouter");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfComm.getText().length()==0)||(tfRating.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Commenter t = new Commenter(tfComm.getText(), Integer.parseInt(tfRating.getText()));
                        t.setForum_id(forum_id);
                        if( CommenterService.getInstance().save(t)) {
                            // SMS
                            
                            String accountSID = "AC7e4dd17d0ffc38639c03001468787de9";
                String authToken = "a4d7179472b1f8a92d96faf3472a3d50";
                String fromPhone = "+15042265856";
                            Twilio.init(accountSID, authToken);

                Message message = Message
                        .creator(new PhoneNumber("+21654502615"), // to
                                new PhoneNumber(fromPhone), // from
                                "Votre commentaire '"+tfComm.getText()+"' a été créé avec succes")
                        .create();

                System.out.println(message.getSid());
                            
                            
                            
                            
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                            new ListCommenter(forum_id).show();
                        } else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfComm,tfRating,btnValider);
     // Revenir vers l'interface précédente
             this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "", 5), evt1 -> new ListCommenter(forum_id).show());


                
    }
    
}
