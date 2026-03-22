package LLD.behavioural.ChainOfResponsibility;


public class Test {
    public static void main(String[] args) {
        ISupportHandler l1Handler = new L1SupportHandler();
        ISupportHandler l2Handler = new L2SupportHandler();
        ISupportHandler l3Handler = new L3SupportHandler();

        l1Handler.setNextHandler(l2Handler);
        l2Handler.setNextHandler(l3Handler);

        Request req1 = new Request(Priority.BASIC);
        Request req2 = new Request(Priority.INTERMEDIATE);
        Request req3 = new Request(Priority.CRITICAL);

        l1Handler.handleRequest(req2); // req2 -> l1 -> l2
        l1Handler.handleRequest(req1); // req1 -> l1
        l1Handler.handleRequest(req3); // req3 -> l1 -> l2 -> l3
    }
}

