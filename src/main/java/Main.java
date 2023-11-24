import models.LoadBalancer;

public class Main {
    public static void main(String[] args) {
                LoadBalancer lb = new LoadBalancer();
                lb.addResource("http://url1.intranet");
                lb.addResource("http://url2.intranet");
                lb.addResource("http://url3.intranet");

                // Simulate client-side load balancing
                for (int i = 0; i < 5; i++) {
                    String urlToCall = lb.next();
                    System.out.println("Calling: " + urlToCall);
                }
            }
        }
