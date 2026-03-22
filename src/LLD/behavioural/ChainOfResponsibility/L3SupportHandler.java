package LLD.behavioural.ChainOfResponsibility;

public class L3SupportHandler implements ISupportHandler {
    private ISupportHandler nextHandler;

    @Override
    public void handleRequest(Request request) {
        if (request.getPriority() == Priority.CRITICAL) {
            System.out.println("L3 support handler processed");
        } else if (nextHandler != null) {
            System.out.println("Passed by L3");
            this.nextHandler.handleRequest(request);
        }
    }

    @Override
    public void setNextHandler(ISupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
