package bg.tuvarna.oop.core.helper;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class StatiscticsReport {

    public JFreeChart generateChart(Map<Integer, Integer> data) throws IOException {
        JFreeChart chart = null;

        CategoryDataset dataset = loadDataType(data);
        chart = createChart(dataset, "", "Number of rolled values");
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.WHITE);

        ChartUtils.writeChartAsPNG(new FileOutputStream("statistics.png"), chart, 450, 400);

        return chart;
    }


    private CategoryDataset loadDataType(Map<Integer, Integer> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.entrySet().forEach(count -> {
            dataset.setValue(count.getValue(), count.getKey(), count.getKey());
        });
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset, String chartTitle, String valueLabel) {

        return ChartFactory.createBarChart(
                chartTitle,
                "",
                valueLabel,
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);
    }
}
