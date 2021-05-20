/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.workit.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
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
import com.codename1.workit.entities.Recruteur;
import com.codename1.workit.services.ServiceRecruteur;
import static com.sun.javafx.fxml.expression.Expression.add;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class AddRecForm extends BaseForm {

    public AddRecForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
          TextField tfn = new TextField("","nom");
       
        TextField tfp = new TextField("","prenom");
       
        TextField tfns = new TextField("","nomsociete");
        
        TextField tfa = new TextField("","adresse");
       
        TextField tfm = new TextField("","mail");
       
        TextField tfnum = new TextField("","numsociete");
        
        TextField tfmdp = new TextField("","mdp");
        
        TextField tft = new TextField("","type");
       
        TextField tfph = new TextField("","photo");
       
        TextField tfc = new TextField("","competence");
       
        
        tfn.setSingleLineTextArea(false);
        tfp.setSingleLineTextArea(false);
        tfns.setSingleLineTextArea(false);
        tfa.setSingleLineTextArea(false);
        tfa.setSingleLineTextArea(false);
        tfnum.setSingleLineTextArea(false);
        tfmdp.setSingleLineTextArea(false);
        tft.setSingleLineTextArea(false);
        tfph.setSingleLineTextArea(false);
        tfc.setSingleLineTextArea(false);
        
        Button next = new Button("Valider");
        Button signIn = new Button("SignIn");
        Button btnValider = new Button("Add Account");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
         next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if((tfn.getText().length()==0)||(tfp.getText().length()==0)||(tfns.getText().length()==0)||(tfa.getText().length()==0)||(tfm.getText().length()==0)||(tfnum.getText().length()==0)||(tfmdp.getText().length()==0)||(tft.getText().length()==0)||(tfph.getText().length()==0)||(tfc.getText().length()==0)){
                    Dialog.show("Alert","Please fill all the fields",new Command("OK"));
                }
                else {
                    try {
                        Recruteur t = new Recruteur(tfn.getText(),tfp.getText(),tfns.getText(),tfa.getText(),tfm.getText(),Integer.valueOf(tfnum.getText()),tfmdp.getText(),tft.getText(),tfph.getText(),tfc.getText());
                         ServiceRecruteur sr = new ServiceRecruteur();
                         sr.addCompte(t);
                         ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("user added successfully!!");
                                                  status.setExpires(10000);   
                      status.show();
                  
                     
                    
                    
                    refreshTheme();

                          new LoginF(res).show();
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Name must be string", new Command("OK"));
                    }
                }
            }

           

           
        });
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(tfn),
                createLineSeparator(),
                new FloatingHint(tfp),
                createLineSeparator(),
                new FloatingHint(tfns),
                createLineSeparator(),
                new FloatingHint(tfa),
                createLineSeparator(),
                new FloatingHint(tfm),
                createLineSeparator(),
                new FloatingHint(tfnum),
                createLineSeparator(),
                new FloatingHint(tfmdp),
                createLineSeparator(),
                new FloatingHint(tft),
                createLineSeparator(),
                new FloatingHint(tfph),
                createLineSeparator(),
                new FloatingHint(tfc),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
       
    }
    
}
