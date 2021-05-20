/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.io.File;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
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
public class AddTestForm extends Form {

    public AddTestForm(Form previous) {
        setTitle("Add new test");
        setLayout(BoxLayout.y());
        
        TextField tfn = new TextField("","nom");
        tfn.getAllStyles().setFgColor(ColorUtil.BLACK);
        TextField tfq1 = new TextField("","q1");
        tfq1.getAllStyles().setFgColor(ColorUtil.BLACK);
        TextField tfr1 = new TextField("","r1");
        tfr1.getAllStyles().setFgColor(ColorUtil.BLACK);
        TextField tfq2 = new TextField("","q2");
        tfq2.getAllStyles().setFgColor(ColorUtil.BLACK);
        TextField tfr2 = new TextField("","r2");
        tfr2.getAllStyles().setFgColor(ColorUtil.BLACK);
        TextField tfq3 = new TextField("","q3");
        tfq3.getAllStyles().setFgColor(ColorUtil.BLACK);
        TextField tfr3 = new TextField("","r3");
        tfr3.getAllStyles().setFgColor(ColorUtil.BLACK);
        TextField tfq4 = new TextField("","q4");
        tfq4.getAllStyles().setFgColor(ColorUtil.BLACK);
        TextField tfr4 = new TextField("","r4");
        tfr4.getAllStyles().setFgColor(ColorUtil.BLACK);
        TextField tfq5 = new TextField("","q5");
        tfq5.getAllStyles().setFgColor(ColorUtil.BLACK);
        TextField tfr5 = new TextField("","r5");
        tfr5.getAllStyles().setFgColor(ColorUtil.BLACK);
        Button btnValider = new Button("Add Test");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if((tfn.getText().length()==0)||(tfq1.getText().length()==0)||(tfr1.getText().length()==0)||(tfq2.getText().length()==0)||(tfr2.getText().length()==0)||(tfq3.getText().length()==0)||(tfr3.getText().length()==0)||(tfq4.getText().length()==0)||(tfr4.getText().length()==0)||(tfq5.getText().length()==0)||(tfr5.getText().length()==0)){
                    Dialog.show("Alert","Please fill all the fields",new Command("OK"));
                }
                else {
                    try {
                        Test t = new Test(tfn.getText(),tfq1.getText(),tfr1.getText(),tfq2.getText(),tfr2.getText(),tfq3.getText(),tfr3.getText(),tfq4.getText(),tfr4.getText(),tfq5.getText(),tfr5.getText());
                        if(new ServiceTest().addTest(t)){
                            Dialog.show("Success","Connection accepted", new Command("OK"));
                          
                            try {  
                                mailUtil.sendMail("seifeddine.fathallah@esprit.tn","seifeddine.fathallah@esprit.tn","seif0404","alert Quiz","Test added successfully");
                            } catch (Exception ex) {
                            }
                        }
                        else {
                            Dialog .show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Name must be string", new Command("OK"));
                    }
                }
            }
        });
        addAll(tfn,tfq1,tfr1,tfq2,tfr2,tfq3,tfr3,tfq4,tfr4,tfq5,tfr5,btnValider);
        
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
