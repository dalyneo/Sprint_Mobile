/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.workit.myapp.gui;


import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.workit.myapp.entities.Cat;
import com.workit.myapp.entities.Evenement;

import com.workit.myapp.services.ServiceEvenement;

/**
 *
 * @author Nadia
 */
public class ModifierEvenement extends BaseForm{
    
      Form f;
      
     
        
        static TextField nomE = new TextField(); 
        static TextField idE = new TextField();
        static TextField dateE = new TextField();
        static TextField descriptionE = new TextField();
        static TextField emailE = new TextField();
       

    public ModifierEvenement(Form previous){
        
         
        f = new Form ();
     //Container c1 = new Container(new BorderLayout());
     //Container c =new Container(new GridLayout(2, 2)); 
     
        setTitle("Modifier Evenement");
      
        setLayout(BoxLayout.y());
        
     
        Label l0 = new Label ("Id Evenement ");
        //TextField idE = new TextField();
        Label l1 = new Label ("Nom Evenement ");
        //TextField nomE = new TextField(); 
        Label l2 = new Label ("Date Evenement ");
        Label l3 = new Label ("Description Evenement ");
        Label l4 = new Label ("Email  ");
        
        
        Button btnmodif = new Button("Modifier");
        
        btnmodif.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
            
            if (nomE.getText().length()==0)
                Dialog.show("Alert", "SVP Remplir les champs", new Command("ok"));
            else{
                try {
                    Evenement e = new Evenement(nomE.getText(),dateE.getText(),descriptionE.getText(),emailE.getText());    
                    if (new ServiceEvenement(). EditEvenement(e,Integer.parseInt(idE.getText())))
                        Dialog.show("Modifie", "Modifie avec succes", new Command("ok"));
                    else 
                        Dialog.show("Error", "Server Error", new Command("ok"));
                } catch (NumberFormatException   e) {
                    Dialog.show("Error", "exple", new Command("ok"));
                }
                
            }
       
            
        }
    });
     
    //nomE.setEditable(true);
  
        
        addAll(l0,idE,l1,nomE,l2,dateE,l3,descriptionE,l4,emailE,btnmodif);
        
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        
        
        
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
