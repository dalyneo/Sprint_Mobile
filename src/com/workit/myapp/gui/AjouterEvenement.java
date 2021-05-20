/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.workit.myapp.gui;


import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.workit.myapp.entities.Cat;
import com.workit.myapp.entities.Evenement;
import com.workit.myapp.services.ServiceCat;
import com.workit.myapp.services.ServiceEvenement;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Nadia
 */
public class AjouterEvenement extends BaseForm {
    
private Resources theme = UIManager.initFirstTheme("/theme");
 Form current;
 
 public AjouterEvenement(Form previous){
        setTitle("Ajouter Evenement");
        setLayout(BoxLayout.y());
        
     
        TextField tfNom = new TextField("","Nom");
        TextField tfDate = new TextField("","Date");
        TextField tfDescription = new TextField("","Description");
        TextField tfEmail = new TextField("","Email");
        
     
        
        
     
        
        Button btnAjouter = new Button("Ajouter Evenement");
        
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if( (tfNom.getText().length()==0) || (tfDate.getText().length()==0) || (tfDescription.getText().length()==0) || (tfEmail.getText().length()==0) )
                    
                    Dialog.show("Alert", "Remplir tous les champs", new Command("OK"));
                
                else {
                   
                        Evenement e = new Evenement(tfNom.getText(),tfDate.getText(),tfDescription.getText(),tfEmail.getText());
                 
                    if(new ServiceEvenement().addEvenement(e))
                    {
                        
                            try {
                                Dialog.show("SUCCES", "Bien ajoute", new Command("OK"));
                                
                                
                                
                                
                                
                                
                                //Message m = new Message("Votre Evenement "+tfNom.getText()+" est bien ajoute Merci!");
                                //Display.getInstance().sendMessage(new String[] {"noreplay.espritwork@gmail.com"}, "Ajout avec succes ", m );
                                
                                send(tfEmail.getText());
                                
                                
                                
                                
                                
                                new AfficherEvenement(current, theme).show();
                            } catch (Exception ex) {
                                
                            }
                    }
                    
                    else 
                        
                        Dialog.show("ERROR", "Erreur de serveur", new Command("OK"));
                    
                }
                
                
                
            }
        });
        
        addAll(tfNom,tfDate,tfDescription,tfEmail,btnAjouter);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        
    } 
 
 public static void send(String recepient) throws Exception {
      
            System.out.println("preparing to send email");
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            
            String myAccountEmail = "noreplay.espritwork@gmail.com";
            String password = "esprit12";
            
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(myAccountEmail, password);
                }
            });
            
            javax.mail.Message message = prepareMessage(session,myAccountEmail,recepient);
            
            Transport.send(message);
            
            
            
            System.out.println("meesage sent successfully");
        

}
    
      private static javax.mail.Message prepareMessage(Session session, String myAccountEmail, String receptient) {
      javax.mail.Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(receptient));
            message.setSubject("Ajout avec succes");
            message.setText("Votre evenement est bien ajoute merci! ");
            return message;
        } catch (Exception ex) {
           // Logger.getLogger(AddEventForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
      
        
        
    }
 
}
