package LLD.behavioural.State;

// ProductSelectedState class
public class ProductSelectedState implements IVendingMachineState {
    @Override
    public void handleRequest() {
        System.out.println("Product selected state: PROCESSING PAYMENT.");
    }
}
