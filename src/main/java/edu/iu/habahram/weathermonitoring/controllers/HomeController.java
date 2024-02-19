package edu.iu.habahram.weathermonitoring.controllers;

import edu.iu.habahram.weathermonitoring.model.Observer;
import edu.iu.habahram.weathermonitoring.model.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private Subject weatherData;

    public HomeController(Subject weatherData) {
        this.weatherData = weatherData;
    }

    @GetMapping
    public ResponseEntity index() {
        String html =
                String.format("<h1>Subscribed screens(%s):</h1>", weatherData.getObservers().size());
        html += "<ul>";
        for(Observer observer : weatherData.getObservers()) {
            html += "<li>";
            html += String.format("<a href=/displays/%s>%s</a>", observer.id(), observer.name());
            html += "</li>";
        }
        html += "</ul>";
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(html);
    }




}
