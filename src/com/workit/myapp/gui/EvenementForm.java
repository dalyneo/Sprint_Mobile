/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.workit.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Nadia
 */
public class EvenementForm extends BaseForm{
    
    Form current;
                private Resources theme = UIManager.initFirstTheme("/theme");

    
    public EvenementForm(Form previous) {
        current=this;
        setTitle("Home Evenement");
        setLayout(BoxLayout.y());
        
        add(new Label("choisir : "));
        Button btnAjoutEvent = new Button("Ajouter Evenement");
        Button btnAfficheEvent = new Button("Liste Evenement");
        
        btnAjoutEvent.addActionListener(e->new AjouterEvenement(current).show());
        btnAfficheEvent.addActionListener(e->new AfficherEvenement(current,theme).show());
        addAll(btnAjoutEvent,btnAfficheEvent);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
}
