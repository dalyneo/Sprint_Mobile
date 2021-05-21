package edu.devit.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import edu.devit.entities.Freelancer;
import edu.devit.services.FreelancerCRUD;
import edu.devit.utils.Connection;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class ajoutFreelancerForm extends BaseForm{


    Form current;

    public ajoutFreelancerForm(Resources res) {
        super("Newsfeed", BoxLayout.y()); //heritate men newsfeed w  l formulaire vertical

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("ajout Freelancer");
        getContentPane().setScrollVisible(false);


        tb.addSearchCommand(e -> {

        });

        Tabs swipe = new Tabs();
        Label s1 = new Label();
        Label s2 = new Label();

        addTab(swipe ,s1, res.getImage("back-logo.png"),"","",res);

        //
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Freelancers", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Freelancer", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        liste.addActionListener((e) -> {

            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

            ajoutFreelancerForm a = new ajoutFreelancerForm(res);
            a.show();
            refreshTheme();
                });

        mesListes.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

              listFreelancerForm a = new listFreelancerForm(res);

              a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });


        //



        TextField email = new TextField("", "entre Email!!");
        email.setUIID("TextFieldBlack");
        addStringValue("email",email);

        TextField name = new TextField("", "entre Name!!");
        name.setUIID("TextFieldBlack");
        addStringValue("name",name);

        TextField password = new TextField("", "entre Password!!");
        email.setUIID("TextFieldBlack");
        addStringValue("password",password);

        TextField photo = new TextField("", "entre Photo!!");
        name.setUIID("TextFieldBlack");
        addStringValue("photo",photo);

        TextField title = new TextField("", "entre Title!!");
        name.setUIID("TextFieldBlack");
        addStringValue("title",title);

        TextField skills = new TextField("", "entre Skills!!");
        name.setUIID("TextFieldBlack");
        addStringValue("skills",skills);

        TextField country = new TextField("", "entre Country!!");
        name.setUIID("TextFieldBlack");
        addStringValue("country",country);

        TextField prix = new TextField("", "entre Prix!!");
        name.setUIID("TextFieldBlack");
        addStringValue("prix",prix);

        TextField experience = new TextField("", "entre Experience!!");
        name.setUIID("TextFieldBlack");
        addStringValue("experience",experience);

        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);


        //on click button event


        btnAjouter.addActionListener((e) -> {

            try {

                if(email.getText().equals("") || name.getText().equals("")){
                    Dialog.show("Veuillez vérifier les données" ,"","Annuler" , "OK");
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress(); //loading after insert date

                    final Dialog iDialog = ip.showInfiniteBlocking();

                    Freelancer f = new Freelancer(String.valueOf(email.getText()).toString() , String.valueOf(name.getText()).toString(), String.valueOf(password.getText()).toString(),String.valueOf(photo.getText()).toString() , String.valueOf(title.getText()).toString() , String.valueOf(skills.getText()).toString(), String.valueOf(country.getText()).toString(), 0, String.valueOf(experience.getText()).toString());
                    System.out.println("data freelancer == "+f);

                    listFreelancerForm a = new listFreelancerForm(res);




                    //appelle methode ajouterFreelancer mta3 service freelancer bech nzido donnée mteena fel base

                    FreelancerCRUD.getInstance().AddFreelancer(f);
                    LocalNotification n = new LocalNotification();
                    n.setId("demo-notification");
                    n.setAlertBody("It's time to take a break and look at me");
                    n.setAlertTitle("Break Time!");
                    n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


                    Display.getInstance().scheduleLocalNotification(
                            n,
                            System.currentTimeMillis() + 10 * 1000, // fire date/time
                            LocalNotification.REPEAT_NONE  // Whether to repeat and what frequency
                    );

                    


                    iDialog.dispose(); //na7iw loading baed maamalna ajout

                    new listFreelancerForm(res).show();


                    refreshTheme(); //actualiser
                }
            }catch(Exception ex) {
                ex.printStackTrace();
            }

        });

    }




    private void addTab(Tabs swipe ,Label spacer, Image image, String string, String text , Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth() , Display.getInstance().getDisplayHeight());
        if(image.getHeight() < size ) {
            image = image.scaledHeight(size);
        }

        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2 ) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }

        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label overLay = new Label("", "ImageOverlay");

        Container page1 =
                LayeredLayout.encloseIn(
                        imageScale,
                             overLay,
                        BorderLayout.south(
                                BoxLayout.encloseY(
                                        new SpanLabel(text , "LargeWhiteText"),
                                        spacer
                                )
                        )
                );
        swipe.addTab("",res.getImage("back-logo.png"), page1);
    }

    public void bindButtonSelection(Button btn , Label l){
        btn.addActionListener(e -> {
          if(btn.isSelected()) {
              updateArrowPosition(btn,l);
          }
        });
    }

    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
    }

    private void addStringValue(String s, Component v) {
     add(BorderLayout.west(new Label(s,"FaddedLabel")).add(BorderLayout.CENTER,v));
     add(createLineSeparator(0xeeeee));
    }


}
