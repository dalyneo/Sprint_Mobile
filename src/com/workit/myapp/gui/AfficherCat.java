/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.workit.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.workit.myapp.entities.Cat;
import com.workit.myapp.entities.Evenement;
import static com.workit.myapp.gui.ModifierCat.idC;
import static com.workit.myapp.gui.ModifierCat.nomC;
import com.workit.myapp.services.ServiceCat;
import com.workit.myapp.services.ServiceEvenement;
import java.util.ArrayList;

/**
 *
 * @author Nadia
 */
public class AfficherCat extends BaseForm{
    
        private Resources theme = UIManager.initFirstTheme("/theme");

//    static TextField nomE = new TextField();
//    static TextField idE = new TextField();
    
    public AfficherCat(Form previous  , Resources res){
        
       
//        setTitle("Afficher Categorie");
//        
//        SpanLabel sp = new SpanLabel();
//        
//        sp.setText(ServiceCategorie.getInstance().getAllCat().toString());
//        
//        
//        
//        
//          Button ButtonModifCat = new  Button("Modifier Categorie");
//          Button ButtonSuppCat = new  Button ("Supprimer Evenement");
//          
//          ButtonModifCat.addActionListener(
//    e -> {
//           
//          //ServiceEvenement sE= new ServiceEvenement();
//            
//             Categorie  c = new Categorie();
//          ModifierCategorie mc =  new ModifierCategorie(c);
//          System.out.println("categorie  :"+c.toString());
//            mc.getF().show();
//           //sE.EditEvenement2(ev);
//           
//    });
//    
//      ButtonSuppCat.addActionListener(
//    e -> {
//           
//           Categorie c = new Categorie();
//           ServiceCategorie sc= new ServiceCategorie();
//        
//           sc.DeleteCategorie(c);
//           new AfficherCategorie(previous,theme).show();
//    });
//    
//          
//          addAll(sp,ButtonModifCat,ButtonSuppCat);
//        
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());




setTitle("List Categorie");
        
 Toolbar tb = new Toolbar(true);
        setToolbar(tb);
       
       // sp.setText(new ServiceEvent().getAllEvent().toString());
        ArrayList<Cat> listC = new ServiceCat().getAllCat();
        
        for (Cat obj : listC){
       
            setLayout(BoxLayout.y());
             Button btnid = new Button();
             //Button delete = new Button("Supprimer Categorie");
             //Button update = new Button("Modifier Categorie");
             
             
             /* delete.addActionListener((evt) -> {
                 new ServiceCategorie().DeleteCategorie(obj.getId());
                 new  AfficherCategorie(previous,theme).show();
             });
//             

             update.addActionListener((evt) -> {
                 ModifierCategorie.nomC.setText(obj.getNom());
                
//                 ModifierCategorie.tflieu.setText(obj.getLieu());
                  ModifierCategorie.idC.setText(String.valueOf(obj.getId()));
                  System.out.println(obj.getNom());
                 new  ModifierCategorie(previous).show();
                 
              
                 
                 
             }); */
             
             btnid.setText(String.valueOf(obj.getId()));
             SpanLabel sp = new SpanLabel();
             //SpanLabel splieu = new SpanLabel();
             sp.setText(obj.getNom());
           
             addAll(btnid,sp);
        }
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        
        
    } 
 


    }
    
    

        
    


    

