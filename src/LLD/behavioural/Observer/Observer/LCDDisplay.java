package LLD.behavioural.Observer.Observer;

import LLD.behavioural.Observer.Observable.Subject;

public class LCDDisplay implements Observer {
    private float temperature;
    private float humidity;
    private float pressure;
    private Subject weatherStation;

    public LCDDisplay(Subject weatherStation) {
        this.weatherStation = weatherStation;
        weatherStation.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public void display() {
        System.out.println("LCD Display: Temperature: " + temperature +
                ", Humidity: " + humidity + ", Pressure: " + pressure);
    }
}
