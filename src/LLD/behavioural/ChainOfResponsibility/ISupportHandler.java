package LLD.behavioural.ChainOfResponsibility;

// contract of support passed on
public interface ISupportHandler {
    void handleRequest(Request request);

    void setNextHandler(ISupportHandler nextHandler);
}
