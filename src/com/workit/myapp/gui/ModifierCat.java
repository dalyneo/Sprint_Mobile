/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.workit.myapp.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.workit.myapp.entities.Cat;
import com.workit.myapp.services.ServiceCat;

/**
 *
 * @author Nadia
 */
public class ModifierCat extends Form{
    
    
        Form f;
        
        static TextField nomC = new TextField(); 
        static TextField idC = new TextField();
        
        
    public ModifierCat(Form previous){
        
        
        f = new Form ();
     //Container c1 = new Container(new BorderLayout());
     //Container c =new Container(new GridLayout(2, 2)); 
     
        setTitle("Modifier Categorie");
        setLayout(BoxLayout.y());
        
     
        Label l0 = new Label ("Id Categorie ");
        //TextField idE = new TextField();
        Label l1 = new Label ("Nom Categorie ");
        //TextField nomE = new TextField(); 
        
        Button btnmodif = new Button("Modifier");
        
        btnmodif.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
            
            if (nomC.getText().length()==0)
                Dialog.show("Alert", "SVP Remplir les champs", new Command("ok"));
            else{
                try {
                    Cat c = new Cat(nomC.getText());    
                    if (new ServiceCat(). EditCategorie(c,Integer.parseInt(idC.getText())))
                        Dialog.show("Success", "Connection accepted", new Command("ok"));
                    else 
                        Dialog.show("Error", "Server Error", new Command("ok"));
                } catch (NumberFormatException   e) {
                    Dialog.show("Error", "exple", new Command("ok"));
                }
                
            }
       
            
        }
    });
     
    //nomE.setEditable(true);
  
        
        addAll(l0,idC,l1,nomC,btnmodif);
        
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
    
}
