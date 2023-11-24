package models;

import java.util.ArrayList;
import java.util.List;

public class LoadBalancer {
    private List<String> resources;
    private int currentIndex;

    public LoadBalancer() {
        this.resources = new ArrayList<>();
        this.currentIndex = 0;
    }

    public void addResource(String resource) {
        resources.add(resource);
    }

    public String next() {
        if (resources.isEmpty()) {
            throw new IllegalStateException("No resources available");
        }

        String resource = resources.get(currentIndex);
        currentIndex = (currentIndex + 1) % resources.size();
        return resource;
    }
}
