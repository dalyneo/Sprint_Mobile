/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.workit.entities.Encapsulation;

/**
 *
 * @author Wael Belhadj
 */
public class ProfilForm extends Form{

    ProfilForm(Resources res) {
        
         Button backButton = new Button("back");
        
        
       Label label1= new Label(Integer.toString(Encapsulation.getId()));
       Label label12 =new Label(Encapsulation.getNom());
       Label label2= new Label(Encapsulation.getPrenom());
       Label label3=   new Label(Encapsulation.getNomsociete());
       Label label4=  new Label(Encapsulation.getAdresse());
       Label label5=  new Label(Encapsulation.getMail());
       Label label6=  new Label(Integer.toString(Encapsulation.getNumsociete()));
       Label label7=  new Label(Encapsulation.getMdp());
       Label label8=  new Label(Encapsulation.getType());
       Label label9=  new Label(Encapsulation.getPhoto());
       Label label10=  new Label(Encapsulation.getCompetence());
       
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new HomeForm(res).show();
             
               
                   
            }
        });
       
        Container by = BoxLayout.encloseY(
                
                
               label1,label12,label3,label4,label5,label6,label7,label8,label9,label10,backButton
               
                
                ) ;    
        add( by);
       
    }
    
}
