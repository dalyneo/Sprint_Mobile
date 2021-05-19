/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.gui;

import com.codename1.ui.Form;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.workit.entites.Forum;


import java.io.IOException;
import java.util.ArrayList;
import com.codename1.workit.services.ForumService;
/**
 *
 * @author HP-PC
 */
public class ListForum extends BaseForm {
    Form current;
     private Resources theme;

    public ListForum(Resources res) {
        super("Forum", BoxLayout.y());
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        addGUIs();
        
        
        
        
    }

    private void addGUIs() {
      
        
       FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LOGOUT, "", 5);
        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_PIE_CHART, "", 5), evt1 -> new ThemeStats());

       

        this.getToolbar().addCommandToRightBar(null, FontImage.createMaterial(FontImage.MATERIAL_ADD, "", 5), evt1 -> new AddForum(theme).show());

        ArrayList<Forum> list = ForumService.getInstance().findAll();
        System.out.println(list);
         
        for(int i=0;i<list.size();i++){

           this.add(item(list.get(i)));
         
     
        }
    }

    private Container item(Forum eventType) {
        Container global = new Container(BoxLayout.x());
        Label lbName = new Label(eventType.getSujet());
         Label lbProbleme = new Label(eventType.getProbleme());
          Label lbTheme = new Label(eventType.getTheme());
          
          Button btnCommentaires = new Button("Commentaires");
          btnCommentaires.addActionListener((evt) -> {
              new ListCommenter(eventType.getId()).show();
          });
         
         
        
        lbName.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        lbName.getAllStyles().setFont(Font.createSystemFont(lbName.getUnselectedStyle().getFont().getFace(), Font.STYLE_UNDERLINED, lbName.getUnselectedStyle().getFont().getSize()));
        
       // lbProbleme.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        lbProbleme.getAllStyles().setFont(Font.createSystemFont(lbProbleme.getUnselectedStyle().getFont().getFace(), Font.STYLE_UNDERLINED, lbProbleme.getUnselectedStyle().getFont().getSize()));

        
        // lbProbleme.getAllStyles().setFgColor(ColorUtil.rgb(228, 53, 83));
        lbTheme.getAllStyles().setFont(Font.createSystemFont(lbTheme.getUnselectedStyle().getFont().getFace(), Font.STYLE_UNDERLINED, lbTheme.getUnselectedStyle().getFont().getSize()));
        
        // Container x = new Container(BoxLayout.x()).add(lbName, btnCommentaires);
        
        Container labels = new Container(BoxLayout.y()).addAll(lbName, btnCommentaires, lbProbleme,lbTheme);
        global.add(labels);

       

         lbName.addPointerReleasedListener(evt -> new ForumShow(eventType).show());
       // global.setLeadComponent(lbName);
        

        return global;
    }
     
}
   
