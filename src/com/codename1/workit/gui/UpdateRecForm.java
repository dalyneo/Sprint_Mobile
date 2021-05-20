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
public class UpdateRecForm extends Form{
     public UpdateRecForm(Form previous) {
    setTitle("Update an account");
        setLayout(BoxLayout.y());
        
        TextField tfn= new TextField("","nom");
        TextField tfp= new TextField("","prenom");
        TextField tfns= new TextField("","nomsociete");
        TextField tfa= new TextField("","adresse");
        TextField tfm= new TextField("","mail");
        TextField tfnum= new TextField("","numsociete");
        TextField tfmdp= new TextField("","mdp");
        TextField tft= new TextField("","type");
        TextField tfph= new TextField("","photo");
        TextField tfc= new TextField("","competence");
        
        Button btnValider = new Button("Update account");
        
        /*btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
               Recruteur c= new Recruteur(tfn.getText(),tfp.getText(), tfns.getText(), tfa.getText(), tfm.getText(), 12, tfnum.getText(), tfmdp.getText(), tft.getText(), tfph.getText(), tfc.getText() ,13);
                if( ServiceRecruteur.getInstance().updateCompte(c))
                     System.out.println("hello there");       
               
                   
            }
        });*/
        
        
        addAll(tfn,tfp,tfns,tfa,tfm,tfnum,tfmdp,tft,tfph,tfc,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
    
    
}
