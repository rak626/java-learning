package LLD.behavioural.State;

// https://www.geeksforgeeks.org/system-design/state-design-pattern/
public class Test {
    public static void main(String[] args) {
        VendingMachineContext vendingMachine = new VendingMachineContext(); // initiate vending machine
        vendingMachine.handleRequest();
        vendingMachine.setState(new ProductSelectedState());
        vendingMachine.handleRequest();
        vendingMachine.setState(new PaymentPendingState());
        vendingMachine.handleRequest();
        vendingMachine.setState(new OutOfStockState());
        vendingMachine.handleRequest();
    }
}
