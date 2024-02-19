package edu.iu.habahram.weathermonitoring.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class WeatherData implements Subject{
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        this.observers = new ArrayList<>();
        startMeasuring();
    }

    private void startMeasuring() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(sensorsChanged, 0, 3, TimeUnit.SECONDS);
    }

    Runnable sensorsChanged = () -> {
        setMeasurements((float) Math.random(),
                (float) Math.random(),
                (float) Math.random());
    };

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
       int i = observers.indexOf(observer);
       if(i >= 0) {
           observers.remove(observer);
       }
    }

    @Override
    public void notifyObservers() {
       for(Observer observer : observers) {
           observer.update(temperature, humidity, pressure);
       }
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    public void measurementChanged() {
        notifyObservers();
    }

    public  void setMeasurements(float temperature,
                                 float humidity,
                                 float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementChanged();
    }
}
