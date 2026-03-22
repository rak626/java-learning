package LLD.behavioural.State;

// OutOfStockState class
public class OutOfStockState implements IVendingMachineState {
    @Override
    public void handleRequest() {
        System.out.println("OUT OF STOCK STATE: Product unavailable. Please select another product.");
    }
}
