/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.workit.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.workit.myapp.entities.Cat;
import com.workit.myapp.entities.Evenement;
import com.workit.myapp.services.ServiceCat;
import com.workit.myapp.services.ServiceEvenement;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.String.valueOf;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Nadia
 */
public class AfficherEvenement extends BaseForm{
    
    
            private Resources theme = UIManager.initFirstTheme("/theme");
            Button capture = new Button("Capture");
            

    public AfficherEvenement(Form previous, Resources res){
        
       
//        setTitle("Afficher Evenement");
//        
//        SpanLabel sp = new SpanLabel();
//        
//        sp.setText(ServiceEvenement.getInstance().getAllEvent().toString());
//        
//          Button ButtonModifEvent = new  Button("Modifier Categorie");
//          Button ButtonSuppEvent = new  Button ("Supprimer Evenement");
//          
//          ButtonModifEvent.addActionListener(
//    e -> {
//           
//          //ServiceEvenement sE= new ServiceEvenement();
//            
//             Evenement ev = new Evenement();
//          ModifierEvenement mc =  new ModifierEvenement(ev);
//          System.out.println("categorie  :"+ev.toString());
//            mc.getF().show();
//           //sE.EditEvenement2(ev);
//           
//    });
//    
//      ButtonSuppEvent.addActionListener(
//    e -> {
//           
//             Evenement ev = new Evenement();
//           ServiceEvenement se= new ServiceEvenement();
//        
//           se.DeleteEvenement(ev);
//           new AfficherEvenement(previous,theme).show();
//    });
//    
//      
//      
//        addAll(sp,ButtonModifEvent,ButtonSuppEvent);
//        
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());

// 


setTitle("List Evenement");
        
       
       // sp.setText(new ServiceEvent().getAllEvent().toString());
        ArrayList<Evenement> listE = new ServiceEvenement().getAllEvent();
        
        for (Evenement obj : listE){
       
            setLayout(BoxLayout.y());
             Button btnid = new Button();
             Button delete = new Button("Supprimer Evenement");
             Button update = new Button("Modifier Evenement");
             Button participe = new Button("Participer");
             
             // rate
            
            Slider star = createStarRankSlider();
        star.setProgress((int)(obj.getNbetoile()*2));
        
        Label lrate = new Label("Rate");
        lrate.setTextPosition(LEFT);
        lrate.addPointerPressedListener(l->{
            // algorithme calcul rating
            float nb_etoile = obj.getNbetoile();
            int nb_rate=obj.getNbvote();
            nb_etoile= nb_etoile*nb_rate;
            nb_etoile=nb_etoile+(float)star.getProgress()/2;
            System.out.println(nb_etoile);
            nb_rate++;
            nb_etoile=nb_etoile/nb_rate;
            obj.setNbetoile(nb_etoile);
            obj.setNbvote(nb_rate);
            if( new ServiceEvenement().UpdateEventRate(obj,obj.getId()))
                            Dialog.show("Success","Merci d'avoir noté l'hotel",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
            
            new  AfficherEvenement(previous,theme).show();
           // new ListEventForm(current).show();
          //  new HotelFront(current).show();
            });
            
            // fin rate
             
             participe.addActionListener((evt) -> {
                 Evenement e = new Evenement ();
                 int ne = e.getNbp();
                 ne = ne +1;
                 Dialog.show("Participation", "Votre Participation est bien enregistre", new Command("OK"));
             });
             delete.addActionListener((evt) -> {
                 new ServiceEvenement().DeleteEvenement(obj.getId());
                 Dialog.show("Evenement Supprime", "Votre Evenement est bien supprime", new Command("OK"));
                 new  AfficherEvenement(previous,theme).show();
             });
//             

             update.addActionListener((evt) -> {
                 ModifierEvenement.nomE.setText(obj.getNom());
                 ModifierEvenement.dateE.setText(obj.getDate());
                 ModifierEvenement.descriptionE.setText(obj.getDescription());
                 ModifierEvenement.emailE.setText(obj.getEmail());
                 //ModifierEvenement.nbpE.setText(String.valueOf(obj.getNbp()));
                
//                 ModifierCategorie.tflieu.setText(obj.getLieu());
                  ModifierEvenement.idE.setText(String.valueOf(obj.getId()));
                  System.out.println(obj.getNom());
                  System.out.println(obj.getDate());
                  System.out.println(obj.getDescription());
                  System.out.println(obj.getEmail());
                  //System.out.println(obj.getNbp());
                 new  ModifierEvenement(previous).show();
                 
              
                 
                 
             });
             
             btnid.setText(String.valueOf(obj.getId()));
             SpanLabel sp = new SpanLabel();
             SpanLabel sp1 = new SpanLabel();
             SpanLabel sp2 = new SpanLabel();
             SpanLabel sp3 = new SpanLabel();
             //SpanLabel sp4 = new SpanLabel();
             //SpanLabel splieu = new SpanLabel();
             sp.setText(obj.getNom());
             sp1.setText(obj.getDate());
             sp2.setText(obj.getDescription());
             sp3.setText(obj.getEmail());
       
             
             addAll(btnid,delete,update,participe,FlowLayout.encloseCenter(star),lrate,sp,sp1,sp2,sp3);
        }
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->previous.showBack());
        
         add(capture);
        capture.addActionListener((e) -> {

            Image screenshot = Image.createImage(this.getWidth(), this.getHeight());
            this.revalidate();
            this.setVisible(true);
            this.paintComponent(screenshot.getGraphics(), true);
            String directory = "file://C:/Users/Nadia/";

            String imageFile = directory + "screenshot" + valueOf(new Random().nextInt()).substring(1, 6) + ".png";
            //FileSystemStorage.getInstance().getAppHomePath().
            System.out.println(imageFile);
            try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
            } catch (IOException err) {
                Log.e(err);
            }
               

        });
        
    }
    
    private Slider createStarRankSlider() {
    Slider starRank = new Slider();
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(10);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    return starRank;
}

    private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}
    
    
    
    
  

        
    
}
    
    
    
    

