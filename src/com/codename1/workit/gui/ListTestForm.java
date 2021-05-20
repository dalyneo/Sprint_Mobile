/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.workit.entities.Test;
import com.codename1.workit.services.ServiceTest;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 * @author walid belhadj
 */
public class ListTestForm extends Form {

    public ListTestForm(Form previous) {
        setTitle("Liste Test");
        
        SpanLabel sp = new SpanLabel();
        
        sp.setText(ServiceTest.getInstance().getAllTests().toString());
        add(sp);
            //// f twig 
    Button pdf=new Button("pdf");
 add(pdf);  
 pdf.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent evt) {
                        String path="";
        
        Document document = new Document();
      try
      {
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path+"Test.pdf"));
           document.open();
          PdfPTable tb1 = new PdfPTable(11);
          tb1.addCell("Nom");
          tb1.addCell("Q1");
          tb1.addCell("R1");
          tb1.addCell("Q2");
          tb1.addCell("R2");
          tb1.addCell("Q3");
          tb1.addCell("R3");
          tb1.addCell("Q4");
          tb1.addCell("R4");
          tb1.addCell("Q5");
          tb1.addCell("R5");
          
         ServiceTest es = new ServiceTest();
        ArrayList<Test> list = es.getAllTests();
          for (int i = 0; i < list.size(); i++) {
            
              String Nom= String.valueOf(list.get(i).getNom());
              String Q1= String.valueOf(list.get(i).getQ1());
              String R1= String.valueOf(list.get(i).getR1());
              String Q2= String.valueOf(list.get(i).getQ2());
              String R2= String.valueOf(list.get(i).getR2());
              String Q3= String.valueOf(list.get(i).getQ3());
              String R3= String.valueOf(list.get(i).getR3());
              String Q4= String.valueOf(list.get(i).getQ4());
              String R4= String.valueOf(list.get(i).getR4());
              String Q5= String.valueOf(list.get(i).getQ5());
              String R5= String.valueOf(list.get(i).getR5());
          tb1.addCell(Nom);
          tb1.addCell(Q1);
          tb1.addCell(R1);
          tb1.addCell(Q2);
          tb1.addCell(R2);
          tb1.addCell(Q3);
          tb1.addCell(R3);
          tb1.addCell(Q4);
          tb1.addCell(R4);
          tb1.addCell(Q5);
          tb1.addCell(R5);
         
         
          }
         document.add(new Paragraph("Test"));
         document.add(tb1);
         document.close();
         writer.close();
        com.codename1.io.File file=new com.codename1.io.File("test.pdf");
        //desktop.open(file);
      } catch (DocumentException e)
      {
         e.printStackTrace();
      }catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
                        //Logger.getLogger(ListFormation.class.getName()).log(Level.SEVERE, null, ex);

                     
                    }
                    
                }
                
                
                );
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}
