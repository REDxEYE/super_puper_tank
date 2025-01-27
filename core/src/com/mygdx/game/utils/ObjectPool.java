package com.mygdx.game.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ObjectPool<T extends Alive> implements Iterable<T> {
    private final List<T> pool;

    public ObjectPool(int initialPoolSize) {
        this.pool = new ArrayList<>(initialPoolSize);
    }

    @Override
    public Iterator<T> iterator() {
        return pool.stream().filter(Alive::isAlive).iterator();
    }

    public void add(T object) {
        for (int i = 0; i < pool.size(); i++) {
            if (!pool.get(i).isAlive()) {
                pool.set(i, object);
                return;
            }
        }
        pool.add(object);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        pool.stream().filter(Alive::isAlive).forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return pool.stream().filter(Alive::isAlive).spliterator();
    }
}
