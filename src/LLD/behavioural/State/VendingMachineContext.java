package LLD.behavioural.State;


// context of the State design pattern
public class VendingMachineContext {

    private IVendingMachineState state;

    public VendingMachineContext() {
        this.state = new ReadyState();
    }

    public void setState(IVendingMachineState state) {
        this.state = state;
    }

    // state handling by context
    public void handleRequest() {
        this.state.handleRequest();
    }
}


