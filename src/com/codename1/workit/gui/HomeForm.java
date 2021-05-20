/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author walid belhadj
 */
public class HomeForm extends Form {
  Form current;
    public HomeForm() {
        current =this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("choose your option"));
        Button btnAddTest = new Button("Add Test");
        Button btnListTest = new Button("List Test");
        Button btnDeleteTest = new Button("Delete Test");
        
        btnAddTest.addActionListener(e-> new AddTestForm(current).show());
        btnListTest.addActionListener(e-> new ListTestForm(current).show());
        btnDeleteTest.addActionListener(e -> new DeleteTestForm(current).show());
        addAll(btnAddTest,btnListTest,btnDeleteTest);
        
    }
    
}
