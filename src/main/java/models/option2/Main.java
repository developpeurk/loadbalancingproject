package models.option2;

public class Main {
    public static void main(String[] args) {
        LoadBalancer2<String> lb = new LoadBalancer2<>();
        lb.addResource("http://url1.intranet");
        lb.addResource("http://url2.intranet");
        lb.addResource("http://url3.intranet");

        // Simulate client-side load balancing
        for (int i = 0; i < 6; i++) {
            String urlToCall = lb.next();
            System.out.println("Resource: " + urlToCall);
        }
    }
}
