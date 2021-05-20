/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.workit.entities.Test;
import com.codename1.workit.services.ServiceTest;

/**
 *
 * @author walid belhadj
 */
public class DeleteTestForm extends Form {
    public DeleteTestForm(Form previous) {
        
        setTitle("Delete Test");
        setLayout(BoxLayout.y());
        
       
        TextField tfId= new TextField("","Id");
        tfId.getAllStyles().setFgColor(ColorUtil.BLACK);
        Button btnValider = new Button("Delete Test");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
               Test t= new Test( Integer.parseInt(tfId.getText()));
                if( ServiceTest.getInstance().deleteTest(t))
                     System.out.println("Deleted successfully");   
                ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("Deleted successfully  ");
                                                  status.setExpires(10000);   
                      status.show();
                  
                     
                    
                    
                    refreshTheme();

               
                   
            }
        });
        
        
        addAll(tfId,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
    }
    
    
}
