/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.workit.myapp.gui;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.workit.myapp.entities.Cat;
import com.workit.myapp.services.ServiceCat;

/**
 *
 * @author Nadia
 */
public class AjouterCat extends Form {
    
    public AjouterCat(Form previous){
        
    
    setTitle("Ajouter Categorie");
        setLayout(BoxLayout.y());
        
     
        TextField tfNom = new TextField("","Nom");
        
        Button btnAjouter = new Button("Ajouter Categorie");
        
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if( (tfNom.getText().length()==0)  )
                    
                    Dialog.show("Alert", "Remplir tous les champs", new Command("OK"));
                
                else {
                   
                        Cat c = new Cat(tfNom.getText());
                 
                    if(new ServiceCat().addCategorie(c))
                        
                    {
                        Dialog.show("SUCCES", "Bien ajoute", new Command("OK"));
                                        
                    
                    
                    }
                    
                    else 
                        
                        Dialog.show("ERROR", "Erreur de serveur", new Command("OK"));
                    
                }
                
                
                
            }
        });
        
        addAll(tfNom,btnAjouter);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    
}
    
}
