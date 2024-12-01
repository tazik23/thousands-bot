package org.example.DictionaryCompiler.Queue;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

public class BoundedPriorityBlockingQueue implements Iterable<PrioritizedWord> {
    private final int capacity;
    private final PriorityBlockingQueue<PrioritizedWord> queue;

    public BoundedPriorityBlockingQueue(int maxSize) {
        this.capacity = maxSize;
        this.queue = new PriorityBlockingQueue<>(maxSize);
    }

    public void add(PrioritizedWord item) {
        if (queue.size() < capacity) {
            queue.offer(item);
        }
        else if (!queue.isEmpty() && item.getPriority() > queue.peek().getPriority()) {
            queue.poll();
            queue.offer(item);
        }
    }

    public PrioritizedWord poll() {
        return queue.poll();
    }


    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @NotNull
    @Override
    public Iterator iterator() {
        return queue.iterator();
    }
}