package edu.devit.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.Style;

import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import edu.devit.entities.Freelancer;
import edu.devit.services.FreelancerCRUD;
import com.codename1.messaging.Message;


import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;


public class listFreelancerForm extends BaseForm{

    Form current;

    public listFreelancerForm(Resources res ) {

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

        partage.addActionListener((e) -> {
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
        //appel affichage methode
        ArrayList<Freelancer> list = FreelancerCRUD.getInstance().affichageFreelancer();

        for(Freelancer fre : list) {
            String urlImage = "back-logo.png";

            Image placeHolder = Image.createImage(120,90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder,false);
            URLImage urlim = URLImage.createToStorage(enc , urlImage , urlImage , URLImage.RESIZE_SCALE);
            addButton(urlim,fre,res);

            ScaleImageLabel image = new ScaleImageLabel(urlim);
            Container containerImg = new Container();

            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }


        //

    }
    public void recherche(){
        TextField searchField;
        searchField = new TextField("", " Rechercher  ");
        searchField.getHintLabel().setUIID("  ");
        searchField.setUIID("  ");
        getToolbar().setTitleComponent(searchField);
//if field content changed
        searchField.addDataChangeListener((i1, i2) -> {
            String t = searchField.getText();
            if(t.length() < 1) {
                for(Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
            } else {
                t = t.toLowerCase();
                for(Component cmp: getContentPane()) {
//tekhou el val ta3 el champ : champ li 3malt 3lih el recherche type span label (emplacement : container->container->spanlabel )
                    String val = ((Label) ((Container)((Container) cmp).getComponentAt(0)).getComponentAt(5)).getText();
                    boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
                    cmp.setHidden(!show);
                    cmp.setVisible(show);
                }
            }
            getContentPane().animateLayout(250);
        });
    }

    private void addButton(Image img , Freelancer fre, Resources res) {

        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);

        Button image = new Button(img.fill(width,height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        Label objetTxt = new Label("Mail :"+fre.getEmail(),"NewsTopLine2");
        Label nomTxt = new Label("nom :"+fre.getName(),"NewsTopLine2");



        //delete button
        Label lSupprimer  = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);

        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        lSupprimer.setIcon(supprimerImage);
        lSupprimer.setTextPosition(RIGHT);
      lSupprimer.addPointerPressedListener(l -> {

          Dialog dig = new Dialog("Delete");


          if(dig.show("Suppresion","vous voulez supprimez ce freelancer ?", "Anuuler","oui")){
              dig.dispose();
          }
          else {
              dig.dispose();
              //n3ayto l supprimer men service freelancer

              if(FreelancerCRUD.getInstance().deleteFreelancer(fre.getId())){




                  String accountSID = "AC68e207c17ed184d8cd034829c9171567";
                  String authToken = "c2e0c292af9a27aeba55e0cf154d3df0";
                  String fromPhone = "+13342928900";


                  Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
                          queryParam("To", "+21655215711").
                          queryParam("From", fromPhone).
                          queryParam("Body", "ef").
                          header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).
                          getAsJsonMap();


                  new listFreelancerForm(res).show();

              }
          }
              });

        //update icon
        Label lModifier  = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);

        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT , modifierStyle );
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);

        lModifier.addPointerPressedListener(l -> {
            System.out.println("date"+fre);
            new ModifierFreelancerForm(res,fre).show();
                });



        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(nomTxt,lSupprimer,lModifier),
                BoxLayout.encloseX(objetTxt)));

        add(cnt);
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

    private void addTab(Tabs swipe ,Label spacer, Image image, String string, String text , com.codename1.ui.util.Resources res) {
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
}
