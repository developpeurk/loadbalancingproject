# Load Balancer Library

This library offers two different options for a load balancer implementation.

## Option 1: LoadBalancer (models.option1)

This implementation uses a basic sequential round-robin strategy to balance resources.

### Usage Example:

``` java
LoadBalancer lb = new LoadBalancer();
lb.addResource("http://url1.intranet");
lb.addResource("http://url2.intranet");
lb.addResource("http://url3.intranet");

// Simulate client-side load balancing
for (int i = 0; i < 5; i++) {
    String urlToCall = lb.next();
    System.out.println("Calling: " + urlToCall);
}

````

## Option 2: LoadBalancer2 (models.option2)

This implementation offers a thread-safe approach using concurrent data structures.

### Usage Example:

````java
LoadBalancer2<String> lb = new LoadBalancer2<>();
lb.addResource("http://url1.intranet");
lb.addResource("http://url2.intranet");
lb.addResource("http://url3.intranet");

// Simulate client-side load balancing
for (int i = 0; i < 6; i++) {
    String urlToCall = lb.next();
    System.out.println("Resource: " + urlToCall);
}
````

## Unit Testing

The test coverage encompasses several scenarios:

- **Happy Path Tests:** Ensuring correct behavior for resource addition and balancing in both LoadBalancer and LoadBalancer2.
- **Edge Cases:** Testing scenarios of empty load balancing and single resource looping to verify the load balancer behavior in extreme cases.
- **Null Resource Handling:** Validating that LoadBalancer2 correctly disallows the addition of null resources, throwing an `IllegalArgumentException`.
- **Stress Test:** Evaluating concurrent resource addition capability in LoadBalancer2 by adding a significant number of resources through multiple threads to ensure robustness under load.


## Running Tests

To execute the unit tests, follow these steps:

1. For LoadBalancer (Option 1):
    - Run `LoadBalancerTest` located in `models.option1` package.

2. For LoadBalancer2 (Option 2):
    - Run `LoadBalancer2Test` located in `models.option2` package.







