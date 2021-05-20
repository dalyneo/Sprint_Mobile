/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.workit.entities.Encapsulation;
import com.codename1.workit.entities.Recruteur;
import com.codename1.workit.services.ServiceRecruteur;
import com.codename1.workit.gui.BaseForm;
import static com.sun.javafx.fxml.expression.Expression.add;

/**
 *
 * @author Wael Belhadj
 */
public class LoginF  extends BaseForm {

    public LoginF(Resources res) {
        super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));
        
        TextField login = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
         TextField erreur= new TextField("");
        login.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button loginButton = new Button("Login");
        Button signUp = new Button("Sign Up");
        Button loginfb = new Button("LOGIN WITH FACEBOOK");
        signUp.addActionListener(e -> new AddRecForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");
         loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
               Recruteur result =ServiceRecruteur.getInstance().login(login.getText(), password.getText()); 
               
                
               if(result.getId()==0)
               {
                   erreur.setText("the username or the password is incorrecte");
               }
               else if(result.getType().equals("recruteur"))
               {
                    new HomeForm(res).show();
                 //  System.out.println("recruteur");
                     Encapsulation.setCompte(result);
                 
               }
               else
               {
                   
                   //System.out.println("candidat");
                    new HomeForm(res).show();
                     Encapsulation.setCompte(result);
                   
               }
               
                   
            }
        });
         loginfb.addActionListener(new ActionListener() {
            private Resources theme_1;
            @Override
            public void actionPerformed(ActionEvent evt) {
                FacebookLogin Fblog = new FacebookLogin(theme_1);
                Fblog.show();
            }
        });
        Container content;
        content = BoxLayout.encloseY(
                new FloatingHint(login),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                loginButton,loginfb,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        loginButton.requestFocus();
       // signIn.addActionListener(e -> new NewsfeedForm(res).show());
    }
    
}
