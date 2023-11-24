package models;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoadBalancerTest {
    private LoadBalancer lb;

    @BeforeEach
    public void setUp() {
        lb = new LoadBalancer();
    }

    @Test
    public void testAddingResources() {
        lb.addResource("url1");
        lb.addResource("url2");
        lb.addResource("url3");
        assertEquals("url1", lb.next());
        assertEquals("url2", lb.next());
        assertEquals("url3", lb.next());
    }

    @Test
    public void testEmptyLoadBalancer() {
        assertThrows(IllegalStateException.class, () -> lb.next());
    }

    @Test
    public void testSingleResourceLoop() {
        lb.addResource("url1");
        assertEquals("url1", lb.next());
        assertEquals("url1", lb.next());
        assertEquals("url1", lb.next());
    }

    @Test
    public void testAddingManyResources() {
        // Adding a large number of resources
        for (int i = 0; i < 1000; i++) {
            lb.addResource("url" + i);
        }
        // Checking if they are balanced
        for (int i = 0; i < 1000; i++) {
            assertEquals("url" + (i % 1000), lb.next());
        }
    }

    @Test
    public void testNullResource() {
        lb.addResource(null);
        // Expecting the load balancer to handle null resource gracefully
        assertDoesNotThrow(() -> lb.next());
    }

}
