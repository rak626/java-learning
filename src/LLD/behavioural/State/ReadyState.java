package LLD.behavioural.State;

// Ready state class
public class ReadyState implements IVendingMachineState {
    @Override
    public void handleRequest() {
        System.out.println(" request is handled by READY STATE");
    }
}


