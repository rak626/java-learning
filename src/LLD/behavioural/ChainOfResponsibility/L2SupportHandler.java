package LLD.behavioural.ChainOfResponsibility;

public class L2SupportHandler implements ISupportHandler {
    private ISupportHandler nextHandler;

    @Override
    public void handleRequest(Request request) {
        if (request.getPriority() == Priority.INTERMEDIATE) {
            System.out.println("Level 2 Support handled the request.");
        } else if (nextHandler != null) {
            System.out.println("Passed by L2");
            this.nextHandler.handleRequest(request);
        }
    }

    @Override
    public void setNextHandler(ISupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
