package engine;

import java.util.ArrayList;

/**
 * Created by harry on 15/02/2018.
 */

public interface State<T extends State> {
    ArrayList<T> getSuccessors();
    boolean sameAs(State state);
    boolean isTheGoal();
}
