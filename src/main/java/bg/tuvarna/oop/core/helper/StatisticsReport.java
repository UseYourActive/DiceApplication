package bg.tuvarna.oop.core.helper;

import javafx.scene.layout.Pane;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import javafx.scene.chart.*;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StatisticsReport {

    public BarChart<String, Number> generateBarChart(Map<Integer, Integer> data, BarChart<String, Number> barChart) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        data.forEach((key, value) -> series.getData().add(new XYChart.Data<>(String.valueOf(key), value)));
        barChart.getData().add(series);
        barChart.setBarGap(0);
        return barChart;
    }

    public void addTrendLine(BarChart<String, Number> barChart, Map<Integer, Integer> data) {
        // Calculate trend line equation (replace with your calculation)
        SimpleRegression regression = new SimpleRegression();
        data.forEach(regression::addData);

        double slope = regression.getSlope();
        double yIntercept = regression.getIntercept();

        // Create a new line chart series with trend line data points
        XYChart.Series<String, Number> trendLineSeries = new XYChart.Series<>();
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : data.entrySet()) {
            double x = entry.getKey();
            double y = slope * x + yIntercept;
            trendLineSeries.getData().add(new XYChart.Data<>(String.valueOf(x), y));

            // Update min and max x-values
            if (x < minX) minX = x;
            if (x > maxX) maxX = x;
        }

        // Create a line chart and add the trend line series
        LineChart<String, Number> trendLineChart = new LineChart<>(barChart.getXAxis(), barChart.getYAxis());
        trendLineChart.getData().add(trendLineSeries);

        // Add the line chart to the same parent as barChart
        ((Pane) barChart.getParent()).getChildren().add(trendLineChart);
    }
}
