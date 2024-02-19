package edu.iu.habahram.weathermonitoring.model;

import org.springframework.stereotype.Component;

@Component
public class CurrentConditionDisplay
        implements Observer, DisplayElement{
    private float temperature;
    private float humidity;
    private float pressure;

    private Subject weatherData;

    public CurrentConditionDisplay(Subject weatherData) {
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
        html += String.format("<label>Temperature: %s</label><br />", temperature);
        html += String.format("<label>Humidity: %s</label><br />", humidity);
        html += String.format("<label>Pressure: %s</label>", pressure);
        html += "</section>";
        html += "</div>";
        return html;
    }

    @Override
    public String name() {
        return "Current Condition Display";
    }

    @Override
    public String id() {
        return "current-condition";
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
         this.temperature = temperature;
         this.humidity = humidity;
         this.pressure = pressure;
    }

    public void subscribe() {
        weatherData.registerObserver(this);
    }

    public void unsubscribe() {
        weatherData.removeObserver(this);
    }
}
