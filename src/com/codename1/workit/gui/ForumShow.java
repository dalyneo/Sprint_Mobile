/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.workit.entites.Forum;
import com.codename1.workit.services.ForumService;
import java.io.IOException;

/**
 *
 * @author HP-PC
 */
public class ForumShow extends Form {
    private Forum eventType;
    private TextField tfName;
     private TextField tfProbleme;
     private ComboBox<String> themeComboBox;
    private Button btnUpdate;
      private Resources theme;

    public ForumShow(Forum eventType) {
        super(eventType.getSujet(), BoxLayout.yCenter());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.eventType = eventType;
        addGUIs();
        addActions();
        
    }

    private void addGUIs() {
        
        //this.getToolbar().addCommandToOverflowMenu("Home", null, evt1 -> new HomeForm().show());
      
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_DELETE, "", 5), actionEvent -> {
            if(Dialog.show("Confirmation", "Supprimer " + eventType.getSujet() + " ?", "Oui", "Non" )) {
                ForumService.getInstance().delete(eventType.getId());
                new ListForum(theme).show();
            }
        });

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "", 5), evt1 -> new ListForum(theme).show());

        tfName = new TextField(eventType.getSujet(), "Nom");
         tfName.getAllStyles().setFgColor(ColorUtil.BLACK);
         tfProbleme = new TextField(eventType.getProbleme(), "probleme");
          tfProbleme.getAllStyles().setFgColor(ColorUtil.BLACK);
        themeComboBox = new ComboBox<>();
        themeComboBox.addItem("Finance");
        themeComboBox.addItem("Managment");
        themeComboBox.addItem("Soft skills");
        themeComboBox.setSelectedItem(eventType.getTheme());
        btnUpdate = new Button("Modifier");

        this.addAll(new Container(BoxLayout.xCenter()).add(tfName),
               new Container(BoxLayout.xCenter()).add(tfProbleme),
               new Container(BoxLayout.xCenter()).add(themeComboBox), btnUpdate);
        

    }

    private void addActions() {
        btnUpdate.addActionListener(actionEvent -> {
            eventType.setSujet(tfName.getText());
            eventType.setProbleme(tfProbleme.getText());
            eventType.setTheme(themeComboBox.getSelectedItem());
            if (ForumService.getInstance().update(eventType)) {
                Dialog.show("Information", eventType.getSujet() + " Modifié", "OK",null);
                new ListForum(theme).show();
            } else {
                Dialog.show("Erreur", "Erreur De Modification", "OK",null);
            }
        });
    }
  
    
}
