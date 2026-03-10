package LLD.behavioural.Observer.Observer;

import LLD.behavioural.Observer.Observable.Subject;

public class PhoneDisplay implements Observer {
    private float temperature;
    private float humidity;
    private float pressure;

    public PhoneDisplay(Subject weatherStation) {
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
        System.out.println("Phone Display: Temperature: " + temperature +
                ", Humidity: " + humidity + ", Pressure: " + pressure);
    }
}



