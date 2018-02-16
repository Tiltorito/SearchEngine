package engine;

import java.util.ArrayList;

/**
 * Created by harry on 15/02/2018.
 */

public interface State<T extends State> {
    ArrayList<State<T>> getSuccessors();
    boolean sameAs(State state);
    T getState();
}
