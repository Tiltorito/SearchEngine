package jug;

import java.util.Optional;

/**
 * Created by harry on 15/02/2018.
 */

public class Jug {
    private final int maxCapacity;
    private final int currentCapacity;

    public Jug(int maxCapacity) {
        this(maxCapacity, 0);
    }

    public Jug(int maxCapacity, int currentCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public boolean isEmpty() {
        return currentCapacity == 0;
    }

    public boolean isFull() {
        return currentCapacity == maxCapacity;
    }

    public Jug fill() {
        return new Jug(maxCapacity, maxCapacity);
    }

    public Jug empty() {
        return new Jug(maxCapacity, 0);
    }

    public Optional<JugState> fillInto(Jug jug) {
        if(!isEmpty() && jug.hasFreeSpace()) {
            int freeSpace = jug.getFreeSpace();
            Jug j1 = null;
            Jug j2 = null;

            if(currentCapacity > freeSpace) {
                j1 = new Jug(maxCapacity, currentCapacity - freeSpace);
                j2 = jug.fill();
            }
            else {
                j1 = empty();
                j2 = new Jug(jug.getMaxCapacity(), currentCapacity + jug.getCurrentCapacity());
            }

            return Optional.of(new JugState(j1, j2));
        }

        return Optional.empty();
    }

    public int getFreeSpace() {
        return maxCapacity - currentCapacity;
    }

    public boolean hasFreeSpace() {
        return getFreeSpace() > 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Jug) {
            Jug jug = (Jug) obj;
            return currentCapacity == jug.currentCapacity;
        }

        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(currentCapacity);
    }
}
