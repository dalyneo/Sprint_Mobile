/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.workit.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.codename1.workit.entites.Forum;
import com.codename1.workit.services.ForumService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author HP-PC
 */
public class ThemeStats {
      private Resources theme;
    public ThemeStats() {
        try {
            theme = Resources.openLayered("/theme");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.createPieChartForm().show();
        
    }
    
    
    
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(50);
    renderer.setLegendTextSize(50);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, HashMap<String, Integer> values) {
    CategorySeries series = new CategorySeries(title);
  
        series.add("Finance", values.get("finance"));
        series.add("Managment", values.get("managment"));
        series.add("Soft Skills", values.get("soft"));
    

    return series;
}

public Form createPieChartForm() {
    // Generate the values
    int finance = 0;
    int managment = 0;
    int soft = 0;
    ArrayList<Forum> forums = ForumService.getInstance().findAll();
    for (int i=0;i<forums.size();i++)
    {
        switch (forums.get(i).getTheme().toLowerCase()) {
            case "finance":
                finance++;
                break;
            case "managment":
                managment++;
                break;
            default:
                soft++;
                break;
        }
    }
    HashMap<String, Integer> values = new HashMap<>();
    values.put("finance", finance);
    values.put("managment", managment);
    values.put("soft", soft);

    // Set up the renderer
    int[] colors = new int[]{ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.CYAN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(30);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    renderer.setLabelsColor(ColorUtil.BLACK);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("Themes Statistique", new BorderLayout());
    f.add(BorderLayout.CENTER, c);
    f.getToolbar().addCommandToLeftBar(null, FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, "", 5), evt1 -> new ListForum(theme).show());
    return f;

}
    
}
