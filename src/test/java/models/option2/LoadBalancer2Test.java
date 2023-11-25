package models.option2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoadBalancer2Test {
    private LoadBalancer2<String> lb;

    @BeforeEach
    public void setUp() {
        lb = new LoadBalancer2<>();
    }

    @Test
     void testAddingResourcesAndBalancing() {
        lb.addResource("url1");
        lb.addResource("url2");
        lb.addResource("url3");

        assertEquals("url1", lb.next());
        assertEquals("url2", lb.next());
        assertEquals("url3", lb.next());
        assertEquals("url1", lb.next());
    }

    @Test
    void testEmptyLoadBalancing() {
        assertThrows(IllegalStateException.class, () -> lb.next());
    }

    @Test
    void testSingleResourceLoop() {
        lb.addResource("url1");
        assertEquals("url1", lb.next());
        assertEquals("url1", lb.next());
        assertEquals("url1", lb.next());
    }

    @Test
    void testNullResource() {
        assertThrows(IllegalArgumentException.class, () -> lb.addResource(null));

    }

    @Test
    void testStressLoadBalancer() throws InterruptedException {
        int numberOfResources = 1000; // Number of resources to add
        int numberOfThreads = 100; // Number of concurrent threads

        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        // Adding a large number of resources concurrently
        for (int i = 0; i < numberOfThreads; i++) {
            executor.submit(() -> {
                for (int j = 0; j < numberOfResources; j++) {
                    lb.addResource("Resource" + j);
                }
                latch.countDown();
            });
        }

        latch.await(); // Wait for all threads to finish adding resources
        executor.shutdown();

        // Ensure the total number of resources matches the expected count
        assertEquals(numberOfResources * numberOfThreads, lb.getResourcesCount());
    }

}
