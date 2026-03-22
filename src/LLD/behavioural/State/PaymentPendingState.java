package LLD.behavioural.State;

// PaymentPendingState class
public class PaymentPendingState implements IVendingMachineState {
    @Override
    public void handleRequest() {
        System.out.println("Payment pending state: DISPENSING PRODUCT.");
    }
}
