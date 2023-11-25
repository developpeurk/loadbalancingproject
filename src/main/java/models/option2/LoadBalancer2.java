package models.option2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadBalancer2<T> {
    private final List<T> resources;
    private final AtomicInteger currentIndex;

    public LoadBalancer2() {
        this.resources = new CopyOnWriteArrayList<>();
        this.currentIndex = new AtomicInteger(0);
    }

    public void addResource(T resource) {
        if (resource == null) {
            throw new IllegalArgumentException("Null resource cannot be added");
        }
        resources.add(resource);
    }

    public T next() {
        if (resources.isEmpty()) {
            throw new IllegalStateException("No resources available");
        }

        int index = currentIndex.getAndIncrement();
        return resources.get(index % resources.size());
    }

    public int getResourcesCount() {
        return resources.size();
    }

}
