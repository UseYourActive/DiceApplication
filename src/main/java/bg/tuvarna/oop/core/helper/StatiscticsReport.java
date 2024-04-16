package bg.tuvarna.oop.core.helper;

import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.geometry.Insets;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import javafx.scene.chart.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Series;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class StatiscticsReport {

    public BarChart generateBarChart(Map<Integer, Integer> data, BarChart barChart){
        XYChart.Series series = new XYChart.Series();
        data.entrySet().forEach(count -> {
            series.getData().add(new XYChart.Data<>(count.getKey().toString(), count.getValue()));
        });
        barChart.getData().add(series);
        barChart.setBarGap(0);
       return barChart;
    }

    public void addTrendLine(BarChart barChart, Map<Integer, Integer> data) {
        // Calculate trend line equation (replace with your calculation)
        final double[] sumX = {0};
        final double[] sumY = {0};
        SimpleRegression regression = new SimpleRegression();
        data.entrySet().forEach(count -> {
            regression.addData(count.getKey(), count.getValue());
            sumX[0] += count.getKey();
            sumY[0] += count.getValue();
        });

        double slope = regression.getSlope();
        double meanX = sumX[0] / data.entrySet().size();
       // double meanY = sumY[0] / data.entrySet().size();
        double yIntercept = regression.getSlope() * meanX + regression.getIntercept();


        // Create a new line chart series with trend line data points
        XYChart.Series<Double, Double> trendLineSeries = new Series<>();
        double minX = barChart.getXAxis().LowerBound;
        double maxX = barChart.getXAxis().UpperBound;
        for (double x = minX; x <= maxX; x += 0.1) { // Adjust step size as needed
            double y = slope * x + yIntercept;
            trendLineSeries.getData().add(new XYChart.Data<>(x, y));
        }

        // Create a line chart and add the trend line series
        LineChart trendLineChart = new LineChart();
        trendLineChart.getData().add(trendLineSeries);

        // Add the line chart to the scene layout on top of the bar chart (implementation omitted)
    }


}
