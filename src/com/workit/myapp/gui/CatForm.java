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
public class CatForm extends Form{
    Form current;
    
            private Resources theme = UIManager.initFirstTheme("/theme");

    
    public CatForm(Form previous){
        current=this;
        setTitle("Home Categorie");
        setLayout(BoxLayout.y());
        
        add(new Label("choisir : "));
        Button btnAjoutCat = new Button("Ajouter Categorie");
        Button btnAfficheCat = new Button("Liste Categorie");
        
        btnAjoutCat.addActionListener(e->new AjouterCat(current).show());
        btnAfficheCat.addActionListener(e->new AfficherCat(current,theme).show());
        addAll(btnAjoutCat,btnAfficheCat);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
}
