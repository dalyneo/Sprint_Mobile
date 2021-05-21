package edu.devit.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import edu.devit.entities.Freelancer;
import com.codename1.ui.TextField;

import com.codename1.ui.util.Resources;
import edu.devit.services.FreelancerCRUD;

public class ModifierFreelancerForm extends BaseForm{

    Form current;
    public ModifierFreelancerForm(Resources res , Freelancer f){
        super("Newsfeed", BoxLayout.y()); //heritate men newsfeed w  l formulaire vertical

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("modifier Freelancer");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        TextField email = new TextField(f.getEmail(), "Email" , 20 , TextField.ANY);
        TextField name = new TextField(f.getName(), "Name" , 20 , TextField.ANY);
        TextField password = new TextField(f.getPassword(), "Password" , 20 , TextField.ANY);
        TextField title = new TextField(f.getTitle(), "Title" , 20 , TextField.ANY);
        TextField skills = new TextField(f.getSkills(), "Skills" , 20 , TextField.ANY);
        TextField country = new TextField(f.getCountry(), "Country" , 20 , TextField.ANY);
        TextField experience = new TextField(f.getExperience(), "Experience" , 20 , TextField.ANY);
        TextField photo = new TextField(f.getPhoto(), "Photo" , 20 , TextField.ANY);
        TextField prix = new TextField(String.valueOf(f.getPrix()), "prix" , 20 , TextField.ANY);

        email.setUIID("NewsTopLine");
        name.setUIID("NewsTopLine");
        password.setUIID("NewsTopLine");
        title.setUIID("NewsTopLine");
        skills.setUIID("NewsTopLine");
        country.setUIID("NewsTopLine");
        experience.setUIID("NewsTopLine");
        photo.setUIID("NewsTopLine");
        prix.setUIID("NewsTopLine");

        email.setSingleLineTextArea(true);
        name.setSingleLineTextArea(true);
        password.setSingleLineTextArea(true);
        title.setSingleLineTextArea(true);
        skills.setSingleLineTextArea(true);
        country.setSingleLineTextArea(true);
        experience.setSingleLineTextArea(true);
        photo.setSingleLineTextArea(true);
        prix.setSingleLineTextArea(true);

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        btnModifier.addPointerPressedListener(l -> {
            System.out.println("data freelancer == "+f);
           f.setEmail(email.getText());
            f.setName(name.getText());
            f.setPassword(password.getText());
            f.setTitle(title.getText());
            f.setSkills(skills.getText());
            f.setCountry(country.getText());
            f.setExperience(experience.getText());
            f.setPhoto(photo.getText());
            f.setPrix(0);

            if(FreelancerCRUD.getInstance().modifierFreelancer(f)){
                new listFreelancerForm(res).show();
            }
            Button btnAnnuler = new Button("Annuler");
            btnAnnuler.addActionListener(e -> {
               new listFreelancerForm(res).show();
            });

            Label l2 = new Label("");

            Label l3 = new Label("");

            Label l4 = new Label("");

            Label l5 = new Label("");

            Label l6 = new Label("");

            Label l7 = new Label("");

            Label l8 = new Label("");

            Label l9 = new Label("");

            Label l11 = new Label("");

            Label l10 = new Label("");


            Label l1 = new Label();

            Container content = BoxLayout.encloseY(
                    l1, l2 , l3 , l4 , l5 , l6 , l7 , l8,l9,
                    new FloatingHint(email),
                    createLineSeparator(),
                    new FloatingHint(name),
                    createLineSeparator(),
                    new FloatingHint(password),
                    createLineSeparator(),
                    new FloatingHint(title),
                    createLineSeparator(),
                    new FloatingHint(skills),
                    createLineSeparator(),
                    new FloatingHint(country),
                    createLineSeparator(),
                    new FloatingHint(experience),
                    createLineSeparator(),
                    new FloatingHint(photo),
                    createLineSeparator(),
                    new FloatingHint(prix),
                    createLineSeparator(),

            l10 , l11 ,
            btnModifier,
            btnAnnuler
            );
            add(content);
            show();
        });















    }
}
