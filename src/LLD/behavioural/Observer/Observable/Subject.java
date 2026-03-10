package LLD.behavioural.Observer.Observable;

import LLD.behavioural.Observer.Observer.Observer;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

