package engine;

/**
 * Created by harry on 16/02/2018.
 */

public interface Predicate<T extends State> {
    boolean test(T state);
}
