/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.workit.entities.Recruteur;
import com.codename1.workit.services.ServiceRecruteur;

/**
 *
 * @author Wael Belhadj
 */
public class DeleteRecForm extends Form {
      public DeleteRecForm(Form previous) {
        
        setTitle("Delete an account");
        setLayout(BoxLayout.y());
        
       
        TextField tfId= new TextField("","Id");
        Button btnValider = new Button("Delete compte");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
               Recruteur c= new Recruteur( Integer.parseInt(tfId.getText()));
                if( ServiceRecruteur.getInstance().deleteCompte(c))
                     System.out.println("hello there");  
                
               
                   
            }
        });
        
        
        addAll(tfId,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
}
