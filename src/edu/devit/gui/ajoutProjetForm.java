package edu.devit.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import edu.devit.entities.Freelancer;
import edu.devit.entities.Projet;
import edu.devit.services.FreelancerCRUD;
import edu.devit.services.ProjetCRUD;

public class ajoutProjetForm extends BaseForm{
    Form current;

    public ajoutProjetForm(Resources res) {
        super("Newsfeed", BoxLayout.y()); //heritate men newsfeed w  l formulaire vertical

        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("ajout Projet");
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
        RadioButton liste = RadioButton.createToggle("Projet", barGroup);
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



        TextField nom_projet = new TextField("", "entre nom!!");
        nom_projet.setUIID("TextFieldBlack");
        addStringValue("nom",nom_projet);

        TextField projet_description = new TextField("", "entre description!!");
        projet_description.setUIID("TextFieldBlack");
        addStringValue("projet_description",projet_description);

        TextField job_type = new TextField("", "entre job_type!!");
        job_type.setUIID("TextFieldBlack");
        addStringValue("job_type",job_type);

        TextField job_salary = new TextField("", "entre job_salary!!");
        job_salary.setUIID("TextFieldBlack");
        addStringValue("job_salary",job_salary);

        TextField job_time = new TextField("", "entre job_time!!");
        job_time.setUIID("TextFieldBlack");
        addStringValue("title",job_time);

        TextField logo = new TextField("", "entre logo!!");
        logo.setUIID("TextFieldBlack");
        addStringValue("logo",logo);



        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);


        //on click button event


        btnAjouter.addActionListener((e) -> {
            try {

                if(nom_projet.getText().equals("") || projet_description.getText().equals("")){
                    Dialog.show("Veuillez vérifier les données" ,"","Annuler" , "OK");
                }
                else {
                    InfiniteProgress ip = new InfiniteProgress(); //loading after insert date

                    final Dialog iDialog = ip.showInfiniteBlocking();

                    Projet p = new Projet(String.valueOf(nom_projet.getText()).toString() , String.valueOf(projet_description.getText()).toString(), String.valueOf(job_type.getText()).toString(),String.valueOf(job_salary.getText()).toString() , String.valueOf(job_time.getText()).toString() , String.valueOf(logo.getText()).toString());
                    System.out.println("data Projet == "+p);


                    //appelle methode ajouterProjet mta3 service freelancer bech nzido donnée mteena fel base

                    ProjetCRUD.getInstance().AddProjet(p);


                    iDialog.dispose(); //na7iw loading baed maamalna ajout

                    //new listFreelancerForm(res).show();

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
