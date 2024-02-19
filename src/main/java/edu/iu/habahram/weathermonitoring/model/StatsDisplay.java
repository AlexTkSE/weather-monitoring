package edu.iu.habahram.weathermonitoring.model;

import org.springframework.stereotype.Component;

@Component
public class StatsDisplay  implements Observer, DisplayElement {
    private float averageTemperature;
    private float minTemperature;
    private float maxTemperature;

    private Subject weatherData;

    public StatsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
    }

    @Override
    public String display() {
        String html = "";
        html += String.format("<div style=\"background-image: " +
                "url(/images/sky.webp); " +
                "height: 400px; " +
                "width: 647.2px;" +
                "display:flex;flex-wrap:wrap;justify-content:center;align-content:center;" +
                "\">");
        html += "<section>";
        html += String.format("<label>Avg Temperature: %s</label><br />", averageTemperature);
        html += String.format("<label>Min Temperature:: %s</label><br />", minTemperature);
        html += String.format("<label>Max Temperature: %s</label>", maxTemperature);
        html += "</section>";
        html += "</div>";
        return html;
    }

    @Override
    public String name() {
        return "Statistics Display";
    }

    @Override
    public String id() {
        return "statistics";
    }

    @Override
    public void update(float avgTemperature, float maxTemperature, float minTemperature) {
        this.minTemperature = Math.min(avgTemperature, Math.min(maxTemperature, minTemperature));
        this.maxTemperature = Math.max(avgTemperature, Math.max(maxTemperature, minTemperature));
        this.averageTemperature = (avgTemperature + maxTemperature + minTemperature) / 3;
    }

    public void subscribe() {
        weatherData.registerObserver(this);
    }

    public void unsubscribe() {
        weatherData.removeObserver(this);
    }
}
