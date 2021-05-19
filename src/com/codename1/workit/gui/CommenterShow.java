/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.workit.entites.Commenter;
import com.codename1.workit.services.CommenterService;
import java.io.IOException;

/**
 *
 * @author HP-PC
 */
public class CommenterShow extends Form {
     private Commenter eventType;
    private TextField tfComm;
     private TextField tfRating;
    private Button btnUpdate;
    
      private Resources theme;

    public CommenterShow(Commenter eventType) {
        super(eventType.getCommentaire(), BoxLayout.yCenter());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.eventType = eventType;
        addGUIs(eventType);
        addActions(eventType);
    }

    private void addGUIs(Commenter commentaire) {
        
        //this.getToolbar().addCommandToOverflowMenu("Home", null, evt1 -> new HomeForm().show());
      
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_DELETE, "", 5), actionEvent -> {
            if(Dialog.show("Confirmation", "Supprimer " + eventType.getCommentaire() + " ?", "Oui", "Non" )) {
                CommenterService.getInstance().delete(eventType.getRef());
                new ListCommenter(commentaire.getForum_id()).show();
            }
        });

        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "", 5), evt1 -> new ListForum(theme).show());

        tfComm = new TextField(eventType.getCommentaire(), "Commentaire");
           tfComm.getAllStyles().setFgColor(ColorUtil.BLACK);
         tfRating = new TextField(eventType.getRating()+"", "Rating");
            tfRating.getAllStyles().setFgColor(ColorUtil.BLACK);
          
        btnUpdate = new Button("Modifier");

        this.addAll(new Container(BoxLayout.xCenter()).add(tfComm),
               new Container(BoxLayout.xCenter()).add(tfRating), btnUpdate);
        

    }

    private void addActions(Commenter commentaire) {
        btnUpdate.addActionListener(actionEvent -> {
            eventType.setCommentaire(tfComm.getText());
            eventType.setRating( Integer.parseInt(tfRating.getText()));
          
            if (CommenterService.getInstance().update(eventType)) {
                Dialog.show("Information", eventType.getCommentaire() + " Modifié", "OK",null);
                new ListCommenter(commentaire.getForum_id()).show();
            } else {
                Dialog.show("Erreur", "Erreur De Modification", "OK",null);
            }
        });
    }
  
    
    
}
