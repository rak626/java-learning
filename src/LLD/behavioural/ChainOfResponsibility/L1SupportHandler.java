package LLD.behavioural.ChainOfResponsibility;


public class L1SupportHandler implements ISupportHandler {
    private ISupportHandler nextHandler;

    @Override
    public void handleRequest(Request request) {
        if (request.getPriority() == Priority.BASIC) {
            System.out.println("Level 1 Support handled the request.");
        } else if (nextHandler != null) {
            System.out.println(" Passed by L1");
            nextHandler.handleRequest(request);
        }
    }

    @Override
    public void setNextHandler(ISupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

