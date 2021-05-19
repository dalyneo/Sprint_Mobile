/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.gui;

import com.codename1.ui.Form;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.workit.entites.Commenter;
import com.codename1.workit.entites.Forum;


import java.io.IOException;
import java.util.ArrayList;
import com.codename1.workit.services.CommenterService;

/**
 *
 * @author HP-PC
 */
public class ListCommenter extends Form {
     private Resources theme;

    public ListCommenter(int forum_id) {
        super("Commentaires", BoxLayout.y());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs(forum_id);
    }

    private void addGUIs(int id) {
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
        this.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "", 5), evt1 -> new ListForum(theme).show());

       

        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_ADD, "", 5), evt1 -> new AddCommenter(id).show());

        ArrayList<Commenter> list = CommenterService.getInstance().findAll();
        for(int i=0;i<list.size();i++){
            if (list.get(i).getForum_id() == id)
                this.add(item(list.get(i)));
        }
    }

    private Container item(Commenter eventType) {
        Container global = new Container(BoxLayout.x());
        Label lbComm = new Label(eventType.getCommentaire());
         Label lbRating = new Label(eventType.getRating()+"");
      
         
         
        
        lbComm.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        lbComm.getAllStyles().setFont(Font.createSystemFont(lbComm.getUnselectedStyle().getFont().getFace(), Font.STYLE_UNDERLINED, lbComm.getUnselectedStyle().getFont().getSize()));
        
       // lbProbleme.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        lbRating.getAllStyles().setFont(Font.createSystemFont(lbRating.getUnselectedStyle().getFont().getFace(), Font.STYLE_UNDERLINED, lbRating.getUnselectedStyle().getFont().getSize()));

        
        
        
        
        Container labels = new Container(BoxLayout.y()).addAll(lbComm,lbRating);
        global.add(labels);

       

         lbComm.addPointerReleasedListener(evt -> new CommenterShow(eventType).show());
        global.setLeadComponent(lbComm);
        

        return global;
    }
    
}
