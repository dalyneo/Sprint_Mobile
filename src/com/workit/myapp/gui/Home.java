/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.workit.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Nadia
 */
public class Home extends BaseForm{
    
    Form current;
    private Resources theme = UIManager.initFirstTheme("/theme");
    public Home(){
        current=this;
        setTitle("Home ");
        setLayout(BoxLayout.y());
       
        
        add(new Label("choisir : "));
        Button btnEvent = new Button("Evenement");
        Button btnCat = new Button("Liste Categorie");
        
        btnEvent.addActionListener(e->new EvenementForm(current).show());
        btnCat.addActionListener(e->new AfficherCat(current,theme).show());
        addAll(btnEvent,btnCat);
    }
    
}
